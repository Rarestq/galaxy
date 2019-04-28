package com.wuxiu.galaxy.web.base.config;

import lombok.NoArgsConstructor;

/**
 * @author wenruo
 * @date 2018/4/27.
 */
@NoArgsConstructor
public class SessionConstants {

    /**
     * session_id常量
     */
    public static final String SESSION_ID = "GALAXYSESSIONID";

    /**
     * Session中存储用户的key
     */
    public static final String OAUTH_SESSION_KEY = "galaxy_sid";

    /**
     * Session失效时间，2d
     */
    public static final int SESSION_EXPIRE_SECOND = 2 * 24 * 3600;

}
