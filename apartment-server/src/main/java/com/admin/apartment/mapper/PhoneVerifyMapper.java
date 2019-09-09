package com.admin.apartment.mapper;

import com.admin.apartment.entity.PhoneVerify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-09-08
 */
public interface PhoneVerifyMapper extends BaseMapper<PhoneVerify> {

    /**
     * 查找验证码
     * */
    PhoneVerify selectByBarcode(@Param("barcode") String barcode);


    /**
     * 更改验证码状态
     * */
    int updateByBarcode(@Param("barcode") String barcode);
}
