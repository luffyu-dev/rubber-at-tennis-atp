package com.rubber.at.tennis.atp.dao.dal;

import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

/**
 * <p>
 * 世界网球比赛表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2023-03-24
 */
public interface IWorldTennisMatchDal extends IBaseAdminService<WorldTennisMatchEntity> {



    WorldTennisMatchEntity getById(String matchId);

}
