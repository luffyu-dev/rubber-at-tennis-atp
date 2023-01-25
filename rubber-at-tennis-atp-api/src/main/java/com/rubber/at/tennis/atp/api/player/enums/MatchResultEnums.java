package com.rubber.at.tennis.atp.api.player.enums;

import lombok.Getter;

/**
 * @author luffyu
 * Created on 2023/1/24
 */
@Getter
public enum  MatchResultEnums {
    W("W","冠军"),

    F("F","亚军"),

    SF("SF","4强"),

    QF("QF","8强"),

    ;

    MatchResultEnums(String result, String desc) {
        this.result = result;
        this.desc = desc;
    }

    private final String result;

    private final String desc;
}
