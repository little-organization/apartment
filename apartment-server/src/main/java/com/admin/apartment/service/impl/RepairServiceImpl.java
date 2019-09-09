package com.admin.apartment.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.admin.apartment.entity.*;
import com.admin.apartment.mapper.*;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.MyPage;
import com.admin.apartment.model.RepairParams;
import com.admin.apartment.service.IRepairService;
import com.admin.apartment.service.ISendMessageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 售后人员信息 服务实现类
 * </p>
 *
 * @author liangming
 * @since 2019-09-09
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    private static final Log LOGGER = LogFactory.get();

    @Autowired
    RepairMapper repairMapper;

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Autowired
    UmsRoleMapper umsRoleMapper;

    @Override
    public Repair selectRepairById(String repairId) {
        return repairMapper.selectById(repairId);
    }

    @Override
    public Page<Repair> selectRepairByInfo(IPage<Repair> repairIPage) {
        return repairMapper.selectRepairByInfo(repairIPage);
    }

    @Override
    public Page<Repair> selectRepairByLike(RepairParams params) {
        //当前页数，查询的最大个数
        MyPage<RepairParams> repairIPage = new MyPage<>(params.getPage(),params.getLimit(),true);
        repairIPage.setT(params);
        //获取所有数据 List
        Page<Repair> page =  repairMapper.selectRepairByLike(repairIPage);
        return page;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean insertRepair(Repair repair) {
        // 添加用户
        boolean insert = repairMapper.insertRepair(repair)>0;
        // 售后人员添加成功后生成售后人员账号密码
        if(insert){
            UmsAdmin umsAdmin= new UmsAdmin();
            umsAdmin.setUsername(repair.getName());
            umsAdmin.setPassword(repair.getIdNumber().substring(repair.getIdNumber().length()-6));
            // 添加后的售后人员直接启用账户
            UmsRole umsRole = umsRoleMapper.selectListByRoleName("售后人员");
            umsAdmin.setRoleId(umsRole.getId());
            umsAdmin.setRoleNote(umsRole.getName());
            umsAdmin.setPhone(repair.getPhone());
            umsAdmin.setRepairId(repair.getId());
            insert = umsAdminMapper.insert(umsAdmin)>0;
        }
        return insert;
    }

    @Override
    public boolean updateRepairById(Repair repair) {
        return repairMapper.updateById(repair)>0;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean deleteRepairById(Repair repair) {
        boolean result = repairMapper.deleteById(repair.getId())>0;
        if(result){
            // 删除售后人员的账号
            result = umsAdminMapper.deleteByRepairId(repair.getId())>0;
        }
        return result;
    }

    @Override
    public List<Repair> selectRepairByName(String repairname) {
        return repairMapper.selectRepairByName(repairname);
    }

    @Override
    public List<FiltersTag> selectIdTypeList() {
        List<String> list = repairMapper.selectIdTypeList();
        List<FiltersTag> tags = ApartmentServiceImpl.changeTagList(list);
        return tags;
    }

}
