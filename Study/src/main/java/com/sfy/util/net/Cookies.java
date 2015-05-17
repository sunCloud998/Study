package com.sfy.util.net;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 封装对cookie的操作
 */
public class Cookies {

    /**
     * 获取指定的Cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie cookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if (StringUtils.isEmpty(name) || ArrayUtils.isEmpty(cookies)) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * 解析字符串类型的cookie
     *
     * @param cookieStr
     * @return
     */
    public static List<Cookie> resolveCookies(String cookieStr) {
        if (StringUtils.isEmpty(cookieStr)) {
            return Lists.newArrayList();
        }
        Splitter splitter = Splitter.on(';').trimResults().omitEmptyStrings();
        List<String> cooStrs = splitter.splitToList(cookieStr);
        List<Cookie> cookies = Lists.newArrayList();
        for (String cooStr : cooStrs) {
            String temp = cooStr.trim();
            int len = temp.length();
            for (int i = 0; i < len; i++) {
                if (temp.charAt(i) == '=') {
                    try {
                        String name = temp.substring(0, i);
                        String value = temp.substring(i + 1, temp.length());
                        cookies.add(new Cookie(name, value));
                    } finally {
                        break;
                    }
                }
            }
        }
        return cookies;
    }
}
