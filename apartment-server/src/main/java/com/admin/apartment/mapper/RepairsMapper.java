package com.admin.apartment.mapper;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.Repairs;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.RepairsParams;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 报修表 Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
public interface RepairsMapper extends BaseMapper<Repairs> {

    /**
     * 查询报修表信息
     * */
    Page<Repairs> selectRepairsByInfo(IPage<RepairsParams> repairsIPage);

    /**
     * 模糊查询文件信息
     * */
    Page<Repairs> selectRepairsByLike(IPage<RepairsParams> repairsIPage);

    /**
     * 获取所有状态列表
     * */
    List<String> selectStatusList();
}
