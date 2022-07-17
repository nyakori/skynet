package com.kaos.walnut.core.frame.spring.advice;

import java.io.IOException;
import java.lang.reflect.Type;

import com.kaos.walnut.core.frame.spring.interceptor.LogInterceptor;
import com.kaos.walnut.core.util.ObjectUtils;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
class LogAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        // 加入body
        LogInterceptor.logBuilder.get().append(String.format(" body = %s", ObjectUtils.serialize(body)));

        // 写日志
        log.info(LogInterceptor.logBuilder.get().toString());

        // 透传body
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
