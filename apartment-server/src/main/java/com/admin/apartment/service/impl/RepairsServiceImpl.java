package com.admin.apartment.service.impl;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.Repairs;
import com.admin.apartment.entity.User;
import com.admin.apartment.mapper.RepairsMapper;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.MyPage;
import com.admin.apartment.model.RepairsParams;
import com.admin.apartment.service.IRepairsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 报修表 服务实现类
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Service
public class RepairsServiceImpl extends ServiceImpl<RepairsMapper, Repairs> implements IRepairsService {

    @Autowired
    RepairsMapper repairsMapper;

    @Override
    public Page<Repairs> selectRepairsByLike(RepairsParams params) {
        //当前页数，查询的最大个数
        MyPage<RepairsParams> repairsParamsMyPage = new MyPage<>(params.getPage(),params.getLimit(),true);
        repairsParamsMyPage.setT(params);
        Page<Repairs> list = repairsMapper.selectRepairsByLike(repairsParamsMyPage);
        return list;
    }

    @Override
    public List<FiltersTag> selectStatusList() {
        //获取数据支付模式 List
        List<String> list = repairsMapper.selectStatusList();
        return ApartmentServiceImpl.changeTagList(list);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean  updateRepair(Repairs repairs) {
        repairs.setConductTime(LocalDateTime.now());
        boolean result = repairsMapper.updateById(repairs)>0;
        return result;
    }

}
