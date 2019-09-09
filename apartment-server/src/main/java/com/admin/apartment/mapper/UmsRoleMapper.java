package com.admin.apartment.mapper;
import	java.lang.reflect.Parameter;

import com.admin.apartment.entity.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @author liangming
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 获取 role
     * */
    @Select("select * from ums_role where id=#{id}")
    List<UmsRole> selectListByRoleid(long id);

    /**
     * 获取 role
     * */
    @Select("select * from ums_role where name like '%${rolename}%'")
    UmsRole selectListByRoleName(@Param("rolename") String rolename);


}
