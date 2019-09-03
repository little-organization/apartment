package com.admin.apartment.controller;

import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.model.message.*;
import com.admin.apartment.service.IQuerySendDetailsService;
import com.admin.apartment.service.ISendMessageService;
import com.admin.apartment.service.ISmsSignService;
import com.admin.apartment.service.ISmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 添加模板
     * */
    @RequestMapping(value = "/addSmsTemplate",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult addSmsTemplate(@RequestBody AddSmsTemplateParams params){
        SmsTemplateResponse response = iSmsTemplateService.addSmsTemplate(params);
        return CommonResult.success(response);
    }

    /**
     * 删除模板
     * */
    @RequestMapping(value = "/deleteSmsTemplate",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult deleteSmsTemplate(@RequestBody DeleteSmsTemplateParams params){
        SmsTemplateResponse response = iSmsTemplateService.deleteSmsTemplate(params);
        return CommonResult.success(response);
    }

    /**
     * 修改模板
     * */
    @RequestMapping(value = "/updateSmsTemplate",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult updateSmsTemplate(@RequestBody ModifySmsTemplateParams params){
        SmsTemplateResponse response = iSmsTemplateService.modifySmsTemplate(params);
        return CommonResult.success(response);
    }

    /**
     * 查询模板
     * */
    @RequestMapping(value = "/querySmsTemplate",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult querySmsTemplate(@RequestBody QuerySmsTemplateParams params){
        SmsTemplateQueryResponse response = iSmsTemplateService.querySmsTemplate(params);
        return CommonResult.success(response);
    }

    /**
     * 删除签名
     * */
    @RequestMapping(value = "/deleteSmsSign",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult deleteSmsSign(@RequestBody DelSmsSignParams params){
        QueryDelSmsSignResponse response = iSmsSignService.deleteSmsSign(params);
        return CommonResult.success(response);
    }

    /**
     * 查询签名
     * */
    @RequestMapping(value = "/querySmsSign",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult querySmsSign(@RequestBody QuerySmsSignParams params){
        QueryDelSmsSignResponse response = iSmsSignService.querySmsSign(params);
        return CommonResult.success(response);
    }

}
