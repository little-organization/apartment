package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendSmsReqParams {

    /**
     * 示例：15900000000
     * 接收短信的手机号码。
     *
     * 格式：
     *
     * 国内短信：11位手机号码，例如15951955195。
     * 国际/港澳台消息：国际区号+号码，例如85200000000。
     * 支持对多个手机号码发送短信，手机号码之间以英文逗号（,）分隔。上限为1000个手机号码。批量调用相对于单条调用及时性稍有延迟。
     * */
    private String PhoneNumbers;

    /**
     * 示例：阿里云
     * 短信签名名称。请在控制台签名管理页面签名名称一列查看。
     * */
    private String SignName;

    /**
     * 示例：SMS_153055065
     * 短信模板ID。请在控制台模板管理页面模板CODE一列查看。
     * */
    private String TemplateCode;

    /**
     * 示例：LTAIP00vvvvvvvvv
     * 主账号AccessKey的ID。
     * */
    private String AccessKeyId;

    /**
     * 示例：SendSms
     * 系统规定参数。取值：SendSms-单个短信发送。
     * */
    private String Action = "SendSms";

    /**
     * 示例：abcdefgh
     * 外部流水扩展字段。
     * */
    private String OutId;

    /**
     * 示例：90999
     * 上行短信扩展码，无特殊需要此字段的用户请忽略此字段。
     * */
    private String SmsUpExtendCode;

    /**
     * 示例：{"code":"1111"}
     * 短信模板变量对应的实际值，JSON格式。
     * */
    private String TemplateParam;
}
