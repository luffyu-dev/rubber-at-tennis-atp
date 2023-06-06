package com.rubber.at.tennis.atp.api.match.enums;

import lombok.Getter;

/**
 * @author luffyu
 * Created on 2023/5/17
 */
@Getter
public enum  MatchStatusEnums {
    /**
     * 状态
     */
    NON_STATE(0,"未知"),

    UN_BEGIN(1,"未开始"),

    LIVING(2,"进行中"),

    END(3,"已结束"),
    ;

    MatchStatusEnums(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    private final Integer type;

    private final String name;

}
