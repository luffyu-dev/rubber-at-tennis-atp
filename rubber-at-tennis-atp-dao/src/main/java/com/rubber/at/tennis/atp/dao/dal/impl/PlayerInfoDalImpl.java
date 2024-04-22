package com.rubber.at.tennis.atp.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.dao.condition.FollowPlayerCondition;
import com.rubber.at.tennis.atp.dao.dal.IPlayerInfoDal;
import com.rubber.at.tennis.atp.dao.entity.PlayerInfoEntity;
import com.rubber.at.tennis.atp.dao.mapper.PlayerInfoMapper;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * ATP运动员详情表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
@Service
public class PlayerInfoDalImpl extends BaseAdminService<PlayerInfoMapper, PlayerInfoEntity> implements IPlayerInfoDal {




    /**
     * 查询关注的球员信息
     *
     * @param page
     * @param condition
     */
    @Override
    public Page<PlayerInfoEntity> queryFollowPlayer(Page<PlayerInfoEntity> page, FollowPlayerCondition condition) {
        return getBaseMapper().queryFollowPlayer(page,condition);
    }

    /**
     * @param ids
     * @return
     */
    @Override
    public List<PlayerInfoEntity> queryByIds(Set<String> ids) {
        LambdaQueryWrapper<PlayerInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.in(PlayerInfoEntity::getPlayerId,ids);
        return list(lqw);
    }
}
