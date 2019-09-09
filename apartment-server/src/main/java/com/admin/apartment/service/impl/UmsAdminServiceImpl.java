package com.admin.apartment.service.impl;

import com.admin.apartment.config.RestPasswordEncoder;
import com.admin.apartment.entity.*;
import com.admin.apartment.mapper.*;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.MyPage;
import com.admin.apartment.model.UmsAdminParams;
import com.admin.apartment.model.UmsAdminResponse;
import com.admin.apartment.service.IUmsAdminService;
import com.admin.apartment.utils.JwtTokenUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author liangming
 * @since 2019-08-12
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {

    @Autowired
    UmsAdminMapper umsAdminMapper;
    @Autowired
    UmsRoleMapper umsRoleMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    PhoneVerifyMapper phoneVerifyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RepairMapper repairMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdmin umsAdmin = umsAdminMapper.selectOneByInfo(new UmsAdmin(username));
        if (umsAdmin == null) {
            //避免返回null，这里返回一个不含有任何值的UmsAdmin对象，在后期的密码比对过程中一样会验证失败
            return new UmsAdmin();
        }
        List<UmsRole> roles = umsRoleMapper.selectListByRoleid(umsAdmin.getRoleId());
        umsAdmin.setUmsRoles(roles);
        return umsAdmin;
    }

    @Override
    public boolean saveOrUpdate(UmsAdmin umsAdmin) {
        return umsAdminMapper.updateById(umsAdmin) == 1;
    }

    @Override
    public IPage<UmsAdmin> page(IPage<UmsAdmin> page, Wrapper<UmsAdmin> queryWrapper) {
        return umsAdminMapper.selectPage(page, queryWrapper);
    }

    @Override
    public String login(UmsAdmin umsAdmin) {
        String token = null;
        UserDetails userDetails = loadUserByUsername(umsAdmin.getUsername());
        if (!(userDetails.getPassword() == null || !userDetails.getPassword().equals(umsAdmin.getPassword()))) {
            //获取权限
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //获取token
            token = tokenHead+jwtTokenUtil.generateToken(userDetails);
            //给请求头设置 token
            updateLoginTimeByUsername(userDetails.getUsername());
        }
        return token;
    }

    @Override
    public UmsAdmin userinfo(String token) {
        String username = jwtTokenUtil.getUserNameFromToken(token.substring(this.tokenHead.length()));
        UmsAdmin umsAdmin =  umsAdminMapper.selectOneByInfo(new UmsAdmin(username));
        List<UmsRole> roles = umsRoleMapper.selectListByRoleid(umsAdmin.getRoleId());
        umsAdmin.setUmsRoles(roles);
        umsAdmin.setPassword(null);
        return umsAdmin;
    }

    @Override
    public boolean checkUsername(String username) {
        UmsAdmin umsAdmin =  umsAdminMapper.selectOneByInfo(new UmsAdmin(username));
        return umsAdmin==null?false:true;
    }

    @Override
    public boolean checkPassword(UmsAdminParams umsAdmin){
        UmsAdmin umsAdmin1 = umsAdminMapper.selectById(umsAdmin.getId());
        if (umsAdmin1.getPassword().equals(umsAdmin.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(UmsAdminParams umsAdmin){
        boolean result = umsAdminMapper.changePassword(umsAdmin)>0;
        return result;
    }

    @Override
    public boolean changeUsername(UmsAdminParams umsAdmin){
        boolean result = umsAdminMapper.changeUsername(umsAdmin)>0;
        return result;
    }

    @Override
    public boolean changePhone(UmsAdminParams params){
        PhoneVerify phoneVerify = phoneVerifyMapper.selectByBarcode(params.getBarcode());
        if (phoneVerify!=null){
            boolean result;
            Duration duration = Duration.between(phoneVerify.getCreatetime(),LocalDateTime.now());
            if(phoneVerify.getState()==0 &&  duration.toMinutes()<1 ){
                result = umsAdminMapper.changePhone(params)>0;
                if (result){
                    // 修改当前租户的手机
                    if(params.getUserId()!=0){
                        User user = new User();
                        user.setId(params.getUserId());
                        user.setPhone(params.getPhone());
                        userMapper.updateUserPhoneById(user);
                    }
                    // 修改当前租户的手机
                    else if(params.getRepairId()!=0){
                        Repair repair = new Repair();
                        repair.setId(params.getRepairId());
                        repair.setPhone(params.getPhone());
                        repairMapper.updateRepairPhoneById(repair);
                    }
                    // 修改使用过的验证码状态
                    phoneVerifyMapper.updateByBarcode(params.getBarcode());
                }
                return result;
            }else{
                throw new IllegalStateException("验证码已使用或已过期");
            }
        }else{
            throw new IllegalStateException("验证码不存在");
        }
    }

    public boolean updateLoginTimeByUsername(String username) {
        return umsAdminMapper.updateByUsername(username)>0;
    }

    @Override
    public Page<UmsAdminResponse> selectUmsAdminByLike(UmsAdminParams params) {
        //当前页数，查询的最大个数
        MyPage<UmsAdminParams> userIPage = new MyPage<>(params.getPage(),params.getLimit(),true);
        userIPage.setT(params);
        //获取所有数据 List
        Page<UmsAdminResponse> page =  umsAdminMapper.selectUmsAdminByLike(userIPage);
        return page;
    }

    @Override
    public boolean delUmsAdmin(long id) {
        return umsAdminMapper.deleteById(id)>0;
    }

    @Override
    public long insertAdminInfo(UmsAdminParams umsAdmin){
        // 系统管理员账号密码
        umsAdmin.setCreateTime(LocalDateTime.now());
        // 启用
        umsAdmin.setStatus(1);
        UmsRole umsRole = umsRoleMapper.selectListByRoleName(umsAdmin.getRoleNote());
        umsAdmin.setRoleId(umsRole.getId());
        umsAdmin.setRoleNote(umsRole.getName());
        umsAdminMapper.insertReturnId(umsAdmin);
        return umsAdmin.getId();
    }

    @Override
    public boolean changeStatus(UmsAdminParams umsAdmin){
        if (umsAdmin.getStatus() == 1) {
            umsAdmin.setStatus(0);
        } else {
            umsAdmin.setStatus(1);
        }
        return umsAdminMapper.changeStatus(umsAdmin)>0;
    }

}
