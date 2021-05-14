package com.imooc.bo;

import com.imooc.valid.IsPayMethod;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;


/**
 * 用于创建订单的BO
 *
 *
 "userId": userInfo.id,
 "itemSpecIds": itemSpecIds,
 "addressId": choosedAddressId,
 "payMethod": choosedPayMethod,
 "leftMsg": orderRemarker,

 * @author Bobo
 */

@Data
public class SubmitOrderBO implements Serializable {
    @NotBlank(message = "地址id不能为空")
    private String addressId;
    @NotBlank(message = "用户id不能为空")
    private String userId;
    @NotBlank(message = "商品规格不能为空")
    private String itemSpecIds;
    @IsPayMethod
    private Integer payMethod;
    private String leftMsg;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemSpecIds() {
        return itemSpecIds;
    }

    public void setItemSpecIds(String itemSpecIds) {
        this.itemSpecIds = itemSpecIds;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getLeftMsg() {
        return leftMsg;
    }

    public void setLeftMsg(String leftMsg) {
        this.leftMsg = leftMsg;
    }
}
