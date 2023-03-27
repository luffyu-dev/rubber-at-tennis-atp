package com.rubber.at.tennis.atp.dao.dal.impl;

import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchScoreEntity;
import com.rubber.at.tennis.atp.dao.mapper.WorldTennisMatchScoreMapper;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchScoreDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 世界网球比赛实时分数 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-03-24
 */
@Service
public class WorldTennisMatchScoreDalImpl extends BaseAdminService<WorldTennisMatchScoreMapper, WorldTennisMatchScoreEntity> implements IWorldTennisMatchScoreDal {

}
