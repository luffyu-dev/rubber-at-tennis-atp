package com.rubber.at.tennis.atp.api.match.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author luffyu
 * Created on 2023/5/15
 */
@Data
public class WorldMatchScoreRecordDto {


    /**
     * 自增id
     */
    private Integer recordId;

    /**
     * 比赛唯一id
     */
    private String matchId;

    /**
     * a第1盘
     */
    private String aset1Num;

    /**
     * a第2盘
     */
    private String aset2Num;

    /**
     * a第3盘
     */
    private String aset3Num;

    /**
     * a第4盘
     */
    private String aset4Num;

    /**
     * a第5盘
     */
    private String aset5Num;

    /**
     * a实时分数
     */
    private String agamingScore;

    /**
     * b第1盘
     */
    private String bset1Num;

    /**
     * b第2盘
     */
    private String bset2Num;

    /**
     * b第3盘
     */
    private String bset3Num;

    /**
     * b第4盘
     */
    private String bset4Num;

    /**
     * b第5盘
     */
    private String bset5Num;

    /**
     * b实时分数
     */
    private String bgamingScore;

    /**
     * 实时描述
     */
    private String gameingDesc;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private String createTimeStr;


    /**
     * A表示a正在打 B表示a正在打
     */
    private String livingFlagStr;
}
