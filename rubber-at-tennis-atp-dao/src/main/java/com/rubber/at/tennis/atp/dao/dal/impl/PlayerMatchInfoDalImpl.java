package com.rubber.at.tennis.atp.dao.dal.impl;

import com.rubber.at.tennis.atp.dao.dal.IPlayerMatchInfoDal;
import com.rubber.at.tennis.atp.dao.mapper.PlayerMatchInfoMapper;
import com.rubber.at.tennis.atp.dao.entity.PlayerMatchInfoEntity;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

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
