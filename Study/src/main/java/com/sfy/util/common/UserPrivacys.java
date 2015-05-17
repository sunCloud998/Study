package com.sfy.util.common;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 判断邮箱是否正确以及对用户信息进行加密
 */
public class UserPrivacys {

    private static final int MAX_EMAIL_LENGTH = 50;

    /**
     * 加密电话号码
     * @param mobile
     * @return
     */
    public static String mobile(String mobile) {
        if (StringUtils.isEmpty(mobile) || mobile.length() < 11) {
            return mobile;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(mobile.substring(0, 3)).append("****").append(mobile.substring(7));
        return sb.toString();
    }


    /**
     * 加密邮箱
     * @param email
     * @return
     */
    public static String email(String email) {
        if (!isEmail(email)) {
            return email;
        }
        StringBuffer sb = new StringBuffer();
        if (email.indexOf("@") > 3) {
            sb.append(email.substring(0, 3));
            sb.append(StringUtils.repeat("*", email.indexOf("@") - 3));
            sb.append(email.substring(email.indexOf("@"), email.length()));
            return sb.toString();
        }
        return email;
    }

    /**
     * 判断是不是邮箱
     * @param email
     * @return
     */
    private static boolean isEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches() && email.length() < MAX_EMAIL_LENGTH;
    }
}
