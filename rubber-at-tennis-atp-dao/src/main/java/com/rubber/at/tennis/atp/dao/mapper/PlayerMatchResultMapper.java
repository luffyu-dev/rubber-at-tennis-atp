package com.rubber.at.tennis.atp.dao.mapper;

import com.rubber.at.tennis.atp.api.base.MatchPlayerGroupBean;
import com.rubber.at.tennis.atp.dao.entity.PlayerMatchResultEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 比赛详情表 Mapper 接口
 * </p>
 *
 * @author rockyu
 * @since 2023-01-24
 */
public interface PlayerMatchResultMapper extends BaseMapper<PlayerMatchResultEntity> {


    @Select("SELECT Fplayer_id as playerId,count(1) as winNum,GROUP_CONCAT(Fmatch_year) as winYearList\n" +
            "FROM t_player_match_result\n" +
            "WHERE Fmatch_name = #{matchName} and Fmatch_result = \"W\"\n" +
            "GROUP BY Fplayer_id\n" +
            "ORDER BY winNum desc")
    List<MatchPlayerGroupBean> groupMatchResultByPlaye(@Param("matchName") String matchName);

}
