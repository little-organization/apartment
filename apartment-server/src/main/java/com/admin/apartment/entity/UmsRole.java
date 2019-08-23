package com.admin.apartment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台用户角色表
 * </p>
 *
 * @author liangming
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
public class UmsRole implements Serializable {

    private static final long serialVersionUID = 10883476571623L;

    @TableId(value = "id")
    private long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限
     */
    private String value;

    /**
     * 后台用户数量
     */
    private Integer adminCount;

    /**
     * 管理员 id
     */
    private Integer adminId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

    /**
     * 状态
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;


}
