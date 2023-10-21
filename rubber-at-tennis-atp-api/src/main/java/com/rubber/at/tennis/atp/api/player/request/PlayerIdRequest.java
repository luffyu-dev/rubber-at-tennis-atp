package com.rubber.at.tennis.atp.api.player.request;

import com.rubber.base.components.util.session.BaseUserSession;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luffyu
 * Created on 2022/11/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerIdRequest extends BaseUserSession {

    /**
     * 球员id
     */
    private String playerId;

    /**
     * 球员类型
     */
    private String playerType;


    /**
     * 是否查询比赛详情
     * 1表示查询 0表示不查询 默认不查
     */
    private Integer queryMatchSize;

}
