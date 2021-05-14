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
 * 轮播图 
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("carousel")
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 图片 图片地址
     */
    private String imageUrl;

    /**
     * 背景色 背景颜色
     */
    private String backgroundColor;

    /**
     * 商品id 商品id
     */
    private String itemId;

    /**
     * 商品分类id 商品分类id
     */
    private String catId;

    /**
     * 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     */
    private Integer type;

    /**
     * 轮播图展示顺序 轮播图展示顺序，从小到大
     */
    private Integer sort;

    /**
     * 是否展示 是否展示，1：展示    0：不展示
     */
    private Integer isShow;

    /**
     * 创建时间 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间 更新
     */
    private LocalDateTime updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
