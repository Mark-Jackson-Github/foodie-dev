package com.imooc.vo;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import lombok.Data;

import java.util.List;

/**
 * 商品详情信息
 * Items items = iItemsService.queryItemsById(itemId);
 *         List<ItemsImg> itemsImgs = iItemsService.queryItemsImgList(itemId);
 *         List<ItemsSpec> itemsSpecs = iItemsService.queryItemsSpecList(itemId);
 *         ItemsParam itemsParam = iItemsService.queryItemsParam(itemId);
 * @author Bobo
 */
@Data
public class ItemInfoVO {

    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
