package com.rubber.at.tennis.atp.api.match;

import com.rubber.at.tennis.atp.api.match.dto.WorldMatchInfo;
import com.rubber.at.tennis.atp.api.match.dto.WorldTourMatchTypeDto;
import com.rubber.at.tennis.atp.api.match.req.WorldMatchReq;
import com.rubber.at.tennis.atp.api.match.req.WorldTourMatchReq;

import java.util.List;

/**
 * @author luffyu
 * Created on 2023/3/24
 */
public interface WorldMatchQueryApi {


    /**
     * 查询巡回赛的基础信息
     * @param req  当前的请求
     * @return 返回巡回赛详情
     */
    WorldTourMatchTypeDto queryTourMatchInfo(WorldTourMatchReq req);



    /**
     * 查询所有巡回赛
     * @param req  当前的请求
     * @return 返回巡回赛详情
     */
    List<WorldTourMatchTypeDto> queryTourMatch(WorldTourMatchReq req);


    /**
     * 查询进行中的比萨
     * @param req
     * @return
     */
    List<WorldMatchInfo> queryLivingWorldMatch(WorldMatchReq req);



    /**
     * 查询比赛的数据信息
     * @param req 当前的请求
     * @return 返回球赛信息
     */
    List<WorldMatchInfo> queryWorldMatch(WorldMatchReq req);


}
