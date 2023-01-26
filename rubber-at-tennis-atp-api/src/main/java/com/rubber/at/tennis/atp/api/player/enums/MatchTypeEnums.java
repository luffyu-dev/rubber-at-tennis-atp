package com.rubber.at.tennis.atp.api.player.enums;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;

/**
 * @author luffyu
 * Created on 2023/1/19
 */
@Getter
public enum MatchTypeEnums {

    /**
     * 结果集合
     */
    GRAND_SLAM("GRAND_SLAM","大满贯",0,1),
    AO("AO","澳网",1,0),
    RG("RG","法网",1,0),
    UO("UO","美网",1,0),
    WC("WC","温网",1,0),
    YEC("YEC","年终",1,1),
    P1000("P1000","大师赛",1,1),
    P500("P500","500赛",1,1),
    P250("P250","250赛",1,1),
    CH("CH","挑战赛",1,1),
    OL("OL","奥运",1,1),
    ITF("ITF","希望赛",0,1)
    ;


    MatchTypeEnums(String type, String name, Integer showChampionHonour, Integer showMatch) {
        this.type = type;
        this.name = name;
        this.showChampionHonour = showChampionHonour;
        this.showMatch = showMatch;
    }

    private final String type;

    private final String name;

    /**
     * 是否展示冠军列
     */
    private Integer showChampionHonour;

    /**
     * 是否展示赛事信息
     */
    private Integer showMatch;


    public static MatchTypeEnums getByType(String type){
        for (MatchTypeEnums matchTypeEnums:MatchTypeEnums.values()){
            if (matchTypeEnums.getType().equals(type)){
                return matchTypeEnums;
            }
        }
        return null;
    }



    public static MatchTypeEnums isBgmByType(String type){
        if (AO.type.equals(type)){
            return AO;
        }
        if (RG.type.equals(type)){
            return RG;
        }
        if (UO.type.equals(type)){
            return UO;
        }
        if (WC.type.equals(type)){
            return WC;
        }
        return null;
    }


}
