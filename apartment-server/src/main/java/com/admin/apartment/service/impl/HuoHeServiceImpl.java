package com.admin.apartment.service.impl;
import java.util.*;

import cn.hutool.core.date.DateTime;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.model.huohe.*;
import com.admin.apartment.service.IHuoHeService;
import com.admin.apartment.utils.HttpURLConnectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HuoHeServiceImpl implements IHuoHeService {

    private static final Log LOGGER = LogFactory.get();

    /**
     * 接口版本号
     * */
    private final static String VERSION = "1.0";

    /**
     * 用户账号
     * */
    private final static String ACCOUNT = "17818339868";

    /**
     * 用户DESEncrypt加密后的密码
     * */
    private final static String PASSWORD = "ca9ef353a3cacb9644e405894e61f49b";

    /**
     * 用户访问凭证
     * */
    private static String ACCESS_TOKEN = "";

    /**
     * 查询门锁列表
     * */
    @Override
    public LockListResponse selectDoorLockList(LockListRequest request){
        LockListResponse lockListResponse = null;
        if (ACCESS_TOKEN.isEmpty()){
            loginHuoHe();
        }
        if (!ACCESS_TOKEN.isEmpty()){
            String result = null;
            try {
                result = doPost("/lock/list",JSONObject.parseObject(JSONObject.toJSONString(request)),ACCESS_TOKEN);
                LOGGER.info("红河对接查询门锁列表结果"+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseResponse response = JSONObject.parseObject(result,BaseResponse.class);
            if("HH0000".equals(response.getRlt_code())){
                lockListResponse = JSONObject.parseObject(JSONObject.toJSONString(response.getData()), LockListResponse.class);
            }
        }
        return lockListResponse;
    }

    /**
     * 查询门锁详情
     * */
    @Override
    public LockViewResponse selectDoorLockDetails(LockViewRequest request){
        LockViewResponse lockListResponse = null;
        if (ACCESS_TOKEN.isEmpty()){
            loginHuoHe();
        }
        if (!ACCESS_TOKEN.isEmpty()){
            String result = null;
            try {
                result = doPost("/lock/view",JSONObject.parseObject(JSONObject.toJSONString(request)),ACCESS_TOKEN);
                LOGGER.info("红河对接查询门锁详情结果"+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseResponse response = JSONObject.parseObject(result,BaseResponse.class);
            if("HH0000".equals(response.getRlt_code())){
                lockListResponse = JSONObject.parseObject(JSONObject.toJSONString(response.getData()), LockViewResponse.class);
            }
        }
        return lockListResponse;
    }

    /**
     * 查询门锁密码信息
     * */
    @Override
    public List<PwdListResponse> selectDoorLockPwdInfo(PwdListRequest request){
        List<PwdListResponse> pwdListResponse = null;
        if (ACCESS_TOKEN.isEmpty()){
            loginHuoHe();
        }
        if (!ACCESS_TOKEN.isEmpty()){
            String result = null;
            try {
                result = doPost("/pwd/list",JSONObject.parseObject(JSONObject.toJSONString(request)),ACCESS_TOKEN);
                LOGGER.info("红河对接查询门锁密码信息结果"+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseResponse response = JSONObject.parseObject(result,BaseResponse.class);
            if("HH0000".equals(response.getRlt_code())){
                pwdListResponse = JSONArray.parseArray(JSONObject.toJSONString(response.getData()), PwdListResponse.class);
            }
        }
        return pwdListResponse;
    }

    /**
     * 新增自定义密码
     * */
    @Override
    public AddSelfPwdResponse changePassword(AddSelfPwdRequest request){
        AddSelfPwdResponse addSelfPwdResponse = null;
        if (ACCESS_TOKEN.isEmpty()){
            loginHuoHe();
        }
        if (!ACCESS_TOKEN.isEmpty()){
            String result = null;
            try {
                result = doPost("/pwd/add",JSONObject.parseObject(JSONObject.toJSONString(request)),ACCESS_TOKEN);
                LOGGER.info("红河对接查询门锁密码信息结果"+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseResponse response = JSONObject.parseObject(result,BaseResponse.class);
            if("HH0000".equals(response.getRlt_code())){
                addSelfPwdResponse = JSONObject.parseObject(JSONObject.toJSONString(response.getData()), AddSelfPwdResponse.class);
            }
        }
        return addSelfPwdResponse;
    }

    /**
     * 登录
     * */
    @Scheduled(cron = "0 0 0/5 * * ?")
    private void loginHuoHe(){
        //请求体
        Map<String, String> body= new HashMap<> (2);
        body.put("account",ACCOUNT);
        body.put("password", PASSWORD);

        String result = null;
        try {
            result = doPost("/login",JSONObject.parseObject(JSONObject.toJSONString(body)),null);
            LOGGER.info("红河对接登录结果"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseResponse<LoginDataResponse> response = JSONObject.parseObject(result,BaseResponse.class);
        LoginDataResponse loginData;
        if("HH0000".equals(response.getRlt_code())){
            loginData = JSONObject.parseObject(JSONObject.toJSONString(response.getData()), LoginDataResponse.class);
            ACCESS_TOKEN=loginData.getAccess_token();
        }else {
            loginHuoHe();
        }

    }

    /**
     * http请求公用方法
     * */
    private static String doPost(String path, JSONObject jsonData,String access_token) throws Exception {

        //请求 url
        String url = "http://ops.huohetech.com";

        //请求头信息
        Map<String, String> headers= new  HashMap<> (2);
        //接口版本号
        headers.put("version",VERSION);
        //业务流水单号
        headers.put("s_id", UUID.randomUUID().toString());
        if (!"/login".equals(path)){
            // 访问凭证
            headers.put("access_token",access_token);
        }
        String result = HttpURLConnectionUtil.doPostJson(url+path,JSONObject.parseObject(JSONObject.toJSONString(headers)),jsonData);
        return result;
    }

}
