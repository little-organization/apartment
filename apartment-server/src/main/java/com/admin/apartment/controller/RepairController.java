package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.Repair;
import com.admin.apartment.entity.Repair;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.RepairParams;
import com.admin.apartment.service.IRepairService;
import com.admin.apartment.service.IRepairService;
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
 * 售后人员信息 前端控制器
 * </p>
 *
 * @author liangming
 * @since 2019-09-09
 */
@Controller
@RequestMapping("/apartment/repair")
public class RepairController {


    @Autowired
    IRepairService iRepairService;

    /**
     * 查询售后人员信息通过 id
     * */
    @RequestMapping(value = "/selectRepairById",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult selectRepairById(@RequestBody String repairId){
        Repair repair = iRepairService.selectRepairById(repairId);
        return CommonResult.success(repair);
    }

    /**
     * 获取公寓信息
     * */
    @RequestMapping(value = "/repairList",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult repairList(@RequestBody RepairParams params){
        Page<Repair> repairList= iRepairService.selectRepairByLike(params);
        return CommonResult.success(repairList);
    }

    /**
     * 查询售后人员信息通过姓名
     * */
    @RequestMapping(value = "/selectRepairByName",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult selectRepairByName(@RequestBody String repairname){
        List<Repair> repairList = iRepairService.selectRepairByName(repairname);
        return CommonResult.success(repairList);
    }

    /**
     * 查询售后人员所有证件类型
     * */
    @RequestMapping(value = "/selectIdTypeList",method = RequestMethod.GET)
    public @ResponseBody
    CommonResult selectIdTypeList(){
        List<FiltersTag> idTypeList = iRepairService.selectIdTypeList();
        return CommonResult.success(idTypeList);
    }

    /**
     * 更新售后人员信息
     * */
    @RequestMapping(value = "/updateRepairById",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult updateRepairById(@RequestBody Repair repair){
        boolean result = iRepairService.updateRepairById(repair);
        return CommonResult.success(result);
    }

    /**
     * 创建售后人员信息
     * */
    @RequestMapping(value = "/createRepair",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult createRepair(@RequestBody Repair repair){
        boolean result = iRepairService.insertRepair(repair);
        return CommonResult.success(result);
    }

    /**
     * 删除售后人员信息
     * */
    @RequestMapping(value = "/deleteRepairById",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult deleteRepairById(@RequestBody Repair repair){
        boolean result = iRepairService.deleteRepairById(repair);
        return CommonResult.success(result);
    }

}
