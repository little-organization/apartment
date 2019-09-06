package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuerySmsTemplateParams {

    /**
     * 页数（阿里短信平台接口没有此参数）
     * */
    private Integer page;
    /**
     * 每页多少条（阿里短信平台接口没有此参数）
     * */
    private Integer limit;


    /**
     * 系统规定参数。取值：QuerySmsTemplate。
     * */
    private String Action="QuerySmsTemplate";

    /**
     * 主账号AccessKey的ID。
     * */
    private String AccessKeyId;

    /**
     * 短信模板CODE。您可以在控制台模板管理页面或API接口AddSmsTemplate的返回参数中获取短信模板CODE
     * */
    private String TemplateCode;

    public QuerySmsTemplateParams(String templateCode) {
        TemplateCode = templateCode;
    }
}
