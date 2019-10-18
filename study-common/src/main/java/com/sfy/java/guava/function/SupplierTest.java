package com.sfy.java.guava.function;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import lombok.Data;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @function:
 * @description: SupplierTest.java
 * @date: 2019/09/29
 * @author: sunfayun
 * @version: 1.0
 */
public class SupplierTest {

    @Data
    public static class User {
        private Integer id;
        private String name;
    }

    @Test
    public void singletonTest() {
        Supplier<User> supplier = Suppliers.memoize(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        });
        for (int i=0;i<10;i++) {
            User user = supplier.get();
            System.err.println(user.hashCode());
        }
    }

    @Test
    public void getUserTest() {
        for (int i=0;i<10;i++) {
            User user = getUser();
            System.err.println(user.hashCode());
        }
    }

    private User getUser() {
        return Suppliers.memoize(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        }).get();
    }

    @Test
    public void supplierTimeoutTest() {
        Supplier<User> supplier = Suppliers.memoizeWithExpiration(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        },2000, TimeUnit.MILLISECONDS);
        User user = supplier.get();
        System.err.println(user);
    }

}
