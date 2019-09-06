package com.admin.apartment.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class QuerySendDetailsParams {

    /**
     * 分页查看发送记录，指定发送记录的的当前页码。
     * */
    private String CurrentPage;

    /**
     * 分页查看发送记录，指定每页显示的短信记录数量。
     *
     * 取值范围为1~50。
     * */
    private String PageSize;

    /**
     * 接收短信的手机号码。
     *
     * 格式：
     *
     * 国内短信：11位手机号码，例如15900000000。
     * 国际/港澳台消息：国际区号+号码，例如85200000000。
     * */
    private String PhoneNumber;

    /**
     * 短信发送日期，支持查询最近30天的记录。
     *
     * 格式为yyyyMMdd，例如20181225。
     * */
    private Date SendDate;

    /**
     * 主账号AccessKey的ID，即Key。
     * */
    private String AccessKeyId;

    /**
     * 系统规定参数。取值：QuerySendDetails。
     * */
    private String Action="QuerySendDetails";

    /**
     * 发送回执ID，即发送流水号。调用发送接口SendSms或SendBatchSms发送短信时，返回值中的BizId字段。
     * */
    private String BizId;
}
