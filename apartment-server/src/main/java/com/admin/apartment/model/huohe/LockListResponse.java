package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LockListResponse implements Serializable {

    private static final long serialVersionUID = -6957361951748382516L;

    /**
     * 总记录数
     * */
    private int total;

    /**
     * 总页数
     * */
    private int total_page;

    /**
     * 页大小
     * */
    private int page_size;

    /**
     * 当前页
     * */
    private int current_page;

    /**
     * 见子对象参数
     * */
    private List<Lock> rows;
}
