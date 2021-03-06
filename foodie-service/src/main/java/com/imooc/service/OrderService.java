package com.imooc.service;


import com.imooc.bo.SubmitOrderBO;
import com.imooc.pojo.OrderStatus;
import com.imooc.vo.OrderVO;

import java.util.List;

/**
 * 2 * @Author: 小王同学
 * 3 * @Date: 2020/12/24 15:08
 * 4
 */
public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO
     */
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    public OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时未支付订单
     */
    public void closeOrder();
}
