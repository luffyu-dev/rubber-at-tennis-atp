package com.rubber.at.tennis.atp.dao.dal;

import com.rubber.at.tennis.atp.api.base.MatchPlayerGroupBean;
import com.rubber.at.tennis.atp.dao.entity.PlayerMatchResultEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

import java.util.List;
import java.util.Set;

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


    List<PlayerMatchResultEntity> queryPlayerMatch(Set<String> playerIds, String matchName);

    /**
     * 比赛名称和分组类型 查询
     *
     * @param matchName
     * @return
     */
    List<MatchPlayerGroupBean> queryWinGroupPlayerByMatchName(String matchName);
}
