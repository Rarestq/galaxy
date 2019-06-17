package com.wuxiu.galaxy.dal.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象拷贝工具
 *
 * @author: wuxiu
 * @date: 2019/4/13 11:30
 */
@Slf4j
public class BeanCopierUtil {

    /**
     * beanCopier缓存
     * (A拷贝到B,确定一个beanCopier)
     */
    private static Map<Class<?>, Map<Class<?>, BeanCopier>> beanCopierMap =
            new ConcurrentHashMap<>();

    /**
     * 拷贝方法
     *
     * @param sourceBean
     * @param targetBean
     * @param <S>
     * @param <T>
     */
    public static <S, T> void copy(S sourceBean, T targetBean) {
        @SuppressWarnings("unchecked")
        Class<S> sourceClass = (Class<S>) sourceBean.getClass();
        @SuppressWarnings("unchecked")
        Class<T> targetClass = (Class<T>) targetBean.getClass();

        BeanCopier beanCopier = getBeanCopier(sourceClass, targetClass);
        beanCopier.copy(sourceBean, targetBean, null);
    }

    /**
     * 拷贝自己N次
     *
     * @param sourceBean
     * @param amount
     * @param <S>
     * @return
     */
    public static <S> List<S> copySelf(S sourceBean, int amount) {
        List<S> resultList = Lists.newArrayListWithCapacity(amount);
        for (int i = 0; i < amount; i++) {
            resultList.add(copySelf(sourceBean));
        }

        return resultList;
    }

    /**
     * 拷贝自己
     *
     * @param sourceBean
     * @param <S>
     * @return
     */
    public static <S> S copySelf(S sourceBean) {
        Class<S> sourceClass = (Class<S>) sourceBean.getClass();
        return convert(sourceBean, sourceClass);
    }

    /**
     * 转换方法
     *
     * @param sourceBean  原对象
     * @param targetClass 目标类
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T convert(S sourceBean, Class<T> targetClass) {
        try {
            assert sourceBean != null;
            T targetBean = targetClass.newInstance();
            copy(sourceBean, targetBean);
            return targetBean;
        } catch (Exception e) {
            log.error("Transform bean error", e);
            throw new RuntimeException(e);
        }
    }

    private static <S, T> BeanCopier getBeanCopier(Class<S> sourceClass, Class<T> targetClass) {
        Map<Class<?>, BeanCopier> map = beanCopierMap.get(sourceClass);
        if (CollectionUtils.isEmpty(map)) {
            BeanCopier newBeanCopier =
                    BeanCopier.create(sourceClass, targetClass, false);
            Map<Class<?>, BeanCopier> newMap = new ConcurrentHashMap<>(64);
            newMap.put(targetClass, newBeanCopier);
            beanCopierMap.put(sourceClass, newMap);
            return newBeanCopier;
        }

        BeanCopier beanCopier = map.get(targetClass);
        if (beanCopier == null) {
            BeanCopier newBeanCopier =
                    BeanCopier.create(sourceClass, targetClass, false);
            map.put(targetClass, newBeanCopier);

            return newBeanCopier;
        }

        return beanCopier;
    }
}
