package com.rubber.at.tennis.map.service.controller;

import com.rubber.at.tennis.map.api.base.SearchQueryRequest;
import com.rubber.at.tennis.map.api.rank.PlayerRankInfoApi;
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
@RequestMapping("/rank")
public class PlayerRankQueryController {

    @Resource
    private PlayerRankInfoApi playerRankInfoApi;


    /**
     * 排名搜索
     */
    @PostMapping("/atp/search")
    public ResultMsg queryRankByPage(@RequestBody SearchQueryRequest request){
        return ResultMsg.success(playerRankInfoApi.queryAtpRankPage(request));
    }


    /**
     * 用户搜索
     */
    @PostMapping("/wta/search")
    public ResultMsg queryPlayerByPage(@RequestBody SearchQueryRequest request){
        return ResultMsg.success(playerRankInfoApi.queryWtaRankPage(request));
    }


}
