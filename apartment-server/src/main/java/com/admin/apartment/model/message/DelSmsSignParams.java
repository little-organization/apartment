package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author liangming
 */
@Getter
@Setter
@NoArgsConstructor
public class DelSmsSignParams {

    /**
     * 签名名称。
     * */
    private String SignName;

    /**
     * 主账号AccessKey的ID。
     * */
    private String AccessKeyId;

    /**
     * 系统规定参数。取值：DeleteSmsSign。
     * */
    private String Action = "DeleteSmsSign";
}
