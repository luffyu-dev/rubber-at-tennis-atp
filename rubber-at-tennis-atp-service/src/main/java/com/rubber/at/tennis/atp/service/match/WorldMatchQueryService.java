package com.rubber.at.tennis.atp.service.match;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.api.match.WorldMatchQueryApi;
import com.rubber.at.tennis.atp.api.match.dto.*;
import com.rubber.at.tennis.atp.api.match.enums.MatchStatusEnums;
import com.rubber.at.tennis.atp.api.match.req.WorldMatchReq;
import com.rubber.at.tennis.atp.api.match.req.WorldTourMatchReq;
import com.rubber.at.tennis.atp.api.player.dto.PlayerH2HDto;
import com.rubber.at.tennis.atp.dao.dal.*;
import com.rubber.at.tennis.atp.dao.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luffyu
 * Created on 2023/3/24
 */
@Slf4j
@Service("worldMatchQueryApi")
public class WorldMatchQueryService  implements WorldMatchQueryApi {

    @Autowired
    private IWorldTennisMatchDal iWorldTennisMatchDal;


    @Autowired
    private IWorldTennisMatchPlayerDal iWorldTennisMatchPlayerDal;

    @Autowired
    private IWorldTourMatchDal iWorldTourMatchDal;

    @Autowired
    private IWorldTennisMatchScoreRecordDal iWorldTennisMatchScoreRecordDal;

    @Autowired
    private IWorldTennisMatchLivingDataDal iWorldTennisMatchLivingDataDal;

    @Autowired
    private IPlayerHtohInfoDal iPlayerHtohInfoDal;

    /**
     * 查询巡回赛的基础信息
     *
     * @param req 当前的请求
     * @return 返回巡回赛详情
     */
    @Override
    public WorldTourMatchTypeDto queryTourMatchInfo(WorldTourMatchReq req) {
        LambdaQueryWrapper<WorldTourMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(WorldTourMatchEntity::getTourYear,String.valueOf(DateUtil.year(new Date())));
        if (!StringUtils.isEmpty(req.getTourId())){
            lqw.eq(WorldTourMatchEntity::getTourId,req.getTourId());
        }
        if (CollUtil.isNotEmpty(req.getStatusList())){
            lqw.in(WorldTourMatchEntity::getStatus,req.getStatusList());
        }
        WorldTourMatchEntity entity = iWorldTourMatchDal.getOne(lqw, false);
        return convertDto(entity);
    }

    /**
     * 查询所有巡回赛
     *
     * @param req 当前的请求
     * @return 返回巡回赛详情
     */
    @Override
    public List<WorldTourMatchTypeDto> queryTourMatch(WorldTourMatchReq req) {
        List<Integer> statusList = req.getStatusList();
        if (CollUtil.isEmpty(statusList)){
            statusList = Arrays.asList(1,2);
        }
        List<WorldTourMatchEntity> tourMatchEntityList = iWorldTourMatchDal.queryByKeys(req.getTourId(),String.valueOf(DateUtil.year(new Date())),statusList);
        if (CollUtil.isEmpty(tourMatchEntityList)){
            return new ArrayList<>();
        }
        return tourMatchEntityList.stream().map(this::convertDto).collect(Collectors.toList());
    }

    /**
     * 查询进行中的比萨
     *
     * @param req
     * @return
     */
    @Override
    public List<WorldMatchInfo> queryLivingWorldMatch(WorldMatchReq req) {
        List<WorldTourMatchEntity> tourMatchList = iWorldTourMatchDal.queryRecommendList(String.valueOf(DateUtil.year(new Date())), Arrays.asList(1,2));
        // 查询进行中的赛事
        if (CollUtil.isEmpty(tourMatchList)){
            return new ArrayList<>();
        }
        List<WorldMatchInfo> worldMatchInfos = new ArrayList<>();

        for (WorldTourMatchEntity worldTourMatchEntity:tourMatchList){
            List<WorldMatchInfo> matchInfoList = doQueryWorldMathByTour(req,worldTourMatchEntity);
            if (CollUtil.isEmpty(matchInfoList)){
                continue;
            }
            worldMatchInfos.addAll(matchInfoList);
        }
        return worldMatchInfos;
    }


    /**
     * 查询用户的比赛数据
     *
     * @param playerKey 名称或者id
     * @param size
     * @return
     */
    @Override
    public List<WorldMatchInfo> queryWorldMatchByPlayer(String playerKey, Integer size) {
        List<WorldTennisMatchEntity> handlerResult = queryLivingByPlayerId(playerKey,size);
        return queryWorldMatchFormDb(handlerResult);
    }


    private List<WorldMatchInfo> doQueryWorldMathByTour(WorldMatchReq req,WorldTourMatchEntity tourMatchEntity){
        int needSize = Math.min(req.getSize(),50);
        List<WorldTennisMatchEntity> handlerResult = new ArrayList<>(queryLivingState(tourMatchEntity, needSize));
        log.info("查询的进行中的长度{}",handlerResult.size());
        if (handlerResult.size() < needSize){
            handlerResult.addAll(queryEndMatch(tourMatchEntity,needSize));
        }
        return queryWorldMatchFormDb(handlerResult);
    }

    /**
     * 查询进行中的比萨
     *
     * @param req
     * @return
     */
    @Override
    public List<RecommendWorldMatchDto> queryRecommendWorldMatch(WorldMatchReq req) {
        List<WorldTourMatchEntity> tourMatchList = iWorldTourMatchDal.queryRecommendList(String.valueOf(DateUtil.year(new Date())), Arrays.asList(1,2));
        if (CollUtil.isEmpty(tourMatchList)){
            return null;
        }
        List<RecommendWorldMatchDto> result = new ArrayList<>();
        int max = Math.min(tourMatchList.size(),2);
        for (int i=0;i<max;i++){
            WorldTourMatchEntity worldTourMatchEntity = tourMatchList.get(i);
            List<WorldMatchInfo> worldMatchInfos = doQueryWorldMathByTour(req, worldTourMatchEntity);
            if (CollUtil.isEmpty(worldMatchInfos)){
                continue;
            }
            RecommendWorldMatchDto dto = new RecommendWorldMatchDto(convertDto(worldTourMatchEntity),worldMatchInfos);
            result.add(dto);
        }
        return result;
    }




    private  List<WorldTennisMatchEntity> queryLivingState(WorldTourMatchEntity tourMatchEntity,int needSize){
        Date now = new Date();
       List<String> needData = new ArrayList<>();
       needData.add(DatePattern.NORM_DATE_FORMAT.format(now));
       needData.add(DatePattern.NORM_DATE_FORMAT.format(DateUtil.offsetDay(now,-1)));
        // 查询进行中的比赛
        LambdaQueryWrapper<WorldTennisMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(WorldTennisMatchEntity::getMatchTypeId,tourMatchEntity.getTourId())
                .in(WorldTennisMatchEntity::getMatchStatus,Arrays.asList(MatchStatusEnums.LIVING.getType(),MatchStatusEnums.UN_BEGIN.getType(),MatchStatusEnums.NON_STATE.getType()))
                .in(WorldTennisMatchEntity::getMatchDay,needData)
                .orderByDesc(WorldTennisMatchEntity::getSeq)
                .orderByDesc(WorldTennisMatchEntity::getMatchStatus)
                .orderByAsc(WorldTennisMatchEntity::getMatchTime);
        lqw.last(" limit " + needSize);
        List<WorldTennisMatchEntity> data = iWorldTennisMatchDal.list(lqw);
        if (data == null){
            data = new ArrayList<>();
        }
        return data;
    }




    private  List<WorldTennisMatchEntity> queryLivingByPlayerId(String playerKeys,int needSize){
        Date now = new Date();
        // 查询最近15天的比赛数据
        LambdaQueryWrapper<WorldTennisMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.like(WorldTennisMatchEntity::getIndexKey,"%"+playerKeys+"%")
                .gt(WorldTennisMatchEntity::getMatchTime,DateUtil.offsetDay(new Date(),-30))
                .orderByDesc(WorldTennisMatchEntity::getMatchTime);
        lqw.last(" limit " + needSize);
        List<WorldTennisMatchEntity> data = iWorldTennisMatchDal.list(lqw);
        if (data == null){
            data = new ArrayList<>();
        }
        return data;
    }




    private  List<WorldTennisMatchEntity> queryEndMatch(WorldTourMatchEntity tourMatchEntity,int needSize){
        Date now = new Date();
        List<String> needData = new ArrayList<>();
        if (DateUtil.compare(now,tourMatchEntity.getEndTime()) <=0){
            needData.add(DatePattern.NORM_DATE_FORMAT.format(now));
            needData.add(DatePattern.NORM_DATE_FORMAT.format(DateUtil.offsetDay(now,-1)));
        }else {
            needData.add(DatePattern.NORM_DATE_FORMAT.format(tourMatchEntity.getEndTime()));
        }
        // 查询进行中的比赛
        LambdaQueryWrapper<WorldTennisMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(WorldTennisMatchEntity::getMatchTypeId,tourMatchEntity.getTourId())
                .eq(WorldTennisMatchEntity::getMatchStatus,MatchStatusEnums.END.getType())
                .in(WorldTennisMatchEntity::getMatchDay,needData)
                .orderByDesc(WorldTennisMatchEntity::getSeq)
                .orderByDesc(WorldTennisMatchEntity::getMatchTime);

        lqw.last(" limit " + needSize);
        List<WorldTennisMatchEntity> data = iWorldTennisMatchDal.list(lqw);
        if (data == null){
            data = new ArrayList<>();
        }
        return data;
    }

    /**
     * 查询比赛的数据信息
     *
     * @param req 当前的请求
     * @return 返回球赛信息
     */
    @Override
    public List<WorldMatchInfo> queryWorldMatch(WorldMatchReq req) {
        if (StrUtil.isEmpty(req.getMatchDay()) ||  "2023-05-21".equals(req.getMatchDay())){
            req.setMatchDay(DatePattern.NORM_DATE_FORMAT.format(new Date()));
        }
        return queryWorldMatchFormDb(req);
    }

    /**
     * 获取比赛详情
     *
     * @param matchId
     * @return
     */
    @Override
    public WorldMatchInfo getLivingDetail(String matchId) {
        WorldTennisMatchEntity matchEntity = iWorldTennisMatchDal.getById(matchId);
        if (matchEntity == null){
            return null;
        }
        List<WorldMatchInfo>  matchInfoList = queryWorldMatchFormDb(Collections.singletonList(matchEntity));
        if (CollUtil.isEmpty(matchInfoList)){
            return null;
        }
        WorldMatchInfo worldMatchInfo = matchInfoList.get(0);
        // 查询明细
        queryWorldMatchRecord(worldMatchInfo);
        // 处理每盘的数据
        handlerLivingSetData(worldMatchInfo);

        // 查询h2h
        queryPlayerH2h(worldMatchInfo);
        // 查询比赛实时数据
        queryMatchLive(worldMatchInfo);

        return worldMatchInfo;
    }


    /**
     * 查询比赛详情
     * @param req
     * @return
     */
    public List<WorldMatchInfo> queryWorldMatchFormDb(WorldMatchReq req) {
        List<WorldMatchInfo> list = new ArrayList<>();
        Page<WorldTennisMatchEntity> matchEntityPage = queryByPage(req);
        if (CollUtil.isEmpty(matchEntityPage.getRecords())){
            return list;
        }
        return queryWorldMatchFormDb(matchEntityPage.getRecords());
    }
    private List<WorldMatchInfo> queryWorldMatchFormDb(List<WorldTennisMatchEntity> matchList){
        if (CollUtil.isEmpty(matchList)){
            return new ArrayList<>();
        }
        List<String> matchIds = matchList.stream().map(WorldTennisMatchEntity::getMatchId).collect(Collectors.toList());

        List<WorldMatchInfo> list = new ArrayList<>();
        List<WorldTennisMatchPlayerEntity> pagePlayer = queryByPagePlayer(matchIds);
        if (CollUtil.isEmpty(pagePlayer)){
            return list;
        }
        Map<String, List<WorldTennisMatchPlayerEntity>> matchPlayer = pagePlayer.stream().collect(Collectors.groupingBy(WorldTennisMatchPlayerEntity::getMatchId));
        for (WorldTennisMatchEntity worldTennisMatchEntity:matchList){
            WorldMatchInfo worldMatchInfo = new WorldMatchInfo();
            BeanUtils.copyProperties(worldTennisMatchEntity,worldMatchInfo);
            worldMatchInfo.setRecommendFlag(worldMatchInfo.getSeq() != null && worldMatchInfo.getSeq() > 950 ? 1 : 0);
            List<WorldTennisMatchPlayerEntity> tennisMatchPlayer = matchPlayer.get(worldMatchInfo.getMatchId());
            if (CollUtil.isEmpty(tennisMatchPlayer)){
                continue;
            }
            Map<String, WorldTennisMatchPlayerEntity> collect = tennisMatchPlayer.stream().collect(Collectors.toMap(WorldTennisMatchPlayerEntity::getPlayerIndex, i -> i, (o1, o2) -> o1));

            /**
             * 球员信息A
             */
            WorldMatchPlayerInfo aPlayer = new WorldMatchPlayerInfo();
            WorldTennisMatchPlayerEntity a = collect.get("A");
            if (a == null){
                continue;
            }
            BeanUtils.copyProperties(a,aPlayer);
            if (!StringUtils.isEmpty(aPlayer.getPlayerName()) && aPlayer.getPlayerName().contains("·")){
                aPlayer.setPlayerName(aPlayer.getPlayerName().substring(aPlayer.getPlayerName().indexOf("·") + 1));
            }
            if (!StringUtils.isEmpty(aPlayer.getPartnerName()) && aPlayer.getPartnerName().contains("·")){
                aPlayer.setPartnerName(aPlayer.getPartnerName().substring(aPlayer.getPartnerName().indexOf("·") + 1));
            }
            worldMatchInfo.setAPlayer(aPlayer);

            /**
             * 球员信息B
             */
            WorldMatchPlayerInfo bPlayer = new WorldMatchPlayerInfo();
            WorldTennisMatchPlayerEntity b = collect.get("B");
            if (b == null){
                continue;
            }
            BeanUtils.copyProperties(b,bPlayer);
            if (!StringUtils.isEmpty(bPlayer.getPlayerName()) && bPlayer.getPlayerName().contains("·")){
                bPlayer.setPlayerName(bPlayer.getPlayerName().substring(bPlayer.getPlayerName().indexOf("·")+1));
            }
            if (!StringUtils.isEmpty(bPlayer.getPartnerName()) && bPlayer.getPartnerName().contains("·")){
                bPlayer.setPartnerName(bPlayer.getPartnerName().substring(bPlayer.getPartnerName().indexOf("·")+1));
            }
            worldMatchInfo.setBPlayer(bPlayer);
            if (worldMatchInfo.getMatchTime() != null){
                worldMatchInfo.setMatchTimeStr(DateUtil.format(worldMatchInfo.getMatchTime(),"MM/dd HH:mm"));
            }
            list.add(worldMatchInfo);
        }
        return list;
    }





    public Page<WorldTennisMatchEntity> queryByPage(WorldMatchReq req){
        Page<WorldTennisMatchEntity> page = new Page<>();
        page.setCurrent(req.getPage());
        page.setSize(req.getSize());
        page.setSearchCount(false);
        LambdaQueryWrapper<WorldTennisMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(WorldTennisMatchEntity::getMatchDay,req.getMatchDay());
        if (req.isOrderBySeq()){
            lqw.orderByDesc(WorldTennisMatchEntity::getSeq)
                    .orderByAsc(WorldTennisMatchEntity::getMatchTime);
        }else {
            lqw.orderByAsc(WorldTennisMatchEntity::getMatchTime);
        }
        if (StrUtil.isNotEmpty(req.getMatchTypeId())){
            lqw.eq(WorldTennisMatchEntity::getMatchTypeId,req.getMatchTypeId());
        }
        if (StrUtil.isNotEmpty(req.getMatchRound())){
            lqw.eq(WorldTennisMatchEntity::getMatchRound,req.getMatchRound());
        }
        if (req.getDoubleFlag() != null){
            lqw.eq(WorldTennisMatchEntity::getDoubleFlag,req.getDoubleFlag());
        }
        if (req.getMatchStatusList() != null){
            lqw.in(WorldTennisMatchEntity::getMatchStatus,req.getMatchStatusList());
        }
        if (StrUtil.isNotEmpty(req.getMatchGender())){
            lqw.eq(WorldTennisMatchEntity::getMatchGender,req.getMatchGender());
        }
        if(StrUtil.isNotEmpty(req.getSearchValue())){
            lqw.and(i->{
                i.like(WorldTennisMatchEntity::getMatchName,req.getSearchValue())
                        .or()
                        .like(WorldTennisMatchEntity::getMatchGender,req.getSearchValue())
                        .or()
                        .like(WorldTennisMatchEntity::getIndexKey,req.getSearchValue());
            });
        }
        return iWorldTennisMatchDal.page(page, lqw);
    }



    public List<WorldTennisMatchPlayerEntity> queryByPagePlayer(List<String> matchId){
        LambdaQueryWrapper<WorldTennisMatchPlayerEntity> lqw = new LambdaQueryWrapper<>();
        lqw.in(WorldTennisMatchPlayerEntity::getMatchId,matchId);
        return iWorldTennisMatchPlayerDal.list(lqw);
    }




    private WorldTourMatchTypeDto convertDto(WorldTourMatchEntity entity){
        WorldTourMatchTypeDto dto = new WorldTourMatchTypeDto();
        BeanUtils.copyProperties(entity,dto);
        List<String> dateStr = new ArrayList<>();
        long betweenDay = DateUtil.betweenDay(entity.getBeginTime(), entity.getEndTime(), true);
        for (int i=0;i<=betweenDay;i++){
            Date temp = DateUtil.offsetDay(entity.getBeginTime(),i);
            dateStr.add(DatePattern.NORM_DATE_FORMAT.format(temp));
        }
        Date now = new Date();
        if (DateUtil.compare(now,entity.getEndTime()) <=0){
            dto.setDefaultMatchDay(DatePattern.NORM_DATE_FORMAT.format(now));
        }else {
            dto.setDefaultMatchDay(DatePattern.NORM_DATE_FORMAT.format(entity.getEndTime()));
        }
        if (StrUtil.isNotEmpty(entity.getMatchRoundList())){
            dto.setMatchRoundList(JSON.parseArray(entity.getMatchRoundList(), MatchRoundDto.class));
        }

        dto.setMatchDays(dateStr);
        return dto;
    }



    private void queryWorldMatchRecord(WorldMatchInfo info){
        List<WorldTennisMatchScoreRecordEntity> list = iWorldTennisMatchScoreRecordDal.queryByMatchId(info.getMatchId());
        if (CollUtil.isNotEmpty(list)){
            List<WorldMatchScoreRecordDto> recordDtos = list.stream().map(i->{
                WorldMatchScoreRecordDto dto = new WorldMatchScoreRecordDto();
                BeanUtils.copyProperties(i,dto);
                if (i.getCreateTime() != null){
                    dto.setCreateTimeStr(DateUtil.format(i.getCreateTime(),"dd号 HH:mm"));
                }
                return dto;
            }).collect(Collectors.toList());
            info.setRecordDtoList(recordDtos);
        }
    }


    private void handlerLivingSetData(WorldMatchInfo worldMatchInfo){
        if (MatchStatusEnums.UN_BEGIN.getType().equals(worldMatchInfo.getMatchStatus()) || MatchStatusEnums.NON_STATE.getType().equals(worldMatchInfo.getMatchStatus())){
            return;
        }
        WorldMatchPlayerInfo playerInfoA = worldMatchInfo.getAPlayer();
        WorldMatchPlayerInfo playerInfoB = worldMatchInfo.getBPlayer();
        if (playerInfoA == null || playerInfoB == null){
            return;
        }
        boolean justAdd = false;
        boolean setSuc5 = doHandlerSet(worldMatchInfo,playerInfoA,playerInfoB,playerInfoA.getSet5Num(),playerInfoB.getSet5Num(),justAdd);
        if (setSuc5){
            justAdd  = true;
            worldMatchInfo.setNowSet(5);
        }

        boolean setSuc4 = doHandlerSet(worldMatchInfo,playerInfoA,playerInfoB,playerInfoA.getSet4Num(),playerInfoB.getSet4Num(),justAdd);
        if (setSuc4){
            justAdd  = true;
            if (worldMatchInfo.getNowSet() == null){
                worldMatchInfo.setNowSet(4);
            }
        }
        boolean setSuc3 = doHandlerSet(worldMatchInfo,playerInfoA,playerInfoB,playerInfoA.getSet3Num(),playerInfoB.getSet3Num(),justAdd);
        if (setSuc3){
            justAdd  = true;
            if (worldMatchInfo.getNowSet() == null){
                worldMatchInfo.setNowSet(3);
            }
        }
        boolean setSuc2 = doHandlerSet(worldMatchInfo,playerInfoA,playerInfoB,playerInfoA.getSet2Num(),playerInfoB.getSet2Num(),justAdd);
        if (setSuc2){
            justAdd  = true;
            if (worldMatchInfo.getNowSet() == null){
                worldMatchInfo.setNowSet(2);
            }
        }
        boolean setSuc1 = doHandlerSet(worldMatchInfo,playerInfoA,playerInfoB,playerInfoA.getSet1Num(),playerInfoB.getSet1Num(),justAdd);
        if (setSuc1){
            if (worldMatchInfo.getNowSet() == null){
                worldMatchInfo.setNowSet(1);
            }
        }

    }


    private boolean doHandlerSet(WorldMatchInfo worldMatchInfo,WorldMatchPlayerInfo playerInfoA,WorldMatchPlayerInfo playerInfoB,String setNumA,String setNumB,boolean justAdd){
        Boolean setResult = compareSetNum(setNumA, setNumB);
        if (setResult != null){
            // 判断是否结束
            if (justAdd || MatchStatusEnums.END.getType().equals(worldMatchInfo.getMatchStatus())){
                if (setResult) {
                    playerInfoA.addWinSet();
                } else {
                    playerInfoB.addWinSet();
                }
            }
            if (StringUtils.isEmpty(playerInfoA.getNowSetNum()) && StringUtils.isEmpty(playerInfoB.getNowSetNum())){
                playerInfoA.setNowSetNum(setNumA);
                playerInfoB.setNowSetNum(setNumB);
            }
            return true;
        }
        return false;
    }



    private Boolean compareSetNum(String setNumA,String setNumB){
        if (StringUtils.isEmpty(setNumA) || StringUtils.isEmpty(setNumB)){
            return null;
        }
        // 表示抢七
        if (setNumA.startsWith("7")){
            return true;
        }
        if (setNumB.startsWith("7")){
            return false;
        }
        if (setNumA.contains("(")){
            setNumA = setNumA.substring(0,setNumA.indexOf("("));
        }
        if (setNumB.contains("(")){
            setNumB = setNumB.substring(0,setNumB.indexOf("("));
        }
        return Integer.parseInt(setNumA) > Integer.parseInt(setNumB);
    }


    /**
     * 查询h2h信息
     * @param worldMatchInfo
     */
    private void queryPlayerH2h(WorldMatchInfo worldMatchInfo){
        LambdaQueryWrapper<PlayerHtohInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PlayerHtohInfoEntity::getAPlayerId,worldMatchInfo.getAPlayer().getPlayerId())
                .eq(PlayerHtohInfoEntity::getBPlayerId,worldMatchInfo.getBPlayer().getPlayerId())
                .last(" limit 1");
        PlayerHtohInfoEntity infoEntity = iPlayerHtohInfoDal.getOne(lqw);
        if (infoEntity != null){
            PlayerH2HDto dto = new PlayerH2HDto();
            BeanUtils.copyProperties(infoEntity,dto);
            if (StrUtil.isNotEmpty(infoEntity.getOldMatchInfo())){
                dto.setOldMatchList(JSON.parseArray(infoEntity.getOldMatchInfo(), PlayerH2HDto.OldMatchDto.class));
            }
            worldMatchInfo.setH2HDto(dto);
        }

    }


    private void queryMatchLive(WorldMatchInfo worldMatchInfo){
        try {
            LambdaQueryWrapper<WorldTennisMatchLivingDataEntity> lqw = new LambdaQueryWrapper<>();
            lqw.eq(WorldTennisMatchLivingDataEntity::getMatchId, worldMatchInfo.getMatchId())
                    .last(" limit 1");
            WorldTennisMatchLivingDataEntity entity = iWorldTennisMatchLivingDataDal.getOne(lqw);
            if (entity != null) {
                worldMatchInfo.setLivingDataList(JSON.parseArray(entity.getMatchLivingData(), MatchResultDataDto.class));
                if (CollUtil.isNotEmpty(worldMatchInfo.getLivingDataList())) {
                    for (MatchResultDataDto dataDto : worldMatchInfo.getLivingDataList()) {
                        if (dataDto != null && CollUtil.isNotEmpty(dataDto.getSetDataList())) {
                            for (MatchResultDataDto.MatchSetBean setBean : dataDto.getSetDataList()) {
                                handlerMatchSetData(setBean);
                            }
                        }
                    }

                }
            }
        }catch (Exception e){
            log.error("查询比赛实时数据宜昌",e);
        }
    }


    private void handlerMatchSetData(MatchResultDataDto.MatchSetBean setBean){
        if (setBean.getAPlayerData().contains("%") && setBean.getBPlayerData().contains("%")){
            int aPoint = Integer.parseInt(setBean.getAPlayerData().substring(0,setBean.getAPlayerData().indexOf("%")));
            int bPoint = Integer.parseInt(setBean.getBPlayerData().substring(0,setBean.getBPlayerData().indexOf("%")));
            setBean.setAPlayerPoint(aPoint);
            setBean.setBPlayerPoint(bPoint);
        }
        if (StrUtil.isNumeric(setBean.getAPlayerData()) && StrUtil.isNumeric(setBean.getBPlayerData())){
            Double xa = Double.parseDouble(setBean.getAPlayerData());
            Double xb = Double.parseDouble(setBean.getBPlayerData());
            BigDecimal all =new BigDecimal(xa + xb);
            if (all.doubleValue() <= 0){
                setBean.setAPlayerPoint(0);
                setBean.setBPlayerPoint(0);
            }else {
                Integer aPoint = BigDecimal.valueOf(xa).divide(all,2, RoundingMode.UP).multiply(new BigDecimal(100)).intValue();
                Integer bPoint =BigDecimal.valueOf(xb).divide(all,2, RoundingMode.UP).multiply(new BigDecimal(100)).intValue();
                setBean.setAPlayerPoint(aPoint);
                setBean.setBPlayerPoint(bPoint);
            }
        }
        if (setBean.getAPlayerPoint() != null && setBean.getBPlayerPoint() != null){
            if (setBean.getAPlayerPoint().equals(setBean.getBPlayerPoint())){
                setBean.setMaxPlayer("all");
            }else {
                setBean.setMaxPlayer(setBean.getAPlayerPoint() > setBean.getBPlayerPoint() ? "a":"b");
            }
        }
    }

}
