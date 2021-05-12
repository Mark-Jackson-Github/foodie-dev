package com.imooc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pojo.ItemsSpec;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 Mapper 接口
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Repository
public interface ItemsSpecMapper extends BaseMapper<ItemsSpec> {

}
