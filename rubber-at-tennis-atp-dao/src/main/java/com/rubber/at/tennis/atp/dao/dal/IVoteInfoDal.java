package com.rubber.at.tennis.atp.dao.dal;

import com.rubber.at.tennis.atp.dao.entity.VoteInfoEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

/**
 * <p>
 * 投票信息表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2024-05-01
 */
public interface IVoteInfoDal extends IBaseAdminService<VoteInfoEntity> {


    VoteInfoEntity queryByKey(String key);


}
