package com.admin.apartment.mapper;

import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.MsgSign;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.message.QuerySmsSignParams;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-09-04
 */
public interface MsgSignMapper extends BaseMapper<MsgSign> {

    Page<MsgSign> selectSignBySignName(IPage<QuerySmsSignParams> signIPage);

    List<MsgSign> selectSignBySignStatus();

    List<MsgSign> selectAllSuccessSign();

}
