package com.admin.apartment.service;

import com.admin.apartment.entity.MsgSign;
import com.admin.apartment.model.message.QueryDelSmsSignResponse;
import com.admin.apartment.model.message.QuerySmsSignParams;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangming
 * @since 2019-09-04
 */
public interface IMsgSignService extends IService<MsgSign> {

    /**
     * 查询签名
     * */
    Page<MsgSign> querySmsSignList(QuerySmsSignParams params);


    /**
     * 删除签名
     * */
    boolean delSmsSignFromAli(String signName);

    /**
     * 查询阿里签名然后添加到数据库，先验证数据库中不存在
     * */
    MsgSign querySmsSignFromAli(String signName);

    /**
     * 查询签名所有可用类型
     * */
    List<MsgSign> selectAllSuccessSign();

}
