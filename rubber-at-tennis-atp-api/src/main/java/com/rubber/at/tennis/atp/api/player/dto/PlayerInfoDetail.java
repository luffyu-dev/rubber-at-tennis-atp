package com.rubber.at.tennis.atp.api.player.dto;

import com.rubber.at.tennis.atp.api.match.dto.WorldMatchInfo;
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
     * 最近的比赛详情
     */
    private List<WorldMatchInfo> matchList;


}
