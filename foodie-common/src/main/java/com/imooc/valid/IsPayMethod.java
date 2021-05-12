package com.imooc.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Bobo
 */
@Documented
@Constraint(
        validatedBy = ValidatePayMethod.class   //校验类
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsPayMethod {

    String message() default "支付方式不支持";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}