package com.rubber.at.tennis.atp.api.player;

import com.rubber.at.tennis.atp.api.player.request.PlayerIdRequest;
import com.rubber.base.components.util.result.ResultMsg;

/**
 * @author luffyu
 * Created on 2022/11/22
 */
public interface UserFollowPlayerApplyApi {


    /**
     * 关注运动员
     * @param request 当前的请求
     * @return 返回是否关注成功的消息
     */
    ResultMsg followPlayer(PlayerIdRequest request);


    /**
     * 取消关注
     * @param request 当前的请求
     * @return 返回是否关注成功的消息
     */
    ResultMsg unFollowPlayer(PlayerIdRequest request);


}
