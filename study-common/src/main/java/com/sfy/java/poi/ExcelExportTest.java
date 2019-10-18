package com.sfy.java.poi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sfy.util.json.JsonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Description: ExcelExportTest.java
 * @Author: sunfayun
 * @Date: 2015/11/23
 * @Time: 下午3:21
 * @Version 1.0
 */
public class ExcelExportTest {

    private static String fileName = "创建工作簿.xls";

    public static void main(String[] args) throws IOException {
        //创建一个新的工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        //创建单元格
        HSSFSheet sheet = workbook.createSheet("第一个Sheet页");
        List<List<HashMap<String,String>>> lists = separateData(buildData());

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("测试测试测试测试");

        int index =1;
        for(int i=0;i<lists.size();i++){
            int temp = 0;
            List<HashMap<String,String>> hashMapList = lists.get(i);
            //合并
            sheet.addMergedRegion(new Region(index,(short)0,index+hashMapList.size()-1,(short)0));

            for(int j=0;j<hashMapList.size();j++){
                Map<String,String> map = hashMapList.get(j);
                temp++;
                row = sheet.createRow(index);
                if(temp == 1){
                    row.createCell(0).setCellValue(i);
                }
                row.createCell(1).setCellValue(map.get("groupRoomCode"));
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, new CellRangeAddress(index, index + hashMapList.size() - 1, 0, 0), sheet, workbook);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN,new CellRangeAddress(index, index+hashMapList.size()-1, 0, 0), sheet, workbook);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN,new CellRangeAddress(index, index+hashMapList.size()-1, 0, 0), sheet, workbook);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN,new CellRangeAddress(index, index+hashMapList.size()-1, 0, 0), sheet, workbook);
//                HSSFCellStyle titleCellStyle = workbook.createCellStyle();
//                titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
//                titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//                titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//                titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//                row.setRowStyle(titleCellStyle);
                index++;
            }

        }

        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    private static List<List<HashMap<String,String>>> separateData(List<HashMap<String,String>> list){
        List<List<HashMap<String,String>>> mapList = new ArrayList<List<HashMap<String, String>>>();
        if(CollectionUtils.isEmpty(list)){
            return mapList;
        }
        Map<String,List<HashMap<String,String>>> stringListMap = new LinkedHashMap<>();
        for(int i=0;i<list.size();i++){
            HashMap<String,String> map = list.get(i);
            String groupRoomCodeTemp = map.get("groupRoomCode");
            if(null == stringListMap.get(groupRoomCodeTemp)){
                List<HashMap<String,String>> hashMapList = new ArrayList<>();
                hashMapList.add(map);
                stringListMap.put(groupRoomCodeTemp,hashMapList);
            }else {
                List<HashMap<String,String>> hashMapList = stringListMap.get(groupRoomCodeTemp);
                hashMapList.add(map);
                stringListMap.put(groupRoomCodeTemp,hashMapList);
            }
        }
        if(MapUtils.isEmpty(stringListMap)){
            return mapList;
        }
        for(Map.Entry<String,List<HashMap<String,String>>> entry : stringListMap.entrySet()){
            mapList.add(entry.getValue());
        }
        System.err.println("==>"+ JsonUtils.toJson(mapList));
        return mapList;
    }

    public static List<HashMap<String,String>> buildData(){
        List<HashMap<String,String>> list = Lists.newArrayList();
        HashMap<String,String> map = Maps.newHashMap();
        map.put("groupRoomCode", "other/other/123");
        HashMap<String,String> map1 = Maps.newHashMap();
        map1.put("groupRoomCode", "other/other/123");
        HashMap<String,String> map2 = Maps.newHashMap();
        map2.put("groupRoomCode", "other/other/123");

        HashMap<String,String> map3 = Maps.newHashMap();
        map3.put("groupRoomCode","45");
        HashMap<String,String> map4 = Maps.newHashMap();
        map4.put("groupRoomCode","45");
        HashMap<String,String> map5 = Maps.newHashMap();
        map5.put("groupRoomCode","6");

        HashMap<String,String> map6 = Maps.newHashMap();
        map6.put("groupRoomCode","123444");
        HashMap<String,String> map7 = Maps.newHashMap();
        map7.put("groupRoomCode","123444");
        HashMap<String,String> map8 = Maps.newHashMap();
        map8.put("groupRoomCode","12344466");

        HashMap<String,String> map9 = Maps.newHashMap();
        map9.put("groupRoomCode","4555");
        HashMap<String,String> map10 = Maps.newHashMap();
        map10.put("groupRoomCode","4555");
        HashMap<String,String> map11 = Maps.newHashMap();
        map11.put("groupRoomCode","689988");

        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
        list.add(map11);
        return list;
    }

}
