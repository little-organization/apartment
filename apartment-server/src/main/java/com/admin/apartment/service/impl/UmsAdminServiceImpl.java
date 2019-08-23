package com.admin.apartment.service.impl;

import com.admin.apartment.config.RestPasswordEncoder;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.entity.UmsRole;
import com.admin.apartment.mapper.UmsAdminMapper;
import com.admin.apartment.mapper.UmsRoleMapper;
import com.admin.apartment.service.IUmsAdminService;
import com.admin.apartment.utils.JwtTokenUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    RestPasswordEncoder restPasswordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdmin umsAdmin = umsAdminMapper.selectOneByInfo(new UmsAdmin(username));
        if (umsAdmin == null) {
            //避免返回null，这里返回一个不含有任何值的UmsAdmin对象，在后期的密码比对过程中一样会验证失败
            return new UmsAdmin();
        }
        List<UmsRole> roles = umsRoleMapper.selectListByAadminid(umsAdmin.getId());
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
            //ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            //HttpServletRequest request = attributes.getRequest();
            //request.setAttribute(tokenHeader,token);
            //updateLoginTimeByUsername(username);
            //insertLoginLog(username);
        }
        return token;
    }

    @Override
    public UmsAdmin userinfo(String token) {
        String username = jwtTokenUtil.getUserNameFromToken(token);
        UmsAdmin umsAdmin =  umsAdminMapper.selectOneByInfo(new UmsAdmin(username));
        List<UmsRole> roles = umsRoleMapper.selectListByAadminid(umsAdmin.getId());
        umsAdmin.setUmsRoles(roles);
        umsAdmin.setPassword(null);
        return umsAdmin;
    }

    @Override
    public boolean checkUsername(String username) {
        UmsAdmin umsAdmin =  umsAdminMapper.selectOneByInfo(new UmsAdmin(username));
        return umsAdmin==null?false:true;
    }
}
