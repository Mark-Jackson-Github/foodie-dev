package com.wjw.service;

import com.wjw.pojo.Carousel;
import com.wjw.pojo.UserAddress;
import com.wjw.pojo.bo.AddressBO;

import java.util.List;

/**
 * 2 * @Author: 小王同学
 * 3 * @Date: 2020/12/24 15:08
 * 4
 */
public interface AddressService {

    /**
     * 根据用户id查询用户的收货地址列表
     * @param userId
     * @return
     */
    public List<UserAddress> queryAll(String userId);

    /**
     * 用户新增地址
     * @param addressBO
     */
    public void addNewUserAddress(AddressBO addressBO);

    /**
     * 根据用户id和地址id，删除对应的用户地址信息
     * @param userId
     * @param addressId
     */
    public void deleteUserAddress(String userId, String addressId);

    /**
     * 修改默认地址
     * @param userId
     * @param addressId
     */
    public void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据用户id和地址id查询具体的用户的某个地址
     * @param userId
     * @param addressId
     * @return
     */
    public UserAddress queryUserAddress(String userId, String addressId);

}
