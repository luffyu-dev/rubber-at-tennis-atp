package com.rubber.at.tennis.atp.dao.dal;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rubber.at.tennis.atp.dao.condition.RankSearchCondition;
import com.rubber.at.tennis.atp.dao.entity.PlayerRankInfoEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;


/**
 * <p>
 * ATP球员的排名表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
public interface IPlayerRankInfoDal extends IBaseAdminService<PlayerRankInfoEntity> {


    IPage<PlayerRankInfoEntity> selectPlayerRank(IPage<PlayerRankInfoEntity> page, RankSearchCondition condition);
}
