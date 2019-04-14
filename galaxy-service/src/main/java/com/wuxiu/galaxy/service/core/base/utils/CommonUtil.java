package com.wuxiu.galaxy.service.core.base.utils;

import com.wuxiu.galaxy.common.entity.APIResult;
import com.wuxiu.galaxy.common.enums.GlobalErrorCode;
import com.wuxiu.galaxy.common.expection.BizException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 公共工具
 *
 * @author: wuxiu
 * @date: 2019/4/13 11:32
 */
@Slf4j
@NoArgsConstructor
public class CommonUtil {

    public static boolean anyIsNull(Object... objects) {
        if (Objects.isNull(objects)) {
            return true;
        }
        return Arrays.stream(objects).anyMatch(Objects::isNull);
    }

    public static boolean anyNonNull(Object... objects) {
        if (Objects.isNull(objects)) {
            return true;
        }
        return Arrays.stream(objects).anyMatch(Objects::nonNull);
    }

    public static boolean allIsNull(Object... objects) {
        if (Objects.isNull(objects)) {
            return true;
        }

        return Arrays.stream(objects).allMatch(Objects::isNull);
    }

    public static boolean allNonNull(Object... objects) {
        if (Objects.isNull(objects)) {
            return false;
        }

        return Arrays.stream(objects).allMatch(Objects::nonNull);
    }

    public static <T> APIResult<T> paramErrorResult(String message) {
        return APIResult.error(GlobalErrorCode.INVALID_PARAM.getCode(), message);
    }

    public static <T> APIResult<T> bizErrorResult(String message) {
        return APIResult.error(GlobalErrorCode.INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public static <T> APIResult<T> systemErrorResult(String message) {
        return APIResult.error(GlobalErrorCode.FAILURE.getCode(), message);
    }

    public static <T> void checkSuccess(APIResult<T> apiResult, Consumer<String> failedLog) {
        if (!apiResult.isSuccess()) {
            failedLog.accept(apiResult.getMessage());
            throw new BizException(apiResult.getMessage());
        }
    }

    /**
     * 如果出现异常就执行默认方法
     *
     * @param supplierMethod
     * @param defaultMethod
     * @param <T>
     * @return
     */
    public static <T> T catchExceptionCore(Supplier<T> supplierMethod, Supplier<T> defaultMethod, Consumer<Exception> logConsumer) {
        try {
            return supplierMethod.get();
        } catch (RuntimeException e) {
            logConsumer.accept(e);
            return defaultMethod.get();
        }
    }

    /**
     * 如果出现异常就返回默认值
     *
     * @param supplierMethod 可能会出现异常的方法
     * @param defaultValue   出现异常后返回指定的默认值
     * @param <T>
     * @return
     */
    public static <T> T catchException(Supplier<T> supplierMethod, T defaultValue) {
        return catchExceptionCore(supplierMethod, () -> defaultValue, e -> log.warn("抓取到异常,执行默认方法", e));
    }
}

