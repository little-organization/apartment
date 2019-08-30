package com.admin.apartment.controller;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.model.Fileparams;
import com.admin.apartment.utils.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

    @Value("${upload.ftpUrl}")
    private String hrefUrl;

    @Autowired
    FileUtil fileUtil;

    /**
     *
     * @param files
     * @param params
     * @return
     */
    @RequestMapping(value = "/baseUpload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("files") MultipartFile[] files, List<Fileparams> params){
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String imgName = file.getName();
                int width = 0;
                int height = 0;
                for (int i = 0; i < params.size(); i++) {
                    if(imgName.equals(params.get(i).getFileName())){
                        width = params.get(i).getWidth();
                        height = params.get(i).getHeight();
                        params.remove(i);
                    }
                }
                String fileName = file.getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                String newName = FileUtil.getFileName(fileName);
                    // 剪裁图片，目前用不到
                    if (fileUtil.cutImage(file, newName, suffix, width, height)) {
                        if (StringUtils.isNotEmpty(imgName)) {
                            //图片更新后删除原图片
                            fileUtil.deleteFile(FileUtil.getFile(imgName));
                        }
                        //图访问路径，返回到前端，提及form表单保存时将访问路径保存到数据库
                        return CommonResult.success(true, hrefUrl + newName);
                    } else {
                        return CommonResult.failed(true);
                    }

            }
        }
        return null;
    }
}
