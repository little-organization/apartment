package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QueryDelSmsSignResponse extends BaseResponse {

    /**
     * 短信模板的创建日期和时间。
     * */
    private String CreateDate;

    /**
     * 审核备注。
     *
     * 如果审核状态为审核通过或审核中，参数Reason显示为“无审核备注”。
     * 如果审核状态为审核未通过，参数Reason显示审核的具体原因。
     * */
    private String Reason;

    /**
     * 短信签名。
     * */
    private String SignName;

    /**
     * 签名审核状态。其中：
     *
     * 0：审核中。
     * 1：审核通过。
     * 2：审核失败，请在返回参数Reason中查看审核失败原因。
     * */
    private Integer SignStatus;


}
