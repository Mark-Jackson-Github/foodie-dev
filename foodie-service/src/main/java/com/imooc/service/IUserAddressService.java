package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.bo.AddressBO;
import com.imooc.pojo.UserAddress;

import java.util.List;

/**
 * <p>
 * 用户地址表  服务类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
public interface IUserAddressService extends IService<UserAddress> {


    /**
     * 查询用户的所有收获地址列表
     * @param userId
     * @return
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 新增地址
     * @param addressBO
     */
    void addNewAddress(AddressBO addressBO);

    /**
     * 修改地址
     * @param addressBO
     */
    void updateAddress(AddressBO addressBO);

    /**
     * 删除地址
     * @param userId
     * @param addressId
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 设置默认地址
     * @param userId
     * @param addressId
     */
    void updateUserAddressToBeDefault(String userId, String addressId);
}
