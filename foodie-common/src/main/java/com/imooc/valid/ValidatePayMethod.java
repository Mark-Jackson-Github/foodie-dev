package com.imooc.valid;

import com.imooc.enums.PayMethod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * 通过自定义注解实现校验
 * @author Bobo
 */
public class ValidatePayMethod implements ConstraintValidator<IsPayMethod,Integer> {

    @Override
    public void initialize(IsPayMethod constraintAnnotation) {

    }



    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        if (s!=null) {
            return Arrays.asList(PayMethod.values()).stream().anyMatch(x -> x.type.equals(s));
        } else {
            return false;
        }
    }
}
