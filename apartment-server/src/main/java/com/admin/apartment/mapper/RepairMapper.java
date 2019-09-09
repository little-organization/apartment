package com.admin.apartment.mapper;

import com.admin.apartment.entity.Repair;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.RepairParams;
import com.admin.apartment.model.UserParams;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 售后人员信息 Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-09-09
 */
public interface RepairMapper extends BaseMapper<Repair> {

    /**
     * 查询租户信息
     * */
    Page<Repair> selectRepairByInfo(IPage<Repair> repairIPage);

    /**
     * 模糊查询文件信息
     * */
    Page<Repair> selectRepairByLike(IPage<RepairParams> repairParamsIPage);

    /**
     * 模糊查询租户信息通过租户姓名
     * */
    List<Repair> selectRepairByName(@Param("name") String username);

    /**
     * 通过 ID 更改租户的状态
     * */
    int updateRepairStatusById(@Param("id") long id,@Param("status") int status);

    /**
     * 获取全部的证件类型
     * */
    List<String> selectIdTypeList();

    /**
     * 新增一条数据返回 id
     * */
    int insertRepair(Repair repair);

    /**
     * 更新售后人员的手机号码
     * */
    int updateRepairPhoneById(Repair repair);
}
