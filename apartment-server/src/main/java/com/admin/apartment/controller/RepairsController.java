package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.File;
import com.admin.apartment.entity.Repairs;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.RepairsParams;
import com.admin.apartment.service.IRepairsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 报修表 前端控制器
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Controller
@RequestMapping("/apartment/repairs")
public class RepairsController {

    @Autowired
    IRepairsService iRepairsService;

    /**
     * 获取公寓信息
     * */
    @RequestMapping(value = "/repairsList",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult repairsList(@RequestBody RepairsParams params){
        Page<Repairs> repairsList= iRepairsService.selectRepairsByLike(params);
        return CommonResult.success(repairsList);
    }

    /**
     * 查询所有的修理状态
     * */
    @RequestMapping(value = "/selectStatusList",method = RequestMethod.GET)
    public @ResponseBody
    CommonResult selectStatusList(){
        List<FiltersTag> list = iRepairsService.selectStatusList();
        return CommonResult.success(list);
    }

    /**
     * 更新报修信息
     * */
    @RequestMapping(value = "/updateRepair",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult updateRepair(@RequestBody Repairs repairs){
        boolean result = iRepairsService.updateRepair(repairs);
        return CommonResult.success(result);
    }

    /**
     * 新增报修信息
     * */
    @RequestMapping(value = "/insertRepairAndFile",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult insertRepairAndFile(@RequestBody Repairs repair){
        boolean result = iRepairsService.insertRepairAndFile(repair);
        return CommonResult.success(result);
    }

}
