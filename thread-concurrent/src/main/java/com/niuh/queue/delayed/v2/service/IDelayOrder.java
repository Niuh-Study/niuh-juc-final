package com.niuh.queue.delayed.v2.service;

import com.niuh.queue.delayed.v2.model.OrderExp;

/**
 * <p>
 * 延时处理订单的接口
 * </p>
 *
 */
public interface IDelayOrder {

    /**
     * 进行延时处理的方法
     * @param order 要进行延时处理的订单
     * @param expireTime 延时时长，单位秒
     */
    void orderDelay(OrderExp order, long expireTime);
}
