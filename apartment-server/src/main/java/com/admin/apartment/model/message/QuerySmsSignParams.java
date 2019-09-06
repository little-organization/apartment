package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuerySmsSignParams {

    /**
     * 页数（阿里短信平台接口没有此参数）
     * */
    private Integer page;
    /**
     * 每页多少条（阿里短信平台接口没有此参数）
     * */
    private Integer limit;

    /**
     * 签名名称。
     * */
    private String SignName;

    /**
     * 主账号AccessKey的ID。
     * */
    private String AccessKeyId;

    /**
     * 系统规定参数。取值：QuerySmsSign。
     * */
    private String Action = "QuerySmsSign";

    public QuerySmsSignParams(String signName) {
        SignName = signName;
    }
}
