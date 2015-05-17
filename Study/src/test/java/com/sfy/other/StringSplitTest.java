package com.sfy.other;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author: sunfayun
 * @Date: 2015/05/14
 * @Time: 下午12:05
 * Description: StringSplitTest.java
 */
public class StringSplitTest {

    @Test
    public void splitStringTest(){
//        String temp = "idl=6361b8fa-0960-4838-8188-bd100cf6d464_1431575896028,idd=5ab9633e-aa12-4276-adaa-26ac8ee3b8bc_1431575900072";
        String temp = "idd=5ab9633e-aa12-4276-adaa-26ac8ee3b8bc_1431575900072";
//        String temp = "idl=6361b8fa-0960-4838-8188-bd100cf6d464_1431575896028";
//        String temp = null;
        List<String> list = Splitter.on(",").splitToList(temp);
        System.err.println("====>"+list.size());
        Map<String,String> map = Maps.newLinkedHashMap();
        if(CollectionUtils.isNotEmpty(list)){
            for(String string : list){
                List<String> singleList = Splitter.on("=").splitToList(string);
                map.put(singleList.get(0),singleList.get(1));
            }
        }
        System.err.println(map);
    }
}
