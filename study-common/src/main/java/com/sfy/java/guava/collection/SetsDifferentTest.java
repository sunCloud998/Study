package com.sfy.java.guava.collection;

import com.google.common.collect.Sets;
import com.sfy.util.json.JsonUtils;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;

import java.util.Set;

/**
 * @function: 集合的交集、并集、差集
 * @description: SetsDifferentTest.java
 * @date: 2019/09/27
 * @author: sunfayun
 * @version: 1.0
 */
public class SetsDifferentTest {

    @Test
    public void setsTest01() {
        Set<BillVo> vo1 = buildData1();
        Set<BillVo> vo2 = buildData2();
        Sets.SetView<BillVo> setView = Sets.difference(vo1,vo2);
        if(setView != null && !setView.isEmpty()) {
            for (BillVo billVo : setView) {
                System.err.println(billVo);
            }
        }
    }

    private Set<BillVo> buildData1() {
        Set<BillVo> set = Sets.newHashSet();
        BillVo vo1 = new BillVo();
        vo1.setTransaction_id("1");
        vo1.setOrigin_fee(100);
        vo1.setTotal_fee(100);
        vo1.setRefund_fee(0);
        set.add(vo1);

        BillVo vo2 = new BillVo();
        vo2.setTransaction_id("2");
        vo2.setOrigin_fee(200);
        vo2.setTotal_fee(200);
        vo2.setRefund_fee(0);
        set.add(vo2);

        BillVo vo3 = new BillVo();
        vo3.setTransaction_id("3");
        vo3.setOrigin_fee(300);
        vo3.setTotal_fee(200);
        vo3.setRefund_fee(100);
        set.add(vo3);

        BillVo vo4 = new BillVo();
        vo4.setTransaction_id("4");
        vo4.setOrigin_fee(400);
        vo4.setTotal_fee(200);
        vo4.setRefund_fee(200);
        set.add(vo4);
        return set;
    }

    private Set<BillVo> buildData2() {
        Set<BillVo> set = Sets.newHashSet();
        BillVo vo1 = new BillVo();
        vo1.setTransaction_id("1");
        vo1.setOrigin_fee(100);
        vo1.setTotal_fee(100);
        vo1.setRefund_fee(0);
        set.add(vo1);

        BillVo vo2 = new BillVo();
        vo2.setTransaction_id("1");
        vo2.setOrigin_fee(200);
        vo2.setTotal_fee(200);
        vo2.setRefund_fee(0);
        set.add(vo2);

        BillVo vo3 = new BillVo();
        vo3.setTransaction_id("1");
        vo3.setOrigin_fee(300);
        vo3.setTotal_fee(200);
        vo3.setRefund_fee(100);
        set.add(vo3);

//        BillVo vo4 = new BillVo();
//        vo4.setTransaction_id("1");
//        vo4.setOrigin_fee(400);
//        vo4.setTotal_fee(200);
//        vo4.setRefund_fee(200);
//        set.add(vo4);
        return set;
    }

    @Data
    public static class BillVo {
        private String transaction_id;
        private int total_fee;
        private int refund_fee;
        private int origin_fee;
    }

    @Test
    public void equalsBuilderTest() {
        BillVo vo1 = new BillVo();
        vo1.setTransaction_id("1");
        vo1.setOrigin_fee(100);
        vo1.setTotal_fee(100);
        vo1.setRefund_fee(0);

        BillVo vo2 = new BillVo();
        vo2.setTransaction_id("1");
        vo2.setOrigin_fee(100);
        vo2.setTotal_fee(100);
        vo2.setRefund_fee(0);

        boolean result = new EqualsBuilder().append(vo1.getTransaction_id(), vo2.getTransaction_id())
                .append(vo1.getOrigin_fee(), vo2.getOrigin_fee())
                .append(vo1.getTotal_fee(), vo2.getTotal_fee())
                .append(vo1.getRefund_fee(), vo2.getRefund_fee())
                .isEquals();
        System.err.println("=>"+result);
    }
}
