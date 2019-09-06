package com.admin.apartment.service.message;

import com.admin.apartment.model.message.SendBatchSmsReqParams;
import com.admin.apartment.model.message.SendSmsReqParams;
import com.admin.apartment.model.message.SendSmsResponse;
import com.admin.apartment.service.ISendMessageService;
import com.admin.apartment.utils.MessageUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl implements ISendMessageService {

    @Autowired
    MessageUtil messageUtil;

    @Value("${aliyun.RegionId}")
    private String RegionId;

    /**
     * 单个短信发送
     * */
    @Override
    public CommonResponse SendSms(SendSmsReqParams params) {
        CommonRequest request = messageUtil.commonRequest();
        request.setAction(params.getAction());
        request.putQueryParameter("RegionId", RegionId);
        // 短信号码
        request.putQueryParameter("PhoneNumbers", params.getPhoneNumbers());
        // 签名
        request.putQueryParameter("SignName", params.getSignName());
        // 模板 ID
        request.putQueryParameter("TemplateCode", params.getTemplateCode());
        // 模板参数
        request.putQueryParameter("TemplateParam", params.getTemplateParam());
        return messageUtil.commonResponse(request);
    }

    /**
     * 批量短信发送
     * */
    @Override
    public SendSmsResponse SendBatchSms(SendBatchSmsReqParams params) {
        CommonRequest request = messageUtil.commonRequest();
        request.setAction(params.getAction());
        request.putQueryParameter("RegionId", RegionId);
        // 短信号码
        request.putQueryParameter("PhoneNumberJson", params.getPhoneNumberJson());
        // 签名
        request.putQueryParameter("SignNameJson",params.getSignNameJson());
        // 模板 ID
        request.putQueryParameter("TemplateCode", params.getTemplateCode());
        // 模板参数
        request.putQueryParameter("TemplateParamJson", params.getTemplateParamJson()==null?"[\"\"]":params.getTemplateParamJson());
        CommonResponse response =  messageUtil.commonResponse(request);
        return JSONObject.parseObject(response.getData(), SendSmsResponse.class);
    }

}
