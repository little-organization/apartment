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
public class AddSelfPwdResponse implements Serializable {

    private static final long serialVersionUID = -695736195174838254L;

    /**
     * 密码编号
     * */
    private int lock_no;

    /**
     * 门锁编码
     * */
    private String pwd_no;

    /**
     * 密码内容
     * 显示 DES 加密后的密文
     * 用 DES 加密后的密文， 433 门锁 4~16 位，蓝牙 门锁 6~16 位; 若为空，随机生成 6~7 位密码;
     * */
    private String pwd_text;

    /**
     * 业务 id
     * 流水号，与密码设置结果推送 的输出参数中的“business_id” 对应
     */
    private String business_id;
}
