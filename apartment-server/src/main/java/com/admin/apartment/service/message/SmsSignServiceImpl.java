package com.admin.apartment.service.message;

import com.admin.apartment.model.message.DelSmsSignParams;
import com.admin.apartment.model.message.QuerySmsSignParams;
import com.admin.apartment.model.message.QueryDelSmsSignResponse;
import com.admin.apartment.service.ISmsSignService;
import com.admin.apartment.utils.MessageUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsSignServiceImpl implements ISmsSignService {

    @Autowired
    MessageUtil messageUtil;

    @Value("${aliyun.RegionId}")
    private String RegionId;

    @Override
    public QueryDelSmsSignResponse deleteSmsSign(DelSmsSignParams params) {
        CommonRequest request = messageUtil.commonRequest();
        request.setAction(params.getAction());
        request.putQueryParameter("RegionId", RegionId);
        // 签名
        request.putQueryParameter("SignName", params.getSignName());
        CommonResponse response =  messageUtil.commonResponse(request);
        return JSONObject.parseObject(response.getData(), QueryDelSmsSignResponse.class);
    }

    @Override
    public QueryDelSmsSignResponse querySmsSign(QuerySmsSignParams params) {
        CommonRequest request = messageUtil.commonRequest();
        request.setAction(params.getAction());
        request.putQueryParameter("RegionId", RegionId);
        // 签名
        request.putQueryParameter("SignName", params.getSignName());
        CommonResponse response =  messageUtil.commonResponse(request);
        return JSONObject.parseObject(response.getData(), QueryDelSmsSignResponse.class);
    }
}
