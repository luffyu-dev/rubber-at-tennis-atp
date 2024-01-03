package com.rubber.at.tennis.atp.service.controller;

import com.rubber.at.tennis.atp.api.match.WorldMatchQueryApi;
import com.rubber.at.tennis.atp.api.match.dto.WorldMatchInfo;
import com.rubber.at.tennis.atp.api.match.enums.MatchStatusEnums;
import com.rubber.at.tennis.atp.api.match.req.WorldMatchReq;
import com.rubber.at.tennis.atp.api.match.req.WorldTourMatchReq;
import com.rubber.base.components.util.result.ResultMsg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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
    @PostMapping("/tour-info")
    public ResultMsg queryTourInfo(@RequestBody WorldTourMatchReq req){
        return ResultMsg.success(worldMatchQueryApi.queryTourMatchInfo(req));
    }


    /**
     * 排名搜索
     */
    @PostMapping("/tour-list")
    public ResultMsg queryTourList(@RequestBody WorldTourMatchReq req){
        return ResultMsg.success(worldMatchQueryApi.queryTourMatch(req));
    }



    /**
     * 查询比赛的数据信息
     */
    @PostMapping("/query")
    public ResultMsg queryByPage(@RequestBody WorldMatchReq req){
        // 兼容性查询 后期删掉
        return ResultMsg.success(worldMatchQueryApi.queryWorldMatch(req));
    }


    /**
     * 获取全部进行中的比赛
     */
    @PostMapping("/query-living")
    public ResultMsg queryLivingByPage(@RequestBody WorldMatchReq req){
        // 兼容性查询 后期删掉
        return ResultMsg.success(worldMatchQueryApi.queryLivingWorldMatch(req));
    }


    /**
     * 查询推荐的比赛
     */
    @PostMapping("/query-recommend")
    public ResultMsg queryRecommend(@RequestBody WorldMatchReq req){
        // 兼容性查询 后期删掉
        return ResultMsg.success();
    }


    /**
     * 查询推荐的比赛
     */
    @PostMapping("/recommend")
    public ResultMsg recommendMatch(@RequestBody WorldMatchReq req){
        // 兼容性查询 后期删掉
        return ResultMsg.success(worldMatchQueryApi.queryRecommendWorldMatch(req));
    }


    /**
     * 查询进行中的比赛详情
     */
    @PostMapping("/living-detail")
    public ResultMsg getLivingDetail(@RequestBody WorldMatchReq req){
        // 兼容性查询 后期删掉
        return ResultMsg.success(worldMatchQueryApi.getLivingDetail(req.getMatchId()));
    }


}
