package org.example.common.dubbo.api;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.example.common.domain.Policy;


public interface IPolicyService {

    /**
     * 创建保单
     *
     * @param businessActionContext 上下文
     * @param policy                参数
     * @return 投保单号
     */
    @TwoPhaseBusinessAction(name = "createPolicy", commitMethod = "commitPolicy", rollbackMethod = "rollBackPolicy", useTCCFence = true)
    String createPolicy(BusinessActionContext businessActionContext, @BusinessActionContextParameter(paramName = "policy") Policy policy);

    /**
     * 由TC发起调用的提交
     *
     * @param businessActionContext 上下文
     * @return 成功或失败
     */
    boolean commitPolicy(BusinessActionContext businessActionContext);


    /**
     * 由TC发起调用的回滚
     *
     * @param businessActionContext 上下文
     * @return 成功或失败
     */
    boolean rollBackPolicy(BusinessActionContext businessActionContext);
}
