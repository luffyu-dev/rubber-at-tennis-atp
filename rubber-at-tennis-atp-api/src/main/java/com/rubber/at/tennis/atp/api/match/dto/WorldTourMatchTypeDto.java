package com.rubber.at.tennis.atp.api.match.dto;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luffyu
 * Created on 2023/4/1
 *
 * 世界巡回比赛详情
 *
 */
@Data
public class WorldTourMatchTypeDto {

    /**
     * 循环年份
     */
    private String tourYear;

    /**
     * 赛事类型
     */
    private String tourId;


    /**
     * 赛事类型，关联的类型名称
     */
    private String tourName;

    /**
     * 比赛名称
     */
    private String title;

    /**
     * 比赛标题
     */
    private String subtitle;


    /**
     * logo
     */
    private String logo;

    /**
     * logo多个
     */
    private List<String> logoList;

    /**
     * 赛事大图
     */
    private String homeImg;




    /**
     * 赛事简介
     */
    private String introduction;


    /**
     * 比赛的天数
     */
    private List<String>  matchDays;


    /**
     * 默认比赛时间
     */
    private String defaultMatchDay;


    /**
     * 开始时间
     */
    private Date beginTime;


    /**
     * 结束时间
     */
    private Date endTime;


    /**
     * 比赛状态
     * 进行中
     * 已结束
     * 未开始
     */
    private Integer status;


    /**
     * 比赛轮次信息
     */
    private List<MatchRoundDto> matchRoundList;

    /**
     * 比赛日期信息
     */
    private List<NormalDescDto> matchDayList;



    /**
     * 推荐标记
     * 1表示走推荐
     * 0表示不走推荐
     */
    private Integer recommendFlag;


    /**
     * 历史的比赛数据
     * key为atp类型
     */
    private Map<String, List<PlayerTourMatchDto>> historyMatchResult;

}
