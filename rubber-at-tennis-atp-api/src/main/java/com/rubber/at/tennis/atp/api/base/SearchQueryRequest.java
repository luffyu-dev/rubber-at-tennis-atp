package com.rubber.at.tennis.atp.api.base;

import com.rubber.base.components.util.result.page.BaseRequestPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchQueryRequest extends BaseRequestPage {

    /**
     * 搜索的值
     */
    private String searchValue;
}
