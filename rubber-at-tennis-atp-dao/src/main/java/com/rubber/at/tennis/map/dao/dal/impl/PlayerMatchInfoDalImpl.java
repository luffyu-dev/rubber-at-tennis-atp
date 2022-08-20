package com.rubber.at.tennis.map.dao.dal.impl;

import com.rubber.at.tennis.map.dao.entity.PlayerMatchInfoEntity;
import com.rubber.at.tennis.map.dao.mapper.PlayerMatchInfoMapper;
import com.rubber.at.tennis.map.dao.dal.IPlayerMatchInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 球员的比赛记录表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
@Service
public class PlayerMatchInfoDalImpl extends BaseAdminService<PlayerMatchInfoMapper, PlayerMatchInfoEntity> implements IPlayerMatchInfoDal {

}
