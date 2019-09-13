package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendBatchSmsReqParams {

    /**
     * 接收短信的手机号码，JSON数组格式。
     *
     * 手机号码格式：
     *
     * 国内短信：11位手机号码，例如15900000000。
     * 国际/港澳台消息：国际区号+号码，例如85200000000。
     * ["15900000000","13500000000"]
     * */
    private String PhoneNumberJson;

    /**
     * 短信签名名称，JSON数组格式。
     *
     * 请在控制台签名管理页面签名名称一列查看。
     * ["阿里云","阿里巴巴"]
     * */
    private String SignNameJson;

    /**
     * 短信模板CODE。请在控制台模板管理页面模板CODE一列查看。
     * */
    private String TemplateCode;

    /**
     * 主账号AccessKey的ID。
     * */
    private String AccessKeyId;

    /**
     * 系统规定参数。取值：SendBatchSms。
     * */
    private String Action = "SendBatchSms";

    /**
     * 上行短信扩展码，JSON数组格式。无特殊需要此字段的用户请忽略此字段。
     * */
    private String SmsUpExtendCodeJson;

    /**
     * 短信模板变量对应的实际值，JSON格式。
     * */
    private String TemplateParamJson;

}
