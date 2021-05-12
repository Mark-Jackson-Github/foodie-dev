package com.imooc.controller;


import cn.hutool.core.util.StrUtil;
import com.imooc.service.IItemsService;
import com.imooc.service.IItemsSpecService;
import com.imooc.utils.ResultModel;
import com.imooc.vo.ShopcartBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Api(value = "购物车接口",tags = {"购物车的相关接口"})//描述controller
@RestController
@RequestMapping("/shopcart")
public class ShopcartController {

    @Autowired
    private IItemsService iItemsService;

    @Autowired
    private IItemsSpecService iItemsSpecService;


    @ApiOperation(value = "购物车添加商品",httpMethod = "POST")//描述接口
    @PostMapping("/add")
    public ResultModel info(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response){
        if (StrUtil.isBlankIfStr(userId)){
            return ResultModel.error();
        }
        System.out.println(shopcartBO);
        // TODO: 2021/2/24 前端用户在登录的情况下，添加商品到购物车会同时在后端同步购物车到redis缓存
        return ResultModel.ok();
    }

    /**
     * 刷新购物车，进行购物车数据渲染，因为不知道之前加入购物车的商品是否售完，或者价格是否变化，需要访问后端去获取最新的商品数据返回前端更新
     */
    @ApiOperation(value = "传递购物车内所有产品的规格ID, 请求后端获得最新数据, 更新购物车原有数据的信息",httpMethod = "GET")//描述接口
    @GetMapping("/refresh")
    public ResultModel refresh(
            @ApiParam(name = "itemSpecIds",value = "商品规格id",required = true)
            @RequestParam String[] itemSpecIds){
        if (null==itemSpecIds||itemSpecIds.length==0){
            return ResultModel.error();
        }
        List<ShopcartBO> shopcartBOS = iItemsSpecService.refreshShopcartBOByItemsSpecId(itemSpecIds);
        return ResultModel.ok(shopcartBOS);
    }

    @ApiOperation(value = "购物车删除商品",httpMethod = "POST")//描述接口
    @PostMapping("/del")
    public ResultModel info(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @ApiParam(name = "itemSpecId",value = "商品规格id",required = true)
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response){
        if (StrUtil.isBlankIfStr(userId)&&StrUtil.isBlankIfStr(itemSpecId)){
            return ResultModel.error();
        }
        // TODO: 2021/2/25 前端用户在登录的情况下，购物车删除商品会同时在后端同步到redis缓存
        return ResultModel.ok();
    }


}
