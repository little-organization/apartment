package com.admin.apartment.utils;

import cn.hutool.json.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangming
 */
@Component
public class OSSUploadUtils {

    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    @Value("${aliyun.endpoint}")
    private String endpoint;

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessSecret}")
    private String accessKeySecret;

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    @Value("${aliyun.bucketName}")
    private String bucketName;

    // Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
    @Value("${aliyun.firstKey}")
    private String firstKey;

    /**
     * 上传单个文件
     * */
    public boolean uploadImage(MultipartFile file,String firstKeyAddfileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream;
        try {
            inputStream =  new ByteArrayInputStream(file.getBytes());
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, firstKeyAddfileName, inputStream);
            System.out.println(JSONObject.toJSONString(putObjectResult.getETag()));
            return true;
        } catch (OSSException oe) {
            System.out.println("捕获OSSException，这意味着您的请求已发送到OSS，但由于某种原因被拒绝并出现错误响应");
            System.out.println("错误信息: " + oe.getErrorCode());
            System.out.println("错误编码:       " + oe.getErrorCode());
            System.out.println("请求ID:      " + oe.getRequestId());
            System.out.println("请求Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("捕获ClientException，这意味着客户端在尝试与OSS通信时遇到严重的内部问题，例如无法访问网络。");
            System.out.println("错误信息: " + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return false;
    }

    /**
     * 下载单个文件
     * */
    public String downloadImage(String firstKeyAddfileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            OSSObject object = ossClient.getObject(new GetObjectRequest(bucketName, firstKeyAddfileName));
            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
            return displayTextInputStream(object.getObjectContent());
        } catch (OSSException oe) {
            System.out.println("捕获OSSException，这意味着您的请求已发送到OSS，但由于某种原因被拒绝并出现错误响应");
            System.out.println("错误信息: " + oe.getErrorCode());
            System.out.println("错误编码:       " + oe.getErrorCode());
            System.out.println("请求ID:      " + oe.getRequestId());
            System.out.println("请求Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("捕获ClientException，这意味着客户端在尝试与OSS通信时遇到严重的内部问题，例如无法访问网络。");
            System.out.println("错误信息: " + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return null;
    }

    /**
     * 删除单个文件
     * */
    public boolean delImage(String firstKeyAddfileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.deleteObject(bucketName, firstKeyAddfileName);
            return true;
        } catch (OSSException oe) {
            System.out.println("捕获OSSException，这意味着您的请求已发送到OSS，但由于某种原因被拒绝并出现错误响应");
            System.out.println("错误信息: " + oe.getErrorCode());
            System.out.println("错误编码:       " + oe.getErrorCode());
            System.out.println("请求ID:      " + oe.getRequestId());
            System.out.println("请求Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("捕获ClientException，这意味着客户端在尝试与OSS通信时遇到严重的内部问题，例如无法访问网络。");
            System.out.println("错误信息: " + ce.getMessage());
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return false;
    }

    /**
     * 文件重新命名
     * @param fileName
     * @return
     */
    public String getFileName(String fileName){
        //获取文件类型
        String type = fileName.substring(fileName.lastIndexOf("."));
        //获取文件名字
        String oldname = fileName.substring(0,fileName.indexOf("."));
        String newname = String.valueOf(Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.now())).getTime())+oldname.hashCode();
        return newname+type;
    }

    /**
     * 文件流的转换
     * @param input
     * @return
     */
    private static String displayTextInputStream(InputStream input) throws IOException {
        StringBuilder objectContent = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            objectContent.append(line);
        }
        reader.close();
        return String.valueOf(objectContent);
    }
}
