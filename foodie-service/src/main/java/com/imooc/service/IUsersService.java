package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;

/**
 * <p>
 * 用户表  服务类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
public interface IUsersService extends IService<Users> {

    /**
     * 判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @return
     */
    public Users createUser(UserBO userBo);

    /**
     * 查找用户用于登录
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username,String password);

}
