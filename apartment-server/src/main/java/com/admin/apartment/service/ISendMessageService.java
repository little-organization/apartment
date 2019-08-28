package com.admin.apartment.service;

import com.admin.apartment.model.message.SendBatchSmsReqParams;
import com.admin.apartment.model.message.SendSmsReqParams;
import com.admin.apartment.model.message.SendSmsResponse;
import com.aliyuncs.CommonResponse;

public interface ISendMessageService {

    /**
     * 单个短信发送
     * */
    public CommonResponse SendSms(SendSmsReqParams params);

    /**
     * 批量短信发送
     * */
    public SendSmsResponse SendBatchSms(SendBatchSmsReqParams params);
}
