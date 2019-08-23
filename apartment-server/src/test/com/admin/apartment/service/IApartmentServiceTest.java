package com.admin.apartment.service;

import com.admin.apartment.model.FiltersTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IApartmentServiceTest {

    @Autowired
    private IApartmentService iApartmentService;

    @Test
    public void testPattrern() {
        List<FiltersTag> list = iApartmentService.selectPattern();
        System.out.println(list);
    }
}
