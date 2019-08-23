package com.admin.apartment.mapper;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.File;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 公寓信息 Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
public interface ApartmentMapper extends BaseMapper<Apartment> {

    /**
     * 查询文件信息
     * */
    Page<Apartment> selectApartmentByInfo(IPage<ApartmentParams> apartmentIPage);

    /**
     * 模糊查询文件信息
     * */
    Page<Apartment> selectApartmentByLike(IPage<ApartmentParams> apartmentIPage);

    /**
     * 查询所有的支付模式
     * */
    List<String> selectPattern();

    /**
     * 查询所有的户型
     * */
    List<String> selectHouseType();

    /**
     * 查询所有的朝向
     * */
    List<String> selectFaceList();

    /**
     * 移除公寓中的租户信息设置为未出租
     * */
    int deleteUserInfoInApartmentById(@Param("id") String id);

    /**
     * 通过租户 id 查询租户租用的公寓
     * */
    List<Apartment> getApartmentListByUserid(@Param("userid") String userid);

    /**
     * 通过租户 id 查询租户租用的公寓
     * */
    Apartment selectByApartmentId(@Param("id") String id);

}
