package com.imooc.controller;


import com.imooc.bo.SubmitOrderBO;
import com.imooc.service.IOrdersService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 订单表  前端控制器
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Api(value = "订单相关",tags = {"订单相关的api接口"})
@RestController
@RequestMapping("/orders")
public class OrdersController {

    public static final String FOODIE_SHOPCART="shopcart";

    @Autowired
    private IOrdersService ordersService;

    @ApiOperation(value = "提交订单，创建订单，返回订单id",httpMethod = "POST")
    @PostMapping(value = "create")
    public ResultModel create(@ApiParam(name = "用于创建订单的BO",value = "submitOrderBO",required = true)
                           @RequestBody @Validated SubmitOrderBO submitOrderBO,
                              HttpServletRequest request,
                              HttpServletResponse response){
        System.out.println(submitOrderBO);
        // 1. 创建订单
        String orderId = ordersService.createOrder(submitOrderBO);
        // 2. 创建订单后，移除购物车中已提交（已结算）的商品
        /**
         * 1001
         * 1002 -> 用户购买
         * 1003 -> 用户购买
         * 1004
         */
        //TODO 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        CookieUtils.setCookie(request,response,FOODIE_SHOPCART,"");

        // 3. 向支付中心发送当前订单，用于保存支付中心的订单数据

        return ResultModel.ok(orderId);
    }

}
