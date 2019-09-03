package com.admin.apartment.mapper;

import com.admin.apartment.entity.Repairs;
import com.admin.apartment.entity.RepairsLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 报修修改日志表 Mapper 接口
 * </p>
 *
 * @author liangming
 * @since 2019-09-03
 */
public interface RepairsLogMapper extends BaseMapper<RepairsLog> {

    int insertLog(Repairs repairs);
}
