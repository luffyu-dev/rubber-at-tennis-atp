package com.rubber.at.tennis.atp.web;

import com.rubber.at.tennis.atp.api.base.PlayerIdRequest;
import com.rubber.at.tennis.atp.api.base.SearchQueryRequest;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDetail;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDto;
import com.rubber.at.tennis.atp.service.player.PlayerInfoQueryService;
import com.rubber.base.components.util.result.page.ResultPage;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest
@ComponentScan("com.rubber.at.tennis.*")
@MapperScan("com.rubber.at.tennis.**.dao.mapper")
class RubberAtTennisAtpWebApplicationTests {


    @Autowired
    private PlayerInfoQueryService playerInfoQueryService;

    @Test
    public void query(){

        SearchQueryRequest searchQueryRequest = new SearchQueryRequest();

        ResultPage<PlayerInfoDto> infoDtoResultPage = playerInfoQueryService.queryAtpInfoPage(searchQueryRequest);


        ResultPage<PlayerInfoDto> playerInfoDtoResultPage = playerInfoQueryService.queryWtaInfoPage(searchQueryRequest);

        PlayerIdRequest playerIdRequest = new PlayerIdRequest();
        playerIdRequest.setPlayerId("Z272");

        PlayerInfoDetail playerDetail = playerInfoQueryService.getPlayerDetail(playerIdRequest);


        System.out.println();


    }


}
