package com.admin.apartment.service.impl;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.User;
import com.admin.apartment.mapper.ApartmentMapper;
import com.admin.apartment.mapper.UserMapper;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.MyPage;
import com.admin.apartment.service.IApartmentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公寓信息 服务实现类
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Service
public class ApartmentServiceImpl extends ServiceImpl<ApartmentMapper, Apartment> implements IApartmentService {

    @Autowired
    ApartmentMapper apartmentMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Page<Apartment> selectApartmentByLike(ApartmentParams apartmentParams) {
        if(apartmentParams.getUsername()!=null){
            List<User> userlist = userMapper.selectUserByName(apartmentParams.getUsername());
            List<Long> userids = new ArrayList<>();
            for (int i = 0; i <userlist.size() ; i++) {
                userids.add(userlist.get(i).getId());
            }
            apartmentParams.setUserids(userids);
        }
        //当前页数，查询的最大个数
        MyPage<ApartmentParams> userIPage = new MyPage<>(apartmentParams.getPage(),apartmentParams.getLimit(),true);
        userIPage.setT(apartmentParams);
        //获取所有数据 List
        Page<Apartment> page =  apartmentMapper.selectApartmentByLike(userIPage);
        return page;
    }

    @Override
    public List<FiltersTag> selectPattern() {
        //获取数据支付模式 List
        List<String> list = apartmentMapper.selectPattern();
        return changeTagList(list);
    }

    @Override
    public List<FiltersTag> selectHouseType() {
        //获取数据户型 List
        List<String> list = apartmentMapper.selectHouseType();
        return changeTagList(list);
    }

    @Override
    public List<FiltersTag> selectFaceList() {
        //获取数据户型 List
        List<String> list = apartmentMapper.selectFaceList();
        return changeTagList(list);
    }

    @Override
    public boolean createApartment(Apartment apartment) {
        //获取数据户型 List
        boolean result = apartmentMapper.insert(apartment)>0;
        return result;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean updateApartment(Apartment apartment) {
        //获取数据户型 List
        Apartment apartment1 = apartmentMapper.selectById(apartment.getId());
        if(apartment1.getUserid()!=apartment.getUserid()){
            if(apartment1.getUserid()!=0){
                userMapper.updateUserStatusById(apartment1.getUserid(),0);
            }
            if(apartment.getUserid()!=0){
                userMapper.updateUserStatusById(apartment.getUserid(),1);
            }
        }
        boolean result = apartmentMapper.updateById(apartment)>0;
        return result;
    }

    @Override
    public boolean deleteApartment(String id) {
        //获取数据户型 List
        boolean result = apartmentMapper.deleteById(id)>0;
        return result;
    }

    @Override
    public boolean deleteUserInfoInApartmentById(String id) {
        //获取数据户型 List
        boolean result = apartmentMapper.deleteUserInfoInApartmentById(id)>0;
        return result;
    }

    @Override
    public List<Apartment> getApartmentListByUserid(String userid) {
        List<Apartment> apartmentList = apartmentMapper.getApartmentListByUserid(userid);
        return apartmentList;
    }

    @Override
    public Apartment getApartmentById(String id) {
        Apartment apartment = apartmentMapper.selectByApartmentId(id);
        return apartment;
    }

    /**
     * 公用静态方法
     * */
    public static List<FiltersTag> changeTagList(List<String> list) {
        //获取数据户型 List
        List<FiltersTag> filtersTagList = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            FiltersTag filtersTag = new FiltersTag();
            filtersTag.setText(list.get(i));
            filtersTag.setValue(list.get(i));
            filtersTagList.add(filtersTag);
        }
        return filtersTagList;
    }
}
