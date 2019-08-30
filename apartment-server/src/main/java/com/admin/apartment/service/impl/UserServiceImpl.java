package com.admin.apartment.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.entity.User;
import com.admin.apartment.mapper.UmsAdminMapper;
import com.admin.apartment.mapper.UserMapper;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.MyPage;
import com.admin.apartment.model.UserParams;
import com.admin.apartment.model.message.SendBatchSmsReqParams;
import com.admin.apartment.model.message.SendSmsResponse;
import com.admin.apartment.model.message.SmsTemplateResponse;
import com.admin.apartment.service.ISendMessageService;
import com.admin.apartment.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.JsonObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 租户信息 服务实现类
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOGGER = LogFactory.get();

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Autowired
    ISendMessageService iSendMessageService;


    @Override
    public Page<User> selectUserByInfo(IPage<User> userIPage) {
        return userMapper.selectUserByInfo(userIPage);
    }

    @Override
    public Page<User> selectUserByLike(UserParams params) {
        //当前页数，查询的最大个数
        MyPage<UserParams> userIPage = new MyPage<>(params.getPage(),params.getLimit(),true);
        userIPage.setT(params);
        //获取所有数据 List
        Page<User> page =  userMapper.selectUserByLike(userIPage);
        return page;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean insertUser(User user) {
        // 添加用户
        boolean insert = userMapper.insert(user)>0;
        // 租户添加成功后生成租户账号密码
        if(insert){
            UmsAdmin umsAdmin= new UmsAdmin();
            umsAdmin.setUsername(user.getName());
            umsAdmin.setPassword(String.valueOf(user.hashCode()));
            // 添加后的租户直接启用账户
            umsAdmin.setStatus(1);
            umsAdmin.setPhone(user.getPhone());
            umsAdmin.setNote("租户");
            insert = umsAdminMapper.insert(umsAdmin)>0;
        }
        return insert;
    }

    @Override
    public boolean updateUserById(User user) {
        return userMapper.updateById(user)>0;
    }

    @Override
    public boolean deleteUserById(String id) {
        return userMapper.deleteById(id)>0;
    }

    @Override
    public User selectUserById(String userid) {
        return userMapper.selectById(userid);
    }

    @Override
    public List<User> selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public List<FiltersTag> selectIdTypeList() {
        List<String> list = userMapper.selectIdTypeList();
        List<FiltersTag> tags = ApartmentServiceImpl.changeTagList(list);
        return tags;
    }

}
