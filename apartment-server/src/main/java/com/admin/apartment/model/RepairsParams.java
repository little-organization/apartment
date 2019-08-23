package com.admin.apartment.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class RepairsParams {

    /**
     * 报修id
     */
    private long id;

    /**
     * 报修租户id
     */
    private long userid;

    /**
     * 报修租户姓名
     */
    private String username;

    /**
     * 报修状态
     */
    private Set<String> statusSet;

    /**
     * 报修文本描述内容
     */
    private String content;

    /**
     * 报修是否有文件 0=>没有 1=>有
     */
    private Integer hasFile;

    /**
     * 创建时间 时间段查询：开始时间
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private LocalDateTime createTimeBefore;

    /**
     * 创建时间 时间段查询：结束时间
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private LocalDateTime createTimeAfter;

    /**
     * 处理时间 时间段查询：开始时间
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private LocalDateTime conductTimeBefore;

    /**
     * 处理时间 时间段查询：结束时间
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private LocalDateTime conductTimeAfter;

    /**
     * 处理时间
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime conductTime;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 每页最大数量
     */
    private Integer limit;

}
