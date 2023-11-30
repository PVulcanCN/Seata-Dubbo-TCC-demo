package org.example.common.dubbo.api;


import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.example.common.domain.Order;


public interface IOrderService {


    @TwoPhaseBusinessAction(name = "createOrder", commitMethod = "commitOrder", rollbackMethod = "rollBackOrder", useTCCFence = true)
    String createOrder(BusinessActionContext businessActionContext, @BusinessActionContextParameter(paramName = "order") Order order);

    /**
     * 由TC发起调用的提交
     *
     * @param businessActionContext 上下文
     * @return 成功或失败
     */
    boolean commitOrder(BusinessActionContext businessActionContext);


    /**
     * 由TC发起调用的回滚
     *
     * @param businessActionContext 上下文
     * @return 成功或失败
     */
    boolean rollBackOrder(BusinessActionContext businessActionContext);
}
