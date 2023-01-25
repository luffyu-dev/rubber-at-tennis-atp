package com.rubber.at.tennis.atp.service.player;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.atp.api.player.request.PagePlayerIdRequest;
import com.rubber.at.tennis.atp.api.player.request.PlayerIdRequest;
import com.rubber.at.tennis.atp.api.base.SearchQueryRequest;
import com.rubber.at.tennis.atp.api.player.PlayerInfoQueryApi;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDetail;
import com.rubber.at.tennis.atp.api.player.dto.PlayerInfoDto;
import com.rubber.at.tennis.atp.api.player.enums.PlayerTypeEnums;
import com.rubber.at.tennis.atp.api.player.response.PlayerMatchResultResponse;
import com.rubber.at.tennis.atp.api.rank.dto.PlayerRankInfoDto;
import com.rubber.at.tennis.atp.api.task.TaskTypeEnums;
import com.rubber.at.tennis.atp.dao.condition.FollowPlayerCondition;
import com.rubber.at.tennis.atp.dao.dal.IPlayerInfoDal;
import com.rubber.at.tennis.atp.dao.entity.PlayerInfoEntity;
import com.rubber.at.tennis.atp.dao.entity.PlayerRankInfoEntity;
import com.rubber.at.tennis.atp.service.common.exception.ErrorCodeEnums;
import com.rubber.at.tennis.atp.service.common.exception.RubberServiceException;
import com.rubber.at.tennis.atp.service.rank.PlayerRankInfoService;
import com.rubber.base.components.util.result.page.ResultPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    private UserFollowPlayerApplyService userFollowPlayerApplyService;

    @Autowired
    private PlayerMatchService playerMatchService;


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
        ResultPage<PlayerInfoDto> resultPage =  convertDtoBatch(page,nowPlayerRank);
        // 查询是否有关注
        handlerFollowed(request,resultPage);
        return resultPage;

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
        ResultPage<PlayerInfoDto> resultPage =  convertDtoBatch(page,nowPlayerRank);
        // 查询是否有关注
        handlerFollowed(request,resultPage);

        return resultPage;
    }

    /**
     * 球员的基本信息
     *
     * @param playerIdRequest 球员id
     * @return 返回球员的基本信息
     */
    @Override
    public PlayerInfoDetail getPlayerDetail(PlayerIdRequest playerIdRequest) {
        // 球员信息
        PlayerInfoEntity playerInfoEntity = getByPlayerId(playerIdRequest.getPlayerId());
        if (playerInfoEntity == null){
            throw new RubberServiceException(ErrorCodeEnums.PLAYER_NOT_EXIST);
        }

        // 结果集合
        PlayerInfoDetail playerInfoDetail = new PlayerInfoDetail();
        BeanUtils.copyProperties(playerInfoEntity,playerInfoDetail);

        TaskTypeEnums taskTypeEnums = TaskTypeEnums.ATP_RANK;
        if (PlayerTypeEnums.wta.toString().equals(playerInfoEntity.getPlayerType())){
            taskTypeEnums = TaskTypeEnums.WTA_RANK;
        }
        // 查询本周排名
        PlayerRankInfoEntity newRank = playerRankInfoService.getPlayerNewRank(playerIdRequest.getPlayerId(),taskTypeEnums);
        if (newRank != null){
            PlayerRankInfoDto rankInfoDto = new PlayerRankInfoDto();
            BeanUtils.copyProperties(newRank,rankInfoDto);
            playerInfoDetail.setWeekRankInfo(rankInfoDto);
            // 查询历史信息
//            List<PlayerRankInfoEntity> oldRankList = playerRankInfoService.queryAllRankByPlayerId(playerIdRequest.getPlayerId());
//            if (CollUtil.isNotEmpty(oldRankList)){
//                playerInfoDetail.setOldRankList(
//                    oldRankList.stream().map(i->{
//                        PlayerRankInfoDto dto = new PlayerRankInfoDto();
//                        BeanUtils.copyProperties(i,dto);
//                        return dto;
//                    }).collect(Collectors.toList())
//                );
//            }
        }

        // 查询是否关注
        if (playerIdRequest.getUid() != null){
            playerInfoDetail.setFollowed(userFollowPlayerApplyService.isFollowed(playerIdRequest));
        }
        return playerInfoDetail;
    }

    /**
     * 查询历史排名信息
     *
     * @param playerIdRequest
     * @return 返回排名信息
     */
    @Override
    public PlayerMatchResultResponse queryAllMatchResult(PlayerIdRequest playerIdRequest) {
        return playerMatchService.queryPlayerMatchResult(playerIdRequest.getPlayerId());
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
        // 只查询关注
        if (request.isJustFollow()){
            FollowPlayerCondition condition = new FollowPlayerCondition();
            condition.setUid(request.getUid());
            condition.setSearchValue(request.getSearchValue());
            return iPlayerInfoDal.queryFollowPlayer(page,condition);
        }

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
     * 查询单个球员信息
     */
    private PlayerInfoEntity getByPlayerId(String playerId){
        LambdaQueryWrapper<PlayerInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PlayerInfoEntity::getPlayerId,playerId);
        return iPlayerInfoDal.getOne(lqw);
    }


    /**
     * 对象转换
     * @param page 分页信息
     * @param nowPlayerRank 当前排名信息
     * @return 返回分页结果
     */
    private ResultPage<PlayerInfoDto> convertDtoBatch(Page<PlayerInfoEntity> page,Map<String, PlayerRankInfoEntity> nowPlayerRank){
        ResultPage<PlayerInfoDto> dtoResultPage = new ResultPage<>();
        dtoResultPage.setCurrent(page.getCurrent());
        dtoResultPage.setPages(page.getPages());
        dtoResultPage.setSize(page.getSize());
        dtoResultPage.setTotal(page.getTotal());

        if (CollUtil.isNotEmpty(page.getRecords())){
            dtoResultPage.setRecords(
                    page.getRecords().stream().map(i->{
                        PlayerRankInfoEntity rankInfo = nowPlayerRank.get(i.getPlayerId());
                        return   convertDto(i,rankInfo);
                    }).collect(Collectors.toList())
            );
        }
        return dtoResultPage;
    }


    /**
     * 对象转换
     * @param playerInfo 球员信息
     * @param rankInfo 当前的排名信息
     * @return
     */
    private PlayerInfoDto convertDto(PlayerInfoEntity playerInfo,PlayerRankInfoEntity rankInfo){
        PlayerInfoDto dto = new PlayerInfoDto();
        BeanUtils.copyProperties(playerInfo,dto);
        if (rankInfo != null){
            PlayerRankInfoDto rankInfoDto = new PlayerRankInfoDto();
            BeanUtils.copyProperties(rankInfo,rankInfoDto);
            dto.setWeekRankInfo(rankInfoDto);
        }
        return dto;
    }

    /**
     * 处理是否关注
     */
    private void handlerFollowed(SearchQueryRequest request,ResultPage<PlayerInfoDto> resultPage){
        if (CollUtil.isEmpty(resultPage.getRecords())){
            return;
        }
        // 查询是否有关注
        if (request.isJustFollow()){
           for (PlayerInfoDto playerInfoDto:resultPage.getRecords()){
               playerInfoDto.setFollowed(true);
           }
        }else if (request.getUid() != null){
            Set<String> followPlayer = userFollowPlayerApplyService.queryUserFollowPlayer(request);
            for (PlayerInfoDto playerInfoDto:resultPage.getRecords()){
                playerInfoDto.setFollowed(followPlayer.contains(playerInfoDto.getPlayerId()));
            }
        }
    }
}
