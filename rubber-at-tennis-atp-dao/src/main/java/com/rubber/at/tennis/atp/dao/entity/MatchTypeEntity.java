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
@TableName("t_match_type")
public class MatchTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "Fid", type = IdType.AUTO)
    private Integer id;

    /**
     * 赛事分组
     */
    @TableField("Fmatch_group")
    private String matchGroup;

    /**
     * 赛事类型
     */
    @TableField("Fmatch_type")
    private String matchType;

    /**
     * 赛事类型名称
     */
    @TableField("Fmatch_type_name")
    private String matchTypeName;

    /**
     * 赛事logo
     */
    @TableField("Fmatch_logo")
    private String matchLogo;

    /**
     * 赛事灰色logo
     */
    @TableField("Fmatch_logo_gray")
    private String matchLogoGray;

    /**
     * 赛事wtalogo
     */
    @TableField("Fmatch_logo_wta")
    private String matchLogoWta;

    /**
     * 赛事wta灰色logo
     */
    @TableField("Fmatch_logo_gray_wta")
    private String matchLogoGrayWta;

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
