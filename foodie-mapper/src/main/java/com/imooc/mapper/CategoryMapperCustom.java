package com.imooc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pojo.Category;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Repository
public interface CategoryMapperCustom extends BaseMapper<Category> {

    /**
     * 根据一级分类id查询二三级分类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO>  getSubCatList( Integer rootCatId);

    /**
     * 查询首页某个一级分类下的最新6个商品
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);

}
