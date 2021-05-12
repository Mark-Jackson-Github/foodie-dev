package com.imooc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pojo.OrderItems;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单商品关联表  Mapper 接口
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Repository
public interface OrderItemsMapper extends BaseMapper<OrderItems> {

}
