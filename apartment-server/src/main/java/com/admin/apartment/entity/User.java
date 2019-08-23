package com.admin.apartment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户信息
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@TableName("apartment_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1893578927L;

    @TableId(value = "id")
    private long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private int sex;

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
    private String idType;

    /**
     * 居住状态
     */
    private int isLive;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    public User(String name) {
        this.name = name;
    }
}
