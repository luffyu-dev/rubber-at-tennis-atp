package com.rubber.at.tennis.atp.dao.dal;

import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchScoreRecordEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

import java.util.List;

/**
 * <p>
 * 世界网球比赛分数记录表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2023-05-15
 */
public interface IWorldTennisMatchScoreRecordDal extends IBaseAdminService<WorldTennisMatchScoreRecordEntity> {

    List<WorldTennisMatchScoreRecordEntity> queryByMatchId(String matchId);
}
