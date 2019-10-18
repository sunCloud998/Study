package com.sfy.other;

import com.sfy.obj.ListNodeTest;
import org.junit.Test;

import java.util.Stack;

/**
 * @Description: DataStructTest.java
 * @User: sunfayun
 * @Date: 2017/04/26
 * @Version: 1.0
 */
public class DataStructTest {

    /**
     * 求 1+2+...+n,要求不能用除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
     */
    @Test
    public void test01(){
        System.err.println("=>"+sum(100));
    }

    private int sum(int n){
        int temp = 0;
        boolean flag = (n>0) && (temp = sum(n-1)) > 0;
        return n+temp;
    }

    //=============================================//

    /**
     * 一个二维数组，每一行从左到右递增，每一列从上到下递增．输
     * 入一个二维数组和一个整数，判断数组中是否含有整数
     */
    @Test
    public void test02(){
        int[][] testarray=new int[4][4];
        testarray[0][0]=1;
        testarray[0][1]=2;
        testarray[0][2]=8;
        testarray[0][3]=9;
        testarray[1][0]=2;
        testarray[1][1]=4;
        testarray[1][2]=9;
        testarray[1][3]=12;
        testarray[2][0]=4;
        testarray[2][1]=7;
        testarray[2][2]=10;
        testarray[2][3]=13;
        testarray[3][0]=6;
        testarray[3][1]=8;
        testarray[3][2]=11;
        testarray[3][3]=15;

        int target = 13;

        int row =0;
        int col = testarray[row].length-1;
        while(row < testarray.length && col >=0){
            if(testarray[row][col] == target){
                System.err.println("row=>"+row + " col=>"+col);
                return;
            }else if(testarray[row][col] > target){
                col--;
            }else {
                row++;
            }
        }
    }

    //==========================================//

    /**
     * 请实现一个函数，把字符串中的每个空格替换成“%20”
     */
    @Test
    public void test03(){
        StringBuilder sb = new StringBuilder();
        String test = "hello world is a world";
        char[] chars = test.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(chars[i] == ' '){
                sb.append("%20");
            }else {
                sb.append(chars[i]);
            }
        }
        System.err.println("==>"+sb.toString());
    }


    //======================================//

    /**
     * 输入一个链表的头结点，从尾到头反过来打印出每个结点的值
     * 思路：如果节点不为空，循环把节点压栈，最后出栈即可
     */
    @Test
    public void test04(){
        ListNodeTest node1 = new ListNodeTest();
        ListNodeTest node2 = new ListNodeTest();
        ListNodeTest node3 = new ListNodeTest();
        ListNodeTest node4 = new ListNodeTest();

        node1.setData(1); node1.setNext(node2);
        node2.setData(2); node2.setNext(node3);
        node3.setData(3); node3.setNext(node4);
        node4.setData(4); node4.setNext(null);

        Stack<ListNodeTest> stack = new Stack<>();
        ListNodeTest node = node1;
        while (node != null){
            stack.push(node);
            node = node.getNext();
        }

        while (!stack.isEmpty()){
            System.err.println("=>"+stack.pop());
        }
    }

    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//


    //======================================//



}
