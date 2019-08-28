package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModifySmsTemplateParams extends AddSmsTemplateParams {

    /**
     * 短信模板CODE。您可以在控制台模板管理页面或API接口AddSmsTemplate的返回参数中获取短信模板CODE。
     * */
    private String TemplateCode;

    /**
     * 系统规定参数。取值：ModifySmsTemplate。
     * */
    private String Action="ModifySmsTemplate";
}
