package com.rubber.at.tennis.atp.dao.dal.impl;

import com.rubber.at.tennis.atp.dao.entity.UserFollowPlayerEntity;
import com.rubber.at.tennis.atp.dao.mapper.UserFollowPlayerMapper;
import com.rubber.at.tennis.atp.dao.dal.IUserFollowPlayerDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户球员关注表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-11-22
 */
@Service
public class UserFollowPlayerDalImpl extends BaseAdminService<UserFollowPlayerMapper, UserFollowPlayerEntity> implements IUserFollowPlayerDal {

}
