package com.imooc.bo;

import com.imooc.valid.IsMobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 用于新增或者修改地址的BO
                            * "addressId": addressId,
 * 								"userId": userInfo.id,
 * 								"receiver": receiver,
 * 								"mobile": mobile,
 * 								"province": prov,
 * 								"city": city,
 * 								"district": district,
 * 								"detail": detail
 * @author Bobo
 */

@Data
public class AddressBO implements Serializable {


    private String addressId;
    private String userId;

    @ApiModelProperty(value = "收货人姓名",name = "receiver",required = true)
    @NotBlank(message = "收货人姓名不能为空")
    @Size(max = 6,message = "收货人姓名不能太长")
    private String receiver;

    @NotBlank(message = "收货人手机号不能为空")
    @IsMobile
    private String mobile;

    @NotBlank(message = "收货地址信息不能为空")
    private String province;

    @NotBlank(message = "收货地址信息不能为空")
    private String city;

    @NotBlank(message = "收货地址信息不能为空")
    private String district;

    @NotBlank(message = "收货地址信息不能为空")
    private String detail;




}
