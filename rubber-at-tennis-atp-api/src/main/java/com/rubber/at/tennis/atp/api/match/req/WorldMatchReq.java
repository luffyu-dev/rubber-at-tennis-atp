package com.rubber.at.tennis.atp.api.match.req;

import com.rubber.base.components.util.result.page.BaseRequestPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author luffyu
 * Created on 2023/3/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WorldMatchReq extends BaseRequestPage {


    /**
     * 调用场景
     */
    private String invokerScene;


    /**
     * 比赛id
     */
    private String matchTypeId;


    /**
     * 比赛所在天
     */
    private String matchDay;

    /**
     * 是否双打
     */
    private Integer doubleFlag;


    /**
     * 男单 还是 女单
     */
    private String matchGender;


    /**
     * 比赛id
     */
    private String matchId;


    /**
     * 比赛状态
     */
    private List<Integer> matchStatusList;


    /**
     * 搜索的值
     */
    private String searchValue;


    /**
     * 是否按照优先级排序
     */
    private boolean orderBySeq;
}
