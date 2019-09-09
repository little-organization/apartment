package com.admin.apartment.mapper;

import com.admin.apartment.entity.PhoneVerify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneVerifyMapperTest {

    @Autowired
    PhoneVerifyMapper phoneVerifyMapper;

    @Test
    public void name() {
        PhoneVerify phoneVerify = phoneVerifyMapper.selectByBarcode("254003");
        System.out.println(phoneVerify);
    }
}
