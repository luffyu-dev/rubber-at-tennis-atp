package com.rubber.at.tennis.atp.api.player.request;

import com.rubber.base.components.util.result.page.BaseRequestPage;
import com.rubber.base.components.util.session.BaseUserSession;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luffyu
 * Created on 2022/11/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PagePlayerIdRequest extends BaseRequestPage {

    /**
     * 球员id
     */
    private String playerId;

}
