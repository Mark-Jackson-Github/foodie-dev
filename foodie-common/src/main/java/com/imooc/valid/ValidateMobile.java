package com.imooc.valid;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 通过自定义注解实现校验
 * @author Bobo
 */
public class ValidateMobile implements ConstraintValidator<IsMobile,String> {

    @Override
    public void initialize(IsMobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StrUtil.isNotBlank(s)) {
            return PhoneUtil.isMobile(s);
        } else {
            return false;
        }
    }
}
