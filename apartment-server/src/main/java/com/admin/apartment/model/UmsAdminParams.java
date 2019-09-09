package com.admin.apartment.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UmsAdminParams {

    /**
     * 页数
     */
    private Integer page;

    /**
     * 每页最大数量
     */
    private Integer limit;

    @TableField(exist = false)
    private long id;

    @TableField(exist = false)
    private String username;

    @TableField(exist = false)
    private String password;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @TableField(exist = false)
    private Integer status;

    /**
     * 联系电话
     */
    @TableField(exist = false)
    private String phone;

    /**
     * 修改手机号的验证码
     */
    @TableField(exist = false)
    private String barcode;

    /**
     * 租户表的 id
     */
    @TableField(exist = false)
    private long userId;

    /**
     * 售后人员表的 id
     */
    @TableField(exist = false)
    private long repairId;

    /**
     * 角色 id
     */
    @TableField(exist = false)
    private long roleId;

    /**
     * 角色信息
     */
    @TableField(exist = false)
    private String roleNote;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 角色
     */
    @TableField(exist = false)
    private Set<String> roleNoteSet;
}
