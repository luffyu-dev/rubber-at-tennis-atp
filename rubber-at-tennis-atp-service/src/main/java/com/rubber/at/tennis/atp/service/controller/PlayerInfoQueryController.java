package com.rubber.at.tennis.atp.service.controller;

import com.rubber.at.tennis.atp.api.player.request.PlayerIdRequest;
import com.rubber.at.tennis.atp.api.base.SearchQueryRequest;
import com.rubber.at.tennis.atp.service.player.PlayerInfoQueryService;
import com.rubber.base.components.util.annotation.NeedLogin;
import com.rubber.base.components.util.result.ResultMsg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2022/5/21
 */
@RestController
@RequestMapping("/player")
public class PlayerInfoQueryController {

    @Resource
    private PlayerInfoQueryService playerInfoQueryService;


    /**
     * 排名搜索
     */
    @PostMapping("/atp/search")
    @NeedLogin(request = false)
    public ResultMsg queryRankByPage(@RequestBody SearchQueryRequest request){
        return ResultMsg.success(playerInfoQueryService.queryAtpInfoPage(request));
    }


    /**
     * 用户搜索
     */
    @PostMapping("/wta/search")
    @NeedLogin(request = false)
    public ResultMsg queryPlayerByPage(@RequestBody SearchQueryRequest request){
        return ResultMsg.success(playerInfoQueryService.queryWtaInfoPage(request));
    }



    /**
     * 用户搜索
     */
    @PostMapping("/detail")
    @NeedLogin(request = false)
    public ResultMsg getPlayerDetail(@RequestBody PlayerIdRequest request){
        return ResultMsg.success(playerInfoQueryService.getPlayerDetail(request));
    }


}
