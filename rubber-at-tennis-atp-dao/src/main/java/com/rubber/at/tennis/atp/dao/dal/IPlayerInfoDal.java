package com.rubber.at.tennis.atp.dao.dal;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.dao.condition.FollowPlayerCondition;
import com.rubber.at.tennis.atp.dao.entity.PlayerInfoEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * ATP运动员详情表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
public interface IPlayerInfoDal extends IBaseAdminService<PlayerInfoEntity> {


    /**
     * 查询关注的球员信息
     */
    Page<PlayerInfoEntity> queryFollowPlayer(Page<PlayerInfoEntity> page, FollowPlayerCondition condition);


    List<PlayerInfoEntity> queryByIds(Set<String> ids);

}
