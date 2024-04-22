package com.rubber.at.tennis.atp.api.match.dto;

import com.rubber.at.tennis.atp.api.base.MatchPlayerGroupBean;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDto;
import lombok.Data;

/**
 * @author luffyu
 * Created on 2024/4/14
 */
@Data
public class PlayerTourMatchDto {

    private String matchId;

    private String matchName;

    private PlayerInfoDto playerInfoDto;

    private MatchPlayerGroupBean matchPlayerGroupBean;
}
