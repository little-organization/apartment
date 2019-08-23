package com.admin.apartment.service;

import com.admin.apartment.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.debugger.Page;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {

    @Autowired
    private IUserService iUserService;

//    @Test
//    public void testPage() {
//
//        Page page = new Page<User>(1,5);
//        iUserService.selectUserByInfo()
//    }
}
