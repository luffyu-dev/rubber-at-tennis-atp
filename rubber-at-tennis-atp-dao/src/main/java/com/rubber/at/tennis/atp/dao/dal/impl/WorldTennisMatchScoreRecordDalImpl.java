package com.rubber.at.tennis.atp.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchScoreRecordEntity;
import com.rubber.at.tennis.atp.dao.mapper.WorldTennisMatchScoreRecordMapper;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchScoreRecordDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 世界网球比赛分数记录表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-05-15
 */
@Service
public class WorldTennisMatchScoreRecordDalImpl extends BaseAdminService<WorldTennisMatchScoreRecordMapper, WorldTennisMatchScoreRecordEntity> implements IWorldTennisMatchScoreRecordDal {

    @Override
    public List<WorldTennisMatchScoreRecordEntity> queryByMatchId(String matchId) {
        LambdaQueryWrapper<WorldTennisMatchScoreRecordEntity> lqw = new LambdaQueryWrapper();
        lqw.eq(WorldTennisMatchScoreRecordEntity::getMatchId,matchId)
                .orderByDesc(WorldTennisMatchScoreRecordEntity::getRecordId);
        return list(lqw);
    }
}
