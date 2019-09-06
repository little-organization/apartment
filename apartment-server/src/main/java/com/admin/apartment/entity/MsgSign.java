package com.admin.apartment.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MsgSign implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 短信签名。
     */
    @TableId("SignName")
    private String SignName;

    /**
     * 签名审核状态。其中：

0：审核中。
1：审核通过。
2：审核失败，请在返回参数Reason中查看审核失败原因。
     */
    @TableField("SignStatus")
    private Integer SignStatus;

    /**
     * 审核备注。

如果审核状态为审核通过或审核中，参数Reason显示为“无审核备注”。
如果审核状态为审核未通过，参数Reason显示审核的具体原因。
     */
    @TableField("Reason")
    private String Reason;

    /**
     * 状态码描述。
     */
    @TableField("Message")
    private String Message;

    /**
     * 短信签名的创建日期和时间。
     */
    @TableField("CreateDate")
    private LocalDateTime CreateDate;

    /**
     * 请求状态码。

返回OK代表请求成功。
其他错误码详见错误码列表。
     */
    @TableField("Code")
    private String Code;

    /**
     * 请求ID。
     */
    @TableField("RequestId")
    private String RequestId;


}
