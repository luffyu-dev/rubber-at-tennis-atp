package com.rubber.at.tennis.atp.dao.dal.impl;

import com.rubber.at.tennis.atp.dao.dal.IPlayerRankInfoDal;
import com.rubber.at.tennis.atp.dao.entity.PlayerRankInfoEntity;
import com.rubber.at.tennis.atp.dao.mapper.PlayerRankInfoMapper;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ATP球员的排名表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
@Service
public class PlayerRankInfoDalImpl extends BaseAdminService<PlayerRankInfoMapper, PlayerRankInfoEntity> implements IPlayerRankInfoDal {

}
