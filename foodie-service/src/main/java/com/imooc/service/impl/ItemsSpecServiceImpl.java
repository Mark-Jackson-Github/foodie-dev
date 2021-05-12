package com.imooc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.mapper.ItemsSpecMapper;
import com.imooc.mapper.ItemsSpecMapperCustom;
import com.imooc.pojo.ItemsSpec;
import com.imooc.service.IItemsSpecService;
import com.imooc.vo.ShopcartBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 服务实现类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Service
public class ItemsSpecServiceImpl extends ServiceImpl<ItemsSpecMapper, ItemsSpec> implements IItemsSpecService {

    @Autowired
    private ItemsSpecMapperCustom itemsSpecMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartBO> refreshShopcartBOByItemsSpecId(String[] itemSpecIds) {
        return itemsSpecMapperCustom.refreshShopcartBOByItemsSpecId(itemSpecIds);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void decreaseItemSpecStock(String specId, int buyCounts) {
        /**
         * 库存扣减的方式
         *
         * 1.synchronized 不推荐使用，集群下无用，性能低下
         * 2.锁数据库，不推荐，导致数据库性能低下
         * 3.分布式锁 zookeeper redis
         *
         * lockUtil.getLock();
         * //1.查库存
         * //2.判断库存是否足够
         * lockUtil.unLock();
         *
         * 4.数据库乐观锁，单体适合
         *
         *
         */

        int result = itemsSpecMapperCustom.decreaseItemSpecStock(specId, buyCounts);
        if (result != 1){
            throw new RuntimeException("订单创建失败，原因：库存不足！");
        }

    }
}
