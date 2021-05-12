package com.imooc.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.service.IItemsService;
import com.imooc.utils.ResultModel;
import com.imooc.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 前端控制器
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Api(value = "商品接口",tags = {"商品信息展示的相关接口"})//描述controller
@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private IItemsService iItemsService;

    @ApiOperation(value = "查询商品详情",httpMethod = "GET")//描述接口
    @GetMapping("/info/{itemId}")
    public ResultModel info(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @PathVariable String itemId){
        if (StrUtil.isBlankIfStr(itemId)){
            return ResultModel.error();
        }

        Items items = iItemsService.queryItemsById(itemId);
        List<ItemsImg> itemsImgs = iItemsService.queryItemsImgList(itemId);
        List<ItemsSpec> itemsSpecs = iItemsService.queryItemsSpecList(itemId);
        ItemsParam itemsParam = iItemsService.queryItemsParam(itemId);

        ItemInfoVO itemInfoVO =new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgs);
        itemInfoVO.setItemSpecList(itemsSpecs);
        itemInfoVO.setItemParams(itemsParam);
        return ResultModel.ok(itemInfoVO);
    }


    @ApiOperation(value = "查询商品评价等级数量",httpMethod = "GET")//描述接口
    @GetMapping("/commentLevel")
    public ResultModel CommentLevelCounts(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId){
        if (StrUtil.isBlankIfStr(itemId)){
            return ResultModel.error();
        }
        CommentLevelCountsVO countsVO = iItemsService.queryCommentCounts(itemId);
        return ResultModel.ok(countsVO);
    }
    @ApiOperation(value = "分页查询商品评价",httpMethod = "GET")//描述接口
    @GetMapping("/comments")
    public ResultModel comments(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level",value = "评价等级",required = false)
            @RequestParam(required = false) Integer level,
            @ApiParam(name = "page",value = "当前页数",required = false)
            @RequestParam(defaultValue = "1",required = false) Integer page,
            @ApiParam(name = "pageSize",value = "每页条数",required = false)
            @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        if (StrUtil.isBlankIfStr(itemId)){
            return ResultModel.error();
        }
        IPage<ItemCommentVO> itemCommentVOIPage = iItemsService.queryPagedComments(itemId, level, page, pageSize);

        return ResultModel.ok(new MyPage<>(itemCommentVOIPage));
    }

    @ApiOperation(value = "搜索商品列表",httpMethod = "GET")//描述接口
    @GetMapping("/search")
    public ResultModel search(
            @ApiParam(name = "keywords",value = "关键字",required = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort",value = "排序字段",required = false)
            @RequestParam(required = false) String sort,
            @ApiParam(name = "page",value = "当前页数",required = false)
            @RequestParam(defaultValue = "1",required = false) Integer page,
            @ApiParam(name = "pageSize",value = "每页条数",required = false)
            @RequestParam(defaultValue = "10",required = false) Integer pageSize){

        IPage<SearchItemsVO> searchItems = iItemsService.searchItems(keywords, sort, page, pageSize);
        return ResultModel.ok(new MyPage<>(searchItems));
    }

    @ApiOperation(value = "根据三级分类id搜索商品列表",httpMethod = "GET")//描述接口
    @GetMapping("/catItems")
    public ResultModel catItems(
            @ApiParam(name = "catId",value = "关键字",required = true)
            @RequestParam Integer catId,
            @ApiParam(name = "sort",value = "排序字段",required = false)
            @RequestParam(required = false) String sort,
            @ApiParam(name = "page",value = "当前页数",required = false)
            @RequestParam(defaultValue = "1",required = false) Integer page,
            @ApiParam(name = "pageSize",value = "每页条数",required = false)
            @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        if (null==catId){
            return ResultModel.error();
        }
        IPage<SearchItemsVO> searchItems = iItemsService.searchItemsByThirdCat(catId, sort, page, pageSize);
        return ResultModel.ok(new MyPage<>(searchItems));
    }
}
