package com.imooc.controller;


import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.service.ICarouselService;
import com.imooc.service.ICategoryService;
import com.imooc.utils.ResultModel;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.NewItemsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Api(value = "首页",tags = {"首页展示的相关接口"})//描述controller
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ICarouselService carouselService;

    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表",httpMethod = "GET")//描述接口
    @GetMapping("/carousel")
    public ResultModel carousel(){
        List<Carousel> carousels = carouselService.queryAll(YesOrNo.YES.type);
        return ResultModel.ok(carousels);
    }

    /**
     * cats
     * subCat
     * 首页分类展示需求
     * 1.第一次查询大分类，渲染到首页
     * 2.如果鼠标移动到大分类，则加载子分类的内容，如果子分类已经有内容，就不重复请求（懒加载）
     */
    @ApiOperation(value = "获取商品分类（一级）",httpMethod = "GET")//描述接口
    @GetMapping("/cats")
    public ResultModel cats(){
        List<Category> categories = categoryService.queryAllRootLevelCat();
        return ResultModel.ok(categories);
    }

    @ApiOperation(value = "根据上级分类父id，获取下级商品分类",httpMethod = "GET")//描述接口
    @GetMapping("/subCat/{rootCatId}")
    public ResultModel subCat(
            @ApiParam(name = "rootCatId",value = "一级分类id",required = true)
            @PathVariable Integer rootCatId){
        if (null==rootCatId){
            return ResultModel.errorMsg("");
        }
        List<CategoryVO> categories = categoryService.getSubCatList(rootCatId);
        return ResultModel.ok(categories);
    }

    @ApiOperation(value = "查询首页某个一级分类下的最新6个商品",httpMethod = "GET")//描述接口
    @GetMapping("/sixNewItems/{rootCatId}")
    public ResultModel sixNewItems(
            @ApiParam(name = "rootCatId",value = "一级分类id",required = true)
            @PathVariable Integer rootCatId){
        if (null==rootCatId){
            return ResultModel.errorMsg("");
        }
        List<NewItemsVO> categories = categoryService.getSixNewItemsLazy(rootCatId);
        return ResultModel.ok(categories);
    }


}
