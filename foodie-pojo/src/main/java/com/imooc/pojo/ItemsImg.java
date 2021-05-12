package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品图片 
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("items_img")
public class ItemsImg implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 商品外键id 商品外键id
     */
    private String itemId;

    /**
     * 图片地址 图片地址
     */
    private String url;

    /**
     * 顺序 图片顺序，从小到大
     */
    private Integer sort;

    /**
     * 是否主图 是否主图，1：是，0：否
     */
    private Integer isMain;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
