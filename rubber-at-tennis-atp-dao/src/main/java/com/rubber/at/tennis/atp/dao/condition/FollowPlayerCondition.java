package com.rubber.at.tennis.atp.dao.condition;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2022/11/28
 */
@Data
public class FollowPlayerCondition {

    /**
     * uid
     */
    private Integer uid;

    /**
     * 搜索的用户名称
     */
    private String searchValue;
}
