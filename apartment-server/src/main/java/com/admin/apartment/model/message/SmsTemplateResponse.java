package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SmsTemplateResponse extends BaseResponse {

    /**
     * 短信模板CODE。您可以使用模板CODE通过API接口QuerySmsTemplate或在控制台查看模板申请状态和结果。
     * */
    private String TemplateCode;
}
