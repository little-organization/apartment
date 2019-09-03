package com.admin.apartment.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author liangming
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class File implements Serializable {

    private static final long serialVersionUID = 184576987189L;

    @TableId(value = "id")
    private long id;

    /**
     * 租户名称
     */
    private String username;

    /**
     * 租户 id
     */
    private String userid;

    /**
     * 文件地址
     */
    private String resource;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 所属维修表id
     */
    private long repairsid;


}
