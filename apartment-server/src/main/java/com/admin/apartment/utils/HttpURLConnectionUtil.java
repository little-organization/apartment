package com.admin.apartment.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * http请求工具方法
 */
public class HttpURLConnectionUtil {

    /**
     * 请求超时数
     */
    private static final Integer CTIMEOUT = 6000;

    /**
     * 读取超时数
     */
    private static final Integer STIMEOUT = 7000;

    private static final Log LOGGER = LogFactory.get();

    /**
     * 请求参数为Json数据
     */
    public static String doPostJson(String url, JSONObject headers, JSONObject body) throws Exception {

        if (url == null || url.equals("")) {
            return "{ " +
                    "\"error\"：\"请求URL错误\"，" +
                    "\"Msg\":\"请求URL不可为空\"" +
                    "}";
        }

        URL localURL = new URL(url.trim());
        URLConnection connection = localURL.openConnection();
        //链接地址
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        //请求超时数
        httpURLConnection.setConnectTimeout(CTIMEOUT);
        //读取超时数
        httpURLConnection.setReadTimeout(STIMEOUT);
        // 设置允许输入输出
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);

        // 设置不用缓存
        httpURLConnection.setUseCaches(false);

        //设置请求方式
        httpURLConnection.setRequestMethod("POST");

        //设置编码格式
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.setRequestProperty("contentType", "UTF-8");
        // 设置维持长连接
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");

        //自定义headers参数的添加
        if (!(headers == null || headers.equals(""))) {
            for (String key : headers.keySet()) {
                httpURLConnection.setRequestProperty(key, headers.getString(key));
            }
        }

        if (!(body == null || body.equals(""))) {
            byte[] writebytes = body.toJSONString().getBytes();
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setRequestProperty("accept", "application/json");
        } else {
            LOGGER.error("参数不能为空！");
            return "{ " +
                    "\"error\"：\"请求参数错误\"，" +
                    "\"Msg\":\"请求参数不可为空\"" +
                    "}";
        }

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader;
        inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer;
        resultBuffer = new StringBuffer();

        try {
            String tempLine;
            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(body.toJSONString().getBytes());
            outputStream.flush();

            System.out.println(body.toJSONString().getBytes());

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                reader = new BufferedReader(inputStreamReader);
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return resultBuffer.toString();

    }

}
