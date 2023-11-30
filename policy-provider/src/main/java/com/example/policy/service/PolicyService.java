package com.example.policy.service;

import com.alibaba.fastjson2.JSONObject;
import com.example.policy.mapper.PolicyMapper;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.common.domain.Policy;
import org.example.common.dubbo.api.IPolicyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@DubboService(interfaceClass = IPolicyService.class)
@RequiredArgsConstructor
public class PolicyService implements IPolicyService {

    private final PolicyMapper policyMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createPolicy(BusinessActionContext businessActionContext, Policy policy) {
        log.info("这是policyTry:{}",RootContext.getXID());
        String applyFromId = UUID.randomUUID().toString().replaceAll("-", "");
        policy.setApplyFromId(applyFromId);
        //policy.setIsLock(Boolean.TRUE);
        policyMapper.insertSelective(policy);
        // 下方是模拟异常
        // Lists.newArrayList().get(0).toString();
        // 设置上下文
        businessActionContext.setUpdated(Boolean.TRUE);
        businessActionContext.getActionContext().put("policy",policy);
        return applyFromId;
    }

    @Override
    public boolean commitPolicy(BusinessActionContext businessActionContext) {
        log.info("commitPolicy:{}",businessActionContext.getXid());
        return true;
    }

    @Override
    public boolean rollBackPolicy(BusinessActionContext businessActionContext) {
        log.info("这是policyRollBack:{}",businessActionContext.getXid());
        String context = JSONObject.toJSONString(businessActionContext.getActionContext("policy"));
        Policy request = JSONObject.parseObject(context, Policy.class);
        Policy policy = new Policy();
        policy.setIsDelete(Boolean.TRUE);
        policy.setApplyFromId(request.getApplyFromId());
        policyMapper.updateByPrimaryKeySelective(policy);
        return true;
    }
}
