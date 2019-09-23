package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TemporaryPasswordRequest implements Serializable {

    private static final long serialVersionUID = -6957365951748382515L;

    /**
     * 门锁编码
     * */
    private String lock_no;

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
     * */
    private String pwd_user_idcard;

    /**
     * 描述
     * */
    private String description;

}
