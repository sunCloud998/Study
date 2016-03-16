package com.sfy.other;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Service;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import sun.org.mozilla.javascript.internal.ErrorReporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: ArrayListTest.java
 * @Date: 2016/01/08
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ArrayListTest {

    @Test
    public void removeTest(){
        List<String> list = Lists.newArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        list.add("gg");
        list.add("hh");
        list.add("ii");
        list.add("jj");
        list.add("kk");
        list.add("ll");
        list.add("mm");
        list.add("nn");
        list.add("oo");
        list.add("pp");
        list.add("qq");
        list.add("rr");
        list.add("ss");
        list.add("tt");
        list.add("uu");
        list.add("vv");
        list.add("ww");

        System.err.println("原来集合大小："+list.size());
        System.err.println("原来集合内容："+list);
        int x = list.size() % 3 == 0 ? list.size()/3 : list.size()/3+1;
        for(int i=1;i<=x;i++){
            int t = 3*(i-1);
//            if(i==x){
//                t = list.size() - (3*(i-1));
//            }
            List<String> newList = this.deleteMapNode(list,t);
            System.err.println("=====>"+newList);
        }
        System.err.println("删除后集合的大小："+list.size());
        System.err.println("删除后集合的内容："+list);
    }

    private List<String> deleteMapNode(List<String> list, int index) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<String> tempList = new ArrayList<>();
        for (int i = index; i < list.size(); i++) {
            tempList.add(list.get(i));
        }
        return tempList;
    }

}
