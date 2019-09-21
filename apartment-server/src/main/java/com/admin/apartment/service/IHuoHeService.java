package com.admin.apartment.service;

import com.admin.apartment.model.huohe.*;

import java.util.List;

public interface IHuoHeService {

    /**
     * 查询门锁列表
     * */
    LockListResponse selectDoorLockList(LockListRequest request);

    /**
     * 查询门锁详情
     * */
    LockViewResponse selectDoorLockDetails(LockViewRequest request);

    /**
     * 查询门锁密码信息
     * */
    List<PwdListResponse> selectDoorLockPwdInfo(PwdListRequest request);

    /**
     * 新增自定义密码
     * */
    AddSelfPwdResponse changePassword(AddSelfPwdRequest request);
}
