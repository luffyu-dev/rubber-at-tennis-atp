package com.rubber.at.tennis.atp.api.match.dto;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2023/10/13
 */
@Data
public class MatchRoundDto {

    /**
     * 比赛轮次
     */
    private String matchRound;

    /**
     * 比赛名称
     */
    private String matchRoundName;
}
