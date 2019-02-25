package com.sfy.java.io;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.sfy.util.json.JsonMapper;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @function:
 * @description: GuavaFiles.java
 * @date: 2018/02/10
 * @author: sunfayun
 * @version: 1.0
 */
public class GuavaFiles {

    public static void main(String[] args) throws IOException {
        GuavaFiles gf = new GuavaFiles();
        gf.test01();
        //System.err.println(BigDecimal.valueOf(23486631.49 + 366516.63 +0.02)+"");
    }

    public void test01() throws IOException {
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/data/2.1");
        List<String> list = Files.readLines(file, Charsets.UTF_8);
        List<List<DataInfo>> dataInfoList = Lists.newArrayList();
        for(String str : list) {
            Result result = JsonMapper.mapObject(str,Result.class);
            List<DataInfo> dataInfos = result.getData();
            dataInfoList.add(dataInfos);
        }
        List<DataInfo> result = dataInfoList.get(0);
        for(int i=1;i<dataInfoList.size();i++) {
            List<DataInfo> other = dataInfoList.get(i);
            for(DataInfo dataInfo : other) {
                for (DataInfo target : result) {
                    if(target.getDate().equalsIgnoreCase(dataInfo.getDate())) {
                        target.setTotalNum(String.valueOf(BigDecimal.valueOf(Integer.valueOf(target.getTotalNum().replaceAll(",",""))+Integer.valueOf(dataInfo.getTotalNum().replaceAll(",","")))));
                        target.setTotalSum(String.valueOf(BigDecimal.valueOf(Double.valueOf(target.getTotalSum().replaceAll(",",""))+ Double.valueOf(dataInfo.getTotalSum().replaceAll(",","")))));
                    }
                }
            }
        }

        for(DataInfo dataInfo : result) {
            dataInfo.setTotalSum(dataInfo.getTotalSum().replaceAll(",",""));
            dataInfo.setTotalNum(dataInfo.getTotalNum().replaceAll(",",""));
        }
        System.err.println(JsonMapper.mapString(result));
    }

    @Data
    public static class Result{
        private int code;
        private String msg;
        private List<DataInfo> data;
    }

    @Data
    public static class DataInfo{
        private String date;
        private String totalSum;
        private String totalNum;
    }

}
