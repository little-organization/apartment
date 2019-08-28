package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuerySmsTemplateResponse {

    /**
     * 请求状态码。
     *
     * 返回OK代表请求成功。
     * 其他错误码详见错误码列表。
     * */
    private String Code;

    /**
     * 短信模板的创建日期和时间。
     * */
    private String CreateDate;

    /**
     * 状态码的描述。
     * */
    private String Message;

    /**
     * 审核备注。
     *
     * 如果审核状态为审核通过或审核中，参数Reason显示为“无审核备注”。
     * 如果审核状态为审核未通过，参数Reason显示审核的具体原因。
     * */
    private String Reason;

    /**
     * 请求ID。
     * */
    private String RequestId;

    /**
     * 短信模板CODE。
     * */
    private String TemplateCode;

    /**
     * 模板内容。
     * */
    private String TemplateContent;

    /**
     * 模板名称。
     * */
    private String TemplateName;

    /**
     * 模板审核状态。其中：
     *
     * 0：审核中。
     * 1：审核通过。
     * 2：审核失败，请在返回参数Reason中查看审核失败原因。
     * */
    private Integer TemplateStatus;

    /**
     * 短信类型。其中：
     *
     * 0：验证码。
     * 1：短信通知。
     * 2：推广短信。
     * 3：国际/港澳台消息。
     * */
    private Integer TemplateType;
}
