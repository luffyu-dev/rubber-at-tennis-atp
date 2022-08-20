package com.rubber.at.tennis.map.service.player;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rubber.at.tennis.map.api.base.SearchQueryRequest;
import com.rubber.at.tennis.map.api.player.PlayerInfoQueryApi;
import com.rubber.at.tennis.map.api.player.dto.PlayerInfoDto;
import com.rubber.at.tennis.map.api.player.enums.PlayerTypeEnums;
import com.rubber.at.tennis.map.dao.dal.IPlayerInfoDal;
import com.rubber.at.tennis.map.dao.entity.PlayerInfoEntity;
import com.rubber.base.components.util.result.page.ResultPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author luffyu
 * Created on 2022/8/15
 */
@Service
public class PlayerInfoQueryService implements PlayerInfoQueryApi {


    @Autowired
    private IPlayerInfoDal iPlayerInfoDal;


    /**
     * apt球员的分页查询
     *
     * @param request 当前请求
     * @return 返回分页数据
     */
    @Override
    public ResultPage<PlayerInfoDto> queryAtpInfoPage(SearchQueryRequest request) {
        Page<PlayerInfoEntity> page = queryByPage(request,PlayerTypeEnums.atp);
        return convertDto(page);
    }

    /**
     * wta球员的分页查询
     *
     * @param request 当前请求
     * @return 返回分页数据
     */
    @Override
    public ResultPage<PlayerInfoDto> queryWtaInfoPage(SearchQueryRequest request) {
        Page<PlayerInfoEntity> page = queryByPage(request,PlayerTypeEnums.wta);
        return convertDto(page);
    }


    /**
     * 分页查询数据
     * @param request
     * @return
     */
    private Page<PlayerInfoEntity> queryByPage(SearchQueryRequest request, PlayerTypeEnums playerTypeEnums){
        Page<PlayerInfoEntity> page = new Page<>();
        page.setCurrent(request.getPage());
        page.setSize(request.getSize());
        page.setSearchCount(false);
        LambdaQueryWrapper<PlayerInfoEntity> lqw = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(request.getSearchValue())) {
            lqw.like(PlayerInfoEntity::getChinaFullName, "%" + request.getSearchValue() + "%")
                    .or()
                    .like(PlayerInfoEntity::getNationChineseName, "%" + request.getSearchValue() + "%");
        }
        lqw.eq(PlayerInfoEntity::getPlayerType,playerTypeEnums.toString())
                .orderByDesc(PlayerInfoEntity::getSeqWeight);
        iPlayerInfoDal.page(page,lqw);
        return page;
    }


    private ResultPage<PlayerInfoDto> convertDto(Page<PlayerInfoEntity> page){
        ResultPage<PlayerInfoDto> dtoResultPage = new ResultPage<>();
        dtoResultPage.setCurrent(page.getCurrent());
        dtoResultPage.setPages(page.getPages());
        dtoResultPage.setSize(page.getSize());
        dtoResultPage.setTotal(page.getTotal());
        if (CollUtil.isNotEmpty(page.getRecords())){
            dtoResultPage.setRecords(
                    page.getRecords().stream().map(i->{
                        PlayerInfoDto dto = new PlayerInfoDto();
                        BeanUtils.copyProperties(i,dto);
                        return dto;
                    }).collect(Collectors.toList())
            );
        }
        return dtoResultPage;
    }
}
