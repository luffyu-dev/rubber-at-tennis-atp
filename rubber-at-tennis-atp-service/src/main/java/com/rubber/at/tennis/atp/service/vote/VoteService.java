package com.rubber.at.tennis.atp.service.vote;

import com.rubber.at.tennis.atp.api.vote.VoteApi;
import com.rubber.at.tennis.atp.api.vote.dto.VoteInfoDto;
import com.rubber.at.tennis.atp.api.vote.request.VoteReq;
import com.rubber.at.tennis.atp.dao.dal.IVoteInfoDal;
import com.rubber.at.tennis.atp.dao.dal.IVoteUserSelectDal;
import com.rubber.at.tennis.atp.dao.entity.VoteInfoEntity;
import com.rubber.at.tennis.atp.dao.entity.VoteUserSelectEntity;
import com.rubber.at.tennis.atp.service.common.exception.ErrorCodeEnums;
import com.rubber.at.tennis.atp.service.common.exception.RubberServiceException;
import com.rubber.base.components.util.result.code.SysCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * @author luffyu
 * Created on 2024/5/1
 */
@Slf4j
@Service
public class VoteService  implements VoteApi {

    @Autowired
    private IVoteInfoDal iVoteInfoDal;


    @Autowired
    private IVoteUserSelectDal iVoteUserSelectDal;


    /**
     * 查询投票详情
     *
     * @param req
     * @return
     */
    @Override
    public VoteInfoDto getVoteInfo(VoteReq req) {
        VoteInfoEntity voteInfoEntity = iVoteInfoDal.queryByKey(req.getVoteKey());
        if (voteInfoEntity == null){
            return null;
        }
        VoteInfoDto dto = new VoteInfoDto();
        BeanUtils.copyProperties(voteInfoEntity,dto);
        if (req.getUid() != null && req.getUid() > 0){
            VoteUserSelectEntity selectEntity = iVoteUserSelectDal.getByUid(req.getUid(), req.getVoteKey());
            if (selectEntity != null){
                dto.setUserSelectPoint(selectEntity.getSelectPoint());
                dto.setUserSelectFlag(1);
            }
        }
        handlerDto(dto);
        return dto;
    }

    /**
     * 选择投票
     *
     * @param req
     */
    @Override
    @Transactional(
            rollbackFor = Exception.class
    )
    public void selectPoint(VoteReq req) {
        VoteInfoEntity voteInfoEntity = iVoteInfoDal.queryByKey(req.getVoteKey());
        if (voteInfoEntity == null){
            return;
        }
        VoteUserSelectEntity selectEntity = iVoteUserSelectDal.getByUid(req.getUid(), req.getVoteKey());
        if (selectEntity != null){
            log.warn("你已经选择过答案");
            return;
        }
        if (voteInfoEntity.getAPoint().equals(req.getSelectPoint())){
            voteInfoEntity.setANum(voteInfoEntity.getANum()+1);
        }
        else if (voteInfoEntity.getBPoint().equals(req.getSelectPoint())){
            voteInfoEntity.setBNum(voteInfoEntity.getBNum()+1);
        }
        VoteUserSelectEntity userSelect = new VoteUserSelectEntity();
        userSelect.setUid(req.getUid());
        userSelect.setVoteKey(req.getVoteKey());
        userSelect.setSelectPoint(req.getSelectPoint());
        userSelect.setUpdateTime(new Date());
        userSelect.setCreateTime(new Date());
        if(!iVoteUserSelectDal.save(userSelect)){
            return;
        }
        if(!iVoteInfoDal.updateById(voteInfoEntity)){
            throw new RubberServiceException(SysCode.SYSTEM_BUS);
        }
    }


    public void handlerDto(VoteInfoDto dto){
        dto.setAllNum(dto.getANum() + dto.getBNum());
        if (dto.getAllNum() > 0){
            BigDecimal allNumB = new BigDecimal(dto.getAllNum());
            Integer aPreD = new BigDecimal(dto.getANum()).multiply(new BigDecimal(100)).divide(allNumB,0, RoundingMode.DOWN).intValue();
            dto.setAPer(aPreD);
            dto.setBPer((100-aPreD));
        }else {
            dto.setAPer(50);
            dto.setBPer(50);
        }
    }
}
