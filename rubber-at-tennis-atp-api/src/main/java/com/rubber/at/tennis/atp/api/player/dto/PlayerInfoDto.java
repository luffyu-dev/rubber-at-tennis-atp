package com.rubber.at.tennis.atp.api.player.dto;


import com.rubber.at.tennis.atp.api.rank.dto.PlayerRankInfoDto;
import lombok.Data;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
@Data
public class PlayerInfoDto {


    /**
     * 运动员id
     */
    private String playerId;

    /**
     * 运动员id
     */
    private String thirdId;

    /**
     * 球员类型 atp/wta
     */
    private String playerType;

    /**
     * 中文名称
     */
    private String chinaName;

    /**
     * 中文全称
     */
    private String chinaFullName;

    /**
     * 英文第一个名称
     */
    private String firstName;

    /**
     * 英文第一个名称
     */
    private String lastName;

    /**
     * 运动员头像
     */
    private String playerAvatar;

    /**
     * 运动员照片
     */
    private String playerImg;

    /**
     * 运动员照片
     */
    private String holdModel;

    /**
     * 国籍名称（中）
     */
    private String nationChineseName;

    /**
     * 国籍名称（英）
     */
    private String nationEnglishName;

    /**
     * 国籍图片
     */
    private String nationImg;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 出生地
     */
    private String birthPlace;

    /**
     * 居住地
     */
    private String residence;

    /**
     * 升高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 转职时间
     */
    private String proYear;

    /**
     * 生涯奖金数
     */
    private String allBonus;

    /**
     * 个人官网
     */
    private String website;

    /**
     * 单打最佳排名
     */
    private Integer singlesRankHeight;

    /**
     * 单打冠军数
     */
    private Integer singlesChampionNum;

    /**
     * 双打最佳排名
     */
    private Integer doubleRankHeight;

    /**
     * 双打冠军数
     */
    private Integer doubleChampionNum;

    /**
     * 备注
     */
    private String remark;


    /**
     * 排序权重
     */
    private Integer seqWeight;

    /**
     * 标签
     */
    private String tags;


    /**
     * 本周排名信息
     */
    private PlayerRankInfoDto weekRankInfo;


    /**
     * 是否关注
     */
    private boolean followed;


    /**
     * 推荐分
     */
    private Integer recommendScore;


    /**
     * 是否存在比赛数据
     * 1表示存在 0表示不存在
     */
    private Integer livingMatchFlag;

}
