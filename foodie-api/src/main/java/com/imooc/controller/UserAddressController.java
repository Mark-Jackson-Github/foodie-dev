package com.imooc.controller;


import cn.hutool.core.util.StrUtil;
import com.imooc.bo.AddressBO;
import com.imooc.pojo.UserAddress;
import com.imooc.service.IUserAddressService;
import com.imooc.utils.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户地址表  前端控制器
 * </p>
 *
 * 用户在确认订单页面，可以针对收获地址做以下操作：
 * 1. 查询用户的所有收获地址列表
 * 2. 新增收货地址
 * 3. 删除收货地址
 * 4. 修改收货地址
 * 5. 设置默认地址
 *
 * @author ainioayi
 * @since 2021-01-29
 */
@Api(value = "地址相关",tags = {"地址相关的api接口"})
@RestController
@RequestMapping("/address")
public class UserAddressController {

    @Autowired
    private IUserAddressService userAddressService;


    @ApiOperation(value = "查询用户的所有收获地址列表",httpMethod = "GET")//描述接口
    @GetMapping(value = "list")
    public ResultModel list(@ApiParam(name = "userId",value = "用户id",required = true)
                                @RequestParam String userId){
        if (StrUtil.isBlank(userId)){
            return ResultModel.error();
        }
        List<UserAddress> userAddresses=userAddressService.queryAll(userId);
        return ResultModel.ok(userAddresses);
    }

    @ApiOperation(value = "新增地址",httpMethod = "POST")
    @PostMapping(value = "add")
    public ResultModel add(@ApiParam(name = "用于新增或者修改地址的BO",value = "addressBO",required = true)
                            @RequestBody @Validated AddressBO addressBO){
        userAddressService.addNewAddress(addressBO);
        return ResultModel.ok();
    }

    @ApiOperation(value = "修改地址",httpMethod = "PUT")
    @PutMapping(value = "update")
    public ResultModel update(@ApiParam(name = "用于新增或者修改地址的BO",value = "addressBO",required = true)
                           @RequestBody @Validated AddressBO addressBO){
        if (StrUtil.isBlank(addressBO.getAddressId())){
            return ResultModel.error();
        }
        userAddressService.updateAddress(addressBO);
        return ResultModel.ok();
    }

    /**
     * 删除用户地址，如果删除的时默认地址，让用户自行重新设置，后台逻辑不处理
     * @param userId
     * @param addressId
     * @return
     */
    @ApiOperation(value = "删除地址",httpMethod = "DELETE")//描述接口
    @DeleteMapping(value = "del")
    public ResultModel del(@ApiParam(name = "userId",value = "用户id",required = true)
                            @RequestParam String userId,
                           @ApiParam(name = "addressId",value = "地址id",required = true)
                           @RequestParam String addressId){
        if (StrUtil.isBlank(userId)||StrUtil.isBlank(addressId)){
            return ResultModel.error();
        }
        userAddressService.deleteUserAddress(userId,addressId);
        return ResultModel.ok();
    }


    @ApiOperation(value = "设置默认地址",httpMethod = "POST")
    @PostMapping(value = "setDefault")
    public ResultModel setDefault(@ApiParam(name = "userId",value = "用户id",required = true)
                               @RequestParam String userId,
                           @ApiParam(name = "addressId",value = "地址id",required = true)
                               @RequestParam String addressId){
        if (StrUtil.isBlank(userId)||StrUtil.isBlank(addressId)){
            return ResultModel.error();
        }
        userAddressService.updateUserAddressToBeDefault(userId,addressId);
        return ResultModel.ok();
    }

}
