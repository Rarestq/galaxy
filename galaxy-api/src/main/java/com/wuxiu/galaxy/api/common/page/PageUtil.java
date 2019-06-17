package com.wuxiu.galaxy.api.common.page;

import com.wuxiu.galaxy.api.common.expection.BizException;

/**
 *
 * @author: wuxiu
 * @date: 2019/4/15 16:58
 */
public class PageUtil {
    /**
     * 分页本地线程变量
     */
    private static final ThreadLocal<Pagination> LOCAL_PAGE = new ThreadLocal<>();

    /**  
      * <p> 获取总条数 </p>
     * 
      * @return int
      */
    public static int getTotal() {
        if (isPageable()) {
            return LOCAL_PAGE.get().getTotal();
        } else {
            throw new BizException("获取数据异常");
        }
    }

    /**  
      * <p> 计算当前分页偏移量 </p>
     * 
      * @param current 当前页
      * @param size 每页显示数量
      * @return int
      */
    public static int offsetCurrent(int current, int size) {
        if (current > 0) {
            return (current - 1) * size;
        }
        return 0;
    }

    /**  
      * <p> Pagination 分页偏移量 </p>
     * 
      * @param pagination
      * @return int
      */
    public static int offsetCurrent(Pagination pagination) {
        if (null == pagination) {
            return 0;
        }
        return offsetCurrent(pagination.getCurrent(), pagination.getSize());
    }

    /**  
      * <p> 释放资源并获取总条数 </p>
     * 
      * @return int
      */
    public static int freeTotal() {
        int total = getTotal();
        // 释放资源
        remove();
        return total;
    }

    /**  
      * <p> 获取分页 </p>
     * 
      * @return
      */
    public static Pagination getPagination() {
        return LOCAL_PAGE.get();
    }

    /**  
      * <p> 设置分页 </p>
     * 
      * @param page
      */
    public static void setPagination(Pagination page) {
        LOCAL_PAGE.set(page);
    }

    /**  
      * <p> 启动分页 </p>
     * 
      * @param current 当前页
      * @param size 页大小
      */
    public static void startPage(int current, int size) {
        LOCAL_PAGE.set(new Pagination(current, size));
    }

    /**  
      * <p> 是否存在分页 </p>
     * 
      * @return boolean
      */
    public static boolean isPageable() {
        return LOCAL_PAGE.get() != null;
    }

    /**  
      * <p> 释放资源 </p>
      *
      */
    public static void remove() {
        LOCAL_PAGE.remove();
    }
}
