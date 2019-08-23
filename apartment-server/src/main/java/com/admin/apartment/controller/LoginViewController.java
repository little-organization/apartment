package com.admin.apartment.controller;

import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.service.IUmsAdminService;
import com.admin.apartment.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apartment")
public class LoginViewController {

    @Autowired
    IUmsAdminService iUmsAdminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登录
     * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult login(@RequestParam String username, @RequestParam String password){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername(username);
        umsAdmin.setPassword(password);
        String token = iUmsAdminService.login(umsAdmin);
        if(token!=null){
            return CommonResult.success(token);
        }else{
            return CommonResult.failed("登录失败,请检查确认密码是否正确!");
        }
    }

    /**
     * 获取当前用户信息
     * */
    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult userinfo(@RequestParam String token){
        UmsAdmin umsAdmin = iUmsAdminService.userinfo(token);
        return CommonResult.success(umsAdmin);
    }

    /**
     * 获取当前用户信息
     * */
    @RequestMapping(value = "/checkUsername",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult checkUsername(@RequestParam String username){
        boolean result = iUmsAdminService.checkUsername(username);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed("用户名不存在，请检查用户名！");
        }

    }

    /**
     * 登出
     * */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public @ResponseBody
    CommonResult logout(){
        return CommonResult.success("您已成功推出登录");
    }

}
