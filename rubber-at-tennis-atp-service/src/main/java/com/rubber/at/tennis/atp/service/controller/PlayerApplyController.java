package com.rubber.at.tennis.atp.service.controller;

import com.rubber.at.tennis.atp.api.player.request.PlayerIdRequest;
import com.rubber.at.tennis.atp.service.player.UserFollowPlayerApplyService;
import com.rubber.base.components.util.annotation.NeedLogin;
import com.rubber.base.components.util.result.ResultMsg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2022/5/21
 */
@RestController
@RequestMapping("/player")
public class PlayerApplyController {

    @Resource
    private UserFollowPlayerApplyService userFollowPlayerApplyService;


    /**
     * 关注
     */
    @PostMapping("/follow")
    @NeedLogin
    public ResultMsg follow(@RequestBody PlayerIdRequest request){
        return ResultMsg.success(userFollowPlayerApplyService.followPlayer(request));
    }


    /**
     * 取消关注
     */
    @PostMapping("/unfollow")
    @NeedLogin
    public ResultMsg unfollow(@RequestBody PlayerIdRequest request){
        return ResultMsg.success(userFollowPlayerApplyService.unFollowPlayer(request));
    }


}
