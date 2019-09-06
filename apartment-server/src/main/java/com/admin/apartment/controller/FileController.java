package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.File;
import com.admin.apartment.service.IFileService;
import com.admin.apartment.utils.OSSUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Controller
@RequestMapping("/apartment/file")
public class FileController {

    @Value("${aliyun.firstKey}")
    private static String firstKey;

    @Autowired
    OSSUploadUtils ossUploadUtils;

    @Autowired
    IFileService iFileService;


    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping(value = "/baseUpload", method = RequestMethod.POST)
    @ResponseBody
    @Scope("request")
    public CommonResult upload(@RequestParam("file") MultipartFile file){
        if (file!=null) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newName = ossUploadUtils.getFileName(fileName);
            Map<String,String> map = new HashMap<>();
            map.put("suffix",suffix);
            map.put("resource",firstKey+"/"+newName);
            map.put("filename",newName);
            // 剪裁图片，目前用不到
            if (ossUploadUtils.uploadImage(file, newName)) {
                //图访问路径，返回到前端，提及form表单保存时将访问路径保存到数据库
                return CommonResult.success(map,"true");
            } else {
                return CommonResult.success(map,"false");
            }
        }
        return null;
    }

    /**
     * 删除图片
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/delImage/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delImage(@PathVariable("fileName") String fileName){
        boolean result = ossUploadUtils.delImage(fileName);
        return CommonResult.success(result, null);
    }

    /**
     * 获取图片的 base64 编码
     * @param image
     * @return
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    @Cacheable
    public CommonResult image(@RequestBody String image){
        String imgStr = null;
        if (image!=null) {
            imgStr = ossUploadUtils.downloadImage(image);
        }
        return CommonResult.success(imgStr!=null,imgStr);
    }

    /**
     * 获取文件列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/getImages/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getImages(@PathVariable("id") long id){
        List<File> list = iFileService.listselectByRepairsId(id);
        return CommonResult.success(list);
    }


    @Value("${server.port}")
    private String port;

    /**
     * 获取本服务器的 ip 地址
     *
     * @return
     */
    @RequestMapping(value = "/getHostAddress", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getHostAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        String upLoadUrl = "http://"+address.getHostAddress()+":"+port+"/apartment/file/baseUpload";
        return CommonResult.success(upLoadUrl);
    }


}
