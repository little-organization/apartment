package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.service.IApartmentService;
import com.admin.apartment.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 公寓信息 前端控制器
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Controller
@RequestMapping("/apartment/apartment")
public class ApartmentController {

    @Autowired
    IApartmentService iApartmentService;

    /**
     * 获取公寓信息
     * */
    @RequestMapping(value = "/apartmentList",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult apartmentList(@RequestBody ApartmentParams params){
        Page<Apartment> apartmentList= iApartmentService.selectApartmentByLike(params);
        return CommonResult.success(apartmentList);
    }

    /**
     * 通过租户 id 查询租户租用的公寓
     * */
    @RequestMapping(value = "/getApartmentListByUserid",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult getApartmentListByUserid(@RequestBody String userid){
        List<Apartment> apartmentList= iApartmentService.getApartmentListByUserid(userid);
        return CommonResult.success(apartmentList);
    }

    /**
     * 查询所有的支付模式
     * */
    @RequestMapping(value = "/selectPattern",method = RequestMethod.GET)
    public @ResponseBody
    CommonResult selectPattern(){
        List<FiltersTag> list = iApartmentService.selectPattern();
        return CommonResult.success(list);
    }

    /**
     * 查询所有的户型
     * */
    @RequestMapping(value = "/selectHouseType",method = RequestMethod.GET)
    public @ResponseBody
    CommonResult selectHouseType(){
        List<FiltersTag> list = iApartmentService.selectHouseType();
        return CommonResult.success(list);
    }

    /**
     * 查询所有的朝向
     * */
    @RequestMapping(value = "/selectFaceList",method = RequestMethod.GET)
    public @ResponseBody
    CommonResult selectFaceList(){
        List<FiltersTag> list = iApartmentService.selectFaceList();
        return CommonResult.success(list);
    }

    /**
     * 创建公寓信息
     * */
    @RequestMapping(value = "/createApartment",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult createApartment(@RequestBody Apartment apartment){
        boolean result = iApartmentService.createApartment(apartment);
        return CommonResult.success(result);
    }

    /**
     * 更新公寓信息
     * */
    @RequestMapping(value = "/updateApartment",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult updateApartment(@RequestBody Apartment apartment){
        boolean result = iApartmentService.updateApartment(apartment);
        return CommonResult.success(result);
    }

    /**
     * 删除公寓信息
     * */
    @RequestMapping(value = "/deleteApartment",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult deleteApartment(@RequestBody String id){
        boolean result = iApartmentService.deleteApartment(id);
        return CommonResult.success(result);
    }

    /**
     * 移除公寓中的租户信息设置为未出租
     * */
    @RequestMapping(value = "/deleteApartmentUser",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult deleteUserInfoInApartmentById(@RequestBody String id){
        boolean result = iApartmentService.deleteUserInfoInApartmentById(id);
        return CommonResult.success(result);
    }

    /**
     * 通过id 查询公寓
     * */
    @RequestMapping(value = "/getApartmentById",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult getApartmentById(@RequestBody String id){
        Apartment apartment= iApartmentService.getApartmentById(id);
        return CommonResult.success(apartment);
    }



}
