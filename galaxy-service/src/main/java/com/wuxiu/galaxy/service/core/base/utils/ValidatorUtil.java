package com.wuxiu.galaxy.service.core.base.utils;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数校验器工具类
 *
 * @author: wuxiu
 * @date: 2019/4/13 11:26
 */
@NoArgsConstructor
public final class ValidatorUtil {
    private static final ValidatorFactory VALIDATOR_FACTORY =
            Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();
    private static final String EMPTY_STRING = "";

    public static <T> Set<ConstraintViolation<T>> validate(T object) {
        return VALIDATOR.validate(object);
    }

    public static final int PHONE_LENGTH = 11;

    /**
     * 如果命中校验错误则返回任意一个错误信息
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String returnAnyMessageIfError(T object, Class... groups) {
        Set<ConstraintViolation<T>> constraintViolationSet =
                VALIDATOR.validate(object, groups);
        if (CollectionUtils.isEmpty(constraintViolationSet)) {
            return EMPTY_STRING;
        }

        //随机获取其中一个错误信息返回
        //noinspection LoopStatementThatDoesntLoop
        for (ConstraintViolation<T> constraintViolation : constraintViolationSet) {
            return constraintViolation.getMessage();
        }

        return EMPTY_STRING;
    }

    public static boolean validatePhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (!Objects.equals(phone.length(), PHONE_LENGTH)) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);

            return m.matches();
        }
    }
}
