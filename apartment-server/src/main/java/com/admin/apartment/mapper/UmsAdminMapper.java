package com.admin.apartment.mapper;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.UmsAdminParams;
import com.admin.apartment.model.UmsAdminResponse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 更新用户登录时间
     * @param username
     * @return
     */
    int updateByUsername(String username);

    /**
     * 修改用户名
     * @param umsAdmin
     * @return
     */
    int changeUsername(UmsAdminParams umsAdmin);

    /**
     * 修改密码
     * @param umsAdmin
     * @return
     */
    int changePassword(UmsAdminParams umsAdmin);

    /**
     * 修改手机号
     * @param umsAdmin
     * @return
     */
    int changePhone(UmsAdminParams umsAdmin);

    /**
     * 模糊查询用户信息
     * */
    Page<UmsAdminResponse> selectUmsAdminByLike(IPage<UmsAdminParams> umsAdminParamsIPage);

    /**
     * 新增一条数据返回 id 添加系统管理员
     * */
    int insertReturnId(UmsAdminParams umsAdmin);

    /**
     * 修改账户启用禁用状态
     * @param umsAdmin
     * @return
     */
    int changeStatus(UmsAdminParams umsAdmin);

    /**
     * 修改账户启用禁用状态
     * @param repairId
     * @return
     */
    int deleteByRepairId(long repairId);

    /**
     * 修改账户启用禁用状态
     * @param userId
     * @return
     */
    int deleteByUserId(long userId);

}
