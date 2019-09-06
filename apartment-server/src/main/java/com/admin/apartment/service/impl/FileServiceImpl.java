package com.admin.apartment.service.impl;

import com.admin.apartment.entity.File;
import com.admin.apartment.mapper.FileMapper;
import com.admin.apartment.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public List<File> listselectByRepairsId(long repairsid) {
        List<File> list = fileMapper.selectByRepairsId(repairsid);
        return list;
    }
}
