package com.sfy.other;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.sfy.util.json.JsonMapper;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: sunfayun
 * @Date: 2015/05/14
 * @Time: 下午12:05
 * Description: StringSplitTest.java
 */
public class StringSplitTest {

    @Test
    public void splitStringTest(){
//        String temp = "idl=6361b8fa-0960-4838-8188-bd100cf6d464_1431575896028,idd=5ab9633e-aa12-4276-adaa-26ac8ee3b8bc_1431575900072";
        String temp = "idd=5ab9633e-aa12-4276-adaa-26ac8ee3b8bc_1431575900072";
//        String temp = "idl=6361b8fa-0960-4838-8188-bd100cf6d464_1431575896028";
//        String temp = null;
        List<String> list = Splitter.on(",").splitToList(temp);
        System.err.println("====>"+list.size());
        Map<String,String> map = Maps.newLinkedHashMap();
        if(CollectionUtils.isNotEmpty(list)){
            for(String string : list){
                List<String> singleList = Splitter.on("=").splitToList(string);
                map.put(singleList.get(0),singleList.get(1));
            }
        }
        System.err.println(map);
    }
//==>[[{"1":"a"}],[{"1":"b"}],[{"3":"c"}],[{"4":"d"}],[{"5":"e"}]]
    @Test
    public void collectionTest(){
        List<HashMap<String,String>> list = Lists.newArrayList();
        HashMap<String,String> map0 = Maps.newHashMap();
        map0.put("groupRoomCode","a");
        list.add(map0);

        HashMap<String,String> map1 = Maps.newHashMap();
        map1.put("groupRoomCode","");
        list.add(map1);

        HashMap<String,String> map2 = Maps.newHashMap();
        map2.put("groupRoomCode","");
        list.add(map2);

        HashMap<String,String> map3 = Maps.newHashMap();
        map3.put("groupRoomCode","a");
        list.add(map3);

        HashMap<String,String> map4 = Maps.newHashMap();
        map4.put("groupRoomCode","e");
        list.add(map4);

        this.separateData(list);
    }

    private List<List<HashMap<String,String>>> separateData(List<HashMap<String,String>> list){
        List<List<HashMap<String,String>>> mapList = new ArrayList<List<HashMap<String, String>>>();
        if(CollectionUtils.isEmpty(list)){
            return mapList;
        }
        Map<String,List<HashMap<String,String>>> stringListMap = new LinkedHashMap<String,List<HashMap<String,String>>>();
        for(int i=0;i<list.size();i++){
            HashMap<String,String> map = list.get(i);
            String groupRoomCodeTemp = map.get("groupRoomCode");
            if(StringUtils.isBlank(groupRoomCodeTemp)){
                List<HashMap<String,String>> hashMapList = new ArrayList<HashMap<String,String>>();
                hashMapList.add(map);
                stringListMap.put(UUID.randomUUID().toString(),hashMapList);
            }else{
                if(null == stringListMap.get(groupRoomCodeTemp)){
                    List<HashMap<String,String>> hashMapList = new ArrayList<HashMap<String,String>>();
                    hashMapList.add(map);
                    stringListMap.put(groupRoomCodeTemp,hashMapList);
                }else {
                    List<HashMap<String,String>> hashMapList = stringListMap.get(groupRoomCodeTemp);
                    hashMapList.add(map);
                    stringListMap.put(groupRoomCodeTemp,hashMapList);
                }
            }
        }
        if(MapUtils.isEmpty(stringListMap)){
            return mapList;
        }
        for(Map.Entry<String,List<HashMap<String,String>>> entry : stringListMap.entrySet()){
            mapList.add(entry.getValue());
        }
        Gson gson = new Gson();
        System.err.println("==>"+gson.toJson(mapList));
        return mapList;
    }

    @Test
    public void splitStringTest_01(){
        String str = "|||||||||||";
        String[] arr = str.split("\\|");
        System.err.println("===>"+arr);
    }

    @Test
    public void collectionSortTest(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Student> studentList = Lists.newArrayList();
            studentList.add(new Student("aa",sdf.parse("2012-12-11")));
            studentList.add(new Student("bb",sdf.parse("2013-11-11")));
            studentList.add(new Student("cc",sdf.parse("2011-01-11")));
            studentList.add(new Student("dd",sdf.parse("2012-02-11")));
            studentList.add(new Student("ee",sdf.parse("2002-12-11")));
            studentList.add(new Student("ff",sdf.parse("2001-12-11")));
            studentList.add(new Student("gg",sdf.parse("2000-12-11")));

            Collections.sort(studentList, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o2.getBirthday().compareTo(o1.getBirthday());
                }
            });

            for(Student student : studentList){
                System.err.println(student.getName()+":"+sdf.format(student.getBirthday()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Data
    public static class Student{
        private String name;
        private Date birthday;

        public Student(){}

        public Student(String name,Date birthday){
            this.name = name;
            this.birthday = birthday;
        }
    }

    @Test
    public void splitDomainTest(){
        String url = "http://qb2c_szyfl.dujia.qunar.com/pd/shophome_%E5%8C%97%E4%BA%AC?subWrapperName=%E6%90%BA%E7%A8%8B%E5%AD%90%E5%BA%97%E9%93%BA";
        url = url.replace("http://","");
        System.err.println(url);
        String domain = url.substring(0,url.indexOf("/"));
        System.err.println("domain:"+domain);
        String params = url.substring(url.indexOf("?")+1,url.length());
        String[] param = params.split("&");
        for(String str : param){
            if(str.contains("subWrapperName")){
                System.err.println("==>"+str);
                break;
            }
        }
    }

    @Test
    public void getNumberTest(){
        long num = 310;
        double temp = num/100;
        System.err.println("=>"+Math.ceil((double)num/100));
    }

    @Test
    public void getDateStringTest(){
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        System.err.println("==>"+date.toString());
    }

    @Test
    public void test01(){
        Map<String,String> map = Maps.newHashMap();
        map.put("gamePlayTitle","上海+杭州+黄山+乌镇");
        map.put("destinationInfo","26970.27055@中国.上海,26970.26972.27100@中国.浙江.杭州,26970.27137.27443@中国.安徽.黄山");
        map.put("showInfo","26970.27055@中国.上海");
        map.put("status","1");
        String jsonData = JsonMapper.mapString(map);
        GamePlayConfigParam param = JsonMapper.mapObject(jsonData,GamePlayConfigParam.class);
        System.err.println(JsonMapper.mapString(map));
    }

    @Data
    public static class GamePlayConfigParam {

        private String id;
        @NotBlank(message = "玩法名称不能为空")
        @Length(min = 1, max = 16, message = "玩法名称长度不合法")
        private String gamePlayTitle;
        @NotBlank(message = "包含目的地不能为空")
        private String destinationInfo;
        @NotBlank(message = "展示板块不能为空")
        private String showInfo;
        @NotNull(message = "状态不能为空")
        private Integer status = 1;//状态，默认：有效

    }

    @Test
    public void test02(){
        String test="东南亚=泰国$清迈@普吉岛,柬埔寨,印度尼西亚,越南,斯里兰卡,马来西亚,新加坡,尼泊尔,印度|日韩=日本|欧洲=德国,法国,意大利,瑞士,英国,爱尔兰,俄罗斯,西班牙,葡萄牙,匈牙利,捷克,丹麦,芬兰,土耳其,奥地利,冰岛,荷兰,挪威,卢森堡,爱沙尼亚,希腊,不丹,比利时,瑞典,波兰,斯洛伐克|美洲=美国,加拿大,墨西哥,哥斯达黎加,巴拿马,巴西,阿根廷,智利,古巴,乌拉圭|澳新=澳大利亚,新西兰|中东非=阿联酋,埃及,土耳其,肯尼亚,南非,坦桑尼亚,伊朗,突尼斯,摩洛哥,以色列,约旦|国内=云南$大理@丽江,海南,四川,福建,广西,北京,华东,山东,安徽,东北,山西,湖南,湖北,江西,西北,贵州,西藏,广东,重庆,新疆,内蒙古,河北,河南天津,上海,宁夏,辽宁,吉林,黑龙江,江苏,浙江,陕西,甘肃,青海|港澳台=香港,澳门,台湾|海岛=普吉岛,苏梅岛,巴厘岛,马尔代夫,塞班,沙巴,长滩,冲绳,济州,岘港,芽庄,毛里求斯,塞舌尔,夏威夷,关岛,大溪地,斐济,帕劳";
        List<Map<String,Map<String,List<String>>>> list = Lists.newArrayList();
//        List<String> test01 = Splitter.on("|").splitToList(test);
//        for(String str : test01){
//            List<String> list01 = Splitter.on("=").splitToList(str);
//            for(String str01 : list01){
//
//                List<String> list02 = Splitter.on("$").splitToList(str);
//
//            }
//        }


        Splitter.on("|").splitToList(test).forEach(stringList -> {
            List<String> list01 = Splitter.on("=").splitToList(stringList);
            Map<String,Map<String,List<String>>> map = Maps.newHashMap();
            Map<String,List<String>> listMap = Maps.newHashMap();
            Splitter.on(",").splitToList(list01.get(1)).forEach(str -> {
                if(str.contains("$")) {
                    List<String> list02 = Splitter.on("$").splitToList(str);
                    listMap.put(list02.get(0),Splitter.on("@").splitToList(list02.get(1)));
                }else {
                    listMap.put(str, ImmutableList.of());
                }
            });
            map.put(list01.get(0),listMap);
            list.add(map);
        });
        System.err.println("=>"+JsonMapper.mapString(list));
    }

}
