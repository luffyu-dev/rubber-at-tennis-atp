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
 * 球员h2h
 * </p>
 *
 * @author rockyu
 * @since 2023-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_player_htoh_info")
public class PlayerHtohInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "Fid", type = IdType.AUTO)
    private Integer id;

    /**
     * a球员id
     */
    @TableField("Fa_player_id")
    private String aPlayerId;

    /**
     * a球员名称
     */
    @TableField("Fa_player_name")
    private String aPlayerName;

    /**
     * a球员赢赛数量
     */
    @TableField("Fa_player_win_num")
    private String aPlayerWinNum;

    /**
     * a球员赢赛比例
     */
    @TableField("Fa_player_win_point")
    private String aPlayerWinPoint;

    /**
     * a球员id
     */
    @TableField("Fb_player_id")
    private String bPlayerId;

    /**
     * a球员名称
     */
    @TableField("Fb_player_name")
    private String bPlayerName;

    /**
     * a球员赢赛数量
     */
    @TableField("Fb_player_win_num")
    private String bPlayerWinNum;

    /**
     * a球员赢赛比例
     */
    @TableField("Fb_player_win_point")
    private String bPlayerWinPoint;

    /**
     * 比赛信息
     */
    @TableField("Fold_match_info")
    private String oldMatchInfo;

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
     * 状态 10表示初始化 20表示待审核 30表示审核不通过 50表示审核通过
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
