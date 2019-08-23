package com.admin.apartment.mapper;

import com.admin.apartment.entity.UmsAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-08-12
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    /**
     * 通过用户信息查询所有用户数据
     * @param umsAdmin
     * @return
     */
    UmsAdmin selectOneByInfo(UmsAdmin umsAdmin);
}
