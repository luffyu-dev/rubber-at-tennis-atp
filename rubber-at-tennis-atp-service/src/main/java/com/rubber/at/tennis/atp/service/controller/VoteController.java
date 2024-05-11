package com.rubber.at.tennis.atp.service.controller;

import com.rubber.at.tennis.atp.api.vote.VoteApi;
import com.rubber.at.tennis.atp.api.vote.request.VoteReq;
import com.rubber.base.components.util.annotation.NeedLogin;
import com.rubber.base.components.util.result.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luffyu
 * Created on 2024/5/1
 */
@RestController
@RequestMapping("/vote")
public class VoteController {


    @Autowired
    private VoteApi voteApi;



    /**
     * atp球员搜索
     */
    @PostMapping("/info")
    @NeedLogin(request = false)
    public ResultMsg voteInfo(@RequestBody VoteReq req){
        return ResultMsg.success(voteApi.getVoteInfo(req));
    }



    /**
     * atp球员搜索
     */
    @PostMapping("/select")
    @NeedLogin
    public ResultMsg selectPoint(@RequestBody VoteReq req){
        voteApi.selectPoint(req);
        return ResultMsg.success();
    }

}
