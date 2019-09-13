package com.admin.apartment.service;

import com.admin.apartment.entity.MsgSign;
import com.admin.apartment.entity.MsgTemplate;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.message.QuerySmsSignParams;
import com.admin.apartment.model.message.QuerySmsTemplateParams;
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
public interface IMsgTemplateService extends IService<MsgTemplate> {

    /**
     * 查询阿里模板然后添加到数据库，先验证数据库中不存在
     * */
    MsgTemplate querySmsTemplateFromAli(String templateCode);

    /**
     * 查询模板
     * */
    Page<MsgTemplate> querySmsTemplateList(QuerySmsTemplateParams params);


    /**
     * 删除模板
     * */
    boolean delSmsTemplateFromAli(String templateCode);

    /**
     * 查询模板所有类型
     * */
    List<FiltersTag> selectTemplateCodeAndNameList();

    /**
     * 查询模板所有可用类型
     * */
    List<MsgTemplate> selectAllSuccessTemplate();
}
