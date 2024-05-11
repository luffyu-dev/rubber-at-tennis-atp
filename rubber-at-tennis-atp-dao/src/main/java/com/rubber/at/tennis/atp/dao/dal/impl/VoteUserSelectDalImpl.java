package com.rubber.at.tennis.atp.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.dao.entity.VoteUserSelectEntity;
import com.rubber.at.tennis.atp.dao.mapper.VoteUserSelectMapper;
import com.rubber.at.tennis.atp.dao.dal.IVoteUserSelectDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户投票结果表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2024-05-01
 */
@Service
public class VoteUserSelectDalImpl extends BaseAdminService<VoteUserSelectMapper, VoteUserSelectEntity> implements IVoteUserSelectDal {

    /**
     * @param uid
     * @param voteKey
     * @return
     */
    @Override
    public VoteUserSelectEntity getByUid(Integer uid, String voteKey) {
        LambdaQueryWrapper<VoteUserSelectEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(VoteUserSelectEntity::getUid,uid)
                .eq(VoteUserSelectEntity::getVoteKey,voteKey);
        return getOne(lqw);
    }
}
