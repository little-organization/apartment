package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author liangming
 */
@Getter
@Setter
@NoArgsConstructor
public class AddSelfPwdRequest implements Serializable {

    private static final long serialVersionUID = -6957361951748382510L;

    /**
     * 门锁编码
     * */
    private String lock_no;

    /**
     * 密码内容
     * 显示 DES 加密后的密文
     * 用 DES 加密后的密文， 433 门锁 4~16 位，蓝牙 门锁 6~16 位; 若为空，随机生成 6~7 位密码;
     * */
    private String pwd_text;

    /**
     * 是否校验密码 相似性
     * 默认:true 校验密码相似 性
     * */
    private boolean similarity_check;

    /**
     * 密码有效期(起)
     * 格式:13 位时间戳(毫秒)
     * */
    private long valid_time_start;

    /**
     * 密码有效期(止)
     * 格式:13 位时间戳(毫秒)
     * */
    private long valid_time_end;

    /**
     * 密码使用人姓名
     * */
    private String pwd_user_name;

    /**
     * 密码使用人手机号
     * */
    private String pwd_user_mobile;

    /**
     * 密码使用人证件号
     * 默认:二代身份证号
     * */
    private String pwd_user_idcard;

    /**
     * 描述
     * 长度不能大于 120 个字 符
     * */
    private String description;

    /**
     * 辅助信息
     * 长度不能大于 1024 个字 符
     * */
    private String extra;

}
