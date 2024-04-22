package com.rubber.at.tennis.atp.api.match;

import com.rubber.at.tennis.atp.api.match.dto.WorldTourMatchTypeDto;
import com.rubber.at.tennis.atp.api.match.req.WorldTourMatchReq;

import java.util.List;

/**
 * @author luffyu
 * Created on 2024/4/14
 */
public interface WorldTourQueryApi {


    /**
     * ok
     * 查询巡回赛的基础信息
     * @param req  当前的请求
     * @return 返回巡回赛详情
     */
    WorldTourMatchTypeDto queryTourMatchInfo(WorldTourMatchReq req);


}
