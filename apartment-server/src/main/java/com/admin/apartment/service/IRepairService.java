package com.admin.apartment.service;

import com.admin.apartment.entity.Repair;
import com.admin.apartment.entity.Repair;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.RepairParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 售后人员信息 服务类
 * </p>
 *
 * @author liangming
 * @since 2019-09-09
 */
public interface IRepairService extends IService<Repair> {

    /**
     * 查询售后人员信息通过 id
     * */
    Repair selectRepairById(String repairId);

    /**
     * 查询用户信息
     * */
    Page<Repair> selectRepairByInfo(IPage<Repair> page);

    /**
     * 模糊查询用户信息
     * */
    Page<Repair> selectRepairByLike(RepairParams params);

    /**
     * 新增用户信息
     * */
    boolean insertRepair(Repair repair);

    /**
     * 更新用户信息
     * */
    boolean updateRepairById(Repair repair);

    /**
     * 删除用户信息
     * */
    boolean deleteRepairById(Repair repair);

    /**
     * 查询用户信息通过 name
     * */
    List<Repair> selectRepairByName(String repairname);

    /**
     * 获取全部的证件类型
     * */
    List<FiltersTag> selectIdTypeList();


}
