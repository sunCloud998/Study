package com.sfy.java.activemq.chart.service;

import com.sfy.java.activemq.chart.domain.ChatRoom;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description: CommonUtil.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午5:12
 * @Version 1.0
 */
public class CommonUtil {
    /**
     * 布尔类型转为整数类型     null=null true=1  false=0
     * @author wwlg   @version 2014-10-16
     * @param flag
     * @return
     */
    public static Integer BooleanToInteger(Boolean flag){

        if (flag == null) {

            return null;

        }else if (flag) {

            return 1;

        }else {

            return 0;

        }

    }
    /**
     * 判断对象是否为空（该对象有一属性为空则整体为空）
     * @author wwlg   @version 2014-10-27
     * @param o
     * @return
     */
    public static boolean isNull(Object o){

        boolean flag = true;
        Field[] fields = o.getClass().getFields();
        for (Field field : fields) {

            String fieldName = field.getName();
            try {

                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + fieldName.substring(1);
                Method method = o.getClass().getMethod(getter, new Class[] {

                });
                Object value = method.invoke(o, new Object[] {

                });
                if (value != null || value != "") {

                    flag = false;

                }


            } catch (Exception e) {

                System.out.println("属性不存在");
                return true;

            }

        }
        return flag;

    }
    /**
     * model属性名转数据库字段名
     * 功能描述：
     * @author 杨帆
     * @editor
     * @date 2014-10-21 下午5:12:23
     * @return
     */
    public static String fieldToColumn(String property){

        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < property.length(); i++) {

            char c = property.charAt(i);
            if ('A' <= c && c <= 'Z') {

                bf.append("_").append((char)(c+32));

            }
            else {

                bf.append(c);

            }

        }
        return bf.toString();

    }
    /**
     * uuid 主键
     * @author wwlg   @version 2014-10-27
     * @return
     */
    public static String getUUId() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");

    }


    /**
     * 将父类所有的属性COPY到子类中。
     * 类定义中child一定要extends father；
     * 而且child和father一定为严格javabean写法，属性为deleteDate，方法为getDeleteDate
     */
    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    public static void fatherToChild (Object father,Object child){

        try {


            Class fatherClass= father.getClass();
            Class childClass= child.getClass();
            List<String> childFieldName = new ArrayList<String>();
            for (Field childField : childClass.getDeclaredFields()) {

                childFieldName.add(childField.getName());

            }
            for(Field field : fatherClass.getDeclaredFields()){

                String name=field.getName();
                if (!childFieldName.contains(name)) {

                    continue;

                }
                Method mf = fatherClass.getMethod("get"+upperHeadChar(name));
                Method mc = childClass.getMethod("set"+upperHeadChar(name),field.getType());
                //方法getDeleteDate
                Object obj=mf.invoke(father);//取出属性值
                mc.invoke(child, obj);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    /**
     * 首字母大写，in:deleteDate，out:DeleteDate
     */
    public static String upperHeadChar(String in){

        String head=in.substring(0,1);
        String out=head.toUpperCase()+in.substring(1,in.length());
        return out;

    }


    public final static String MD5(String s) {

        char hexDigits[]={
                '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
        };
        try {

            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {

                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];

            }
            return new String(str);

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    public static String getDate(String dateFormat){

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date());

    }
    public static String getDate(String dateFormat,Date date){

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date());

    }

    /**
     * 获取房间秘钥
     */
    public static String getChatRoomKey(ChatRoom chatRoom){

        return MD5(chatRoom.getId()+chatRoom.getChatRoomName());

    }
    /**
     * 获取房间秘钥
     */
    public static String getQueueKey(Integer userId){

        return MD5(userId+"dd");

    }
}
