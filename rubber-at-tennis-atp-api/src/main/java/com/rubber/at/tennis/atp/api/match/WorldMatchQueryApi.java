package com.rubber.at.tennis.atp.api.match;

import com.rubber.at.tennis.atp.api.match.dto.WorldMatchInfo;
import com.rubber.at.tennis.atp.api.match.req.WorldMatchReq;

import java.util.List;

/**
 * @author luffyu
 * Created on 2023/3/24
 */
public interface WorldMatchQueryApi {

    /**
     * 查询比赛的数据信息
     * @param req 当前的请求
     * @return 返回球赛信息
     */
    List<WorldMatchInfo> queryWorldMatch(WorldMatchReq req);


}