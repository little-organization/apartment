package com.admin.apartment.service;

import com.admin.apartment.entity.MsgSign;
import com.admin.apartment.model.message.*;

public interface ISmsSignService {

    /**
     * 删除签名
     * */
    QueryDelSmsSignResponse deleteSmsSign(DelSmsSignParams params);

    /**
     * 查询签名
     * */
    MsgSign querySmsSign(QuerySmsSignParams params);
}
