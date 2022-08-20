package com.rubber.at.tennis.map.dao.dal.impl;

import com.rubber.at.tennis.map.dao.entity.PlayerInfoEntity;
import com.rubber.at.tennis.map.dao.mapper.PlayerInfoMapper;
import com.rubber.at.tennis.map.dao.dal.IPlayerInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ATP运动员详情表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
@Service
public class PlayerInfoDalImpl extends BaseAdminService<PlayerInfoMapper, PlayerInfoEntity> implements IPlayerInfoDal {

}
