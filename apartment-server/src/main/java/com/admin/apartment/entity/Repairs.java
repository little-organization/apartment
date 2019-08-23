package com.admin.apartment.entity;

import java.time.LocalDate;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报修表
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Repairs implements Serializable {

    private static final long serialVersionUID = 18562374592L;

    @TableId(value = "id")
    private long id;

    /**
     * 租户id
     */
    private long userid;

    /**
     * 租户姓名
     */
    private String username;

    /**
     * 租户公寓id
     */
    private String apartmentid;

    /**
     * 租户公寓地址
     */
    private String apartmentAddress;

    /**
     * 报修状态
     */
    private String status;

    /**
     * 文本描述内容
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField(el = "conductTime, jdbcType=timestamp")
    private LocalDateTime createtime;

    /**
     * 处理时间
     */
    @TableField(el = "conductTime, jdbcType=timestamp", update="now()")
    private LocalDateTime conductTime;

    /**
     * 是否有文件 0=>没有 1=>有
     */
    private Integer hasFile;


}
