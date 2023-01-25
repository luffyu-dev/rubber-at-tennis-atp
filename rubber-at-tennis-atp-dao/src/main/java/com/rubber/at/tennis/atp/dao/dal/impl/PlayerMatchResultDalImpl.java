package com.rubber.at.tennis.atp.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.dao.entity.PlayerMatchInfoEntity;
import com.rubber.at.tennis.atp.dao.entity.PlayerMatchResultEntity;
import com.rubber.at.tennis.atp.dao.mapper.PlayerMatchResultMapper;
import com.rubber.at.tennis.atp.dao.dal.IPlayerMatchResultDal;
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
}
