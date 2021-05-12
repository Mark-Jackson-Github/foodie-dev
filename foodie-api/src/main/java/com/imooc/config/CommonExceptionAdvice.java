package com.imooc.config;

import com.imooc.utils.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
@Slf4j
public class CommonExceptionAdvice {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultModel handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数验证失败:{}", e.getMessage());
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        List<String> errorMessages = errorList.stream().map(x->{
            String itemMessage= messageSource.getMessage(x.getDefaultMessage(), null, x.getDefaultMessage(), LocaleContextHolder.getLocale());
            return String.format("%s", itemMessage);
        }).collect(Collectors.toList());
        return ResultModel.errorMsg(errorMessages.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultModel handleRuntimeException(RuntimeException e) {
        log.error("运行时异常:{}", e.getMessage());
        return ResultModel.errorMsg(e.getMessage());
    }
}
