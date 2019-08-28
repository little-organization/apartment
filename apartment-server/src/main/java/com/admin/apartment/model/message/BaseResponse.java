package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {

    /**
     * 请求状态码。
     *
     * 返回OK代表请求成功。
     * 其他错误码详见错误码列表。https://help.aliyun.com/document_detail/101346.html?spm=a2c4g.11186623.2.14.3d7a56e06cnFI2
     * */
    private String Code;

    /**
     * 状态码的描述。
     * */
    private String Message;

    /**
     * 请求ID。
     * */
    private String RequestId;
}
