package com.rubber.at.tennis.atp.service.match;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.api.match.WorldMatchQueryApi;
import com.rubber.at.tennis.atp.api.match.dto.WorldMatchInfo;
import com.rubber.at.tennis.atp.api.match.dto.WorldMatchPlayerInfo;
import com.rubber.at.tennis.atp.api.match.req.WorldMatchReq;
import com.rubber.at.tennis.atp.dao.dal.IMatchTypeDal;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchDal;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchPlayerDal;
import com.rubber.at.tennis.atp.dao.entity.MatchTypeEntity;
import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchEntity;
import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchPlayerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private IMatchTypeDal iMatchTypeDal;


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




}
