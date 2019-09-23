package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TemporaryPasswordResponse implements Serializable {

    private static final long serialVersionUID = -6057361951748382513L;

    /**
     * 门锁编码
     * */
    private String lock_no;

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
     * 密码有效期(起)
     * 格式:13 位时间戳(毫秒)
     * */
    private long valid_time_start;

    /**
     * 密码有效期(止)
     * 格式:13 位时间戳(毫秒)
     * */
    private long valid_time_end;
}
