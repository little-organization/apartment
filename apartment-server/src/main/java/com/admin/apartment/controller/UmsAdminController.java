package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.UmsAdminParams;
import com.admin.apartment.model.UmsAdminResponse;
import com.admin.apartment.service.IUmsAdminService;
import com.admin.apartment.utils.JwtTokenUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author liangming
 * @since 2019-08-12
 */
@RestController
@RequestMapping("/apartment/ums-admin")
public class UmsAdminController {

    @Autowired
    IUmsAdminService iUmsAdminService;

    /**
     * 个人信息
     * */
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public @ResponseBody
    UmsAdmin getInfo(@RequestParam UmsAdmin umsAdmin){
        return iUmsAdminService.getById(umsAdmin.getId());
    }

    /**
     * 获取用户信息
     * */
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult getUserList(@RequestBody UmsAdminParams params){
        Page<UmsAdminResponse> umsAdminPage= iUmsAdminService.selectUmsAdminByLike(params);
        return CommonResult.success(umsAdminPage);
    }

    /**
     * 添加系统管理员信息
     * */
    @RequestMapping(value = "/insertAdminInfo", method = RequestMethod.POST)
    public @ResponseBody
    CommonResult insertAdminInfo(@RequestBody UmsAdminParams umsAdmin){
        iUmsAdminService.insertAdminInfo(umsAdmin);
        return CommonResult.success(umsAdmin);
    }

    /**
     * 删除用户信息
     * */
    @RequestMapping(value = "/delAdminInfo/{id}", method = RequestMethod.GET)
    public @ResponseBody CommonResult delAdminInfo(@PathVariable("id") long id){
        boolean result =  iUmsAdminService.delUmsAdmin(id);
        return CommonResult.success(result);
    }

    /**
     * 检查用户密码是否正确
     * */
    @RequestMapping(value = "/checkPassword",method = RequestMethod.POST)
    public @ResponseBody CommonResult checkPassword(@RequestBody UmsAdminParams params){
        boolean result = iUmsAdminService.checkPassword(params);
        return CommonResult.success(result);
    }

    /**
     * 修改Password
     * @param params
     * @return
     */
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public @ResponseBody CommonResult changePassword(@RequestBody UmsAdminParams params){
        boolean result = iUmsAdminService.changePassword(params);
        return CommonResult.success(result);
    }

    /**
     * 修改账户的启用禁用状态
     * @param params
     * @return
     */
    @RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
    public @ResponseBody CommonResult changeStatus(@RequestBody UmsAdminParams params){
        boolean result = iUmsAdminService.changeStatus(params);
        return CommonResult.success(result);
    }

    /**
     * 修改用户名
     * @param params
     * @return
     */
    @RequestMapping(value = "/changeUsername",method = RequestMethod.POST)
    public @ResponseBody CommonResult changeUsername(@RequestBody UmsAdminParams params){
        boolean result = iUmsAdminService.changeUsername(params);
        return CommonResult.success(result);
    }

    /**
     * 修改Phone
     * @param params
     * @return
     */
    @RequestMapping(value = "/changePhone",method = RequestMethod.POST)
    public @ResponseBody CommonResult changePhone(@RequestBody UmsAdminParams params){
        boolean result = iUmsAdminService.changePhone(params);
        return CommonResult.success(result);
    }

    /**
     * 检查用户名
     * */
    @RequestMapping(value = "/checkUsername",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult checkUsername(@RequestBody UmsAdminParams params){
        boolean result = iUmsAdminService.checkUsername(params.getUsername());
        return CommonResult.success(result);

    }
}
