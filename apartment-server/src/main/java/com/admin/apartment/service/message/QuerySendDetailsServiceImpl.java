package com.admin.apartment.service.message;

import com.admin.apartment.model.message.QuerySendDetailsParams;
import com.admin.apartment.model.message.QuerySendDetailsResponse;
import com.admin.apartment.model.message.SendSmsResponse;
import com.admin.apartment.service.IQuerySendDetailsService;
import com.admin.apartment.utils.MessageUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QuerySendDetailsServiceImpl implements IQuerySendDetailsService {

    @Autowired
    MessageUtil messageUtil;

    @Value("${aliyun.RegionId}")
    private String RegionId;

    /**
     *调用QuerySendDetails接口查看短信发送记录和发送状态。
     * */
    @Override
    public QuerySendDetailsResponse querySendDetails(QuerySendDetailsParams params) {

        CommonRequest request = messageUtil.commonRequest();
        request.setAction(params.getAction());
        request.putQueryParameter("RegionId", RegionId);
        request.putQueryParameter("PhoneNumber", "asdf");
        request.putQueryParameter("SendDate", "asdf");
        request.putQueryParameter("CurrentPage", "asdf");
        request.putQueryParameter("BizId", "asdf");
        CommonResponse response =  messageUtil.commonResponse(request);
        return JSONObject.parseObject(response.getData(), QuerySendDetailsResponse.class);
    }
}
