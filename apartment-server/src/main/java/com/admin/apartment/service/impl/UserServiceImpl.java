package com.admin.apartment.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.entity.UmsRole;
import com.admin.apartment.entity.User;
import com.admin.apartment.mapper.ApartmentMapper;
import com.admin.apartment.mapper.UmsAdminMapper;
import com.admin.apartment.mapper.UmsRoleMapper;
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
    ApartmentMapper apartmentMapper;

    @Autowired
    UmsRoleMapper umsRoleMapper;


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
        boolean insert = userMapper.insertHolder(user)>0;
        // 租户添加成功后生成租户账号密码
        if(insert){
            UmsAdmin umsAdmin= new UmsAdmin();
            umsAdmin.setUsername(user.getName());
            umsAdmin.setPassword(user.getIdNumber().substring(user.getIdNumber().length()-6));
            // 添加后的租户直接启用账户
            UmsRole umsRole = umsRoleMapper.selectListByRoleName("租户");
            umsAdmin.setRoleId(umsRole.getId());
            umsAdmin.setRoleNote(umsRole.getName());
            umsAdmin.setPhone(user.getPhone());
            umsAdmin.setUserId(user.getId());
            insert = umsAdminMapper.insert(umsAdmin)>0;
        }
        return insert;
    }

    @Override
    public boolean updateUserById(User user) {
        return userMapper.updateById(user)>0;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean deleteUserById(User user) {
        boolean result = userMapper.deleteById(user.getId())>0;
        if(result){
            // 修改与租户绑定的公寓信息
            List<Apartment> list = apartmentMapper.getApartmentListByUserid(user.getId());
            if(list.size()>=1){
                for (int i = 0; i <list.size() ; i++) {
                    list.get(i).setUserid(0);
                    apartmentMapper.updateById(list.get(i));
                }
            }
            // 删除租户的账号
            result = umsAdminMapper.deleteByUserId(user.getId())>0;
        }
        return result;
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
