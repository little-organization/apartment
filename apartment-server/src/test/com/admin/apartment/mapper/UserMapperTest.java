package com.admin.apartment.mapper;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.MyPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    UserMapper userMapper;

    /**
     * 分页查询
     * */
    @Test
    public void testSelectUserByInfo() {
        User user= new User();
        user.setName("test");
        //当前页数，查询的最大个数
        MyPage<User> userIPage = new MyPage<>(1,5,true);
        userIPage.setT(user);
        Page<User> page =  userMapper.selectUserByInfo(userIPage);

        System.out.println(page.toString());
    }
}
