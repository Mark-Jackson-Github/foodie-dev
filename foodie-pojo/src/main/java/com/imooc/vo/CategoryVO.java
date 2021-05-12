package com.imooc.vo;

import lombok.Data;

import java.util.List;

/**
 * 二级分类
 *              f.id AS id,
 *             f.`name` AS `name`,
 *             f.father_id AS fatherId,
 *             f.type AS type,
 *             c.id AS subId,
 *             c.`name` AS subName,
 *             c.type AS subType,
 *             c.father_id AS subFatherId
 * @author Bobo
 */
@Data
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
    private List<SubCategoryVO> subCatList;

}
