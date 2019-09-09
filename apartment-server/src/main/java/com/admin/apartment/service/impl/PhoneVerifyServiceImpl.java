package com.admin.apartment.service.impl;

import com.admin.apartment.entity.PhoneVerify;
import com.admin.apartment.mapper.PhoneVerifyMapper;
import com.admin.apartment.service.IPhoneVerifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangming
 * @since 2019-09-08
 */
@Service
public class PhoneVerifyServiceImpl extends ServiceImpl<PhoneVerifyMapper, PhoneVerify> implements IPhoneVerifyService {

    @Autowired
    PhoneVerifyMapper phoneVerifyMapper;

    @Override
    public boolean insertNode(PhoneVerify phoneVerify){
        return phoneVerifyMapper.insert(phoneVerify)>0;
    }

}
