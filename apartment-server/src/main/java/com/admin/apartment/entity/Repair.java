package com.admin.apartment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 售后人员信息
 * </p>
 *
 * @author liangming
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("apartment_repair")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1324545262341898L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 ：2 女， 1 男
     */
    private Integer sex;

    /**
     * 电话

     */
    private String phone;

    /**
     * 证件号
     */
    @TableField("idNumber")
    private String idNumber;

    /**
     * 证件类型
     */
    @TableField("idType")
    private String idType;

    /**
     * 状态 0=>在职，1=》离职
     */
    @TableField("isLive")
    private Integer isLive;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


}
