package com.rubber.at.tennis.atp.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchEntity;
import com.rubber.at.tennis.atp.dao.mapper.WorldTennisMatchMapper;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 世界网球比赛表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-03-24
 */
@Service
public class WorldTennisMatchDalImpl extends BaseAdminService<WorldTennisMatchMapper, WorldTennisMatchEntity> implements IWorldTennisMatchDal {

    @Override
    public WorldTennisMatchEntity getById(String matchId) {
        LambdaQueryWrapper<WorldTennisMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(WorldTennisMatchEntity::getMatchId,matchId)
                .last("limit 1");
        return getOne(lqw);
    }
}
