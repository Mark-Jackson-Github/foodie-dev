package com.imooc.vo;

import lombok.Data;

/**
 *
 *  商品搜索列表VO
 * 	i.id AS itemId,
 * 	i.item_name AS itemName,
 * 	i.sell_counts AS sellCounts,
 * 	ii.url as  imgUrl,
 * 	tempSpec.priceDiscount as price
 * @author Bobo
 */
@Data
public class SearchItemsVO {
    private String itemId;
    private String itemName;
    private Integer sellCounts;
    private String imgUrl;
    private Integer price;

}
