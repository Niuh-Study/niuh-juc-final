package com.niuh.queue.delayed.v2.dao;

import com.niuh.queue.delayed.v2.model.OrderExp;

import java.util.List;

/**
 * <p>
 * 订单过期数据模型处理类
 * </p>
 *
 */
public class OrderExpDao {
    public void insertDelayOrder(OrderExp orderExp, long expireTime) {
    }

    public Integer updateExpireOrders() {
        return null;
    }

    public List<OrderExp> selectUnPayOrders() {
        return null;
    }

    public OrderExp selectByPrimaryKey(Integer id) {
        return null;
    }

    public void updateExpireOrder(Integer id) {
    }
}
