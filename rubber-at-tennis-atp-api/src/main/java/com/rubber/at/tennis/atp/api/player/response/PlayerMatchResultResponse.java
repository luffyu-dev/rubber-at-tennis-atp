package com.rubber.at.tennis.atp.api.player.response;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rubber.at.tennis.atp.api.player.dto.PlayerGrandSlamDto;
import com.rubber.at.tennis.atp.api.player.dto.PlayerMatchTypeDto;
import lombok.Data;

import java.util.List;

/**
 * @author luffyu
 * Created on 2023/1/22
 */
@Data
public class PlayerMatchResultResponse {

    /**
     * 大满贯成就
     */
    @JsonProperty("gsrL")
    private List<PlayerGrandSlamDto> grandSlamResultList;

    /**
     * 所有赛事成就
     */
    @JsonProperty("amrL")
    private List<PlayerMatchTypeDto> allMatchResultList;



}
