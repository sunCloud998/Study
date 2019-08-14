package com.sfy.other;

import java.sql.*;
import java.util.ArrayList;

public class TotalExcel {

    private String[] columnNames = null;

    private int columnNum = 14;

    private int colNum = 0;

    private ArrayList valueList = null;

    public TotalExcel() {
        columnNames = new String[columnNum];
        columnNames[0] = "deptName";
        //发文
        columnNames[1] = "sendRecieve";//发文应收
        columnNames[2] = "send100SignCount";//发文100%会签数量
        columnNames[3] = "send100SignScore";//发文100%会签得分
        columnNames[4] = "sendForceBackCount";//发文强制退回数量
        columnNames[5] = "sendForceBackRate";//发文强制退回占比
        columnNames[6] = "sendForceBackScore";//发文强制退回扣分
        //签报
        columnNames[7] = "singRecieve";//签报应收
        columnNames[8] = "sing100SignCount";//签报100%会签数量
        columnNames[9] = "sing100SignScore";//签报100%会签得分
        columnNames[10] = "singForceBackCount";//签报强制退回数量
        columnNames[11] = "singForceBackRate";//签报强制退回占比
        columnNames[12] = "singForceBackScore";//签报强制退回扣分
        //总分
        columnNames[13] = "totalScore";//总分
    }

    public int getColumnCount() {
        return columnNum;
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public int getRowCount() {
        init();
        return valueList.size();
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        init();
        if (columnIndex >= colNum) {
            return null;
        }
        return ((Object[]) valueList.get(rowIndex))[columnIndex];
    }

    public void init() {
        if (valueList != null) {
            return;
        }
        //发文SQL,sql上计算得分还有数量的不一定对，需要看看改下
        String sql = "select count(a.发文) sendReceive," + //发文应收数量
                "min(a.SIGN_DEPT_LEVEL_NAME)DEPT_NAME," +
                "a.TJ_CATE_ID,min(b.name) category_name,\n" +
                "sum(case when a.force_submit<> 0 then 1 else 0 end)sendForceBackNum," + //强制退回数量
                "sum(case when cxl.SIGN_TIME_ACTUAL_NUM<=3 then 1 else 0 end)sendOntimeNum,"+ //发文100%的数量
                "min(xxx)sendOntimeScore"+ //发文100%得分---这个可能不对，有可能不是这里计算的

                "count(a.签报) signReceive," + //签报应收数量
                "sum(case when a.force_submit<> 0 then 1 else 0 end)signforceBackNum," + //签报退回数量
                "sum(case when cxl.SIGN_TIME_ACTUAL_NUM<=3 then 1 else 0 end)signOntimeNum,"+ //签报100%的数量
                "min(xxx)signOntimeScore"+ //签报100%得分---这个可能不对，有可能不是这里计算的
                "FROM\n" +
                "CMBC_XSTJ_LCTJ a \n" +
                "left JOIN CMBC_XSTJ_PROC_CATEGORY b ON a.TJ_CATE_ID=b.ID\n" +
                "where a.TJ_CATE_ID in ('发文的ID')\n" +
                "group by a.SIGN_DEPT_LEVEL_ID,a.tj_cate_id";

        valueList = new ArrayList();
        Connection conn = this.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // FRContext.getLogger().info("Query SQL of TotalExcel: \n" + sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();

            Object[]objArray = null;
            while (rs.next()) {
                objArray = new Object[columnNum];

                double sendReceive = rs.getInt(1);//应收数量
                String deptName = rs.getString(2);//部门名称
                String cateId = rs.getString(3);//流程分类ID
                //String cateName = rs.getString(4);//流程分类名称
                double sendForceBackNum = rs.getDouble(5);//强制退回数量
                //double sendForceScore = rs.getDouble(6); //强制退回分数
                double sendOntimeNum = rs.getDouble(6);//发文100%数量
                double sendOntimeScore = rs.getDouble(7);//发文100%得分

                double signReceive = rs.getDouble(8);//签报应收数量
                double signForceBackNum = rs.getDouble(9); //签报强制退回数量
                //double signforceBackScore = rs.getDouble(10); //签报强制退回分数
                double signOntimeNum = rs.getDouble(10);//签报100%数量
                double signOntimeScore = rs.getDouble(11);//签报100%得分

                //这里对应上具体的值，眼睛都看瞎了，没对上，你自己对下
                objArray[0]=deptName;
                objArray[1]=sendReceive;
                objArray[2]=sendOntimeNum;
                objArray[3]=sendOntimeScore;
                objArray[4]=sendForceBackNum;//5,6是占比得分后面计算

                objArray[7]=signReceive;
                objArray[8]=signOntimeNum;
                objArray[9]=signOntimeScore;
                objArray[10]=signForceBackNum;//11,12是签报占比得分后面计算

                //////////////////////////////
                double sendForceScore =0; //强制退回分数
                double signForceBackScore = 0; //签报强制退回分数
                if(cateId.equals("xxx")) {
                    //发文占比
                    double sendRate=sendForceBackNum*100/sendReceive;
                    //计算得分
                    String scoreSql1="select min(score)score from CMBC_XSTJ_SCORE_RULE \n" +
                            "where  RULE_BEGIN<"+sendRate+" and RULE_END>="+sendRate+" \n" +
                            "and TJ_CATEGORY_ID='80206a4f-334f-4a75-8634-267eabf3d470' \n" +
                            "and CATEGORY_TYPE=1 \n" +
                            "and CATEGORY_KIND=2 \n" +
                            "group by id";
                    Statement st1 = conn.createStatement();
                    ResultSet rt1 = st1.executeQuery(scoreSql1);
                    //FRContext.getLogger().info("超时签收: \n" + scoreSql1);
                    while(rt1.next()){
                        sendForceScore=rt1.getInt(1);
                    }
                    objArray[5]=sendRate;//发文占比
                    objArray[6]=sendForceScore;//发文得分
                    rt1.close();
                    st1.close();
                } else if (cateId.equals("xxx")) {
                    //签报占比
                    double singRate=signForceBackNum*100/signReceive;
                    String scoreSql2="select min(score)score  from CMBC_XSTJ_SCORE_RULE \n" +
                            "where  RULE_BEGIN<"+singRate+" and RULE_END>="+singRate+" \n" +
                            "and TJ_CATEGORY_ID='80206a4f-334f-4a75-8634-267eabf3d470' \n" +
                            "and CATEGORY_TYPE=1 \n" +
                            "and CATEGORY_KIND=3 \n" +
                            "group by id";
                    Statement st2 = conn.createStatement();
                    ResultSet rt2 = st2.executeQuery(scoreSql2);
                    //FRContext.getLogger().info("超时未签收: \n" + scoreSql2);
                    while(rt2.next()){
                        signForceBackScore=rt2.getInt(1);
                    }
                    objArray[11]=singRate;
                    objArray[12]=signForceBackScore;
                    rt2.close();
                    st2.close();
                } else {
                    objArray[5]=0;//发文占比
                    objArray[6]=0;//发文得分
                    objArray[11]=0;
                    objArray[12]=0;
                }

                // 100%那个分数如果是计算的，后面还需要加

                //总分
                double totalScore=100-sendForceScore-signForceBackScore;
                objArray[13] = totalScore;

                valueList.add(objArray);

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        String driverName = "com.ibm.db2.jcc.DB2Driver";
        String url = "jdbc:db2://197.3.136.243:60000/ODDB";
        String username = "sapoaqdb";
        String password = "1Qaz@wsx";
        Connection con = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return con;
    }

    /**
     * 把两个数组的内容拼接到一起
     * 范围前面的列 + 范围的列
     * @param before
     * @param after
     * @return
     */
    private Object[] joinerArray(Object[] before, Object[] after) {
        if(null == before || null == after) {
            return null;
        }
        Object[] target = new Object[before.length + after.length];
        int columnNum = 0;
        for(Object obj : before) {
            target[columnNum] = obj;
            columnNum++;
        }
        for(Object obj : after) {
            target[columnNum] = obj;
            columnNum++;
        }
        return target;
    }

}
