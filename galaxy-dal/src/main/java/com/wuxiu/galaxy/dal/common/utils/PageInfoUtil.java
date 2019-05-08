package com.wuxiu.galaxy.dal.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页工具
 *
 * @author: wuxiu
 * @date: 2019/4/13 11:27
 */
@NoArgsConstructor
public class PageInfoUtil {

    /**
     * 构建空页
     *
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> ofEmptyPage(PageInfo pageInfo) {
        PageInfo<T> resultPage = new PageInfo<>();
        resultPage.setCondition(pageInfo.getCondition())
                .setAscs(pageInfo.getAscs())
                .setCurrent(pageInfo.getCurrent())
                .setDescs(pageInfo.getDescs())
                .setSize(pageInfo.getSize())
                .setTotal(pageInfo.getTotal());

        return resultPage;
    }

    /**
     * 构建空页
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> ofEmptyPage(Page page) {
        PageInfo<T> resultPage = new PageInfo<>();
        resultPage.setCondition(page.getCondition())
                .setAscs(page.getAscs())
                .setCurrent(page.getCurrent())
                .setDescs(page.getDescs())
                .setSize(page.getSize())
                .setTotal((int) page.getTotal());

        return resultPage;
    }

    /**
     * 构建DAL层Page
     *
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> Page<T> convert(PageInfo pageInfo) {
        Page<T> resultPage = new Page<>();
        resultPage
                .setCondition(pageInfo.getCondition())
                .setAscs(pageInfo.getAscs())
                .setCurrent(pageInfo.getCurrent())
                .setDescs(pageInfo.getDescs())
                .setSize(pageInfo.getSize())
                .setTotal(pageInfo.getTotal());

        return resultPage;
    }

    /***
     * 判断是否为空页
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Page<T> page) {
        return page.getTotal() == 0L;
    }

    /**
     * 构建结果页
     *
     * @param page
     * @param record
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> of(Page page, List<T> record) {
        PageInfo<T> resultPageInfo = ofEmptyPage(page);
        return resultPageInfo.setRecords(record);
    }

    /**
     * 构建结果页
     *
     * @param pageInfo
     * @param record
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> of(PageInfo pageInfo, List<T> record) {
        PageInfo<T> resultPageInfo = ofEmptyPage(pageInfo);
        return resultPageInfo.setRecords(record);
    }

    /**
     * 拷贝分页参数
     *
     * @param source
     * @param target
     */
    public static void copy(PageInfo source, PageInfo target) {
        target.setCondition(source.getCondition())
                .setAscs(source.getAscs())
                .setCurrent(source.getCurrent())
                .setDescs(source.getDescs())
                .setSize(source.getSize())
                .setTotal(source.getTotal());
    }
}

