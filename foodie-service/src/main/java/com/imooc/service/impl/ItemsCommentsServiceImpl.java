package com.imooc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.mapper.ItemsCommentsMapper;
import com.imooc.pojo.ItemsComments;
import com.imooc.service.IItemsCommentsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表  服务实现类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Service
public class ItemsCommentsServiceImpl extends ServiceImpl<ItemsCommentsMapper, ItemsComments> implements IItemsCommentsService {

}
