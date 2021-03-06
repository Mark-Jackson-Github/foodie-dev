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


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
