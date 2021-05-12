package com.imooc.vo;

import lombok.Data;

/**
 * 6个最新商品的简单数据类型
 * @author Bobo
 *
 *  i.id as itemId,
 *         i.item_name as itemName,
 *         ii.url as itemUrl,
 */
@Data
public class SimpleItemVO {

    private String itemId;
    private String itemName;
    private String itemUrl;
}
