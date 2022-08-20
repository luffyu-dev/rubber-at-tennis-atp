package com.rubber.at.tennis.map.service.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rubber.at.tennis.map.api.task.TaskTypeEnums;
import com.rubber.at.tennis.map.dao.dal.ITaskInfoDal;
import com.rubber.at.tennis.map.dao.entity.TaskInfoEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
@Service
public class TaskQueryService {


    @Resource
    private ITaskInfoDal iTaskInfoDal;


    /**
     * 获取有效的任务
     */
    public TaskInfoEntity getValidTask(TaskTypeEnums taskTypeEnums){
        QueryWrapper<TaskInfoEntity> qw = new QueryWrapper<>();
        qw.lambda().eq(TaskInfoEntity::getTaskType,taskTypeEnums.toString())
                .eq(TaskInfoEntity::getStatus,1)
                .orderByDesc(TaskInfoEntity::getId)
                .last(" limit 1");
        return iTaskInfoDal.getOne(qw);
    }
}
