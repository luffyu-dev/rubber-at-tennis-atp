package com.rubber.at.tennis.atp.api.base;

import com.rubber.base.components.util.session.BaseUserSession;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchQueryRequest extends BaseUserSession {

    /**
     * 当前页
     */
    private int page = 1;

    /**
     * 一页的大小
     */
    private int size = 20;

    /**
     * 搜索的值
     */
    private String searchValue;

    /**
     * 是否只查询关注的球员
     */
    private boolean justFollow;


    /**
     * 是否只查询关注的球员
     */
    private String playerType;


    /**
     * 查询的排序字段
     * seq_weight
     * recommend_score
     */
    private String seqType;
}
