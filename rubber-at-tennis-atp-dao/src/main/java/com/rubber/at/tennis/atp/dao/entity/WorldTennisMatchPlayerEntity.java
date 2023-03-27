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
 * 世界网球比赛球员表
 * </p>
 *
 * @author rockyu
 * @since 2023-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_world_tennis_match_player")
public class WorldTennisMatchPlayerEntity extends BaseEntity {

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
     * 比赛分组 A 和 B
     */
    @TableField("Fplayer_index")
    private String playerIndex;

    /**
     * 是否赢了 0表示未知  1表示赢了 2表示输了
     */
    @TableField("Fwin_flag")
    private Integer winFlag;

    /**
     * 球员赔率
     */
    @TableField("Fplayer_odds")
    private String playerOdds;

    /**
     * 球员id
     */
    @TableField("Fplayer_id")
    private String playerId;

    /**
     * 球员名称
     */
    @TableField("Fplayer_name")
    private String playerName;

    /**
     * 球员排名
     */
    @TableField("Fplayer_rank")
    private String playerRank;

    /**
     * 球员伙伴id
     */
    @TableField("Fpartner_id")
    private String partnerId;

    /**
     * 球员伙伴名称
     */
    @TableField("Fpartner_name")
    private String partnerName;

    @TableField("Fpartner_rank")
    private String partnerRank;

    /**
     * 第1盘
     */
    @TableField("Fset1_num")
    private String set1Num;

    /**
     * 第2盘
     */
    @TableField("Fset2_num")
    private String set2Num;

    /**
     * 第3盘
     */
    @TableField("Fset3_num")
    private String set3Num;

    /**
     * 第4盘
     */
    @TableField("Fset4_num")
    private String set4Num;

    /**
     * 第5盘
     */
    @TableField("Fset5_num")
    private String set5Num;

    /**
     * 实时分数
     */
    @TableField("Fgaming_score")
    private String gamingScore;

    /**
     * 实时描述
     */
    @TableField("Fgameing_desc")
    private String gameingDesc;

    /**
     * 比赛中的描述
     */
    @TableField("Fliving_Flag")
    private Integer livingFlag;

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
