package com.wuxiu.galaxy.service.core.base.utils;

import lombok.NoArgsConstructor;

/**
 * 唯一 No 生成器 TODO: 分布式id ？？？
 *
 * @author: wuxiu
 * @date: 2019/4/14 21:02
 */
@NoArgsConstructor
public class UUIDGenerateUtil {

    /**
     * 任务实例编号前缀
     */
    private static final String TASK_INSTANCE_PREFIX = "RW";

    /**
     * 行为类型编号前缀
     */
    private static final String ACTION_TYPE_PREFIX = "XWLX";

    /**
     * 任务模板编号前缀
     */
    private static final String TASK_TEMPLATE_NO_PREFIX = "RWMB";

    /**
     * 任务类别编号前缀
     */
    private static final String TASK_CATEGORY_NO_PREFIX = "RWLB";

    /**
     * 任务异常编号前缀
     */
    private static final String TASK_EXCEPTION_NO_PREFIX = "RWYC";

    public static String genTaskInstanceNo(){
        return null;
    }

    public static String generateActionTypeNo() {
        return null;
    }

    public static String generateTaskTemplateNo() {
        return null;
    }

    public static String generateTaskCategoryCode() {
        return null;
    }

    public static String generateTaskExceptionNo() {
        return null;
    }
}
