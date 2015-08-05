package com.sfy.java.poi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sfy.util.lang.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.DateTimeUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 读写Excel
 * @Description: ExcelReadAndWrite.java
 * @Author: sunfayun
 * @Date: 2015/07/03
 * @Time: 上午10:54
 * @Version 1.0
 */
public class ExcelReadAndWrite {

    private static String fileName = "创建工作簿.xls";

    public static void main(String[] args) throws Exception {
        ExcelReadAndWrite excelReadAndWrite = new ExcelReadAndWrite();
        //创建一个新的工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        //创建单元格
        HSSFSheet sheet = workbook.createSheet("第一个Sheet页");
        sheet.setColumnWidth((short)0,5*256);
        sheet.setColumnWidth((short)1,15*256);
        sheet.setColumnWidth((short)2,10*256);
        sheet.setColumnWidth((short)3,10*256);
        sheet.setColumnWidth((short)4,10*256);
        sheet.setColumnWidth((short)5,30*256);
        sheet.setColumnWidth((short)6,15*256);
        sheet.setColumnWidth((short)7,15*256);
        sheet.setColumnWidth((short)8,15*256);
        sheet.setColumnWidth((short)9,20*256);
        sheet.addMergedRegion(new Region(0,(short)0,0,(short)12));
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 1000);

        //设置表头
        sheet.addMergedRegion(new Region(1,(short)3,1,(short)4));

        row = sheet.createRow(1);
        row.createCell((short) 1).setCellValue(40);
        row.createCell((short) 3).setCellValue("UTOUR-BJ-W7-09072015-S-TG-TL0107");
        row.createCell((short) 6).setCellValue("2015");

        sheet.addMergedRegion(new Region(2,(short)3,2,(short)4));
        row = sheet.createRow(2);
        row.setHeight((short) 800);
        row.createCell((short)1).setCellValue("领队姓名");
        row.createCell((short)3).setCellValue("领队证号");

        row = sheet.createRow(3);
        List<String> list = excelReadAndWrite.buildData();
        for(int i=0;i<list.size();i++){
            row.createCell(i).setCellValue(list.get(i));
        }
        List<Map<String,String>> data = excelReadAndWrite.data();
        int x =0;
        for(int i = 0;i<data.size();i++){
            x = i+4;
            Map<String,String> map = data.get(i);
            row = sheet.createRow(x);
            row.createCell(0).setCellValue(map.get("id"));
            row.createCell(1).setCellValue(map.get("orderId"));
            row.createCell(2).setCellValue(map.get("name"));
            row.createCell(3).setCellValue(map.get("add"));
            row.createCell(4).setCellValue(map.get("source"));
        }

        sheet.addMergedRegion(new Region(x,(short)0,x,(short)2));
        sheet.addMergedRegion(new Region(x+1,(short)0,x+1,(short)12));
        row = sheet.createRow(x+1);
        row.setHeight((short)1000);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public List<Map<String,String>> data(){
        List<Map<String,String>> list = Lists.newArrayList();
        for(int i=0;i<10;i++){
            Map<String,String> map = Maps.newHashMap();
            map.put("id",i+"");
            map.put("orderId","0000000001");
            map.put("name","测试");
            map.put("add","北京");
            map.put("source","TTS");
            list.add(map);
        }
        return list;
    }

    public List<String> buildData(){
        List<String> list = Lists.newArrayList();
        list.add("序号");
        list.add("订单号");
        list.add("产品名称");
        list.add("出发地/到达地/返程地");
        list.add("订单来源");
        return list;
    }

}
