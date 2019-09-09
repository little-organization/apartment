package com.admin.apartment.service;

import com.admin.apartment.entity.PhoneVerify;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangming
 * @since 2019-09-08
 */
public interface IPhoneVerifyService extends IService<PhoneVerify> {

    /**
     * 新增发送短信信息
     * */
    boolean insertNode(PhoneVerify phoneVerify);

}
