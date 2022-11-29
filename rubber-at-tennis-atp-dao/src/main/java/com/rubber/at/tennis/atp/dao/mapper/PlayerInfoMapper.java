package com.rubber.at.tennis.atp.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.dao.condition.FollowPlayerCondition;
import com.rubber.at.tennis.atp.dao.entity.PlayerInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ATP运动员详情表 Mapper 接口
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
public interface PlayerInfoMapper extends BaseMapper<PlayerInfoEntity> {

    /**
     * 搜索查询关注的球员
     * @param page
     * @param condition
     * @return
     */
    Page<PlayerInfoEntity> queryFollowPlayer(Page<PlayerInfoEntity> page,
                                             @Param("condition") FollowPlayerCondition condition);


}
