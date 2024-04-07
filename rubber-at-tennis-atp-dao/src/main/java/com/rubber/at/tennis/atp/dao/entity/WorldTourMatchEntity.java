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
 * 世界巡回表详情表
 * </p>
 *
 * @author rockyu
 * @since 2023-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_world_tour_match")
public class WorldTourMatchEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "Fid", type = IdType.AUTO)
    private Integer id;

    /**
     * 巡回赛年份
     */
    @TableField("Ftour_year")
    private String tourYear;

    /**
     * 赛事类型
     */
    @TableField("Ftour_id")
    private String tourId;

    /**
     * 赛事类型，关联的类型名称
     */
    @TableField("Ftour_name")
    private String tourName;

    /**
     * 比赛标题
     */
    @TableField("Ftitle")
    private String title;

    /**
     * 比赛副标题
     */
    @TableField("Fsubtitle")
    private String subtitle;

    /**
     * 赛事简介
     */
    @TableField("Fintroduction")
    private String introduction;

    /**
     * 赛事logo
     */
    @TableField("Flogo")
    private String logo;

    /**
     * 赛事宣传大图
     */
    @TableField("FhomeImg")
    private String homeImg;

    /**
     * 比赛开始时间
     */
    @TableField("Fbegin_time")
    private Date beginTime;

    /**
     * 比赛结束时间
     */
    @TableField("Fend_time")
    private Date endTime;

    /**
     * 状态 比赛状态 0表示未知 1表示未开始 2表示进行中 3表示已结束
     */
    @TableField("Fstatus")
    private Integer status;

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
     * 创建时间
     */
    @TableField("Fcreate_time")
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    @TableField("Fupdate_time")
    private Date updateTime;


    /**
     * 轮次list
     */
    @TableField("Fmatch_round_list")
    private String matchRoundList;


    /**
     * 推荐标记
     * 1表示走推荐
     * 0表示不走推荐
     */
    @TableField("Frecommend_flag")
    private Integer recommendFlag;


    /**
     * 排序字段
     */
    @TableField("Fseq")
    private Integer seq;

    /**
     * 版本更新
     */
    @TableField("Fupdate_version")
    private String updateVersion;

}
