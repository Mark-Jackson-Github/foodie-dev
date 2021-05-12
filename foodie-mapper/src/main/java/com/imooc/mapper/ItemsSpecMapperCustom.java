package com.imooc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pojo.ItemsSpec;
import com.imooc.vo.ShopcartBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 Mapper 接口
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Repository
public interface ItemsSpecMapperCustom extends BaseMapper<ItemsSpec> {

    public List<ShopcartBO> refreshShopcartBOByItemsSpecId(@Param("itemSpecIds")String[] itemSpecIds);


    int decreaseItemSpecStock(@Param("itemSpecIds")String specId, @Param("buyCounts")int buyCounts);
}
