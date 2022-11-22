package com.rubber.at.tennis.atp.api.player.dto;

import com.rubber.at.tennis.atp.api.rank.dto.PlayerRankInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author luffyu
 * Created on 2022/11/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerInfoDetail extends PlayerInfoDto{


    /**
     * 存量的排名信息
     */
    private List<PlayerRankInfoDto> oldRankList;

}
