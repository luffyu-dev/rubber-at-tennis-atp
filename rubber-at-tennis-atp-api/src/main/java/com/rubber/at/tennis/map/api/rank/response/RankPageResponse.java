package com.rubber.at.tennis.map.api.rank.response;

import com.rubber.base.components.util.result.page.ResultPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RankPageResponse<T>  extends ResultPage<T> {


    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 更新时间的字符串
     */
    private String modifyTimeStr;

}
