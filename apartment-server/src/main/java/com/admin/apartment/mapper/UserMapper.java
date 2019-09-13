package com.admin.apartment.mapper;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.Repairs;
import com.admin.apartment.entity.User;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.MyPage;
import com.admin.apartment.model.UserParams;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 租户信息 Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询租户信息
     * */
    Page<User> selectUserByInfo(IPage<User> userIPage);

    /**
     * 模糊查询文件信息
     * */
    Page<User> selectUserByLike(IPage<UserParams> userParamsIPage);

    /**
     * 模糊查询租户信息通过租户姓名
     * */
    List<User> selectUserByName(@Param("name") String username);

    /**
     * 通过 ID 更改租户的状态
     * */
    int updateUserIsLiveById(@Param("id") long id,@Param("isLive") int isLive);

    /**
     * 获取全部的证件类型
     * */
    List<String> selectIdTypeList();

    /**
     * 新增一条数据返回 id
     * */
    int insertHolder(User user);

    /**
     * 更新租户的手机号码
     * */
    int updateUserPhoneById(User user);

}
