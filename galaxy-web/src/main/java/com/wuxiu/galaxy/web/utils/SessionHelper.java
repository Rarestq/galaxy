package com.wuxiu.galaxy.web.utils;

import com.google.gson.Gson;
import com.wuxiu.galaxy.web.base.config.RedisExpire;
import com.wuxiu.galaxy.web.base.config.SessionConstants;
import com.wuxiu.galaxy.dal.domain.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * session 助手
 *
 * @author wuxiu
 */
@Slf4j
@Component
public class SessionHelper {

    @Autowired
    private RedisCacheTemplate redisManager;

    private Gson gson = new Gson();

    public Admin getUser() {
        String key = WebSessionUtil.getSessionId();
        return getUserBySessionId(key);
    }

    public Admin getUserBySessionId(String key) {
        // 先从本地session获取
        Admin user = (Admin) WebSessionUtil.getSession()
                .getAttribute(SessionConstants.OAUTH_SESSION_KEY + key);
        if (null != user && null != user.getAdminId()) {
            return user;
        }

        String jsonUser = "";
        try {
            jsonUser = redisManager.getString(SessionConstants.OAUTH_SESSION_KEY + key);
        } catch (Exception e) {
            log.error("Redis is unabled, Please check: Get user operationrecord.", e);
        }
        if (null != jsonUser) {
            user = gson.fromJson(jsonUser, Admin.class);
            WebSessionUtil.getSession()
                    .setAttribute(SessionConstants.OAUTH_SESSION_KEY + key, user);
            return user;
        }
        return null;
    }

    public void setUser(Admin user) {
        this.setUser(user, RedisExpire.SESSION_TIME_OUT);
    }

    public void setUser(Admin user, Long timeMillis) {
        String key = WebSessionUtil.getSessionId();
        setUser(user, timeMillis, key);
    }

    public void setUser(Admin user, Long timeMillis, String sessionId) {
        // 缓存到本地session
        log.info("util user to local session ...");
        WebSessionUtil.getSession().setAttribute(SessionConstants.OAUTH_SESSION_KEY + sessionId, user);
        String jsonUser = gson.toJson(user);
        try {
            redisManager.setString(SessionConstants.OAUTH_SESSION_KEY + sessionId, jsonUser, (int) (timeMillis / 1000));
        } catch (Exception e) {
            log.error("Redis is unable, Please check: Store user operationRecord", e);
        }
    }

    public void deleteUser() {
        log.info("delete user ...");
        WebSessionUtil.deleteSession();
    }
}
