package com.rubber.at.tennis.atp.service.controller;

import com.rubber.at.tennis.atp.api.match.WorldMatchQueryApi;
import com.rubber.at.tennis.atp.api.match.req.WorldMatchReq;
import com.rubber.base.components.util.result.ResultMsg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2023/3/24
 */
@RestController
@RequestMapping("/world-match")
public class WorldMatchQueryController {

    @Resource
    private WorldMatchQueryApi worldMatchQueryApi;

    /**
     * 排名搜索
     */
    @PostMapping("/query")
    public ResultMsg queryRankByPage(@RequestBody WorldMatchReq req){
        return ResultMsg.success(worldMatchQueryApi.queryWorldMatch(req));
    }


}
