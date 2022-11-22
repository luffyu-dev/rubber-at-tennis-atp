package com.rubber.at.tennis.atp.api.player;

import com.rubber.at.tennis.atp.api.base.PlayerIdRequest;
import com.rubber.at.tennis.atp.api.base.SearchQueryRequest;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDetail;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDto;
import com.rubber.base.components.util.result.page.ResultPage;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
public interface PlayerInfoQueryApi {


    /**
     * apt球员的分页查询
     * @param request 当前请求
     * @return 返回分页数据
     */
    ResultPage<PlayerInfoDto>  queryAtpInfoPage(SearchQueryRequest request);

    /**
     * wta球员的分页查询
     * @param request 当前请求
     * @return 返回分页数据
     */
    ResultPage<PlayerInfoDto>  queryWtaInfoPage(SearchQueryRequest request);


    /**
     * 球员的基本信息
     * @param playerIdRequest  球员id
     * @return 返回球员的基本信息
     */
    PlayerInfoDetail getPlayerDetail(PlayerIdRequest playerIdRequest);

}
