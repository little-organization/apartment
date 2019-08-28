package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddSmsTemplateParams {

    /**
     * 短信类型。其中：
     *
     * 0：验证码。
     * 1：短信通知。
     * 2：推广短信。
     * 3：国际/港澳台消息。
     * */
    private String TemplateType;

    /**
     * 模板名称，长度为1~30个字符。
     * */
    private String TemplateName;

    /**
     * 模板内容，长度为1~500个字符。
     *
     * 模板内容需要符合文本短信模板规范或国际/港澳台短信模板规范。
     * */
    private String TemplateContent;

    /**
     * 短信模板申请说明。请在申请说明中描述您的业务使用场景，长度为1~100个字符。
     * */
    private String Remark;

    /**
     * 系统规定参数。取值：AddSmsTemplate。
     * */
    private String Action="AddSmsTemplate";

    /**
     * 主账号AccessKey的ID。
     * */
    private String AccessKeyId;

}
