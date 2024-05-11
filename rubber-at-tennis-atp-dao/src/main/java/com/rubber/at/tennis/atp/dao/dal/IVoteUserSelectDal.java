package com.rubber.at.tennis.atp.dao.dal;

import com.rubber.at.tennis.atp.dao.entity.VoteUserSelectEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

/**
 * <p>
 * 用户投票结果表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2024-05-01
 */
public interface IVoteUserSelectDal extends IBaseAdminService<VoteUserSelectEntity> {

    VoteUserSelectEntity  getByUid(Integer uid,String voteKey);

}
