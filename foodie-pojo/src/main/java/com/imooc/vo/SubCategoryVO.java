package com.imooc.vo;

import lombok.Data;

/**
 * 三级分类
 * @author Bobo
 */
@Data
public class SubCategoryVO {
    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;
}
