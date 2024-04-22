package com.rubber.at.tennis.atp.service.match;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.api.base.MatchPlayerGroupBean;
import com.rubber.at.tennis.atp.api.match.WorldTourQueryApi;
import com.rubber.at.tennis.atp.api.match.dto.MatchRoundDto;
import com.rubber.at.tennis.atp.api.match.dto.PlayerTourMatchDto;
import com.rubber.at.tennis.atp.api.match.dto.WorldTourMatchTypeDto;
import com.rubber.at.tennis.atp.api.match.req.WorldTourMatchReq;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDto;
import com.rubber.at.tennis.atp.dao.dal.IPlayerInfoDal;
import com.rubber.at.tennis.atp.dao.dal.IPlayerMatchResultDal;
import com.rubber.at.tennis.atp.dao.dal.IWorldTourMatchDal;
import com.rubber.at.tennis.atp.dao.entity.PlayerInfoEntity;
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
 * Created on 2024/4/14
 */
@Slf4j
@Service("worldTourQueryApi")
public class WorldTourQueryService implements WorldTourQueryApi {

    @Autowired
    private IPlayerMatchResultDal iPlayerMatchResultDal;

    @Autowired
    private IWorldTourMatchDal iWorldTourMatchDal;

    @Autowired
    private IPlayerInfoDal iPlayerInfoDal;


    /**
     * 查询巡回赛的基础信息
     *
     * @param req 当前的请求
     * @return 返回巡回赛详情
     */
    @Override
    public WorldTourMatchTypeDto queryTourMatchInfo(WorldTourMatchReq req) {
        LambdaQueryWrapper<WorldTourMatchEntity> lqw = new LambdaQueryWrapper<>();
        if (StrUtil.isEmpty(req.getTourYear())){
            req.setTourYear(String.valueOf(DateUtil.year(new Date())));
        }
        lqw.eq(WorldTourMatchEntity::getTourYear,req.getTourYear());
        if (!StringUtils.isEmpty(req.getTourId())){
            lqw.eq(WorldTourMatchEntity::getTourId,req.getTourId());
        }
        if (CollUtil.isNotEmpty(req.getStatusList())){
            lqw.in(WorldTourMatchEntity::getStatus,req.getStatusList());
        }
        WorldTourMatchEntity entity = iWorldTourMatchDal.getOne(lqw, false);
        WorldTourMatchTypeDto dto = convertDto(entity);
        if (CollUtil.isNotEmpty(req.getDataModelList()) && req.getDataModelList().contains("historyMatchResult")){
            List<PlayerTourMatchDto> playerTourMatchDtos = queryTourHistoryMatch(entity);
            if (CollUtil.isNotEmpty(playerTourMatchDtos)){
                Map<String, List<PlayerTourMatchDto>> atpTypeResult = playerTourMatchDtos.stream().collect(Collectors.groupingBy(i -> {
                    if (i.getPlayerInfoDto() == null) {
                        return "null";
                    }
                    return i.getPlayerInfoDto().getPlayerType();
                }));
                dto.setHistoryMatchResult(atpTypeResult);
            }
        }

        return dto;
    }


    /**
     * 查询巡回赛历年的比赛数据
     * @param tourMatch
     * @return
     */
    private List<PlayerTourMatchDto> queryTourHistoryMatch(WorldTourMatchEntity tourMatch){
        if (tourMatch == null){
            return null;
        }
        List<MatchPlayerGroupBean> matchPlayerGroupBeans = iPlayerMatchResultDal.queryWinGroupPlayerByMatchName(tourMatch.getTourName());
        if (CollUtil.isEmpty(matchPlayerGroupBeans)){
            return null;
        }
        Set<String> playerIds = matchPlayerGroupBeans.stream().map(MatchPlayerGroupBean::getPlayerId).collect(Collectors.toSet());
        // 球员信息
        List<PlayerInfoEntity> playerInfoEntities = iPlayerInfoDal.queryByIds(playerIds);
        if (playerInfoEntities == null){
            return null;
        }
        Map<String, PlayerInfoEntity> playerInfoEntityMap = playerInfoEntities.stream().collect(Collectors.toMap(PlayerInfoEntity::getPlayerId, o1 -> o1, (o1, o2) -> o1));

        List<PlayerTourMatchDto> list = new ArrayList<>();
        for (MatchPlayerGroupBean matchPlayerGroupBean:matchPlayerGroupBeans){
            PlayerTourMatchDto dto = new PlayerTourMatchDto();

            dto.setMatchPlayerGroupBean(matchPlayerGroupBean);
            dto.setMatchId(tourMatch.getTourId());
            dto.setMatchName(tourMatch.getTourName());

            PlayerInfoEntity playerInfoEntity = playerInfoEntityMap.get(matchPlayerGroupBean.getPlayerId());
            PlayerInfoDto playerInfoDto = new PlayerInfoDto();
            BeanUtils.copyProperties(playerInfoEntity,playerInfoDto);
            dto.setPlayerInfoDto(playerInfoDto);

            list.add(dto);
        }

        return list;
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
        if (StrUtil.isNotEmpty(entity.getLogo())){
            String[] split = entity.getLogo().split(",");
            dto.setLogo(split[0]);
            dto.setLogoList(Arrays.asList(split));
        }
        dto.setMatchDays(dateStr);
        return dto;
    }
}
