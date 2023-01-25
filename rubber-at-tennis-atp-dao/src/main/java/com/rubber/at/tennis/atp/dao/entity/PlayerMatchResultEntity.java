package com.rubber.at.tennis.atp.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.rubber.base.components.mysql.plugins.admin.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 比赛详情表
 * </p>
 *
 * @author rockyu
 * @since 2023-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_player_match_result")
public class PlayerMatchResultEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "Fid", type = IdType.AUTO)
    private Integer id;

    /**
     * 运动员id
     */
    @TableField("Fplayer_id")
    private String playerId;

    /**
     * 赛事类型
     */
    @TableField("Fmatch_type")
    private String matchType;

    /**
     * 赛事名称
     */
    @TableField("Fmatch_name")
    private String matchName;

    /**
     * 赛事年份
     */
    @TableField("Fmatch_year")
    private String matchYear;

    /**
     * 比赛结果
     */
    @TableField("Fmatch_result")
    private String matchResult;

    /**
     * 备注
     */
    @TableField("Fremark")
    private String remark;

    /**
     * 版本号
     */
    @TableField("Fversion")
    private Integer version;

    /**
     * 状态 1表示正常
     */
    @TableField("Fstatus")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("Fcreate_time")
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    @TableField("Fupdate_time")
    private Date updateTime;


}
