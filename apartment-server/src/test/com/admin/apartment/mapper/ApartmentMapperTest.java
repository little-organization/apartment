package com.admin.apartment.mapper;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.MyPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApartmentMapperTest {

    @Autowired
    ApartmentMapper apartmentMapper;

    @Test
    public void testSelectApartmentByLike() {
        ApartmentParams apartmentParams= new ApartmentParams();
        Set<String> face = new CopyOnWriteArraySet<>();
        face.add("朝北");
        face.add("朝南");
        face.add("朝西南");
        apartmentParams.setFaceSet(face);

        Set<String> patternList = new CopyOnWriteArraySet<>();
        patternList.add("押一付一");
        patternList.add("押一付三");
        apartmentParams.setPatternSet(patternList);
        //当前页数，查询的最大个数
        MyPage<ApartmentParams> userIPage = new MyPage<>(1,5,true);
        userIPage.setT(apartmentParams);

        Page<Apartment> page =  apartmentMapper.selectApartmentByLike(userIPage);
        System.out.println(page.toString());
    }

    @Test
    public void testPattern() {
        List<String> page =  apartmentMapper.selectPattern();
        System.out.println(page);
    }
}
