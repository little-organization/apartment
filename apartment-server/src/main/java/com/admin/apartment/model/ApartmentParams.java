package com.admin.apartment.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ApartmentParams {

    /**
     * 对外标价 查询条件 最小值
     */
    @TableField(exist = false)
    private int priceMin;

    /**
     * 对外标价 查询条件 最大值
     */
    @TableField(exist = false)
    private int priceMax;

    /**
     * 房间面积 查询条件 最小值
     */
    @TableField(exist = false)
    private int roomAreaMin;

    /**
     * 房间面积 查询条件 最大值
     */
    @TableField(exist = false)
    private int roomAreaMax;

    /**
     * 支付模式（如押一付一）
     */
    private Set<String> patternSet;

    /**
     * 租户id
     */
    private List<Long> userids;

    /**
     * 公寓地址
     */
    private String address;

    /**
     * 状态
     */
    private Set<Integer> statusSet;

    /**
     * 朝向
     */
    private Set<String> faceSet;


    /**
     * 户型
     */
    private Set<String> houseTypeSet;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 每页最大数量
     */
    private Integer limit;

    /**
     * 租户姓名
     */
    private String username;
}
