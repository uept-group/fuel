package tech.uept.fuel.admin.api.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import tech.uept.fuel.admin.basic.exp.BizException;

@ControllerAdvice("tech.uept.fuel.admin.api")
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        if (body instanceof Response) {
            return body;
        }
        Response resp = new Response();
        resp.setCode(Response.success);
        resp.setData(body);
        return resp;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception ex) {
        Response resp = new Response();
        resp.setMsg(ex.getMessage());
        if (ex instanceof BizException) {
            resp.setCode(Response.bizError);
            return resp;
        }
        resp.setCode(Response.systemError);
        logger.error("handlerException, exception is ", ex);
        return resp;
    }
}