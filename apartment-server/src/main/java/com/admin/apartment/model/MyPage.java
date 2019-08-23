package com.admin.apartment.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 自定义分页
 *
 * @author miemie
 * @since 2018-08-13
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {

    private static final long serialVersionUID = 5194933845448697148L;


    private T t;

    /**
     * current：当前页
     *
     * size：每页显示条数，默认 10
     *
     * */
    public MyPage(long current, long size) {
        super(current, size);
    }

    /**
     *  是否查询数据总数 默认 true
     * */
    public MyPage(long current, long size, boolean isSearchCount) {
        super(current, size,isSearchCount);
    }
}
