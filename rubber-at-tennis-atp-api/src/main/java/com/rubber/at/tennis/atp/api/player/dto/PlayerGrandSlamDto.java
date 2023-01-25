package com.rubber.at.tennis.atp.api.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 大满贯成就对象
 * @author luffyu
 * Created on 2023/1/24
 */
@Data
public class PlayerGrandSlamDto {

    /**
     * 年份
     */
    @JsonProperty("y")
    private String year;

    /**
     * 赛事名称
     */
    @JsonProperty("mNa")
    private String matchName;

    /**
     * 澳网比赛结果
     */
    @JsonProperty("aor")
    private String aoResult;

    /**
     * 澳网比赛结果
     */
    @JsonProperty("rgr")
    private String rgResult;

    /**
     * 澳网比赛结果
     */
    @JsonProperty("wcr")
    private String wcResult;

    /**
     * 澳网比赛结果
     */
    @JsonProperty("uor")
    private String uoResult;

}
