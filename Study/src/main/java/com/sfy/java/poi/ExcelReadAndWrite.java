package com.sfy.java.poi;

import com.google.common.collect.Lists;
import com.sfy.util.lang.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTimeUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
        Row row = sheet.createRow(0);
        List<String> list = excelReadAndWrite.buildData();
        for(int i=0;i<list.size();i++){
            row.createCell(i).setCellValue(list.get(i));
        }
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public List<String> buildData(){
        List<String> list = Lists.newArrayList();
        list.add("序号");
        list.add("订单号");
        list.add("产品名称");
        list.add("出发地/到达地/返程地");
        list.add("起飞时间");
        list.add("到达时间");
        list.add("入住时间");
        list.add("离店时间");
        list.add("店铺名称");
        list.add("成人");
        list.add("儿童");
        list.add("房间数");
        list.add("联系人姓名");
        list.add("联系人电话");
        list.add("下单时间");
        list.add("下单状态");
        list.add("订单金额");
        list.add("机票底价");
        list.add("机票卖价");
        list.add("酒店底价");
        list.add("酒店卖价");
        list.add("保险金额（单位元）");
        list.add("关闭原因");
        list.add("订单来源");
        return list;
    }

}
