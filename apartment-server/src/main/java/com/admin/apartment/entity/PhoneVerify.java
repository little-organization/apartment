package com.admin.apartment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author liangming
 * @since 2019-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PhoneVerify implements Serializable {

    private static final long serialVersionUID = 1879172398479123L;

    /**
     * id
     */
    @TableId(value = "id")
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String barcode;

    /**
     * 0=未使用
1=已使用
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


}
