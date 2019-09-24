package com.admin.apartment;
import	java.security.acl.Group;


import com.admin.apartment.common.CommonResult;
import com.admin.apartment.entity.UmsAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@RestController
public class ApartmentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApartmentServerApplication.class, args);
    }

    /**
     * 登录
     * */
//    @GetMapping("/")
//    public @ResponseBody
//    ModelAndView login(){
//        ModelAndView modelAndView= new ModelAndView();
//        modelAndView.setViewName("forward:/login");
//       return modelAndView;
//    }
}
