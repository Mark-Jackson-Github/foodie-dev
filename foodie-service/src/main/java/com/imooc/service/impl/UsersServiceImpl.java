package com.imooc.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.bo.UserBO;
import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    public UsersMapper usersMapper;

    public static final String USER_FACE="https://himg.bdimg.com/sys/portraitn/item/bed06a6d63656d6e6133393436bb21";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        LambdaQueryWrapper<Users> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,username);
        Users users = usersMapper.selectOne(wrapper);
        return users==null?false:true;
    }

    /**
     * 创建用户
     *
     * @param userBo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBo) {
        Users users=new Users();
        users.setUsername(userBo.getUsername());
        users.setPassword(SecureUtil.md5(userBo.getPassword()));
        //默认用户昵称同用户名
        users.setNickname(userBo.getUsername());
        //默认头像
        users.setFace(USER_FACE);
        users.setBirthday(DateUtil.parseLocalDateTime("1900-01-01 00:00:00").toLocalDate());
        //默认性别为保密
        users.setSex(Sex.secret.type);
        LocalDateTime now = LocalDateTime.now();
        users.setCreatedTime(now);
        users.setUpdatedTime(now);
        int insert = usersMapper.insert(users);
        return users;
    }

    /**
     * 查找用户用于登录
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        LambdaQueryWrapper<Users> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,username);
        wrapper.eq(Users::getPassword,SecureUtil.md5(password));
        Users users = usersMapper.selectOne(wrapper);
        return users;
    }
}
