package com.rubber.at.tennis.atp.api.match.dto;

import com.rubber.at.tennis.atp.api.player.dto.PlayerH2HDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author luffyu
 * Created on 2023/3/24
 */
@Data
public class WorldMatchInfo {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 比赛唯一id
     */
    private String matchId;

    /**
     * 比赛所在年份
     */
    private String matchYear;

    /**
     * 比赛所在天
     */
    private String matchDay;

    /**
     * 赛事类型
     */
    private String matchTypeId;

    /**
     * 赛事类型，关联的类型名称
     */
    private String matchTypeName;

    /**
     * 比赛code，关联的比赛实时数据code
     */
    private String matchCode;

    /**
     * 比赛名称
     */
    private String matchName;

    /**
     * 所属球场名称
     */
    private String courtName;

    /**
     * 比赛描述 男单 女单 男双 女双
     */
    private String matchGender;

    /**
     * 比赛轮次
     */
    private String matchRound;

    /**
     * 是否双打 0表示单打 1表示双打
     */
    private Integer doubleFlag;

    /**
     * 比赛开始时间
     */
    private Date matchTime;

    /**
     * 比赛开始时间描述
     */
    private String matchTimeStr;

    /**
     * 比赛结束时间
     */
    private Date matchEndTime;

    /**
     * 状态 比赛状态 0表示未知 1表示未进行 2表示进行中 3表示已结束
     */
    private Integer matchStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 比赛中的描述信息
     */
    private String livingDesc;

    /**
     * 比赛使用的时间
     */
    private String usedTime;


    /**
     * 优先级
     */
    private Integer seq;

    /**
     * 是否推荐
     */
    private Integer recommendFlag;


    /**
     * 球员信息A
     */
    private WorldMatchPlayerInfo aPlayer;

    /**
     * 球员信息B
     */
    private WorldMatchPlayerInfo bPlayer;


    /**
     * 当前是第几盘
     */
    private Integer nowSet;

    /**
     * 比赛信息
     */
    private List<WorldMatchScoreRecordDto> recordDtoList;


    /**
     * H2H的数据信息
     */
    private PlayerH2HDto h2HDto;


    /**
     * 比赛数据结果
     */
    private List<MatchResultDataDto> livingDataList;
}
