package com.wuxiu.galaxy.service.core.base.utils;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.enums.GlobalErrorCodeEnum;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
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

    public static <T, R> APIResult<R> errorAPIResult(APIResult<T> apiResult) {
        if (apiResult.isSuccess()) {
            throw new IllegalStateException("状态非法");
        }

        return APIResult.error(apiResult.getCode(), apiResult.getMessage());
    }

    public static <T, R> APIResult<R> okBeanCopy(APIResult<T> apiResult, Class<R> clazz) {
        if (!apiResult.isSuccess()) {
            throw new IllegalStateException("状态非法");
        }
        if (Objects.isNull(apiResult.getData())) {
            return APIResult.ok();
        }

        return APIResult.ok(BeanCopierUtil.convert(apiResult.getData(), clazz));
    }

    public static <T, R> APIResult<List<R>> okListBeanCopy(APIResult<List<T>> apiResult, Class<R> clazz) {
        if (!apiResult.isSuccess()) {
            throw new IllegalStateException("状态非法");
        }

        return APIResult.ok(StreamUtil.convertBeanCopy(apiResult.getData(), clazz));
    }

    public static <T, R> APIResult<PageInfo<R>> okPageBeanCopy(APIResult<PageInfo<T>> apiResult, Class<R> clazz) {
        if (!apiResult.isSuccess()) {
            throw new IllegalStateException("状态非法");
        }

        return APIResult.ok(convertPageInfoBeanCopy(apiResult.getData(), clazz));
    }

    public static <T, R> PageInfo<R> convertPageInfo(PageInfo<T> sourcePageInfo, Function<T, R> convert) {
        PageInfo<R> resultPage = new PageInfo<>();
        resultPage.setCondition(sourcePageInfo.getCondition())
                .setAscs(sourcePageInfo.getAscs())
                .setCurrent(sourcePageInfo.getCurrent())
                .setDescs(sourcePageInfo.getDescs())
                .setSize(sourcePageInfo.getSize())
                .setTotal(sourcePageInfo.getTotal());

        List<R> resultDataList = StreamUtil.convert(sourcePageInfo.getRecords(), convert);

        return resultPage.setRecords(resultDataList);
    }

    public static <T, R> PageInfo<R> convertPageInfoBeanCopy(PageInfo<T> sourcePageInfo, Class<R> clazz) {
        return convertPageInfo(sourcePageInfo, t -> BeanCopierUtil.convert(t, clazz));
    }

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
        return APIResult.error(GlobalErrorCodeEnum.INVALID_PARAM.getCode(), message);
    }

    public static <T> APIResult<T> bizErrorResult(String message) {
        return APIResult.error(message);
    }

    public static <T> APIResult<T> systemErrorResult(String message) {
        return APIResult.error(GlobalErrorCodeEnum.FAILURE.getCode(), message);
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

    /**
     * 如果出现异常返回Null
     *
     * @param supplierMethod
     * @param <T>
     * @return
     */
    public static <T> T catchException(Supplier<T> supplierMethod) {
        return catchExceptionCore(supplierMethod, () -> null, e -> log.warn("抓取到异常,执行默认方法", e));
    }

}
