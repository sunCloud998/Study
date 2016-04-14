package com.sfy.util.regx;

import org.apache.commons.lang3.StringEscapeUtils;
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

    /**
     * 将字符串转换成html 语义的字符串
     * @param str
     * @return
     */
    public static String convert2HtmlSemantic(String str) {
        if (str == null) {
            return null;
        }
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("'", "&#39;");
        return str;
    }

    /**
     * 过滤html标签
     * @param input
     * @return
     */
    public static String escapeHtml(String input){
        Pattern html_tag_pattern = Pattern.compile("<(?!img)[^>]*>");
        Matcher matcher = html_tag_pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 转义html标签
     * @param input
     * @return
     */
    public static String convertHTML(String input) {
        Pattern coding_pattern = Pattern.compile("[\"'<>%&\\(\\);\\+-\\[\\]\\{\\}]");
        if (input == null) return "";
        StringBuffer sb = new StringBuffer();
        Matcher m = coding_pattern.matcher(input);
        while(m.find()) {
            m.appendReplacement(sb, StringEscapeUtils.escapeHtml4(m.group()));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
