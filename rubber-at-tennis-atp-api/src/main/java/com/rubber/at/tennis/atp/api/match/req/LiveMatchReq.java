package com.rubber.at.tennis.atp.api.match.req;

import com.rubber.base.components.util.result.page.BaseRequestPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * @author luffyu
 * Created on 2023/3/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LiveMatchReq extends BaseRequestPage {


    /**
     * 比赛id
     */
    private String matchId;

    /**
     * 比赛数据
     */
    private List<String> dataModelList = Arrays.asList("h2h","timeLine","setAllData");

}
