package com.rubber.at.tennis.atp.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.api.base.MatchPlayerGroupBean;
import com.rubber.at.tennis.atp.dao.entity.PlayerMatchResultEntity;
import com.rubber.at.tennis.atp.dao.mapper.PlayerMatchResultMapper;
import com.rubber.at.tennis.atp.dao.dal.IPlayerMatchResultDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 比赛详情表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-01-24
 */
@Service
public class PlayerMatchResultDalImpl extends BaseAdminService<PlayerMatchResultMapper, PlayerMatchResultEntity> implements IPlayerMatchResultDal {

    /**
     * 查询所有的比赛记录信息
     * @param playerId
     * @return
     */
    @Override
    public List<PlayerMatchResultEntity> queryPlayerMatch(String playerId){
        LambdaQueryWrapper<PlayerMatchResultEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PlayerMatchResultEntity::getPlayerId,playerId)
                .orderByDesc(PlayerMatchResultEntity::getMatchYear);
        return list(lqw);
    }

    /**
     * @param playerIds
     * @param matchName
     * @return
     */
    @Override
    public List<PlayerMatchResultEntity> queryPlayerMatch(Set<String> playerIds, String matchName) {
        LambdaQueryWrapper<PlayerMatchResultEntity> lqw = new LambdaQueryWrapper<>();
        lqw.in(PlayerMatchResultEntity::getPlayerId,playerIds)
                .eq(PlayerMatchResultEntity::getMatchName,matchName)
                .orderByDesc(PlayerMatchResultEntity::getMatchYear);
        return list(lqw);
    }

    /**
     * 比赛名称和分组类型 查询
     *
     * @param matchName
     * @return
     */
    @Override
    public List<MatchPlayerGroupBean> queryWinGroupPlayerByMatchName(String matchName) {
        return this.getBaseMapper().groupMatchResultByPlaye(matchName);
    }


}
