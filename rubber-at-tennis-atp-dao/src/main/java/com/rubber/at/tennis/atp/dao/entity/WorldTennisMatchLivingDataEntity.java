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
 * 世界网球比赛数据
 * </p>
 *
 * @author rockyu
 * @since 2023-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_world_tennis_match_living_data")
public class WorldTennisMatchLivingDataEntity extends BaseEntity {

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
     * 比赛数据
     */
    @TableField("Fmatch_living_data")
    private String matchLivingData;

    /**
     * 状态 比赛状态 0表示未知 1表示未开始 2表示进行中 3表示已结束
     */
    @TableField("Fmatch_status")
    private Integer matchStatus;

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
