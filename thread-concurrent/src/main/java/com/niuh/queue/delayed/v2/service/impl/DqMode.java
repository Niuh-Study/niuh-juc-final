package com.niuh.queue.delayed.v2.service.impl;

import com.niuh.queue.delayed.v2.model.OrderExp;
import com.niuh.queue.delayed.v2.service.busi.DlyOrderProcessor;
import com.niuh.queue.delayed.v2.vo.ItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.DelayQueue;

/**
 * <p>
 * 阻塞队列的实现
 * </p>
 */
@Service
@Qualifier("dq")
@Slf4j
public class DqMode {

    @Autowired
    private DlyOrderProcessor processDelayOrder;
    private Thread takeOrder;

    private static DelayQueue<ItemVo<OrderExp>> delayOrder = new DelayQueue<ItemVo<OrderExp>>();

    public void orderDelay(OrderExp order, long expireTime) {
        ItemVo<OrderExp> itemOrder = new ItemVo<OrderExp>(expireTime*1000,order);
        delayOrder.put(itemOrder);
        log.info("订单[超时时长："+expireTime+"秒]被推入检查队列，订单详情："+order);
    }

    private class TakeOrder implements Runnable{

        private DlyOrderProcessor processDelayOrder;

        public TakeOrder(DlyOrderProcessor processDelayOrder) {
            super();
            this.processDelayOrder = processDelayOrder;
        }

        public void run() {
            log.info("处理到期订单线程已经启动！");
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    ItemVo<OrderExp> itemOrder = delayOrder.take();
                    if (itemOrder!=null) {
                        processDelayOrder.checkDelayOrder(itemOrder.getData());
                    }
                } catch (Exception e) {
                    log.error("The thread :",e);
                }
            }
            log.info("处理到期订单线程准备关闭......");
        }
    }

    @PostConstruct
    public void init() {
        takeOrder = new Thread(new TakeOrder(processDelayOrder));
        takeOrder.start();
    }

    @PreDestroy
    public void close() {
        takeOrder.interrupt();
    }
}
