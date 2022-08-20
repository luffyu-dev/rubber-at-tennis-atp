package com.rubber.at.tennis.map.dao.dal.impl;

import com.rubber.at.tennis.map.dao.entity.TaskInfoEntity;
import com.rubber.at.tennis.map.dao.mapper.TaskInfoMapper;
import com.rubber.at.tennis.map.dao.dal.ITaskInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务记录表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-15
 */
@Service
public class TaskInfoDalImpl extends BaseAdminService<TaskInfoMapper, TaskInfoEntity> implements ITaskInfoDal {

}
