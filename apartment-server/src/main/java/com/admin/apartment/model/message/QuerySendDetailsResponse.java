package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuerySendDetailsResponse extends BaseResponse{

    /**
     * 短信发送总条数。
     * */
    private Integer TotalCount;

    /**
     * 短信发送总条数。
     * */
    private SmsSendDetailDTOs SmsSendDetailDTOs;
}

@Getter
@Setter
@NoArgsConstructor
class SmsSendDetailDTOs{

    private List<SmsSendDetailDTO> SmsSendDetailDTO;
}

@Getter
@Setter
@NoArgsConstructor
class SmsSendDetailDTO{

    /**
     * 短信内容。
     * */
    private String Content;

    /**
     * 运营商短信状态码。
     *
     * 短信发送成功：DELIVRD。
     * 短信发送失败：失败错误码请参考错误码文档。。https://help.aliyun.com/document_detail/101347.html?spm=a2c4g.11186623.2.14.4d9ebc45sLtLtX
     * */
    private String ErrCode;

    /**
     * 外部流水扩展字段。。
     * */
    private String OutId;

    /**
     * 接收短信的手机号码。
     * */
    private String PhoneNum;

    /**
     * 短信接收日期和时间。。
     * */
    private String ReceiveDate;

    /**
     * 短信发送日期和时间。。
     * */
    private String SendDate;

    /**
     * 短信发送状态，包括：
     *
     * 1：等待回执。
     * 2：发送失败。
     * 3：发送成功。。
     * */
    private String SendStatus;

    /**
     * 短信模板ID。。
     * */
    private String TemplateCode;

}
