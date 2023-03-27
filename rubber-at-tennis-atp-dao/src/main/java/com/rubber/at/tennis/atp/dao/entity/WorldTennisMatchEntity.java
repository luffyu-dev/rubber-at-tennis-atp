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
 * 世界网球比赛表
 * </p>
 *
 * @author rockyu
 * @since 2023-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_world_tennis_match")
public class WorldTennisMatchEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "Fid", type = IdType.AUTO)
    private Integer id;

    /**
     * 比赛唯一id
     */
    @TableField("Fmatch_id")
    private String matchId;

    /**
     * 比赛所在年份
     */
    @TableField("Fmatch_year")
    private String matchYear;

    /**
     * 比赛所在天
     */
    @TableField("Fmatch_day")
    private String matchDay;

    /**
     * 赛事类型
     */
    @TableField("Fmatch_type_id")
    private String matchTypeId;

    /**
     * 赛事类型，关联的类型名称
     */
    @TableField("Fmatch_type_name")
    private String matchTypeName;

    /**
     * 比赛code，关联的比赛实时数据code
     */
    @TableField("Fmatch_code")
    private String matchCode;

    /**
     * 比赛名称
     */
    @TableField("Fmatch_name")
    private String matchName;

    /**
     * 所属球场名称
     */
    @TableField("Fcourt_name")
    private String courtName;

    /**
     * 比赛描述 男单 女单 男双 女双
     */
    @TableField("Fmatch_gender")
    private String matchGender;

    /**
     * 比赛轮次
     */
    @TableField("Fmatch_round")
    private String matchRound;

    /**
     * 是否双打 0表示单打 1表示双打
     */
    @TableField("Fdouble_flag")
    private Integer doubleFlag;

    /**
     * 比赛开始时间
     */
    @TableField("Fmatch_time")
    private Date matchTime;

    /**
     * 比赛结束时间
     */
    @TableField("Fmatch_end_time")
    private Date matchEndTime;

    /**
     * 状态 比赛状态 0表示未开始 1表示进行中 2表示已结束
     */
    @TableField("Fmatch_status")
    private Integer matchStatus;


    /**
     * 比赛中的描述信息
     */
    @TableField("Fliving_desc")
    private String livingDesc;

    /**
     * 比赛使用的时间
     */
    @TableField("Fused_time")
    private String usedTime;

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


}
