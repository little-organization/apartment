package com.admin.apartment.model;

import com.admin.apartment.entity.UmsRole;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author liangming
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsAdminResponse implements Serializable {

    private static final long serialVersionUID = 13456908192834L;

    @TableId(value = "id")
    private long id;

    private String username;

    private String password;

    /**
     * 备注信息
     */
    private String roleNote;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 租户表 id
     */
    private long userId;

    /**
     * 售后人员表 id
     */
    private long repairId;

    /**
     * 角色 id
     */
    private long roleId;

}
