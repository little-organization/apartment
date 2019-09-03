package com.admin.apartment.mapper;

import com.admin.apartment.entity.File;
import com.admin.apartment.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
public interface FileMapper extends BaseMapper<File> {

    /**
     * 查询文件信息
     * */
    Page<File> selectFileByInfo(IPage<File> fileIPage);

    /**
     * 模糊查询文件信息
     * */
    Page<File> selectFileByLike(IPage<File> fileIPage);

    /**
     * 批量插入图片
     * */
    boolean insertBatch(List<File> files);

    /**
     * 通过维修单 id 查询多有图片
     * */
    List<File> selectByRepairsId(long repairsid);

}
