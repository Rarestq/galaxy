package com.wuxiu.galaxy.web.exception;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.enums.GlobalErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获
 *
 * @author: wuxiu
 * @date: 2019/5/29 21:32
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public APIResult handleException(HttpServletResponse res, Exception ex) {
        log.error("系统异常,ex:", ex);
        return APIResult.error(GlobalErrorCodeEnum.SYSTEM_EXCEPTION.getCode(),
                "系统异常,请联系管理员");
    }

    /**
     * validator 参数校验 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIResult<?> handleMessageBindException(MethodArgumentNotValidException e) {
        log.error("参数绑定失败", e.getBindingResult());
        log.error("参数绑定失败", e.getMessage());
        log.error("参数绑定失败", e.getParameter());

        BindingResult result = e.getBindingResult();

        return buildParamsErrorResult(result);
    }

    private APIResult<?> buildParamsErrorResult(BindingResult result) {
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);

        log.error("参数错误：{}", message);

        return APIResult.error(code, message);
    }
}
