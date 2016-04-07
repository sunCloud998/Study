package com.sfy.io;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * @Description: GenerateSqlTest.java
 * @Date: 2016/03/25
 * @Author: sunfayun
 * @Version: 1.0
 */
public class GenerateSqlTest {

    @Test
    public void generateSqlTest(){
        try {
            InputStream is = GenerateSqlTest.class.getClassLoader().getResourceAsStream("mapping/sql.csv");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            List<String> sqlList = Lists.newArrayList();
            while ((line = br.readLine()) != null){
                String[] lineArr = line.split(",");
                sqlList.add("update b2c_supplier_info set customer_id="+lineArr[1]+" where contract_company_id="+lineArr[0]+" and business_source=1;");
            }

            br.close();isr.close();is.close();

            if(CollectionUtils.isEmpty(sqlList)){
                System.err.println("==>error");
                return;
            }

            File file = new File("update.sql");
            if(!file.exists()){
                boolean flag = file.createNewFile();
                if(flag){
                    System.err.println("===>文件创建成功");
                }
            }
            OutputStream os = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            for(int i=0;i<sqlList.size();i++){
                bw.write(sqlList.get(i));
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
