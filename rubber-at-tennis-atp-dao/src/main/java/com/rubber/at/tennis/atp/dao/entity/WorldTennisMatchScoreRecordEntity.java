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
 * 世界网球比赛分数记录表
 * </p>
 *
 * @author rockyu
 * @since 2023-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_world_tennis_match_score_record")
public class WorldTennisMatchScoreRecordEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "Frecord_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 比赛唯一id
     */
    @TableField("Fmatch_id")
    private String matchId;

    /**
     * a第1盘
     */
    @TableField("Faset1_num")
    private String aset1Num;

    /**
     * a第2盘
     */
    @TableField("Faset2_num")
    private String aset2Num;

    /**
     * a第3盘
     */
    @TableField("Faset3_num")
    private String aset3Num;

    /**
     * a第4盘
     */
    @TableField("Faset4_num")
    private String aset4Num;

    /**
     * a第5盘
     */
    @TableField("Faset5_num")
    private String aset5Num;

    /**
     * a实时分数
     */
    @TableField("Fagaming_score")
    private String agamingScore;

    /**
     * b第1盘
     */
    @TableField("Fbset1_num")
    private String bset1Num;

    /**
     * b第2盘
     */
    @TableField("Fbset2_num")
    private String bset2Num;

    /**
     * b第3盘
     */
    @TableField("Fbset3_num")
    private String bset3Num;

    /**
     * b第4盘
     */
    @TableField("Fbset4_num")
    private String bset4Num;

    /**
     * b第5盘
     */
    @TableField("Fbset5_num")
    private String bset5Num;

    /**
     * b实时分数
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

    /**
     * A表示a正在打 B表示a正在打
     */
    @TableField("Fliving_flag_str")
    private String livingFlagStr;


}
