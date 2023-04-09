package com.rubber.at.tennis.atp.dao.dal;

import com.rubber.at.tennis.atp.dao.entity.WorldTourMatchEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

import java.util.List;

/**
 * <p>
 * 世界巡回表详情表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2023-04-01
 */
public interface IWorldTourMatchDal extends IBaseAdminService<WorldTourMatchEntity> {



    WorldTourMatchEntity getById(String tourId,String year);



    List<WorldTourMatchEntity> queryByKeys(String tourId,String year,List<Integer> status);

}
