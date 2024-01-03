package com.rubber.at.tennis.atp.api.player.dto;

import lombok.Data;

import java.util.List;

/**
 * @author luffyu
 * Created on 2023/11/4
 */
@Data
public class PlayerH2HDto {


    /**
     * a球员的信息
     */
    private String aPlayerId;

    private String aPlayerName;

    private String aPlayerWinNum;

    private String aPlayerWinPoint;


    /**
     * b球员的信息
     */
    private String bPlayerId;

    private String bPlayerName;

    private String bPlayerWinNum;

    private String bPlayerWinPoint;




    private List<OldMatchDto> oldMatchList;



    @Data
    public static class OldMatchDto{

        /**
         * 年份
         */
        private String year;

        /**
         * 赛事级别
         */
        private String level;

        /**
         * 场地类型
         */
        private String  courtTypeName;

        /**
         * 赛事
         */
        private String  matchName;

        /**
         * 轮次
         */
        private String  round;

        /**
         * 结果
         */
        private String  result;

        /**
         * 比分
         */
        private String  source;
    }

}
