package com.rubber.at.tennis.atp.dao.dal.impl;

import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchLivingDataEntity;
import com.rubber.at.tennis.atp.dao.mapper.WorldTennisMatchLivingDataMapper;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchLivingDataDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 世界网球比赛数据 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-11-09
 */
@Service
public class WorldTennisMatchLivingDataDalImpl extends BaseAdminService<WorldTennisMatchLivingDataMapper, WorldTennisMatchLivingDataEntity> implements IWorldTennisMatchLivingDataDal {

}
