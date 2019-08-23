package com.admin.apartment.service;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公寓信息 服务类
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
public interface IApartmentService extends IService<Apartment> {

    /**
     * 模糊查询文件信息
     * */
    Page<Apartment> selectApartmentByLike(ApartmentParams apartmentParams);

    /**
     * 查询所有的支付模式
     * */
    List<FiltersTag> selectPattern();

    /**
     * 查询所有的户型
     * */
    List<FiltersTag> selectHouseType();

    /**
     * 查询所有的朝向
     * */
    List<FiltersTag> selectFaceList();

    /**
     * 创建公寓信息
     * */
    boolean createApartment(Apartment apartment);

    /**
     * 更新公寓信息
     * */
    boolean updateApartment(Apartment apartment);

    /**
     * 删除公寓信息
     * */
    boolean deleteApartment(String id);

    /**
     * 移除公寓中的租户信息设置为未出租
     * */
    boolean deleteUserInfoInApartmentById(String id);

    /**
     * 通过租户 id 查询租户租用的公寓
     * */
    List<Apartment> getApartmentListByUserid(String userid);

    /**
     * 通过id 查询公寓
     * */
    Apartment getApartmentById(String id);



}
