package com.rubber.at.tennis.atp.service.player;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.api.base.SearchQueryRequest;
import com.rubber.at.tennis.atp.api.player.PlayerInfoQueryApi;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDto;
import com.rubber.at.tennis.atp.api.player.enums.PlayerTypeEnums;
import com.rubber.at.tennis.atp.api.rank.dto.PlayerRankInfoDto;
import com.rubber.at.tennis.atp.api.task.TaskTypeEnums;
import com.rubber.at.tennis.atp.dao.dal.IPlayerInfoDal;
import com.rubber.at.tennis.atp.dao.dal.IPlayerRankInfoDal;
import com.rubber.at.tennis.atp.dao.entity.PlayerInfoEntity;
import com.rubber.at.tennis.atp.dao.entity.PlayerRankInfoEntity;
import com.rubber.at.tennis.atp.dao.entity.TaskInfoEntity;
import com.rubber.at.tennis.atp.service.rank.PlayerRankInfoService;
import com.rubber.at.tennis.atp.service.task.TaskQueryService;
import com.rubber.base.components.util.result.page.ResultPage;
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
public class PlayerInfoQueryService implements PlayerInfoQueryApi {


    @Autowired
    private IPlayerInfoDal iPlayerInfoDal;

    @Autowired
    private PlayerRankInfoService playerRankInfoService;


    /**
     * apt球员的分页查询
     *
     * @param request 当前请求
     * @return 返回分页数据
     */
    @Override
    public ResultPage<PlayerInfoDto> queryAtpInfoPage(SearchQueryRequest request) {
        // 球员分页查询
        Page<PlayerInfoEntity> page = queryByPage(request,PlayerTypeEnums.atp);
        // 排名查询
        Map<String, PlayerRankInfoEntity> nowPlayerRank = new HashMap<>();
        if (CollUtil.isNotEmpty(page.getRecords())){
            List<String> playerIds = page.getRecords().stream().map(PlayerInfoEntity::getPlayerId).collect(Collectors.toList());
            nowPlayerRank = playerRankInfoService.queryRankInfo(playerIds,TaskTypeEnums.ATP_RANK);
        }
        return convertDto(page,nowPlayerRank);

    }

    /**
     * wta球员的分页查询
     *
     * @param request 当前请求
     * @return 返回分页数据
     */
    @Override
    public ResultPage<PlayerInfoDto> queryWtaInfoPage(SearchQueryRequest request) {
        // 球员分页查询
        Page<PlayerInfoEntity> page = queryByPage(request,PlayerTypeEnums.wta);
        // 排名查询
        Map<String, PlayerRankInfoEntity> nowPlayerRank = new HashMap<>();
        if (CollUtil.isNotEmpty(page.getRecords())){
            List<String> playerIds = page.getRecords().stream().map(PlayerInfoEntity::getPlayerId).collect(Collectors.toList());
            nowPlayerRank = playerRankInfoService.queryRankInfo(playerIds,TaskTypeEnums.WTA_RANK);
        }
        return convertDto(page,nowPlayerRank);
    }


    /**
     * 查询球员的分页信息
     * @param request 当前的请求
     * @return
     */
    private Page<PlayerInfoEntity> queryByPage(SearchQueryRequest request, PlayerTypeEnums playerTypeEnums){
        Page<PlayerInfoEntity> page = new Page<>();
        page.setCurrent(request.getPage());
        page.setSize(request.getSize());
        page.setSearchCount(false);
        LambdaQueryWrapper<PlayerInfoEntity> lqw = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(request.getSearchValue())) {
            lqw.like(PlayerInfoEntity::getChinaFullName, "%" + request.getSearchValue() + "%")
                    .or()
                    .like(PlayerInfoEntity::getNationChineseName, "%" + request.getSearchValue() + "%");
        }
        lqw.eq(PlayerInfoEntity::getPlayerType,playerTypeEnums.toString())
                .orderByDesc(PlayerInfoEntity::getSeqWeight);
        iPlayerInfoDal.page(page,lqw);
        return page;
    }


    /**
     * 对象转换
     * @param page 分页信息
     * @param nowPlayerRank 当前排名信息
     * @return 返回分页结果
     */
    private ResultPage<PlayerInfoDto> convertDto(Page<PlayerInfoEntity> page,Map<String, PlayerRankInfoEntity> nowPlayerRank){
        ResultPage<PlayerInfoDto> dtoResultPage = new ResultPage<>();
        dtoResultPage.setCurrent(page.getCurrent());
        dtoResultPage.setPages(page.getPages());
        dtoResultPage.setSize(page.getSize());
        dtoResultPage.setTotal(page.getTotal());

        if (CollUtil.isNotEmpty(page.getRecords())){
            dtoResultPage.setRecords(
                    page.getRecords().stream().map(i->{
                        PlayerInfoDto dto = new PlayerInfoDto();
                        BeanUtils.copyProperties(i,dto);
                        PlayerRankInfoEntity rankInfo = nowPlayerRank.get(i.getPlayerId());
                        if (rankInfo != null){
                            PlayerRankInfoDto rankInfoDto = new PlayerRankInfoDto();
                            BeanUtils.copyProperties(rankInfo,rankInfoDto);
                            dto.setWeekRankInfo(rankInfoDto);
                        }
                        return dto;
                    }).collect(Collectors.toList())
            );
        }
        return dtoResultPage;
    }
}
