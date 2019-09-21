package com.admin.apartment.controller;

import com.admin.apartment.common.CommonResult;
import com.admin.apartment.model.huohe.*;
import com.admin.apartment.service.IApartmentService;
import com.admin.apartment.service.IHuoHeService;
import com.admin.apartment.utils.DESEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liangming
 */
@Controller
@RequestMapping("/apartment/doorlock")
public class DoorLockController {

    @Autowired
    IHuoHeService iHuoHeService;

    /**
     * 查询门锁列表
     * */
    @RequestMapping(value = "/selectDoorLockList",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult selectDoorLockList(@RequestBody LockListRequest request){
        LockListResponse response = iHuoHeService.selectDoorLockList(request);
        return CommonResult.success(response);
    }

    /**
     * 查询门锁详情
     * */
    @RequestMapping(value = "/selectDoorLockDetails",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult selectDoorLockDetails(@RequestBody LockViewRequest request){
        LockViewResponse response = iHuoHeService.selectDoorLockDetails(request);
        return CommonResult.success(response);
    }

    /**
     * 查询门锁密码信息
     * */
    @RequestMapping(value = "/selectDoorLockPwdInfo",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult selectDoorLockPwdInfo(@RequestBody PwdListRequest request){
        List<PwdListResponse> response = iHuoHeService.selectDoorLockPwdInfo(request);
        return CommonResult.success(response);
    }

    /**
     * 新增自定义密码
     * */
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult changePassword(@RequestBody AddSelfPwdRequest request) throws Exception {
        if (request.getPwd_text()!=null && request.getPwd_text().length()>0) {
            DESEncrypt desEncrypt = new DESEncrypt();
            request.setPwd_text(desEncrypt.encrypt(request.getPwd_text()));
        }
        AddSelfPwdResponse response = iHuoHeService.changePassword(request);
        return CommonResult.success(response);
    }
}
