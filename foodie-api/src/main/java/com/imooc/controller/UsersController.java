package com.imooc.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;
import com.imooc.service.IUsersService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表  前端控制器
 * </p>
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Api(value = "注册登陆",tags = {"用于注册登陆的相关接口"})//描述controller
@RestController
@RequestMapping("/passport")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")//描述接口
    @GetMapping("/usernameIsExist")
    public ResultModel usernameIsExist(@RequestParam String username){
        //1.判断用户名不能为空
        if(StrUtil.isBlank(username)){
            return ResultModel.errorMsg("用户名不能为空");
        }
        //2.查找注册的用户名是否存在
        boolean isExist = usersService.queryUsernameIsExist(username);
        if(isExist){
            return ResultModel.errorMsg("用户名已存在");
        }
        //3.用户名没有重复
        return ResultModel.ok();
    }

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")//描述接口
    @PostMapping("/regist")
    public ResultModel register(@Validated @RequestBody UserBO userBo,
                                HttpServletRequest request, HttpServletResponse response){
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();
        //判断用户名是否存在
        boolean isExist = usersService.queryUsernameIsExist(username);
        if(isExist){
            return ResultModel.errorMsg("用户名已存在");
        }
        //判断两次密码是否一直
        if(!password.equals(confirmPassword)){
            return ResultModel.errorMsg("密码与确认密码不一致");
        }
        //实现注册
        Users user = usersService.createUser(userBo);
        user=setNullProperty(user);
        //服务端给前端设置cookie,并且加密
        //前端使用var userCookie = app.getCookie("user"); 获取用户信息
        CookieUtils.setCookie(request,response,"user",JSONUtil.toJsonStr(user),true);

        //TODO 生成用户token，存入redis会话
        //TODO 同步购物车数据

        return ResultModel.ok(user);
    }

    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")//描述接口
    @PostMapping("/login")
    public ResultModel login(@RequestBody UserBO userBo,
                             HttpServletRequest request, HttpServletResponse response){
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();
        //实现注册
        Users user = usersService.queryUserForLogin(username,password);
        if(user==null){
            return ResultModel.errorMsg("用户名或密码不正确");
        }
        user=setNullProperty(user);
        //服务端给前端设置cookie,并且加密
        //前端使用var userCookie = app.getCookie("user"); 获取用户信息
        CookieUtils.setCookie(request,response,"user",JSONUtil.toJsonStr(user),true);

        //TODO 生成用户token，存入redis会话
        //TODO 同步购物车数据

        return ResultModel.ok(user);
    }

    //脱敏
    private Users setNullProperty(Users user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);
        return user;
    }

    @ApiOperation(value = "用户登出",notes = "用户登出",httpMethod = "POST")//描述接口
    @GetMapping(value = "logout")
    public ResultModel logout(@RequestParam String userId,
                              HttpServletRequest request, HttpServletResponse response){
        CookieUtils.deleteCookie(request,response,"user");

        //TODO 用户退出登录，需要清空购物车
        //TODO 分布式回话需要清楚用户数据
        return ResultModel.ok();
    }


}
