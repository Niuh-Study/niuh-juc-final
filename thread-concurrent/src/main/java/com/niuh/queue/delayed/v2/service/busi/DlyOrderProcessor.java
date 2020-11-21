package com.niuh.queue.delayed.v2.service.busi;

import com.niuh.queue.delayed.v2.dao.OrderExpDao;
import com.niuh.queue.delayed.v2.model.OrderExp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  处理延期订单的服务
 * </p>
 *
 */
@Service
@Slf4j
public class DlyOrderProcessor {

    @Autowired
    private OrderExpDao orderExpDao;

    /**检查数据库中指定id的订单的状态,如果为未支付，则修改为已过期*/
    public void checkDelayOrder(OrderExp record) {
        OrderExp dbOrder = orderExpDao.selectByPrimaryKey(record.getId());
        if(dbOrder.getOrderStatus()== SaveOrder.UNPAY) {
            log.info("订单【"+record+"】未支付已过期，需要更改为过期订单！");
            orderExpDao.updateExpireOrder(record.getId());
        }else {
            log.info("已支付订单【"+record+"】，无需修改！");
        }
    }
}
