package com.admin.apartment.service;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.UserParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 租户信息 服务类
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
public interface IUserService extends IService<User> {

    /**
     * 查询用户信息
     * */
    Page<User> selectUserByInfo(IPage<User> page);

    /**
     * 模糊查询用户信息
     * */
    Page<User> selectUserByLike(UserParams params);

    /**
     * 新增用户信息
     * */
    boolean insertUser(User user);

    /**
     * 更新用户信息
     * */
    boolean updateUserById(User user);

    /**
     * 删除用户信息
     * */
    boolean deleteUserById(String id);

    /**
     * 查询用户信息通过 id
     * */
    User selectUserById(String userid);

    /**
     * 查询用户信息通过 name
     * */
    List<User> selectUserByName(String username);

    /**
     * 获取全部的证件类型
     * */
    List<FiltersTag> selectIdTypeList();


}
