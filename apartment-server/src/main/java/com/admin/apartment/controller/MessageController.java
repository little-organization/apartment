package com.admin.apartment.controller;

import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.MsgSign;
import com.admin.apartment.entity.MsgTemplate;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.message.*;
import com.admin.apartment.service.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartment/message")
public class MessageController {

    @Autowired
    IQuerySendDetailsService iQuerySendDetailsService;

    @Autowired
    ISendMessageService iSendMessageService;

    @Autowired
    ISmsTemplateService iSmsTemplateService;

    @Autowired
    ISmsSignService iSmsSignService;

    @Autowired
    IMsgSignService iMsgSignService;

    @Autowired
    IMsgTemplateService iMsgTemplateService;

    /**
     * 发送短信
     * */
    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult sendMsg(@RequestBody SendBatchSmsReqParams params){
        SendSmsResponse response = iSendMessageService.SendBatchSms(params);
        return CommonResult.success(response);
    }

    /**
     * 查看短信发送记录和发送状态。
     * */
    @RequestMapping(value = "/queryMsgDetails",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult queryMsgDetails(@RequestBody QuerySendDetailsParams params){
        QuerySendDetailsResponse response = iQuerySendDetailsService.querySendDetails(params);
        return CommonResult.success(response);
    }

    /**
     * 查询模板所有类型
     * */
    @RequestMapping(value = "/selectTemplateCodeAndNameList",method = RequestMethod.GET)
    public @ResponseBody
    CommonResult selectTemplateCodeAndNameList(){
        List<FiltersTag> response = iMsgTemplateService.selectTemplateCodeAndNameList();
        return CommonResult.success(response);
    }

    /**
     * 添加模板（查询阿里模板然后添加到数据库，先验证数据库中不存在）
     * */
    @RequestMapping(value = "/insertTemplateSignFromAli",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult querySmsTemplateFromAli(@RequestBody String templateCode){
        MsgTemplate response = iMsgTemplateService.querySmsTemplateFromAli(templateCode);
        return CommonResult.success(response);
    }

    /**
     * 删除模板
     * */
    @RequestMapping(value = "/deleteSmsTemplate",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult deleteSmsTemplate(@RequestBody String templateCode){
        boolean response =  iMsgTemplateService.delSmsTemplateFromAli(templateCode);
        return CommonResult.success(response);
    }

    /**
     * 查询模板
     * */
    @RequestMapping(value = "/querySmsTemplate",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult querySmsTemplate(@RequestBody QuerySmsTemplateParams params){
        Page<MsgTemplate> response = iMsgTemplateService.querySmsTemplateList(params);
        return CommonResult.success(response);
    }

    /**
     * 删除签名
     * */
    @RequestMapping(value = "/deleteSmsSign",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult deleteSmsSign(@RequestBody String signName){
        boolean response = iMsgSignService.delSmsSignFromAli(signName);
        return CommonResult.success(response);
    }

    /**
     * 查询签名
     * */
    @RequestMapping(value = "/querySmsSign",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult querySmsSign(@RequestBody(required = false) QuerySmsSignParams params){
        Page<MsgSign> response = iMsgSignService.querySmsSignList(params);
        return CommonResult.success(response);
    }

    /**
     * 添加签名（查询阿里签名然后添加到数据库，先验证数据库中不存在）
     * */
    @RequestMapping(value = "/insertSmsSignFromAli",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult querySmsSignFromAli(@RequestBody String signName){
        MsgSign response = iMsgSignService.querySmsSignFromAli(signName);
        return CommonResult.success(response);
    }

}
