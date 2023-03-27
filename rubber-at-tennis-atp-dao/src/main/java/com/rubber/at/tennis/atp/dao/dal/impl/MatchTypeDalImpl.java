package com.rubber.at.tennis.atp.dao.dal.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.dao.entity.MatchTypeEntity;
import com.rubber.at.tennis.atp.dao.mapper.MatchTypeMapper;
import com.rubber.at.tennis.atp.dao.dal.IMatchTypeDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 比赛详情表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-01-24
 */
@Service
public class MatchTypeDalImpl extends BaseAdminService<MatchTypeMapper, MatchTypeEntity> implements IMatchTypeDal {

    @Override
    public List<MatchTypeEntity> queryByGroupId(String group) {
        LambdaQueryWrapper<MatchTypeEntity> lqw = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(group)){
            lqw.eq(MatchTypeEntity::getMatchGroup,group);
        }
        return list(lqw);
    }
}
