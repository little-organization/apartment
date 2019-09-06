package com.admin.apartment.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.admin.apartment.entity.MsgSign;
import com.admin.apartment.entity.MsgTemplate;
import com.admin.apartment.mapper.MsgTemplateMapper;
import com.admin.apartment.model.FiltersTag;
import com.admin.apartment.model.MyPage;
import com.admin.apartment.model.message.*;
import com.admin.apartment.service.IMsgTemplateService;
import com.admin.apartment.service.ISmsTemplateService;
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
public class MsgTemplateServiceImpl extends ServiceImpl<MsgTemplateMapper, MsgTemplate> implements IMsgTemplateService {
    private static final Log LOGGER = LogFactory.get();
    @Autowired
    ISmsTemplateService iSmsTemplateService;

    @Autowired
    MsgTemplateMapper msgTemplateMapper;

    @Override
    public List<FiltersTag> selectTemplateCodeAndNameList() {
        //获取数据模板名称和 CODE List
        List<MsgTemplate> list = msgTemplateMapper.selectTemplateCodeAndNameList();
        List<FiltersTag> filtersTagList = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            FiltersTag filtersTag = new FiltersTag();
            filtersTag.setText(list.get(i).getTemplateName());
            filtersTag.setValue(list.get(i).getTemplateCode());
            filtersTagList.add(filtersTag);
        }
        return filtersTagList;
    }

    @Override
    public Page<MsgTemplate> querySmsTemplateList(QuerySmsTemplateParams params) {
        //当前页数，查询的最大个数
        MyPage<QuerySmsTemplateParams> templateIPage = new MyPage<>(params.getPage(),params.getLimit(),true);
        templateIPage.setT(params);
        //获取所有数据 List
        Page<MsgTemplate> page =  msgTemplateMapper.selectSignByTemplateCode(templateIPage);
        return page;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean delSmsTemplateFromAli(String templateCode){
        SmsTemplateResponse response = iSmsTemplateService.deleteSmsTemplate((new DeleteSmsTemplateParams(templateCode)));
        LOGGER.info("删除阿里云模板"+templateCode+"已经，结果："+ JSONObject.toJSONString(response));
        boolean flag = msgTemplateMapper.deleteById(templateCode)>0;
        if (!flag) {
            flag = msgTemplateMapper.deleteById(templateCode)>0;
            LOGGER.info("删除模板失败"+templateCode+"已经重试，结果："+flag);
        }
        LOGGER.info("删除模板成功"+templateCode+"，删除数据库数据结果："+flag);
        return flag;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public MsgTemplate querySmsTemplateFromAli(String templateCode){
        MsgTemplate msgTemplate = msgTemplateMapper.selectById(templateCode);
        if(msgTemplate==null){
            MsgTemplate msgTemplate1 = iSmsTemplateService.querySmsTemplate(new QuerySmsTemplateParams(templateCode));
            msgTemplate1.setTemplateCode(templateCode);
            if(msgTemplate1.getTemplateStatus()==null){
                return null;
            }else{
                boolean flag = msgTemplateMapper.insert(msgTemplate1)>0;
                if (!flag) {
                    flag = msgTemplateMapper.insert(msgTemplate1)>0;
                    LOGGER.info("添加模板失败"+templateCode+"已经重试，结果："+flag);
                }
                return msgTemplate1;
            }
        }
        return msgTemplate;
    }

    /**
     * 定时获取审核没通过模板信息
     * */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshSmsTemplateAli(){
        List<MsgTemplate> list = msgTemplateMapper.selectTemplateByTemplateStatus();
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                MsgTemplate msgTemplate = iSmsTemplateService.querySmsTemplate(new QuerySmsTemplateParams(list.get(i).getTemplateCode()));
                boolean flag = msgTemplateMapper.updateById(msgTemplate)>0;
                if (!flag) {
                    flag = msgTemplateMapper.updateById(msgTemplate)>0;
                    LOGGER.info("更新模板失败"+msgTemplate.getTemplateCode()+"已经重试，结果："+flag);
                }
                LOGGER.info("更新模板成功"+ JSONObject.toJSONString(msgTemplate));
            }
        }
    }

}
