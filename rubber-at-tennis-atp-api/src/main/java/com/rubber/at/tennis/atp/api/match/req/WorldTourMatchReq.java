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
public class WorldTourMatchReq extends BaseRequestPage {


    /**
     * 比赛id
     */
    private String tourId;


    /**
     * 比赛年份
     */
    private String tourYear;


    /**
     * 更新的版本
     */
    private String updateVersion;


    /**
     * 比赛状态
     */
    private List<Integer>statusList;

    /**
     * 模块信息
     */
    private List<String> dataModelList;
}
