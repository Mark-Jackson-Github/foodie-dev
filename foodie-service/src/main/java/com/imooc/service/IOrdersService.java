package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.bo.SubmitOrderBO;
import com.imooc.pojo.Orders;

/**
 * <p>
 * 订单表  服务类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
public interface IOrdersService extends IService<Orders> {

    /**
     * 创建订单相关信息
     * @param submitOrderBO
     */
    String createOrder(SubmitOrderBO submitOrderBO);
}
