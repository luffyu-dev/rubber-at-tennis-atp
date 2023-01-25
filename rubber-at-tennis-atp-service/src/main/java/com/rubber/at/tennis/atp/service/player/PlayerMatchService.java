package com.rubber.at.tennis.atp.service.player;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.rubber.at.tennis.atp.api.player.dto.PlayerGrandSlamDto;
import com.rubber.at.tennis.atp.api.player.dto.PlayerMatchTypeDto;
import com.rubber.at.tennis.atp.api.player.enums.MatchResultEnums;
import com.rubber.at.tennis.atp.api.player.enums.MatchTypeEnums;
import com.rubber.at.tennis.atp.api.player.enums.PlayerTypeEnums;
import com.rubber.at.tennis.atp.api.player.response.PlayerMatchResultResponse;
import com.rubber.at.tennis.atp.dao.dal.IMatchTypeDal;
import com.rubber.at.tennis.atp.dao.dal.IPlayerMatchResultDal;
import com.rubber.at.tennis.atp.dao.entity.MatchTypeEntity;
import com.rubber.at.tennis.atp.dao.entity.PlayerMatchInfoEntity;
import com.rubber.at.tennis.atp.dao.entity.PlayerMatchResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luffyu
 * Created on 2023/1/24
 */
@Slf4j
@Service
public class PlayerMatchService {

    @Autowired
    private IPlayerMatchResultDal iPlayerMatchResultDal;


    @Autowired
    private IMatchTypeDal iMatchTypeDal;


    /**
     * 传销球员结果信息
     */
    public PlayerMatchResultResponse queryPlayerMatchResult(String playerId){
        PlayerMatchResultResponse response = new PlayerMatchResultResponse();
        // 比赛类型
        List<MatchTypeEntity> matchTypeList = iMatchTypeDal.queryByGroupId("#");
        Map<String,MatchTypeEntity> matchTypeDist = matchTypeList.stream().collect(Collectors.toMap(MatchTypeEntity::getMatchType,i->i,(o1,o2)->o1));

        // 球员比赛信息
        List<PlayerMatchResultEntity> playerMatchInfoList = iPlayerMatchResultDal.queryPlayerMatch(playerId);
        //按照类型进行分组
        Map<String, List<PlayerMatchResultEntity>> matchInfoDist = playerMatchInfoList.stream().collect(Collectors.groupingBy(PlayerMatchResultEntity::getMatchType));

        // 大满贯数据信息
        Map<String, PlayerGrandSlamDto> playerGrandSlamDict = new HashMap<>(8);


        List<PlayerMatchTypeDto> allMatchResultList = new ArrayList<>();

        PlayerMatchTypeDto grandSlamMatch = null;
        // 按照比赛类型进行分组
        for (MatchTypeEnums matchTypeEnums:MatchTypeEnums.values()){

            MatchTypeEntity matchType = matchTypeDist.get(matchTypeEnums.getType());
            if (matchType == null){
                continue;
            }

            List<PlayerMatchResultEntity> matchInfoList = matchInfoDist.get(matchTypeEnums.getType());

            PlayerMatchTypeDto matchTypeDto = createMatchTypeDto(matchType,matchInfoList,playerGrandSlamDict);
            if (MatchTypeEnums.GRAND_SLAM.equals(matchTypeEnums)){
                grandSlamMatch = matchTypeDto;
            }else {
                margeGrandSlam(grandSlamMatch,matchTypeDto);
            }
            allMatchResultList.add(matchTypeDto);
        }
        response.setGrandSlamResultList(new ArrayList<>(playerGrandSlamDict.values()));
        response.setAllMatchResultList(allMatchResultList);
        return response;
    }



    /**
     * 创建比赛数据
     */
    private PlayerMatchTypeDto createMatchTypeDto(MatchTypeEntity matchType,List<PlayerMatchResultEntity> matchInfoList
            ,Map<String, PlayerGrandSlamDto> playerGrandSlamDict){
        PlayerMatchTypeDto matchTypeDto = new PlayerMatchTypeDto();
        matchTypeDto.setMatchType(matchType.getMatchType());
        matchTypeDto.setMatchTypeName(matchType.getMatchTypeName());
        matchTypeDto.setMatchLogo(matchType.getMatchLogo());
        MatchTypeEnums matchTypeEnums = MatchTypeEnums.getByType(matchType.getMatchType());
        if (matchTypeEnums != null){
            matchTypeDto.setShowChampionHonour(matchTypeEnums.getShowChampionHonour());
            matchTypeDto.setShowMatch(matchTypeEnums.getShowMatch());
        }
        if (CollUtil.isEmpty(matchInfoList)){
            return matchTypeDto;
        }
        List<PlayerMatchTypeDto.MatchResultDto> resultDtoList = new ArrayList<>();
        for (PlayerMatchResultEntity matchInfo:matchInfoList){
            // 创建大满贯数据
            createGrandSlamDto(matchInfo,playerGrandSlamDict);
            // 循环记录比赛结果数据
            PlayerMatchTypeDto.MatchResultDto resultDto = new PlayerMatchTypeDto.MatchResultDto();
            BeanUtils.copyProperties(matchInfo,resultDto);
            if (StrUtil.isEmpty(matchInfo.getMatchResult())){
                continue;
            }
            if (MatchResultEnums.W.getResult().equals(matchInfo.getMatchResult())){
                matchTypeDto.addW();
                resultDtoList.add(resultDto);
            }else if (MatchResultEnums.F.getResult().equals(matchInfo.getMatchResult())){
                matchTypeDto.addF();
                resultDtoList.add(resultDto);
            } else if (MatchResultEnums.SF.getResult().equals(matchInfo.getMatchResult())){
                matchTypeDto.addSf();
                resultDtoList.add(resultDto);
            } else if (MatchResultEnums.QF.getResult().equals(matchInfo.getMatchResult())){
                matchTypeDto.addQf();
                resultDtoList.add(resultDto);
            } else if (matchInfo.getMatchResult().startsWith("Attend")){
                String num = matchInfo.getMatchResult().substring(matchInfo.getMatchResult().lastIndexOf(":")+1);
//                matchTypeDto.addO(Integer.parseInt(num));
            }
        }
        matchTypeDto.setMatchResultList(resultDtoList);
        return matchTypeDto;
    }


    /**
     * 创建一个大满贯数据对象
     */
    private void createGrandSlamDto(PlayerMatchResultEntity matchInfo, Map<String, PlayerGrandSlamDto> map){
        MatchTypeEnums bgm = MatchTypeEnums.isBgmByType(matchInfo.getMatchType());
        if (bgm == null){
            return;
        }
        PlayerGrandSlamDto grandSlamDto = map.getOrDefault(matchInfo.getMatchYear(),new PlayerGrandSlamDto());
        grandSlamDto.setYear(matchInfo.getMatchYear());
        switch (bgm){
            case AO:
                grandSlamDto.setAoResult(matchInfo.getMatchResult());
                break;
            case RG:
                grandSlamDto.setRgResult(matchInfo.getMatchResult());
                break;
            case UO:
                grandSlamDto.setUoResult(matchInfo.getMatchResult());
                break;
            case WC:
                grandSlamDto.setWcResult(matchInfo.getMatchResult());
                break;
            default:
                break;
        }
        map.put(matchInfo.getMatchYear(),grandSlamDto);
    }


    private void margeGrandSlam(PlayerMatchTypeDto grandSlamMatch,PlayerMatchTypeDto singleMatch){
        if (MatchTypeEnums.isBgmByType(singleMatch.getMatchType()) == null){
            return;
        }
        if (grandSlamMatch == null){
            return;
        }
        grandSlamMatch.addW(singleMatch.getWNum());
        grandSlamMatch.addF(singleMatch.getFNum());
        grandSlamMatch.addSf(singleMatch.getSfNum());
        grandSlamMatch.addQf(singleMatch.getQfNum());
        grandSlamMatch.addO(singleMatch.getONum());
        grandSlamMatch.getMatchResultList().addAll(singleMatch.getMatchResultList());
        singleMatch.setMatchResultList(null);
    }
}
