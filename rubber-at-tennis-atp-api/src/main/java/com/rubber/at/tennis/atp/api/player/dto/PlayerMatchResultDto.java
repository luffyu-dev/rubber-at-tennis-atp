package com.rubber.at.tennis.atp.api.player.dto;


import lombok.Data;

/**
 * <p>
 * 比赛详情表
 * </p>
 *
 * @author rockyu
 * @since 2023-01-24
 */
@Data
public class PlayerMatchResultDto  {


    /**
     * 自增id
     */
    private Integer id;

    /**
     * 运动员id
     */
    private String playerId;

    /**
     * 赛事类型
     */
    private String matchType;

    /**
     * 赛事名称
     */
    private String matchName;

    /**
     * 赛事年份
     */
    private String matchYear;

    /**
     * 比赛结果
     */
    private String matchResult;

    /**
     * 备注
     */
    private String remark;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 状态 1表示正常
     */
    private Integer status;


}
