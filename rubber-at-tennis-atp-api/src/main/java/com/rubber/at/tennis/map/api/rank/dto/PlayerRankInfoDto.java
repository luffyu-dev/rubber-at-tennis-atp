package com.rubber.at.tennis.map.api.rank.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
@Data
public class PlayerRankInfoDto {



    /**
     * 运动员id
     */
    private String playerId;

    /**
     * 运动员id
     */
    private String thirdId;

    /**
     * 球员类型 atp/wta
     */
    private String playerType;

    /**
     * 中文全称
     */
    private String chinaFullName;

    /**
     * 国籍名称（中）
     */
    private String nationChineseName;

    /**
     * 国籍图片
     */
    private String nationImg;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 本周失效积分
     */
    private Integer flopPoint;

    /**
     * 本周新增积分
     */
    private Integer winPoint;

    /**
     * 排名
     */
    private Integer rank;

    /**
     * 积分
     */
    private Integer rankChange;

    /**
     * 官方排名
     */
    private Integer officialRank;

    /**
     * 最佳排名
     */
    private Integer highestRank;

    /**
     * 胜率
     */
    private String cycleWinPro;

    /**
     * 胜场
     */
    private Integer cycleWinNum;

    /**
     * 负场
     */
    private Integer cycleLoseNum;

    /**
     * 奖金
     */
    private String cycleBonus;

    /**
     * 日期版本
     */
    private String dateVersion;

    /**
     * 备注
     */
    private String remark;

}
