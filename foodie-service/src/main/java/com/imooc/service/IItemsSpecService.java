package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pojo.ItemsSpec;
import com.imooc.vo.ShopcartBO;

import java.util.List;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 服务类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
public interface IItemsSpecService extends IService<ItemsSpec> {

    public List<ShopcartBO> refreshShopcartBOByItemsSpecId(String[] itemSpecIds);

    /**
     * 根据规格id与购买数量，扣减库存
     * @param specId
     * @param buyCounts
     */
    void decreaseItemSpecStock(String specId,int buyCounts);
}
