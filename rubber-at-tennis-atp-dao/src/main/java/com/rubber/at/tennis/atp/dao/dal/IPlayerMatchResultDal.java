package com.rubber.at.tennis.atp.dao.dal;

import com.rubber.at.tennis.atp.dao.entity.PlayerMatchResultEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

import java.util.List;

/**
 * <p>
 * 比赛详情表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2023-01-24
 */
public interface IPlayerMatchResultDal extends IBaseAdminService<PlayerMatchResultEntity> {

    List<PlayerMatchResultEntity> queryPlayerMatch(String playerId);
}
