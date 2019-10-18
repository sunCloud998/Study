package com.sfy.java.thread.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @function:
 * @description: ThreadSaveInfoVo.java
 * @date: 2018/08/20
 * @author: sunfayun
 * @version: 1.0
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ThreadSaveInfoVo {

    private Integer id;
    private String name;

}
