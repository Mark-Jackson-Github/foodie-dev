package com.imooc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.vo.CommentLevelCountsVO;
import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.SearchItemsVO;

import java.util.List;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
public interface IItemsService extends IService<Items> {

    /**
     * 根据商品id查询商品详情
     * @param id
     * @return
     */
    public Items queryItemsById(String id);

    /**
     * 根据商品id查询商品图片列表
     * @param itemsId
     * @return
     */
    public List<ItemsImg> queryItemsImgList(String itemsId);

    /**
     * 根据商品id查询商品规格列表
     * @param itemsId
     * @return
     */
    public List<ItemsSpec> queryItemsSpecList(String itemsId);

    /**
     * 根据商品id查询商品参数
     * @param itemsId
     * @return
     */
    public ItemsParam queryItemsParam(String itemsId);


    /**
     * 根据商品id查询商品参数
     * @param itemsId
     * @return
     */
    public CommentLevelCountsVO queryCommentCounts(String itemsId);

    /**
     * 根据商品id查询商品评价（分页）
     * @param itemsId
     * @param level
     * @return
     */
    IPage<ItemCommentVO> queryPagedComments(String itemsId, Integer level, Integer pageNum, Integer pageSize);

    /**
     * 搜索商品列表（分页）
     * @return
     */
    IPage<SearchItemsVO> searchItems(String keywords, String sort, Integer pageNum, Integer pageSize);

    /**
     * 搜索商品列表通过三级分类（分页）
     * @return
     */
    IPage<SearchItemsVO> searchItemsByThirdCat(Integer catId, String sort, Integer pageNum, Integer pageSize);


}
