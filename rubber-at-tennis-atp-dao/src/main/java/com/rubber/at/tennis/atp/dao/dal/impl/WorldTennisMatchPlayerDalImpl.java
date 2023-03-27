package com.rubber.at.tennis.atp.dao.dal.impl;

import com.rubber.at.tennis.atp.dao.entity.WorldTennisMatchPlayerEntity;
import com.rubber.at.tennis.atp.dao.mapper.WorldTennisMatchPlayerMapper;
import com.rubber.at.tennis.atp.dao.dal.IWorldTennisMatchPlayerDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 世界网球比赛球员表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-03-24
 */
@Service
public class WorldTennisMatchPlayerDalImpl extends BaseAdminService<WorldTennisMatchPlayerMapper, WorldTennisMatchPlayerEntity> implements IWorldTennisMatchPlayerDal {

}
