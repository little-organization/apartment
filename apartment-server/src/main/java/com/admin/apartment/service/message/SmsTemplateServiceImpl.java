package com.admin.apartment.service.message;

import com.admin.apartment.entity.MsgTemplate;
import com.admin.apartment.model.message.*;
import com.admin.apartment.service.ISmsTemplateService;
import com.admin.apartment.utils.MessageUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsTemplateServiceImpl implements ISmsTemplateService {

    @Autowired
    MessageUtil messageUtil;

    @Value("${aliyun.regionId}")
    private String RegionId;

    /**
     * 创建模板
     * */
    @Override
    public SmsTemplateResponse addSmsTemplate(AddSmsTemplateParams params) {
        CommonRequest request = messageUtil.commonRequest();
        request.setAction(params.getAction());
        request.putQueryParameter("RegionId", RegionId);
        // 短信号码
        request.putQueryParameter("TemplateType", params.getTemplateType());
        // 签名
        request.putQueryParameter("TemplateName", params.getTemplateName());
        // 模板 ID
        request.putQueryParameter("TemplateContent", params.getTemplateContent());
        // 模板参数
        request.putQueryParameter("Remark", params.getRemark());
        CommonResponse response =  messageUtil.commonResponse(request);
        return JSONObject.parseObject(response.getData(), SmsTemplateResponse.class);
    }

    /**
     * 删除模板
     * */
    @Override
    public SmsTemplateResponse deleteSmsTemplate(DeleteSmsTemplateParams params) {
        CommonRequest request = messageUtil.commonRequest();
        request.setAction(params.getAction());
        request.putQueryParameter("RegionId", RegionId);
        // 模板 code
        request.putQueryParameter("TemplateCode", params.getTemplateCode());
        CommonResponse response =  messageUtil.commonResponse(request);
        return JSONObject.parseObject(response.getData(), SmsTemplateResponse.class);
    }

    /**
     * 修改模板
     * */
    @Override
    public MsgTemplate modifySmsTemplate(MsgTemplate params) {
        CommonRequest request = messageUtil.commonRequest();
        request.setAction("ModifySmsTemplate");
        request.putQueryParameter("RegionId", RegionId);
        // 短信号码
        request.putQueryParameter("TemplateType", String.valueOf(params.getTemplateType()));
        // 签名
        request.putQueryParameter("TemplateName", params.getTemplateName());
        // 模板 code
        request.putQueryParameter("TemplateCode", params.getTemplateCode());
        // 模板 ID
        request.putQueryParameter("TemplateContent", params.getTemplateContent());
        // 模板参数
        request.putQueryParameter("Remark", "对【"+params.getTemplateName()+"】更改一下模板名称");
        CommonResponse response =  messageUtil.commonResponse(request);
        return JSONObject.parseObject(response.getData(), MsgTemplate.class);
    }

    /**
     * 查询模板
     * */
    @Override
    public MsgTemplate querySmsTemplate(QuerySmsTemplateParams params) {
        CommonRequest request = messageUtil.commonRequest();
        request.setAction(params.getAction());
        request.putQueryParameter("RegionId", RegionId);
        // 模板 code 测试模板 SMS_173245372
        request.putQueryParameter("TemplateCode", params.getTemplateCode());
        CommonResponse response =  messageUtil.commonResponse(request);
        return JSONObject.parseObject(response.getData(), MsgTemplate.class);
    }
}
