package com.rubber.at.tennis.atp.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.dao.entity.VoteInfoEntity;
import com.rubber.at.tennis.atp.dao.mapper.VoteInfoMapper;
import com.rubber.at.tennis.atp.dao.dal.IVoteInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 投票信息表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2024-05-01
 */
@Service
public class VoteInfoDalImpl extends BaseAdminService<VoteInfoMapper, VoteInfoEntity> implements IVoteInfoDal {

    /**
     * @param key
     * @return
     */
    @Override
    public VoteInfoEntity queryByKey(String key) {
        LambdaQueryWrapper<VoteInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(VoteInfoEntity::getVoteKey,key);
        return getOne(lqw);
    }
}
