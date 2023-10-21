package com.rubber.at.tennis.atp.api.match.dto;

import lombok.Data;

import java.util.List;

/**
 * @author luffyu
 * Created on 2023/10/19
 */
@Data
public class RecommendWorldMatchDto {

    /**
     * 循环赛事详细信息
     */
    private WorldTourMatchTypeDto  tourMatchInfo;


    /**
     * 比赛list
     */
    private List<WorldMatchInfo> matchList;


    public RecommendWorldMatchDto() {
    }

    public RecommendWorldMatchDto(WorldTourMatchTypeDto tourMatchInfo, List<WorldMatchInfo> matchList) {
        this.tourMatchInfo = tourMatchInfo;
        this.matchList = matchList;
    }
}
