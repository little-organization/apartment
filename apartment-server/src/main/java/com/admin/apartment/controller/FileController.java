package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.File;
import com.admin.apartment.mapper.FileMapper;
import com.admin.apartment.model.Fileparams;
import com.admin.apartment.service.IFileService;
import com.admin.apartment.utils.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
@Scope("request")
public class FileController {

    @Value("${upload.ftppath}")
    private String FTP_BASEPATH;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    IFileService iFileService;


    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping(value = "/baseUpload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("file") MultipartFile file){
        if (file!=null) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newName = FileUtil.getFileName(fileName);
            Map<String,String> map = new HashMap<>();
            map.put("suffix",suffix);
            map.put("name",newName);
            // 剪裁图片，目前用不到
            if (fileUtil.notCutImage(file, newName)) {
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
        boolean result = fileUtil.deleteFile(FileUtil.getFile(fileName));
        return CommonResult.success(result, null);
    }

    /**
     *
     * @param image
     * @return
     */
    @RequestMapping(value = "/image/{image}", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable
    public CommonResult image(@PathVariable("image") String image){
        String imgStr = null;
        if (image!=null) {
            imgStr = fileUtil.download(image);
        }
        return CommonResult.success(imgStr!=null,imgStr);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getImages/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getImages(@PathVariable("id") long id){
        List<File> list = iFileService.listselectByRepairsId(id);
        return CommonResult.success(list);
    }
}
