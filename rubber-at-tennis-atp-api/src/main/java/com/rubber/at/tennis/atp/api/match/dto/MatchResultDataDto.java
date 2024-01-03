package com.rubber.at.tennis.atp.api.match.dto;

import lombok.Data;

import java.util.List;

/**
 * @author luffyu
 * Created on 2023/11/8
 */
@Data
public class MatchResultDataDto {

    /**
     * 名称
     * 全场
     * 第一盘
     * 第二盘
     */
    private String matchSetName;

    /**
     * 数据
     */
    private List<MatchSetBean> setDataList;

    @Data
    public static class MatchSetBean{

        /**
         * 数据名称
         * ACE / 一发得分率
         */
        private String name;

        private String aPlayerData;

        private String bPlayerData;

        private Integer aPlayerPoint;

        private Integer bPlayerPoint;

        private String maxPlayer;

    }




}
