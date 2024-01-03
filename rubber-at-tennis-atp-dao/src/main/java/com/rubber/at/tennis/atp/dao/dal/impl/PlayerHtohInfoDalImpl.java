package com.rubber.at.tennis.atp.dao.dal.impl;

import com.rubber.at.tennis.atp.dao.entity.PlayerHtohInfoEntity;
import com.rubber.at.tennis.atp.dao.mapper.PlayerHtohInfoMapper;
import com.rubber.at.tennis.atp.dao.dal.IPlayerHtohInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 球员h2h 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-11-09
 */
@Service
public class PlayerHtohInfoDalImpl extends BaseAdminService<PlayerHtohInfoMapper, PlayerHtohInfoEntity> implements IPlayerHtohInfoDal {

}
