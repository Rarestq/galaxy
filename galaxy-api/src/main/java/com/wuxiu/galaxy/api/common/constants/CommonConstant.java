package com.wuxiu.galaxy.api.common.constants;

import lombok.NoArgsConstructor;

/**
 * 公共常量
 *
 * @author: wuxiu
 * @date: 2019/4/27 21:05
 */
@NoArgsConstructor
public class CommonConstant {

    /**
     * 超级管理员编号前缀
     */
    public static final String SUPER_ADMIN_NO_PREFIX = "SADMIN";

    /**
     * 普通管理员编号前缀
     */
    public static final String ADMIN_NO_PREFIX = "ADMIN";

    /**
     * 系统
     */
    public static final String SYSTEM_PREFIX = "SYSTEM";

    /**
     * 行李寄存记录编号前缀
     */
    public static final String LUGGAGE_STORAGE_RECORD_NO_PREFIX = "XLJC";

    /**
     * 行李逾期记录编号前缀
     */
    public static final String OVERDUE_RECORD_NO_PREFIX = "XLYQ";

    /**
     * 遗失登记记录编号前缀
     */
    public static final String REGISTER_RECORD_NO_PREFIX = "YSDJ";

    /**
     * 取件记录编号前缀
     */
    public static final String PICKUP_RECORD_NO_PREFIX = "XLQJ";

    /**
     * 行李遗失赔偿编号前缀
     */
    public static final String LUGGAGE_LOST_COMPENSATE_NO_PREFIX = "YSPC";

    /**
     * 行李寄存柜编号前缀
     */
    public static final String LUGGAGE_CABINET_NO_PREFIX = "C";

    /**
     * 逗号分隔符
     */
    public static final String COMMA = ",";

    /**
     * 日期格式 yyyy-MM-dd HH:mm
     */
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String SECONDS_PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * 电话号码区号
     */
    public static final String PHONE_AREA_CODE = "+86";
}
