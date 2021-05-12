package com.imooc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.pojo.Items;
import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 Mapper 接口
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Repository
public interface ItemsMapperCustom extends BaseMapper<Items> {

    /**
     * 查询商品评价
     */
    public IPage<ItemCommentVO> queryItemComments(Page<ItemCommentVO> page, @Param("ItemsId") String ItemsId, @Param("level") Integer level);


    public IPage<SearchItemsVO> searchItems(Page<SearchItemsVO> page,@Param("Keywords")String Keywords,@Param("sort")String sort);

    public IPage<SearchItemsVO> searchItemsByThirdCat(Page<SearchItemsVO> page,@Param("catId")Integer catId,@Param("sort")String sort);


}
