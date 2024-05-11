package com.rubber.at.tennis.atp.api.vote.request;

import com.rubber.base.components.util.session.BaseUserSession;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luffyu
 * Created on 2024/5/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VoteReq extends BaseUserSession {

    private String voteKey;


    private String selectPoint;
}
