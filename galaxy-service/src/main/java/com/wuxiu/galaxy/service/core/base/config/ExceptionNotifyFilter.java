package com.wuxiu.galaxy.service.core.base.config;

import com.alibaba.dubbo.rpc.*;
import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.expection.BizException;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.expection.SmsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * META-INF.dubbo 异常通知过滤器
 *
 * @author: wuxiu
 * @date: 2019/4/17 20:31
 */
@Slf4j
@Component
public class ExceptionNotifyFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = invoker.invoke(invocation);
        if (result.hasException()) {
            //获取dubbo异常
            Throwable exception = result.getException();

            // 是Dubbo本身的异常，直接抛出
            if (exception instanceof RpcException) {
                log.error("dubbo异常", exception);
                return result;
            }

            // validator 参数校验 异常处理
            if (exception instanceof BindException) {
                BindException e = (BindException) exception;
                BindingResult bindingResult = e.getBindingResult();
                APIResult<?> apiResult = buildParamErrorResult(bindingResult);
                log.info("参数错误，{}", apiResult.getMessage());
                return new RpcResult(apiResult);
            }

            // validator 参数校验 异常处理
            if (exception instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
                BindingResult bindingResult = e.getBindingResult();
                APIResult<?> apiResult = buildParamErrorResult(bindingResult);
                log.info("参数错误，{}", apiResult.getMessage());
                return new RpcResult(apiResult);
            }

            // 参数异常处理
            if (exception instanceof ParamException) {
                log.info("参数错误：{}", exception.getMessage());
                return new RpcResult(APIResult.error(exception.getMessage()));
            }

            // 业务异常处理
            if (exception instanceof BizException) {
                log.info("业务异常：{}", exception.getMessage());
                return new RpcResult(APIResult.error(exception.getMessage()));
            }

            // 短信异常处理
            if (exception instanceof SmsException) {
                log.info("短信异常:{}", exception.getMessage());
                return new RpcResult(APIResult.error(exception.getMessage()));
            }

            // 运行时异常
            if (exception instanceof RuntimeException) {
                log.info("运行时异常：{}", exception.getMessage());
                return new RpcResult(APIResult.error(exception.getMessage()));
            }
        }

        return result;
    }

    private APIResult<?> buildParamErrorResult(BindingResult result) {
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);

        log.error("参数错误：{}", message);

        return APIResult.error(code);
    }
}
