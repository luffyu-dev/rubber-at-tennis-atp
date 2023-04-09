package com.rubber.at.tennis.atp.service.match;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.api.match.WorldMatchQueryApi;
import com.rubber.at.tennis.atp.api.match.dto.WorldMatchInfo;
import com.rubber.at.tennis.atp.api.match.dto.WorldMatchPlayerInfo;
import com.rubber.at.tennis.atp.api.match.dto.WorldTourMatchTypeDto;
import com.rubber.at.tennis.atp.api.match.req.WorldMatchReq;
import com.rubber.at.tennis.atp.api.match.req.WorldTourMatchReq;
import com.rubber.at.tennis.atp.dao.dal.IMatchTypeDal;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchDal;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchPlayerDal;
import com.rubber.at.tennis.atp.dao.dal.IWorldTourMatchDal;
import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchEntity;
import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchPlayerEntity;
import com.rubber.at.tennis.atp.dao.entity.WorldTourMatchEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        List<WorldTourMatchEntity> tourMatchEntityList = iWorldTourMatchDal.queryByKeys(req.getTourId(),String.valueOf(DateUtil.year(new Date())),req.getStatusList());
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
        List<WorldTourMatchEntity> tourMatchList = iWorldTourMatchDal.queryByKeys(null,String.valueOf(DateUtil.year(new Date())), Arrays.asList(1,2));
        // 查询进行中的比赛
        if (CollUtil.isEmpty(tourMatchList)){
            return new ArrayList<>();
        }
        // 进行中的比赛
        WorldTourMatchEntity tourMatchEntity = tourMatchList.get(tourMatchList.size() - 1);
        req.setMatchTypeId(tourMatchEntity.getTourId());
        Date now = new Date();
        if (DateUtil.compare(now,tourMatchEntity.getEndTime()) <=0){
            req.setMatchDay(DatePattern.NORM_DATE_FORMAT.format(now));
        }else {
            req.setMatchDay(DatePattern.NORM_DATE_FORMAT.format(tourMatchEntity.getEndTime()));
        }
        return queryWorldMatch(req);
    }

    /**
     * 查询比赛的数据信息
     *
     * @param req 当前的请求
     * @return 返回球赛信息
     */
    @Override
    public List<WorldMatchInfo> queryWorldMatch(WorldMatchReq req) {
        return queryWorldMatchFormDb(req);
    }
    public List<WorldMatchInfo> queryWorldMatchFormDb(WorldMatchReq req) {
        List<WorldMatchInfo> list = new ArrayList<>();
        Page<WorldTennisMatchEntity> matchEntityPage = queryByPage(req);
        if (CollUtil.isEmpty(matchEntityPage.getRecords())){
            return list;
        }
        List<String> matchIds = matchEntityPage.getRecords().stream().map(WorldTennisMatchEntity::getMatchId).collect(Collectors.toList());
        List<WorldTennisMatchPlayerEntity> pagePlayer = queryByPagePlayer(matchIds);
        if (CollUtil.isEmpty(pagePlayer)){
            return list;
        }
        Map<String, List<WorldTennisMatchPlayerEntity>> matchPlayer = pagePlayer.stream().collect(Collectors.groupingBy(WorldTennisMatchPlayerEntity::getMatchId));
        for (WorldTennisMatchEntity worldTennisMatchEntity:matchEntityPage.getRecords()){
            WorldMatchInfo worldMatchInfo = new WorldMatchInfo();
            BeanUtils.copyProperties(worldTennisMatchEntity,worldMatchInfo);
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
            worldMatchInfo.setBPlayer(bPlayer);
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
        lqw.eq(WorldTennisMatchEntity::getMatchDay,req.getMatchDay())
                .orderByAsc(WorldTennisMatchEntity::getMatchTime);
        if (StrUtil.isNotEmpty(req.getMatchTypeId())){
            lqw.eq(WorldTennisMatchEntity::getMatchTypeId,req.getMatchTypeId());
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
        dto.setMatchDays(dateStr);
        return dto;
    }

}
