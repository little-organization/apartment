package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PwdListRequest implements Serializable {

    private static final long serialVersionUID = -6957361951748382512L;

    /**
     * 门锁编码
     * */
    private String lock_no;

    /**
     * 密码编号
     * 一代协议 433 门锁: 在线自定义密码:[1-29]
     * 蓝牙门锁: 一次性密码:[31-50] 在线自定义密码:[81-100] 离线时效密码:[101-120]
     * 二代协议 433 门锁: 在线自定义密码:[1-29] 一次性密码:[31-38] 临时密码:[39-42] 离线时效密码:[101-108]
     * */
//    private int pwd_no;

    /**
     * 密码状态
     * 数据字典:
     * 启用中:01
     * 禁用中:02
     * 删除中:03
     * 已启用 11 已禁用 12 已删除 13
     * 启用失败:21 禁用失败:22 删除失败:23
     * */
    private String status;
}
