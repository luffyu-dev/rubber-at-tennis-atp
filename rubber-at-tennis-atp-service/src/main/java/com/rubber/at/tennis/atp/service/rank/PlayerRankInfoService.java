package com.rubber.at.tennis.atp.service.rank;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.api.base.SearchQueryRequest;
import com.rubber.at.tennis.atp.api.player.enums.PlayerTypeEnums;
import com.rubber.at.tennis.atp.api.rank.PlayerRankInfoApi;
import com.rubber.at.tennis.atp.api.rank.dto.PlayerRankInfoDto;
import com.rubber.at.tennis.atp.api.rank.response.RankPageResponse;
import com.rubber.at.tennis.atp.api.task.TaskTypeEnums;
import com.rubber.at.tennis.atp.dao.dal.IPlayerRankInfoDal;
import com.rubber.at.tennis.atp.dao.entity.PlayerRankInfoEntity;
import com.rubber.at.tennis.atp.dao.entity.TaskInfoEntity;
import com.rubber.at.tennis.atp.service.task.TaskQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
@Service
public class PlayerRankInfoService implements PlayerRankInfoApi {


    @Resource
    private IPlayerRankInfoDal iPlayerRankInfoDal;

    @Autowired
    private TaskQueryService taskQueryService;


    /**
     * apt球员的分页查询
     *
     * @param request 当前请求
     * @return 返回分页数据
     */
    @Override
    public RankPageResponse<PlayerRankInfoDto> queryAtpRankPage(SearchQueryRequest request) {
        TaskInfoEntity rankTask = taskQueryService.getValidTask(TaskTypeEnums.ATP_RANK);
        Page<PlayerRankInfoEntity> atpRankInfo = queryByPage(request, PlayerTypeEnums.atp, rankTask);
        return convertDto(atpRankInfo,rankTask);
    }

    /**
     * wta球员的分页查询
     *
     * @param request 当前请求
     * @return 返回分页数据
     */
    @Override
    public RankPageResponse<PlayerRankInfoDto> queryWtaRankPage(SearchQueryRequest request) {
        TaskInfoEntity rankTask = taskQueryService.getValidTask(TaskTypeEnums.WTA_RANK);
        Page<PlayerRankInfoEntity> wtaRankInfo = queryByPage(request, PlayerTypeEnums.wta, rankTask);
        return convertDto(wtaRankInfo,rankTask);
    }



    /**
     * 分页查询数据
     * @param queryDto
     * @return
     */
    public Page<PlayerRankInfoEntity> queryByPage(SearchQueryRequest queryDto, PlayerTypeEnums playerTypeEnums, TaskInfoEntity rankTask){
        Page<PlayerRankInfoEntity> page = new Page<>();
        page.setCurrent(queryDto.getPage());
        page.setSize(queryDto.getSize());
        page.setOptimizeCountSql(false);
        page.setSearchCount(false);
        LambdaQueryWrapper<PlayerRankInfoEntity> lambda = new LambdaQueryWrapper<>();
        if (rankTask != null && StrUtil.isNotBlank(rankTask.getDataVersion())){
            lambda.eq(PlayerRankInfoEntity::getDateVersion,rankTask.getDataVersion());
        }
        if (StrUtil.isNotBlank(queryDto.getSearchValue())) {
            lambda.and(wq ->
                    wq.like(PlayerRankInfoEntity::getChinaFullName, "%" + queryDto.getSearchValue() + "%")
                            .or()
                            .like(PlayerRankInfoEntity::getNationChineseName, "%" + queryDto.getSearchValue() + "%")
            );
        }
        lambda.eq(PlayerRankInfoEntity::getPlayerType,playerTypeEnums.toString())
                .orderByAsc(PlayerRankInfoEntity::getRank);
        iPlayerRankInfoDal.page(page,lambda);
        return page;
    }


    /**
     * 通过球员id查询最新的排名信息
     */
    public Map<String, PlayerRankInfoEntity> queryRankInfo(List<String> playerIds,TaskTypeEnums taskTypeEnums){
        TaskInfoEntity rankTask = taskQueryService.getValidTask(taskTypeEnums);
        if (rankTask == null || StrUtil.isEmpty(rankTask.getDataVersion())){
            return new HashMap<>();
        }
        LambdaQueryWrapper<PlayerRankInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PlayerRankInfoEntity::getDateVersion,rankTask.getDataVersion())
                .in(PlayerRankInfoEntity::getPlayerId,playerIds);

        List<PlayerRankInfoEntity> list = iPlayerRankInfoDal.list(lqw);
        if (CollUtil.isEmpty(list)){
            return new HashMap<>();
        }
        return list.stream().collect(Collectors.toMap(PlayerRankInfoEntity::getPlayerId,i->i,(o1,o2)->o1));
    }


    /**
     * 对象转换
     */
    private RankPageResponse<PlayerRankInfoDto> convertDto(Page<PlayerRankInfoEntity> page, TaskInfoEntity rankTask){
        RankPageResponse<PlayerRankInfoDto> dtoResultPage = new RankPageResponse<>();
        dtoResultPage.setCurrent(page.getCurrent());
        dtoResultPage.setPages(page.getPages());
        dtoResultPage.setSize(page.getSize());
        dtoResultPage.setTotal(page.getTotal());
        if (CollUtil.isNotEmpty(page.getRecords())){
            dtoResultPage.setRecords(
                    page.getRecords().stream().map(i->{
                        PlayerRankInfoDto dto = new PlayerRankInfoDto();
                        BeanUtils.copyProperties(i,dto);
                        return dto;
                    }).collect(Collectors.toList())
            );
        }
        if (rankTask != null){
            dtoResultPage.setModifyTime(rankTask.getUpdateTime());
            dtoResultPage.setModifyTimeStr(DateUtil.format(rankTask.getUpdateTime(),"yyyy-MM-dd HH:mm"));
        }
        return dtoResultPage;
    }

}
