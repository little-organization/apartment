package com.admin.apartment.mapper;

import com.admin.apartment.entity.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liangming
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 获取 role
     * */
    @Select("select * from ums_role where admin_id=#{id}")
    List<UmsRole> selectListByAadminid(long id);


}
