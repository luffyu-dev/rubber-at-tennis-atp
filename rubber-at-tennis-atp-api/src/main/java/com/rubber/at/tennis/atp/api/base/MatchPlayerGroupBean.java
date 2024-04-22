package com.rubber.at.tennis.atp.api.base;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2024/4/14
 */
@Data
public class MatchPlayerGroupBean {

    /**
     *
     * 球员id
     */
    private String playerId;

    /**
     * 比赛名称
     */
    private String matchName;

    /**
     * 拿冠军的次数
     */
    private String winNum;

    /**
     * 赢得比赛的数量
     */
    private String winYearList;
}
