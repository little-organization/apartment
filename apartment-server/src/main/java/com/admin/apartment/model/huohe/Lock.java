package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Lock implements Serializable {

    private static final long serialVersionUID = -6957361951748382519L;
    /**
     * 门锁编码
     * */
    private String lock_no;

    /**
     * 数据字典: 0:一代协议 433 门锁 1:蓝牙门锁 3:二代协议 433 门锁
     * 门锁类型
     * */
    private String lock_kind;

    /**
     * 01:蓝牙外门锁 A221
     * 02: 433 内门锁 120T 03:蓝牙内门锁 A121 04:433 外门锁 A220T
     * 05: 433 内门锁 A120Q 32:433 内门锁 A130
     * 48: 433 外门锁 A230 门锁 49:刷身份证外门锁 A230ID
     * 门锁型号
     * */
    private String type;

    /**
     * 软件版本
     * */
    private String software_version;

    /**
     * 最新软件版本（仅门锁详情使用）
     * 升级后与当前版本一致
     * */
    private String new_software_version;

    /**
     * 硬件版本
     * */
    private String hardware_version;

    /**
     * 房源编码
     * */
    private String house_code;

    /**
     * 房间编码
     * */
    private String room_code;

    /**
     * 网关编码
     * 注:仅 433 门锁有效
     * */
    private String node_no;

    /**
     * 安装日期
     * 格式:13 位时间戳(毫秒)
     * */
    private Long install_time;

    /**
     * 质保开始日期
     * 格式:13 位时间戳(毫秒)
     * */
    private Long guarantee_time_start;

    /**
     * 质保截至日期
     * 格式:13 位时间戳(毫秒)
     * */
    private Long guarantee_time_end;

    /**
     * 安装地址
     * */
    private String address;

    /**
     * 是否支持门磁
     * 0、不支持 1、支持
     * */
    private Integer magnet_flag;

    /**
     * 门状态
     * 0、门开 1、门关
     * */
    private String open_door_status;

    /**
     * 电量
     * 取值范围 0-100
     * */
    private Integer power;

    /**
     * 电量更新时间（仅门锁详情使用）
     * 格式:13 位时间戳(毫秒)
     * */
    private Long power_update_time;

    /**
     * 网关通信状态
     * 数据字典: 00:通信正常;01:通信异常; 注:仅 433 门锁有效
     * */
    private String node_comu_status;

    /**
     * 网关接收到的锁信号强度（仅门锁详情使用）
     * 0~4:信号强度 -1:未上报信号强度
     * */
    private Integer rssi;

    /**
     * 门锁通信状态
     * 数据字典: 00:通信正常;01:通信异常;
     * */
    private String comu_status;

    /**
     * 门锁通信状态更新（仅门锁详情使用）
     * 格式:13 位时间戳(毫秒)
     * */
    private Long comu_status_update_time;

    /**
     * 安装地区
     * 格式:
     * [
     * {"code":"code","name":"name","level":level},
     * {"code":"code","name":"name","level":level},
     * {"code":"code","name":"name","level":level},
     * {"code":"code","name":"name","level":level} ]
     * 举例:
     * [
     * {"code":"0086","name":" 中国","level":0},
     * {"code":"110000","name":"黑龙江","level":1},
     * {"code":"110100","name":"牡丹江","level":2},
     * {"code":"110101","name":"海淀区","level":3}
     * ]
     * */
    private List<String> region;

    /**
     * 描述（仅门锁详情使用）
     * */
    private String description;
}
