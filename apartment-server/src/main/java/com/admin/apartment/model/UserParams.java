package com.admin.apartment.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserParams {

    @TableId(value = "id")
    private long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Set<Integer> sexSet;

    /**
     * 电话
     */
    private String phone;

    /**
     * 证件号
     */
    private String idNumber;

    /**
     * 证件类型
     */
    private Set<String> idTypeSet;

    /**
     * 居住状态
     */
    private Set<Integer> isLiveSet;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 每页最大数量
     */
    private Integer limit;
}
