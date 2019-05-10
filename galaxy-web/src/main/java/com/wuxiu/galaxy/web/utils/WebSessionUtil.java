package com.wuxiu.galaxy.web.utils;

import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * session 工具类
 *
 * @author wuxiu
 */
@Slf4j
public class WebSessionUtil {

    private static final String GALAXY_SESSION_ID = "GALAXYSESSIONID";
    private static final String URL_SESSION_PARAM = "JSESSIONID";
    private static final String REQUEST_ATTR_SESSION = "JSESSIONID";

    public static HttpSession getSession() {
        return Objects.requireNonNull(NetWorkUtil.getRequest()).getSession();
    }

    public static String getSessionId() {
        Cookie[] cookies = Objects.requireNonNull(NetWorkUtil.getRequest()).getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (GALAXY_SESSION_ID.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        String sessionId = NetWorkUtil.getRequest().getParameter(URL_SESSION_PARAM);
        if (sessionId != null) {
            return sessionId;
        }

        sessionId = (String) NetWorkUtil.getRequest().getAttribute(REQUEST_ATTR_SESSION);
        if (sessionId != null) {
            return sessionId;
        }

        // todo:前台并没有将 sessionId 返回给服务器，所以这里是直接自己产生
        return generSession();
    }

    public static String generSession() {
        String sessionId = UUIDGenerator.getUUID();
        Cookie cookie = new Cookie(GALAXY_SESSION_ID, sessionId);
        cookie.setPath("/");
        Objects.requireNonNull(NetWorkUtil.getRequest())
                .setAttribute(REQUEST_ATTR_SESSION, sessionId);
        Objects.requireNonNull(NetWorkUtil.getResponse()).addCookie(cookie);

        return sessionId;
    }

    public static void deleteSession() {
        Cookie cookie = new Cookie(GALAXY_SESSION_ID, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        Objects.requireNonNull(NetWorkUtil.getResponse()).addCookie(cookie);
    }
}
