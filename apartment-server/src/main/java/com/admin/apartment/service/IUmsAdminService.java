package com.admin.apartment.service;

import com.admin.apartment.entity.UmsAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

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
}
