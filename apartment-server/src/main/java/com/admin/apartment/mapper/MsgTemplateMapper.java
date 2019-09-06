package com.admin.apartment.mapper;

import com.admin.apartment.entity.MsgSign;
import com.admin.apartment.entity.MsgTemplate;
import com.admin.apartment.model.message.QuerySmsSignParams;
import com.admin.apartment.model.message.QuerySmsTemplateParams;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-09-04
 */
public interface MsgTemplateMapper extends BaseMapper<MsgTemplate> {

    Page<MsgTemplate> selectSignByTemplateCode(IPage<QuerySmsTemplateParams> templateIPage);

    List<MsgTemplate> selectTemplateByTemplateStatus();

    List<MsgTemplate> selectTemplateCodeAndNameList();

}
