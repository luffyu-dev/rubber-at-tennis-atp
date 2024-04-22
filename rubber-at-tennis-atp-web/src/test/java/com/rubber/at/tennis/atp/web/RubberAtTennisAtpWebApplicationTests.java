package com.rubber.at.tennis.atp.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.api.base.MatchPlayerGroupBean;
import com.rubber.at.tennis.atp.api.match.WorldMatchQueryApi;
import com.rubber.at.tennis.atp.api.match.WorldTourQueryApi;
import com.rubber.at.tennis.atp.api.match.dto.PlayerTourMatchDto;
import com.rubber.at.tennis.atp.api.match.req.WorldTourMatchReq;
import com.rubber.at.tennis.atp.dao.condition.RankSearchCondition;
import com.rubber.at.tennis.atp.dao.dal.IPlayerRankInfoDal;
import com.rubber.at.tennis.atp.dao.entity.PlayerRankInfoEntity;
import com.rubber.at.tennis.atp.dao.mapper.PlayerMatchResultMapper;
import com.rubber.at.tennis.atp.service.player.PlayerInfoQueryService;
import com.rubber.at.tennis.atp.service.player.UserFollowPlayerApplyService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;


@SpringBootTest
@ComponentScan("com.rubber.at.tennis.*")
@MapperScan("com.rubber.at.tennis.**.dao.mapper")
class RubberAtTennisAtpWebApplicationTests {


    @Autowired
    private PlayerInfoQueryService playerInfoQueryService;

    @Autowired
    private UserFollowPlayerApplyService userFollowPlayerApplyService;

    @Autowired
    private WorldMatchQueryApi worldMatchQueryApi;

    @Autowired
    private WorldTourQueryApi worldTourQueryApi;

    @Test
    public void query(){
        WorldTourMatchReq req = new WorldTourMatchReq();
        req.setTourId("Miami");
        req.setTourYear("2023");




//        PlayerIdRequest playerId = new PlayerIdRequest();
//        playerId.setPlayerId("Z272");
//        playerId.setUid(1000001);
//        userFollowPlayerApplyService.followPlayer(playerId);
//
//
//
//        SearchQueryRequest searchQueryRequest = new SearchQueryRequest();
//        searchQueryRequest.setUid(1000001);
//
//        ResultPage<PlayerInfoDto> infoDtoResultPage = playerInfoQueryService.queryAtpInfoPage(searchQueryRequest);
//
//
//        ResultPage<PlayerInfoDto> playerInfoDtoResultPage = playerInfoQueryService.queryWtaInfoPage(searchQueryRequest);
//
//        PlayerIdRequest playerIdRequest = new PlayerIdRequest();
//        playerIdRequest.setPlayerId("Z272");
//
//        PlayerInfoDetail playerDetail = playerInfoQueryService.getPlayerDetail(playerIdRequest);
//
//
//        System.out.println();


   }

    @Autowired
    private IPlayerRankInfoDal iPlayerRankInfoDal;

    @Test
    public void queryTourMatchInfo(){
        IPage<PlayerRankInfoEntity> page = new Page<>();
        page.setSize(4);
        page.setCurrent(1);
        RankSearchCondition condition = new RankSearchCondition();
        condition.setDataVersion("20220521");
        condition.setPlayerType("atp");
        condition.setSearchValue("中国");
        IPage<PlayerRankInfoEntity> playerRankInfoEntities = iPlayerRankInfoDal.selectPlayerRank(page,condition);
        System.out.println(playerRankInfoEntities);
    }


}
