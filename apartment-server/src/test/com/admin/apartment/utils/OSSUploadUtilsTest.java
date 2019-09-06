package com.admin.apartment.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OSSUploadUtilsTest {

    @Resource
    OSSUploadUtils ossUploadUtils;

    @Test
    public void downloadImage() {

        String sd = ossUploadUtils.downloadImage("images/3z2onEwIe1Y.jpg");
        System.out.println(sd);
    }
}
