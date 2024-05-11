package com.rubber.at.tennis.atp.api.vote.dto;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2024/5/1
 */
@Data
public class VoteInfoDto {

    /**
     * 投票key
     */
    private String voteKey;

    /**
     * 投票标题
     */
    private String voteTitle;

    /**
     * a观点
     */
    private String aPoint;

    /**
     * b观点
     */
    private String bPoint;

    /**
     * a结果
     */
    private Integer aNum = 0;

    /**
     * b结果
     */
    private Integer bNum = 0;


    /**
     * a的占比
     */
    private Integer aPer;


    /**
     * b的占比
     */
    private Integer bPer;

    /**
     * 总投票数
     */
    private Integer allNum = 0;


    /**
     * 用户是否选择的标记
     * 1表示已选择，0表示没有
     */
    private Integer userSelectFlag = 0 ;


    /**
     * 用户选择的观点
     */
    private String userSelectPoint;



}
