package com.rubber.at.tennis.atp.api.base;

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

}
