package com.imooc.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 购物车业务对象
 * this.itemId = itemId;
 * 		this.itemImgUrl = itemImgUrl;
 * 		this.itemName = itemName;
 *         this.specId = specId;
 *         this.specName = specName;
 *         this.buyCounts = buyCounts;
 *         this.priceDiscount = priceDiscount;
 *         this.priceNormal = priceNormal;
 * @author Bobo
 */

@Data
@ToString
public class ShopcartBO {

    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private String priceDiscount;
    private String priceNormal;


}
