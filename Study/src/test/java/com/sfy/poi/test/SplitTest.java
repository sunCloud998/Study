package com.sfy.poi.test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;

/**
 * @Description: SplitTest.java
 * @User: sunfayun
 * @Date: 2016/03/15
 * @Version: 1.0
 */
public class SplitTest {

    @Test
    public void splitStringTest(){
        String source = "a|b|c|d";
        List<String> targetList = Splitter.on("|").splitToList(source);
        if(CollectionUtils.isEmpty(targetList)){
            System.err.println("==>hehehe");
        }else {
            for(String str : targetList){
                System.err.println("===>"+str);
            }
        }
    }

    @Test
    public void joinStringTest(){
        List<String> sourceList = Lists.newArrayList();
        sourceList.add("a");
        sourceList.add("b");
        sourceList.add("c");
        sourceList.add("d");

        String target = Joiner.on("|").join(sourceList);
        System.err.println("==>"+target);
    }

}
