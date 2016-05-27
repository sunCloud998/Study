package com.sfy.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.*;

/**
 * @Description: SerializableTest.java
 * @Date: 2016/05/23
 * @Author: sunfayun
 * @Version: 1.0
 */
public class SerializableTest {

    @Test
    public void serializableUserInfoTest(){
        try {
            UserInfo userInfo = new UserInfo("张三",22,"abc12345");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userInfo.out"));
            oos.writeObject(userInfo);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userInfo.out"));
            UserInfo ui = (UserInfo) ois.readObject();
            System.err.println("==>"+ui);
            //输出的password为null
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

@Data   @AllArgsConstructor
@NoArgsConstructor
class UserInfo implements Serializable{
    private String name;
    private Integer age;
    /**
     * transient关键字表示在序列化的时候被标记的内容不会被序列化
     */
    private transient String password;
}

