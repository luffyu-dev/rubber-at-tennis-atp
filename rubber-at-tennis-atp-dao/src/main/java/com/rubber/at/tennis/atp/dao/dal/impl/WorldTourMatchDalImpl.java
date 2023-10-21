package com.rubber.at.tennis.atp.dao.dal.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.atp.dao.entity.WorldTourMatchEntity;
import com.rubber.at.tennis.atp.dao.mapper.WorldTourMatchMapper;
import com.rubber.at.tennis.atp.dao.dal.IWorldTourMatchDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 世界巡回表详情表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2023-04-01
 */
@Service
public class WorldTourMatchDalImpl extends BaseAdminService<WorldTourMatchMapper, WorldTourMatchEntity> implements IWorldTourMatchDal {

    @Override
    public WorldTourMatchEntity getById(String tourId,String year) {
        LambdaQueryWrapper<WorldTourMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(WorldTourMatchEntity::getTourId,tourId)
                .eq(WorldTourMatchEntity::getTourYear,year);
        return getOne(lqw);
    }

    @Override
    public List<WorldTourMatchEntity> queryByKeys(String tourId, String year,List<Integer> status) {
        LambdaQueryWrapper<WorldTourMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(WorldTourMatchEntity::getTourYear,year);
        if (StrUtil.isNotEmpty(tourId)){
            lqw.eq(WorldTourMatchEntity::getTourId,tourId);
        }
        if (CollUtil.isNotEmpty(status)){
            lqw.in(WorldTourMatchEntity::getStatus,status);
        }
        lqw.orderByAsc(WorldTourMatchEntity::getBeginTime);
        return list(lqw);
    }

    /**
     * @param year
     * @param status
     * @return
     */
    @Override
    public List<WorldTourMatchEntity> queryRecommendList(String year, List<Integer> status) {
        LambdaQueryWrapper<WorldTourMatchEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(WorldTourMatchEntity::getTourYear,year)
                .eq(WorldTourMatchEntity::getRecommendFlag,1);

        if (CollUtil.isNotEmpty(status)){
            lqw.in(WorldTourMatchEntity::getStatus,status);
        }
        lqw.orderByDesc(WorldTourMatchEntity::getBeginTime);
        return list(lqw);
    }
}
