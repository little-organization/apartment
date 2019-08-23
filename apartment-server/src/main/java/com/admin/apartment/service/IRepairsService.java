package com.admin.apartment.service;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.Repairs;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.RepairsParams;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 报修表 服务类
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
public interface IRepairsService extends IService<Repairs> {

    /**
     * 模糊查询文件信息
     * */
    Page<Repairs> selectRepairsByLike(RepairsParams params);

    /**
     * 查询所有的修理状态
     * */
    List<FiltersTag> selectStatusList();

    /**
     * 更新报修信息
     * */
    boolean updateRepair(Repairs repairs);



}
