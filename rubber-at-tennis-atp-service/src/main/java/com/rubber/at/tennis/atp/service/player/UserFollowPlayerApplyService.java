package com.rubber.at.tennis.atp.service.player;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.api.player.UserFollowPlayerApplyApi;
import com.rubber.at.tennis.atp.api.player.request.PlayerIdRequest;
import com.rubber.at.tennis.atp.dao.dal.IUserFollowPlayerDal;
import com.rubber.at.tennis.atp.dao.entity.UserFollowPlayerEntity;
import com.rubber.base.components.util.result.ResultMsg;
import com.rubber.base.components.util.session.BaseUserSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luffyu
 * Created on 2022/11/22
 */
@Service("userFollowPlayerApplyApi")
public class UserFollowPlayerApplyService implements UserFollowPlayerApplyApi  {

    @Resource
    private IUserFollowPlayerDal iUserFollowPlayerDal;




    /**
     * 关注运动员
     *
     * @param request 当前的请求
     * @return 返回是否关注成功的消息
     */
    @Override
    public ResultMsg followPlayer(PlayerIdRequest request) {
        UserFollowPlayerEntity userPlayerEntity = getByUserId(request);
        if (userPlayerEntity == null){
            userPlayerEntity = new UserFollowPlayerEntity();
            userPlayerEntity.setUid(request.getUid());
            userPlayerEntity.setPlayerId(request.getPlayerId());
            userPlayerEntity.setCreateTime(new Date());
            userPlayerEntity.setStatus(1);
            userPlayerEntity.setUpdateTime(new Date());
            iUserFollowPlayerDal.save(userPlayerEntity);
        }else if (userPlayerEntity.getStatus() != 1){
            userPlayerEntity.setStatus(1);
            userPlayerEntity.setUpdateTime(new Date());
            iUserFollowPlayerDal.updateById(userPlayerEntity);
        }
        return new ResultMsg();
    }

    /**
     * 取消关注
     *
     * @param request 当前的请求
     * @return 返回是否关注成功的消息
     */
    @Override
    public ResultMsg unFollowPlayer(PlayerIdRequest request) {
        UserFollowPlayerEntity userPlayerEntity = getByUserId(request);
        if (userPlayerEntity != null && userPlayerEntity.getStatus() != 0){
            userPlayerEntity.setStatus(0);
            userPlayerEntity.setUpdateTime(new Date());
            iUserFollowPlayerDal.updateById(userPlayerEntity);
        }
        return new ResultMsg();
    }


    /**
     * 查询是否关注了某个球员
     * @param request
     * @return
     */
    public UserFollowPlayerEntity getByUserId(PlayerIdRequest request){
        LambdaQueryWrapper<UserFollowPlayerEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserFollowPlayerEntity::getPlayerId,request.getPlayerId())
                .eq(UserFollowPlayerEntity::getUid,request.getUid())
                .last("limit 1");
        return iUserFollowPlayerDal.getOne(lqw);
    }

    /**
     * 判断是否关注
     * @param request
     * @return
     */
    public boolean isFollowed(PlayerIdRequest request){
        UserFollowPlayerEntity userFollowPlayerEntity = getByUserId(request);
        if (userFollowPlayerEntity != null && Integer.valueOf(1).equals(userFollowPlayerEntity.getStatus())){
            return true;
        }
        return false;
    }


    /**
     * 查询用户关注的球员id集合
     */
    public Set<String> queryUserFollowPlayer(BaseUserSession userSession){
        LambdaQueryWrapper<UserFollowPlayerEntity> lqw = new LambdaQueryWrapper<>();
        lqw.select(UserFollowPlayerEntity::getPlayerId)
                .eq(UserFollowPlayerEntity::getUid,userSession.getUid())
                .eq(UserFollowPlayerEntity::getStatus,1);
        List<UserFollowPlayerEntity> followPlayer = iUserFollowPlayerDal.list(lqw);
        if (CollUtil.isEmpty(followPlayer)){
            return new HashSet<>();
        }
        return followPlayer.stream().map(UserFollowPlayerEntity::getPlayerId).collect(Collectors.toSet());

    }



}
