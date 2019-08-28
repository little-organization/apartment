package com.admin.apartment.service;

import com.admin.apartment.model.message.*;
import com.aliyuncs.CommonResponse;

public interface ISmsTemplateService {

    /**
     * 创建模板
     * */
    public SmsTemplateResponse addSmsTemplate(AddSmsTemplateParams params);

    /**
     * 删除模板
     * */
    public SmsTemplateResponse deleteSmsTemplate(DeleteSmsTemplateParams params);

    /**
     * 修改模板
     * */
    public SmsTemplateResponse modifySmsTemplate(ModifySmsTemplateParams params);

    /**
     * 查询模板
     * */
    public SmsTemplateQueryResponse querySmsTemplate(QuerySmsTemplateParams params);
}
