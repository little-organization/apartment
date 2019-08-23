package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.UserParams;
import com.admin.apartment.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 租户信息 前端控制器
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Controller
@RequestMapping("/apartment/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    /**
     * 获取公寓信息
     * */
    @RequestMapping(value = "/userList",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult userList(@RequestBody UserParams params){
        Page<User> userList= iUserService.selectUserByLike(params);
        return CommonResult.success(userList);
    }

    /**
     * 查询租户信息通过 id
     * */
    @RequestMapping(value = "/selectUserById",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult selectUserById(@RequestBody String userid){
        User user = iUserService.selectUserById(userid);
        return CommonResult.success(user);
    }

    /**
     * 查询租户信息通过姓名
     * */
    @RequestMapping(value = "/selectUserByName",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult selectUserByName(@RequestBody String username){
        List<User> userList = iUserService.selectUserByName(username);
        return CommonResult.success(userList);
    }

    /**
     * 查询租户所有证件类型
     * */
    @RequestMapping(value = "/selectIdTypeList",method = RequestMethod.GET)
    public @ResponseBody
    CommonResult selectIdTypeList(){
        List<FiltersTag> idTypeList = iUserService.selectIdTypeList();
        return CommonResult.success(idTypeList);
    }

    /**
     * 更新租户信息
     * */
    @RequestMapping(value = "/updateUserById",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult updateUserById(@RequestBody User user){
        boolean result = iUserService.updateUserById(user);
        return CommonResult.success(result);
    }

    /**
     * 创建租户信息
     * */
    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult createUser(@RequestBody User user){
        boolean result = iUserService.insertUser(user);
        return CommonResult.success(result);
    }

    /**
     * 删除租户信息
     * */
    @RequestMapping(value = "/deleteUserById",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult deleteUserById(@RequestBody String id){
        boolean result = iUserService.deleteUserById(id);
        return CommonResult.success(result);
    }


}
