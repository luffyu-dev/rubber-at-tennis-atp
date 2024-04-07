package com.rubber.at.tennis.atp.api.match.dto;

import lombok.Data;

import java.util.List;

/**
 * @author luffyu
 * Created on 2024/4/6
 */
@Data
public class TourInfoDayDto {


    private List<WorldTourMatchTypeDto> tourList;

    private List<DayInfoDto> dayInfo;

    private String onDay;

}
