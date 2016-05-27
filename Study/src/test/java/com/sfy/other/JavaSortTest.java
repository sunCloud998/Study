package com.sfy.other;

import org.junit.Test;

/**
 * 排序java实现：
 * 分类：内部排序（只使用内存），外部排序（内存+外部存储结合使用）
 * 内部排序分类：
 * 插入排序=>直接插入排序，希尔排序
 * 选择排序=>简单选择排序，堆排序
 * 交换排序=>冒泡排序，快速排序
 * 归并排序
 * 快速排序
 * @Description: JavaSortTest.java
 * @Date: 2016/05/20
 * @Author: sunfayun
 * @Version: 1.0
 */
public class JavaSortTest {

    /**
     * 直接插入排序
     * 基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
     * 好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
     * 也是排好顺序的。如此反复循环，直到全部排好顺序。
     */
    @Test
    public void insertSortTest(){
        int[] data = {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
        int temp;
        for(int i=1;i<data.length;i++){
            int j = i-1;
            temp = data[i];
            for(;j>=0 && temp<data[j];j--){
                data[j+1] = data[j];
            }
            data[j+1] = temp;
        }
        System.err.println("直接插入排序结果：");
        for(int value : data){
            System.err.print(value+" ");
        }
    }

    /**
     * 希尔排序
     * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
     * 然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
     */
    @Test
    public void shellSortTest(){
        //int[] data = {1,54,6,3,78,34,12,45,56,99,100};
        int[] data = {5,4,3,2,1,0};
        int length = data.length;
        int temp;
        while (true){
            length = (int) Math.ceil(length/2);//向上取整
            for(int x=0;x<length;x++){
                for(int i=x+length;i<data.length;i+=length){
                    int j = i-length;
                    temp = data[i];
                    for(;j>=0&&temp<data[j];j-=length){
                        data[j+length] = data[j];
                    }
                    data[j+length] = temp;
                }
            }
            if(length == 1){
                break;
            }
        }
        System.err.println("希尔排序结果：");
        for(Integer value : data){
            System.err.print(value + " ");
        }
    }

    /**
     * 选择排序
     * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
     */
    @Test
    public void selectSortTest(){
        int[] data = {1,54,6,3,78,34,12,45};
        int position;
        for(int i=0;i<data.length;i++){
            int j=i+1;
            position = i;
            int temp = data[i];
            for(;j<data.length;j++){
                if(data[j] < temp){
                    temp = data[j];
                    position = j;
                }
            }
            data[position] = data[i];
            data[i] = temp;
        }
        System.err.println("选择排序结果：");
        for(int value : data){
            System.err.print(value + " ");
        }
    }

    /**
     * 堆排序
     *
     */
    @Test
    public void heapSortTest(){

    }

}
