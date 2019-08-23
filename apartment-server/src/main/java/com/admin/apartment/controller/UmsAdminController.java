package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.service.IUmsAdminService;
import com.admin.apartment.utils.JwtTokenUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 管理员信息
     * */
    @RequestMapping("/getInfo")
    public UmsAdmin getInfo(@RequestParam UmsAdmin umsAdmin){
        return iUmsAdminService.getById(umsAdmin.getId());
    }

    /**
     * 查询管理员信息
     * */
    @RequestMapping("/selectAdminInfo")
    public IPage<UmsAdmin> selectAdminInfo(@RequestParam Page<UmsAdmin> page){
        return iUmsAdminService.page(page);
    }

    /**
     * 添加管理员信息
     * */
    @RequestMapping("/insertAdminInfo")
    public Boolean insertAdminInfo(@RequestParam UmsAdmin umsAdmin){
        return iUmsAdminService.save(umsAdmin);
    }

    /**
     * 删除管理员信息
     * */
    @RequestMapping("/delAdminInfo")
    public Boolean delAdminInfo(@RequestParam UmsAdmin umsAdmin){
        return iUmsAdminService.removeById(umsAdmin);
    }

    /**
     * 修改管理员信息
     * */
    @RequestMapping("/verbAdminInfo")
    public Boolean verbAdminInfo(@RequestParam UmsAdmin umsAdmin){
        return iUmsAdminService.saveOrUpdate(umsAdmin);
    }
}
