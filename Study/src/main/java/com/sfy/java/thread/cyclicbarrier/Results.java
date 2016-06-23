package com.sfy.java.thread.cyclicbarrier;

/**
 * @Function:
 * @Description: Results.java
 * @Date: 2016/06/23
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Results {

    private int data[];

    public Results(int size){
        data = new int[size];
    }

    public void setData(int position,int value){
        data[position] = value;
    }

    public int[] getData(){
        return data;
    }

}
