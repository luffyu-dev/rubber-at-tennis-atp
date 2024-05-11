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
 * 投票信息表
 * </p>
 *
 * @author rockyu
 * @since 2024-05-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_vote_info")
public class VoteInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "Fid", type = IdType.AUTO)
    private Integer id;

    /**
     * 投票key
     */
    @TableField("Fvote_key")
    private String voteKey;

    /**
     * 投票标题
     */
    @TableField("Fvote_title")
    private String voteTitle;

    /**
     * a观点
     */
    @TableField("Fa_point")
    private String aPoint;

    /**
     * b观点
     */
    @TableField("Fb_point")
    private String bPoint;

    /**
     * a结果
     */
    @TableField("Fa_num")
    private Integer aNum;

    /**
     * b结果
     */
    @TableField("Fb_num")
    private Integer bNum;

    /**
     * 版本号
     */
    @TableField("Fversion")
    private Integer version;

    /**
     * 状态 10表示初始化 20表示已发布，30表示已完成
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
