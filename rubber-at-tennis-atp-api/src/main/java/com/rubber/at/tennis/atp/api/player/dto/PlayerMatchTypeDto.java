package com.rubber.at.tennis.atp.api.player.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luffyu
 * Created on 2023/1/24
 */
@Data
public class PlayerMatchTypeDto {

    /**
     * 比赛名称
     */
    @JsonProperty("mt")
    private String matchType;

    /**
     * 比赛类型名称
     */
    @JsonProperty("mtNa")
    private String matchTypeName;

    /**
     * 比赛logo
     */
    @JsonProperty("mLg")
    private String matchLogo;

    /**
     * 比赛logo灰色
     */
    @JsonProperty("mLgg")
    private String matchLogoGray;

    /**
     * 冠军数量
     */
    @JsonProperty("wNum")
    private Integer wNum = 0;

    /**
     * 半决赛数量
     */
    @JsonProperty("fNum")
    private Integer fNum = 0;

    /**
     * 4强数量
     */
    @JsonProperty("sfNum")
    private Integer sfNum = 0;

    /**
     * 8强数量
     */
    @JsonProperty("qfNum")
    private Integer qfNum = 0;

    /**
     * 其他数量
     */
    @JsonProperty("oNum")
    private Integer oNum = 0;

    /**
     * 总数量
     */
    @JsonProperty("aNum")
    private Integer aNum = 0;


    /**
     * 是否展示冠军列
     */
    @JsonProperty("sc")
    private Integer showChampionHonour;


    /**
     * 是否展示赛事信息
     */
    @JsonProperty("sm")
    private Integer showMatch;


    /**
     * 比赛结果集合
     */
    @JsonProperty("mDeL")
    private List<MatchResultDto> matchResultList = new ArrayList<>();



    @Data
    public static class MatchResultDto{
        /**
         * 年份
         */
        @JsonProperty("y")
        private String matchYear;

        /**
         * 赛事名称
         */
        @JsonProperty("mNa")
        private String matchName;

        /**
         * 赛事结果
         */
        @JsonProperty("mr")
        private String matchResult;


    }


    public void addW(){
        this.wNum++;
        this.aNum++;
    }
    public void addW(int x){
        this.wNum+=x;
        this.aNum+=x;
    }

    public void addF(){
        this.fNum++;
        this.aNum++;
    }

    public void addF(int x){
        this.fNum+=x;
        this.aNum+=x;
    }

    public void addSf(){
        this.sfNum++;
        this.aNum++;
    }

    public void addSf(int x){
        this.sfNum+=x;
        this.aNum+=x;
    }

    public void addQf(){
        this.qfNum++;
        this.aNum++;
    }

    public void addQf(int x){
        this.qfNum+=x;
        this.aNum+=x;
    }

    public void addO(){
        this.oNum++;
        this.aNum++;
    }

    public void addO(int x){
        this.oNum+=x;
        this.aNum+=x;
    }


}
