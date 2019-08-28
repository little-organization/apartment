package com.admin.apartment.service;

import com.admin.apartment.model.message.QuerySendDetailsParams;
import com.admin.apartment.model.message.QuerySendDetailsResponse;
import com.aliyuncs.CommonResponse;

public interface IQuerySendDetailsService {

    /**
     *调用QuerySendDetails接口查看短信发送记录和发送状态。
     * */
    public QuerySendDetailsResponse querySendDetails(QuerySendDetailsParams params);
}
