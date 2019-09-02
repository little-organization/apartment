package com.admin.apartment.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUtilTest {

    @Resource
    FileUtil fileUtil;

    @Test
    public void download() {

        String sd = fileUtil.download("3z2onEwIe1Y.jpg");
        System.out.println(sd);
    }
}
