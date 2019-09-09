package com.admin.apartment.service;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.UmsAdminParams;
import com.admin.apartment.model.UmsAdminResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author liangming
 * @since 2019-08-12
 */
public interface IUmsAdminService extends IService<UmsAdmin>,UserDetailsService {

    String login(UmsAdmin umsAdmin);

    UmsAdmin userinfo(String token);

    boolean checkUsername(String username);

    /**
     * 检查用户密码是否正确
     * */
    boolean checkPassword(UmsAdminParams umsAdmin);

    /**
     * 修改用户名
     * @param umsAdmin
     * @return
     */
    boolean changeUsername(UmsAdminParams umsAdmin);

    /**
     * 修改密码
     * @param umsAdmin
     * @return
     */
    boolean changePassword(UmsAdminParams umsAdmin);

    /**
     * 修改手机号
     * @param umsAdmin
     * @return
     */
    boolean changePhone(UmsAdminParams umsAdmin);

    /**
     * 添加系统管理员信息
     * */
    long insertAdminInfo(UmsAdminParams umsAdmin);

    /**
     * 模糊查询用户信息
     * */
    Page<UmsAdminResponse> selectUmsAdminByLike(UmsAdminParams params);

    /**
     * 模糊查询用户信息
     * */
    boolean delUmsAdmin(long id);

    /**
     * 修改账户启用禁用状态
     * @param umsAdmin
     * @return
     */
    boolean changeStatus(UmsAdminParams umsAdmin);
}
