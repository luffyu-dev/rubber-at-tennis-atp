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
 * 世界网球比赛实时分数
 * </p>
 *
 * @author rockyu
 * @since 2023-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_world_tennis_match_score")
public class WorldTennisMatchScoreEntity extends BaseEntity {

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
     * 当前盘
     */
    @TableField("Fmatch_set")
    private String matchSet;

    /**
     * a赢的局
     */
    @TableField("Faset_num")
    private String asetNum;

    /**
     * 当前分数
     */
    @TableField("Fagaming_score")
    private String agamingScore;

    /**
     * b赢的局
     */
    @TableField("Fbset_num")
    private String bsetNum;

    /**
     * 当前分数
     */
    @TableField("Fbgaming_score")
    private String bgamingScore;

    /**
     * 实时描述
     */
    @TableField("Fgameing_desc")
    private String gameingDesc;

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
