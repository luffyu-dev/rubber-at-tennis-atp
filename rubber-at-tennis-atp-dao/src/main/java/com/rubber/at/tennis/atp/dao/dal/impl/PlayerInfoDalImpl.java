package com.rubber.at.tennis.atp.dao.dal.impl;

import com.rubber.at.tennis.atp.dao.dal.IPlayerInfoDal;
import com.rubber.at.tennis.atp.dao.entity.PlayerInfoEntity;
import com.rubber.at.tennis.atp.dao.mapper.PlayerInfoMapper;
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
