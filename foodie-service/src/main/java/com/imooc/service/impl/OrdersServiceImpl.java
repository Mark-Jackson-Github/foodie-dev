package com.imooc.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.bo.SubmitOrderBO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.service.IItemsSpecService;
import com.imooc.service.IOrderItemsService;
import com.imooc.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 订单表  服务实现类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private IOrderItemsService orderItemsService;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private IItemsSpecService iItemsSpecService;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public String createOrder(SubmitOrderBO submitOrderBO) {


        LocalDateTime now = LocalDateTime.now();
        //包邮费用设置为0
        Integer postAmount=0;

        // 1. 保存订单表
        Orders orders = new Orders();
        orders.setUserId(submitOrderBO.getUserId());
        orders.setPayMethod(submitOrderBO.getPayMethod());
        orders.setLeftMsg(submitOrderBO.getLeftMsg());

        // 1.1 订单地址
        UserAddress userAddress = userAddressMapper.selectById(submitOrderBO.getAddressId());
        orders.setReceiverAddress(userAddress.getProvince()+userAddress.getCity()+userAddress.getDistrict()+userAddress.getDetail());
        orders.setReceiverMobile(userAddress.getMobile());
        orders.setReceiverName(userAddress.getReceiver());
        orders.setPostAmount(postAmount);
        orders.setCreatedTime(now);
        orders.setUpdatedTime(now);
        orders.setIsComment(YesOrNo.NO.type);
        orders.setIsDelete(YesOrNo.NO.type);

        //插入获取id

        AtomicReference<Integer> totalAmount= new AtomicReference<>(0);

        AtomicReference<Integer> realPayAmount= new AtomicReference<>(0);
        orders.setRealPayAmount(realPayAmount.get());
        orders.setTotalAmount(totalAmount.get());
        ordersMapper.insert(orders);


        final String ordersId = orders.getId();
        // 2. 循环根据itemSpecIds保存订单商品信息表
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        String[] split = itemSpecIds.split(",");
        List<String> itemSpecIdList = Stream.of(split).collect(Collectors.toList());
        List<ItemsSpec> itemsSpecs = itemsSpecMapper.selectBatchIds(itemSpecIdList);

        // 预查item信息
        List<String> itemIds = itemsSpecs.stream().map(ItemsSpec::getItemId).collect(Collectors.toList());
        List<Items> items = itemsMapper.selectBatchIds(itemIds);
        Map<String, List<Items>> itemsMap = items.stream().collect(Collectors.groupingBy(Items::getId));

        // 预查img信息
        LambdaQueryWrapper<ItemsImg> itemsImgLambdaQueryWrapper = Wrappers.lambdaQuery();
        itemsImgLambdaQueryWrapper.in(ItemsImg::getItemId,itemIds);
        itemsImgLambdaQueryWrapper.eq(ItemsImg::getIsMain,YesOrNo.YES.type);
        List<ItemsImg> itemsImgs = itemsImgMapper.selectList(itemsImgLambdaQueryWrapper);
        Map<String, List<ItemsImg>> itemsImgsMap = itemsImgs.stream().collect(Collectors.groupingBy(ItemsImg::getItemId));

        List<OrderItems> orderItemsList = new ArrayList<>(itemsSpecs.size());
        itemsSpecs.stream().forEach(x->{
            //TODO 整合redis后，商品购买的数量从redis的购物车中获取
            int buyCount=1;
            OrderItems orderItems = new OrderItems();
            //规格信息
            orderItems.setItemSpecId(x.getId());
            orderItems.setItemSpecName(x.getName());
            // 商品信息
            Items item = itemsMap.get(x.getItemId()).get(0);
            orderItems.setItemId(item.getId());
            orderItems.setItemImg(itemsImgsMap.get(x.getItemId()).get(0).getUrl());
            orderItems.setItemName(item.getItemName());
            // 子订单价格
            orderItems.setBuyCounts(buyCount);
            int price = x.getPriceDiscount() * buyCount;
            orderItems.setPrice(price);
            //累计主订单价格
            totalAmount.updateAndGet(v -> v + x.getPriceNormal() * buyCount);
            realPayAmount.updateAndGet(v -> v + price);

            orderItems.setOrderId(ordersId);
            orderItemsList.add(orderItems);

            // 提交订单后，规格表扣减库存信息
            iItemsSpecService.decreaseItemSpecStock(x.getId(),buyCount);
            //商品主表的累计销量增加 Items sellCounts
            item.setSellCounts(item.getSellCounts()+buyCount);
            itemsMapper.updateById(item);


        });
        orderItemsService.saveBatch(orderItemsList);
        orders.setRealPayAmount(realPayAmount.get());
        orders.setTotalAmount(totalAmount.get());

        // 3. 保存订单状态表，特殊设计：订单状态表主键与订单表主键一致
        com.imooc.pojo.OrderStatus waitPayOrderStatus = new com.imooc.pojo.OrderStatus();
        waitPayOrderStatus.setOrderId(ordersId);
        waitPayOrderStatus.setCreatedTime(now);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatusMapper.insert(waitPayOrderStatus);

        ordersMapper.updateById(orders);

        return ordersId;
    }
}
