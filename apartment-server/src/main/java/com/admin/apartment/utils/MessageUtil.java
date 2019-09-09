package com.admin.apartment.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MessageUtil {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessSecret}")
    private String accessSecret;
    @Value("${aliyun.regionId}")
    private String RegionId;

    /**
     * 短信发送公共参数添加
     * */
    public CommonRequest commonRequest() {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.putQueryParameter("AccessKeyId",accessKeyId);
        return request;
    }

    /**
     * 短信发送公共请求方法
     * */
    public CommonResponse commonResponse(CommonRequest request) {
        DefaultProfile profile = DefaultProfile.getProfile(RegionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response;
    }
}
