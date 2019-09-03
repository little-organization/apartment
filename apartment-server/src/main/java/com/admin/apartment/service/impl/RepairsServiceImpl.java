package com.admin.apartment.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.admin.apartment.entity.*;
import com.admin.apartment.mapper.FileMapper;
import com.admin.apartment.mapper.RepairsLogMapper;
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

    private static final Log LOGGER = LogFactory.get();

    @Autowired
    RepairsMapper repairsMapper;

    @Autowired
    RepairsLogMapper repairsLogMapper;

    @Autowired
    FileMapper fileMapper;

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
        // 记录维修日志
        if (result){
            repairsLogMapper.insertLog(repairs);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean insertRepairAndFile(Repairs repair) {
        // 新增维修单
        if(repair.getFileListSave().size()>0){
            repair.setHasfile(1);
        }
        repair.setStatus("未修理");
        boolean result = repairsMapper.insertRepairAndFile(repair)>0;
        // 新增文件
        List<File> files = repair.getFileListSave();
        boolean flag = true;
        if(files.size()>0) {
            for (File file : files) {
                file.setRepairsid(repair.getId());
            }
            flag = fileMapper.insertBatch(files);
        }
        // 记录维修日志
        if (result){
            repairsLogMapper.insertLog(repair);
        }
        return result && flag;
    }
}
