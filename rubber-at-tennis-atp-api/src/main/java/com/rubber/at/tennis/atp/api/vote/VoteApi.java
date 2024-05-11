package com.rubber.at.tennis.atp.api.vote;

import com.rubber.at.tennis.atp.api.vote.dto.VoteInfoDto;
import com.rubber.at.tennis.atp.api.vote.request.VoteReq;

/**
 * @author luffyu
 * Created on 2024/5/1
 */
public interface VoteApi {

    /**
     * 查询投票详情
     * @param req
     * @return
     */
    VoteInfoDto getVoteInfo(VoteReq req);


    /**
     * 选择投票
     * @param req
     */
    void selectPoint(VoteReq req);
}
