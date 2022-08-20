package com.rubber.at.tennis.map.api.rank;

import com.rubber.at.tennis.map.api.base.SearchQueryRequest;
import com.rubber.at.tennis.map.api.rank.dto.PlayerRankInfoDto;
import com.rubber.at.tennis.map.api.rank.response.RankPageResponse;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
public interface PlayerRankInfoApi {



    /**
     * apt球员的分页查询
     * @param request 当前请求
     * @return 返回分页数据
     */
    RankPageResponse<PlayerRankInfoDto> queryAtpRankPage(SearchQueryRequest request);

    /**
     * wta球员的分页查询
     * @param request 当前请求
     * @return 返回分页数据
     */
    RankPageResponse<PlayerRankInfoDto>  queryWtaRankPage(SearchQueryRequest request);

}
