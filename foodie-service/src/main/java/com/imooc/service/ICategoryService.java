package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pojo.Category;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.NewItemsVO;

import java.util.List;

/**
 * <p>
 * 商品分类  服务类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 查询所有一级分类
     * @return
     */
    public List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id查询二三级分类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);


    /**
     * 查询首页某个一级分类下的最新6个商品
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);


}
