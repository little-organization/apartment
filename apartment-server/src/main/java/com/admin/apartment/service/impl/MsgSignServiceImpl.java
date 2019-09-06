package com.admin.apartment.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.admin.apartment.entity.Apartment;
import com.admin.apartment.entity.MsgSign;
import com.admin.apartment.entity.User;
import com.admin.apartment.mapper.MsgSignMapper;
import com.admin.apartment.model.ApartmentParams;
import com.admin.apartment.model.MyPage;
import com.admin.apartment.model.message.DelSmsSignParams;
import com.admin.apartment.model.message.QueryDelSmsSignResponse;
import com.admin.apartment.model.message.QuerySmsSignParams;
import com.admin.apartment.service.IMsgSignService;
import com.admin.apartment.service.ISendMessageService;
import com.admin.apartment.service.ISmsSignService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangming
 * @since 2019-09-04
 */
@Service
public class MsgSignServiceImpl extends ServiceImpl<MsgSignMapper, MsgSign> implements IMsgSignService {
    private static final Log LOGGER = LogFactory.get();
    @Autowired
    MsgSignMapper msgSignMapper;

    @Autowired
    ISmsSignService iSmsSignService;

    @Override
    public Page<MsgSign> querySmsSignList(QuerySmsSignParams params) {
        //当前页数，查询的最大个数
        MyPage<QuerySmsSignParams> signIPage = new MyPage<>(params.getPage(),params.getLimit(),true);
        signIPage.setT(params);
        //获取所有数据 List
        Page<MsgSign> page =  msgSignMapper.selectSignBySignName(signIPage);
        return page;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public MsgSign querySmsSignFromAli(String signName){
        MsgSign msgSign = msgSignMapper.selectById(signName);
        if(msgSign==null){
            MsgSign msgSign1 = iSmsSignService.querySmsSign(new QuerySmsSignParams(signName));
            msgSign1.setSignName(signName);
            if(msgSign1.getSignStatus()==null){
                return null;
            }else{
                boolean flag = msgSignMapper.insert(msgSign1)>0;
                if (!flag) {
                    flag = msgSignMapper.insert(msgSign1)>0;
                    LOGGER.info("添加签名失败"+signName+"已经重试，结果："+flag);
                }
                return msgSign1;
            }
        }
        return msgSign;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean delSmsSignFromAli(String signName){
        QueryDelSmsSignResponse response = iSmsSignService.deleteSmsSign(new DelSmsSignParams(signName));
        LOGGER.info("删除阿里云签名"+signName+"已经，结果："+JSONObject.toJSONString(response));
        boolean flag = msgSignMapper.deleteById(signName)>0;
        if (!flag) {
            flag = msgSignMapper.deleteById(signName)>0;
            LOGGER.info("删除签名失败"+signName+"已经重试，结果："+flag);
        }
        LOGGER.info("删除签名成功"+signName+"，删除数据库数据结果："+flag);
        return flag;
    }

    /**
     * 定时获取审核没通过签名信息
     * */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshSmsSignAli(){
        List<MsgSign> list = msgSignMapper.selectSignBySignStatus();
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                MsgSign msgSign1 = iSmsSignService.querySmsSign(new QuerySmsSignParams(list.get(i).getSignName()));
                boolean flag = msgSignMapper.updateById(msgSign1)>0;
                if (!flag) {
                    flag = msgSignMapper.updateById(msgSign1)>0;
                    LOGGER.info("更新签名失败"+msgSign1.getSignName()+"已经重试，结果："+flag);
                }
                LOGGER.info("更新签名成功"+ JSONObject.toJSONString(msgSign1));
            }
        }
    }
}
