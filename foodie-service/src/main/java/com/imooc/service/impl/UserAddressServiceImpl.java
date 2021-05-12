package com.imooc.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.bo.AddressBO;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import com.imooc.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户地址表  服务实现类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {


    @Autowired
    private UserAddressMapper userAddressMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
        LambdaQueryWrapper<UserAddress> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserAddress::getUserId,userId);
        return userAddressMapper.selectList(wrapper);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void addNewAddress(AddressBO addressBO) {
        //1. 判断当前用户是否存在地址，如果没有，则新增为默认地址
        Integer idDefault=0;
        List<UserAddress> userAddresses = this.queryAll(addressBO.getUserId());
        if (CollectionUtil.isEmpty(userAddresses)){
            idDefault=1;
        }
        //2. 保存地址到数据库
        UserAddress userAddress = new UserAddress();
        BeanUtil.copyProperties(addressBO,userAddress);
        LocalDateTime now = LocalDateTime.now();
        userAddress.setCreatedTime(now);
        userAddress.setUpdatedTime(now);
        userAddress.setIsDefault(idDefault);
        userAddressMapper.insert(userAddress);

    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updateAddress(AddressBO addressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtil.copyProperties(addressBO,userAddress);
        LocalDateTime now = LocalDateTime.now();
        userAddress.setUpdatedTime(now);
        userAddress.setId(addressBO.getAddressId());
        userAddressMapper.updateById(userAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void deleteUserAddress(String userId, String addressId) {
        LambdaUpdateWrapper<UserAddress> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(UserAddress::getUserId,userId);
        wrapper.eq(UserAddress::getId,addressId);
        userAddressMapper.delete(wrapper);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        //1. 查找默认地址，设置不默认
        UserAddress userAddress = new UserAddress();
        userAddress.setIsDefault(YesOrNo.NO.type);

        LambdaUpdateWrapper<UserAddress> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(UserAddress::getUserId,userId);
        updateWrapper.eq(UserAddress::getIsDefault,YesOrNo.YES.type);
        userAddressMapper.update(userAddress,updateWrapper);
        //2. 根据地址id修改为默认地址

        UserAddress userAddress2 = new UserAddress();
        userAddress2.setIsDefault(YesOrNo.YES.type);
        LambdaUpdateWrapper<UserAddress> wrapper2 = Wrappers.lambdaUpdate();
        wrapper2.eq(UserAddress::getId,addressId);
        userAddressMapper.update(userAddress2,wrapper2);
    }
}
