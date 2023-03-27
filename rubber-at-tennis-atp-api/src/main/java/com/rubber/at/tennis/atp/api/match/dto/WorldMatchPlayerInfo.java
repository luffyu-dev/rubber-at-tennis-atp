package com.rubber.at.tennis.atp.api.match.dto;

import lombok.Data;


/**
 * @author luffyu
 * Created on 2023/3/24
 */
@Data
public class WorldMatchPlayerInfo {


    /**
     * 自增id
     */
    private Integer id;

    /**
     * 比赛唯一id
     */
    private String matchId;

    /**
     * 比赛分组 A 和 B
     */
    private String playerIndex;

    /**
     * 是否赢了 0表示未知  1表示赢了 2表示输了
     */
    private Integer winFlag;

    /**
     * 球员赔率
     */
    private String playerOdds;

    /**
     * 球员id
     */
    private String playerId;

    /**
     * 球员名称
     */
    private String playerName;

    /**
     * 球员排名
     */
    private String playerRank;

    /**
     * 球员伙伴id
     */
    private String partnerId;

    /**
     * 球员伙伴名称
     */
    private String partnerName;

    private String partnerRank;

    /**
     * 第1盘
     */
    private String set1Num;

    /**
     * 第2盘
     */
    private String set2Num;

    /**
     * 第3盘
     */
    private String set3Num;

    /**
     * 第4盘
     */
    private String set4Num;

    /**
     * 第5盘
     */
    private String set5Num;

    /**
     * 实时分数
     */
    private String gamingScore;

    /**
     * 实时描述
     */
    private String gameingDesc;

    /**
     * 比赛中的描述
     */
    private Integer livingFlag;
}
