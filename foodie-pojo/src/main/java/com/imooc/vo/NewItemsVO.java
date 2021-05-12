package com.imooc.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * @author Bobo
 */
@Data
public class NewItemsVO {

    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;



}
