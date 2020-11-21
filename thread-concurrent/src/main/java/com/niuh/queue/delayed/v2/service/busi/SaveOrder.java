package com.niuh.queue.delayed.v2.service.busi;

import com.niuh.queue.delayed.v2.service.IDelayOrder;
import com.niuh.queue.delayed.v2.dao.OrderExpDao;
import com.niuh.queue.delayed.v2.model.OrderExp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 订单相关的服务
 * </p>
 **/
@Slf4j
public class SaveOrder {


    // 取消付款
    public final static short UNPAY = 0;
    // 付款
    public final static short PAYED = 1;
    // 过期
    public final static short EXPIRED = -1;

    @Autowired
    private OrderExpDao orderExpDao;

    @Autowired
    @Qualifier("dq")
    private IDelayOrder delayOrder;

    /**
     * 接收前端页面参数，生成订单
     *
     * @param orderNumber 订单个数
     */
    public void insertOrders(int orderNumber) {
        Random r = new Random();
        OrderExp orderExp;
        for (int i = 0; i < orderNumber; i++) {
            //订单的超时时长，单位秒
            long expireTime = r.nextInt(20) + 5;
            orderExp = new OrderExp();
            String orderNo = "DD00_" + expireTime + "S";
            orderExp.setOrderNo(orderNo);
            orderExp.setOrderNote("火车票订单——" + orderNo);
            orderExp.setOrderStatus(UNPAY);
            orderExpDao.insertDelayOrder(orderExp, expireTime);
            log.info("保存订单到DB:" + orderNo);
            delayOrder.orderDelay(orderExp, expireTime);
        }
    }

    /**
     * 应用重启带来的问题：
     * 1、保存在Queue中的订单会丢失，这些丢失的订单会在什么时候过期，因为队列里已经没有这个订单了，无法检查了，这些订单就得不到处理了。
     * 2、已过期的订单不会被处理，在应用的重启阶段，可能会有一部分订单过期，这部分过期未支付的订单同样也得不到处理，会一直放在数据库里，
     * 过期未支付订单所对应的资源比如电影票所对应的座位，就不能被释放出来，让别的用户来购买。
     * 解决之道 ：在系统启动时另行处理
     */
    @PostConstruct
    public void initDelayOrder() {
        log.info("系统启动，扫描表中过期未支付的订单并处理.........");
        int counts = orderExpDao.updateExpireOrders();
        log.info("系统启动，处理了表中[" + counts + "]个过期未支付的订单！");
        List<OrderExp> orderList = orderExpDao.selectUnPayOrders();
        log.info("系统启动，发现了表中还有[" + orderList.size() + "]个未到期未支付的订单！推入检查队列准备到期检查....");
        for (OrderExp order : orderList) {
            long expireTime = order.getExpireTime().getTime() - (new Date().getTime());
            delayOrder.orderDelay(order, expireTime);
        }
    }
}
