package com.admin.apartment.service;

import com.admin.apartment.entity.UmsAdmin;
import com.admin.apartment.mapper.UmsAdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUmsAdminServiceTest {


    @Autowired
    private IUmsAdminService iUmsAdminService;

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- 登录测试 ------"));
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setPassword("123456");
        umsAdmin.setUsername("admin1");
//        umsAdminMapper.selectOneByInfo(umsAdmin);
        String result = iUmsAdminService.login(umsAdmin);
        System.out.println(result);
    }
}

