package com.rubber.at.tennis.atp.api.match.dto;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author luffyu
 * Created on 2024/4/6
 */
@Data
public class DayInfoDto {

    private String dateKey;

    private String dateDesc;

    private String weakDesc;


    public DayInfoDto() {
    }


    public DayInfoDto(Date date) {
        this.dateKey = DateUtil.format(date, DatePattern.NORM_DATE_PATTERN);
        this.dateDesc = DateUtil.format(date, "MM月dd日");
        int week = DateUtil.dayOfWeek(date);
        String weakDesc = "";
        switch (week){
            case 1:
                weakDesc ="周日";
                break;
            case 2:
                weakDesc ="周一";
                break;
            case 3:
                weakDesc ="周二";
                break;
            case 4:
                weakDesc ="周三";
                break;
            case 5:
                weakDesc ="周四";
                break;
            case 6:
                weakDesc ="周五";
                break;
            case 7:
                weakDesc ="周六";
                break;
            default:
        }
        Date now = new Date();
        // 当前日期的天数
        long x = DateUtil.betweenDay(now, date,true);
        if (DateUtil.compare(now,date) > 0){
            x = -x;
        }
        switch ((int) x) {
            case -1:
                weakDesc = "昨天";
                break;
            case 0:
                weakDesc = "今天";
                break;
            case 1:
                weakDesc = "明天";
                break;
            default:
        }
        this.weakDesc = weakDesc;


    }
}
