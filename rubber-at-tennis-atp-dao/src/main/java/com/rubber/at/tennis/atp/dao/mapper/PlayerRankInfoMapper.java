package com.rubber.at.tennis.atp.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rubber.at.tennis.atp.dao.condition.RankSearchCondition;
import com.rubber.at.tennis.atp.dao.entity.PlayerRankInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * ATP球员的排名表 Mapper 接口
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
public interface PlayerRankInfoMapper extends BaseMapper<PlayerRankInfoEntity> {


    List<PlayerRankInfoEntity> selectPlayerRank(IPage<PlayerRankInfoEntity> page, @Param("condition") RankSearchCondition condition);
}
