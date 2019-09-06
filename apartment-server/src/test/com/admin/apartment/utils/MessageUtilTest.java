package com.admin.apartment.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageUtilTest {

    @Resource
    MessageUtil message;

    @Test
    public void commonRequest() {

        message.commonRequest();
    }

    @Test
    public void commonResponse() {
    }
}
