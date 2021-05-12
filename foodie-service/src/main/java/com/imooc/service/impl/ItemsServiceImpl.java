package com.imooc.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.enums.CommentLevel;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.service.IItemsService;
import com.imooc.utils.DesensitizationUtil;
import com.imooc.vo.CommentLevelCountsVO;
import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.SearchItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务实现类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemsById(String id) {
        return itemsMapper.selectById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemsImgList(String itemsId) {
        LambdaQueryWrapper<ItemsImg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ItemsImg::getItemId,itemsId);
        return itemsImgMapper.selectList(queryWrapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemsSpecList(String itemsId) {
        LambdaQueryWrapper<ItemsSpec> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ItemsSpec::getItemId,itemsId);
        return itemsSpecMapper.selectList(queryWrapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemsParam(String itemsId) {
        LambdaQueryWrapper<ItemsParam> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ItemsParam::getItemId,itemsId);
        return itemsParamMapper.selectOne(queryWrapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemsId) {
        Integer goodCounts = getCommentCounts(itemsId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentCounts(itemsId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemsId, CommentLevel.BAD.type);
        Integer totalCounts = goodCounts + normalCounts + badCounts;

        CommentLevelCountsVO countsVO=new CommentLevelCountsVO();
        countsVO.setGoodCounts(goodCounts);
        countsVO.setBadCounts(badCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setTotalCounts(totalCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public IPage<ItemCommentVO> queryPagedComments(String itemsId, Integer level, Integer pageNum, Integer pageSize) {
        Page<ItemCommentVO> page = new Page<>(pageNum,pageSize);
        IPage<ItemCommentVO> itemCommentVOIPage = itemsMapperCustom.queryItemComments(page, itemsId, level);
        //昵称脱敏
        itemCommentVOIPage.getRecords().forEach(x->{
            x.setNickname(DesensitizationUtil.commonDisplay(x.getNickname()));
        });
        return itemCommentVOIPage;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public IPage<SearchItemsVO> searchItems(String keywords, String sort, Integer pageNum, Integer pageSize) {
        Page<SearchItemsVO> page = new Page<>(pageNum,pageSize);
        IPage<SearchItemsVO>  searchItems= itemsMapperCustom.searchItems(page,keywords,sort);
        return searchItems;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public IPage<SearchItemsVO> searchItemsByThirdCat(Integer catId, String sort, Integer pageNum, Integer pageSize) {
        Page<SearchItemsVO> page = new Page<>(pageNum,pageSize);
        IPage<SearchItemsVO>  searchItems= itemsMapperCustom.searchItemsByThirdCat(page,catId,sort);
        return searchItems;    }


    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemsId, Integer level){
        LambdaQueryWrapper<ItemsComments> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ItemsComments::getItemId,itemsId);
        queryWrapper.eq(ItemsComments::getCommentLevel,level);
        return itemsCommentsMapper.selectCount(queryWrapper);
    }
}
