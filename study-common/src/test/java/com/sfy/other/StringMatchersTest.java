package com.sfy.other;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sfy.util.regx.UserPrivacys;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: StringMatchersTest.java
 * @Date: 2016/01/25
 * @Author: sunfayun
 * @Version: 1.0
 */
public class StringMatchersTest {

    @Test
    public void matcherTest(){
        String str = "[0-9a-zA-Z]{9,20}";
        String regx = "213993190213 ";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(regx);
        if(matcher.matches()){
            System.err.println("===>hahahahahaha");
        }
        boolean flag = str.matches("爆款");
        boolean flag1 = "爆款".matches(str);
        System.err.println("===>"+flag);
        System.err.println("===>"+flag1);

        String test = "测试测试|章节";
        String newString = test.substring(test.indexOf("|")+1,test.length());
        System.err.println("=====>"+newString);


    }

    @Test
    public void htmlReplaceTest(){
        String test = "<tr class=\"ZTableTr\">\n" +
                "                                        <td colspan=\"1\">游览行程</td>\n" +
                "                                        <td colspan=\"7\">\n" +
                "                                            <div class=\"userDiv\">\n" +
                "                                                <div>\n" +
                " <p><span></span></p>\n" +
                " <p><span>免费接站或接酒店,乘车赴”京津门户”之称--旅顺【30公里约40分钟】，沿途经过IT产业的基地—大连软件园，学子向往之地—旅顺大学区，沿途观赏小平岛、蓝湾2大高尚住宅区，到达旅顺后，游览日俄战争遗址</span><b><span>-</span></b><b><span>东鸡冠山</span></b><span>【</span><span><span>游览时间</span>30分钟】，外观世界五大军港之一</span><span>旅顺军港，</span><span><span>展示蛇类文化的</span>—</span><b><span>蛇博物馆</span></b><span>【</span><span><span>游览时间</span>30分钟】，抵达亚洲最大的海滨广场—</span><b><span>星海湾广场</span></b><span>【</span><span><span>游览时间</span>30分钟】，大连建市百年的标志性建筑--百年城雕，华表驻足观望，环游广场感受大连海滨的无限风光，乘车游览长达33公里，号称“东北最美海滨观光路”—滨海路，游览福娃之父韩美林先生早期作品—</span><b><span>虎雕广场</span></b><span>【</span><span><span>游览时间</span>20分钟】，</span><span>行程结束后，送回酒店休息。</span><span></span></p>\n" +
                " <p><span>购物店：</span><span><span>奇峰珍宝馆或宏兴购物【参观时间约</span>40分钟】自愿无强迫</span><span></span></p>\n" +
                " <p><span><span>推荐自费景点：白玉山景区</span>100元，</span><span><span>鳄鱼园表演</span>180元，印象旅顺320元，旅顺潜艇基地220元 （<span>自愿无强迫</span>\u200B）</span></p>\n" +
                " <p></p>\n" +
                " <p>\n" +
                "  <!--[if><span><span></span></span><!--[endif]--><span><span></span><span></span><span></span></span></p>\n" +
                "</div>\n" +
                "                                            </div>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                                                    <tr class=\"ZTableTr\">\n" +
                "                                        <td colspan=\"1\">天数</td>\n" +
                "                                        <td colspan=\"1\"><span>2</span>\n" +
                "                                        </td>\n" +
                "                                        <td colspan=\"1\">站点</td>\n" +
                "                                        <td colspan=\"1\"><span>1</span>\n" +
                "                                        </td>";
        String reg = "<!--[if><span><span></span></span><!--[endif]-->";
        String target = test.replace(reg,"");
        System.err.println("===>"+target);
    }

    @Test
    public void convertStringTest(){
        //String test = "\\n6月8日的收满了 ";
        String test = "[{\"chat_detail\":[{\"sender\":\"商家\",\"content\":\"亲，您好\"},{\"sender\":\"用户\",\"content\":\"我想问这个的话从佛山到深圳是你们有车一起去还是要自己去的？\"},{\"sender\":\"用户\",\"content\":\"还有签证方面是如何操作呢\"},{\"sender\":\"商家\",\"content\":\"亲，是火车呢\"},{\"sender\":\"用户\",\"content\":\"佛山的话是哪里集中\"},{\"sender\":\"商家\",\"content\":\"签证我们给您办理的\"},{\"sender\":\"商家\",\"content\":\"亲，我们给您包含往返火车票，如果您自行前往，我们会给您减去费用\"},{\"sender\":\"用户\",\"content\":\"是把护照寄给你？\"},{\"sender\":\"商家\",\"content\":\"不用，沙巴是电子签\"},{\"sender\":\"商家\",\"content\":\"扫描就好了\"},{\"sender\":\"用户\",\"content\":\"具体自费的项目有哪些呢？\"},{\"sender\":\"用户\",\"content\":\"如果购买的话付款后还要怎么操作\"},{\"sender\":\"商家\",\"content\":\"亲，自费到了当地导游给您介绍\"},{\"sender\":\"商家\",\"content\":\"您要哪天去，我给您落实一下，然后您在去哪儿网下单付款\"},{\"sender\":\"商家\",\"content\":\"我们给您安排出行\"},{\"sender\":\"用户\",\"content\":\"想提前了解一下自费项目再下单喔\"},{\"sender\":\"用户\",\"content\":\"就是付款后怎么能保障我们的权利呢？\"},{\"sender\":\"商家\",\"content\":\"亲，去哪儿网就是保障，我们可以给您签合同，开发票\"},{\"sender\":\"商家\",\"content\":\"有水上运动:降落伞、海底漫步、摩托艇等，水上运动项目客人可自行选择购买参加\"},{\"sender\":\"商家\",\"content\":\"购物点就这两个\"},{\"sender\":\"用户\",\"content\":\"是在深圳哪里集合呢？\"},{\"sender\":\"商家\",\"content\":\"亲，您好\"},{\"sender\":\"用户\",\"content\":\"火车票是和谐号吗\"},{\"sender\":\"商家\",\"content\":\"恩是的\"},{\"sender\":\"用户\",\"content\":\"如果自己去集合的会回给多少钱补贴呢？\"},{\"sender\":\"用户\",\"content\":\"是在深圳哪里集合呢？\"},{\"sender\":\"商家\",\"content\":\"亲，往返可以给您减50元/人\"},{\"sender\":\"商家\",\"content\":\"您好\"},{\"sender\":\"用户\",\"content\":\"请问是去深圳哪里集中\"},{\"sender\":\"用户\",\"content\":\"佛山出发的话我们自己要怎么去\"},{\"sender\":\"商家\",\"content\":\"一般是深圳湾或者蛇口\"},{\"sender\":\"商家\",\"content\":\"汽车和火车都有直达深圳的呀\"},{\"sender\":\"商家\",\"content\":\"很方便\"},{\"sender\":\"用户\",\"content\":\"那就是你给我们火车票～然后我们还要自己去深圳湾或蛇口这样吗3\"},{\"sender\":\"用户\",\"content\":\"请问现在出了最后行程了吗\"},{\"sender\":\"商家\",\"content\":\"不是，您自己去到车站订就好了，很方便\"},{\"sender\":\"商家\",\"content\":\"行程就是我们去哪网上面的具体行程呀\"},{\"sender\":\"商家\",\"content\":\"您点参考行程就是啦\"},{\"sender\":\"用户\",\"content\":\"那就是你们只给补贴？\"},{\"sender\":\"商家\",\"content\":\"是的\"},{\"sender\":\"用户\",\"content\":\"签证只需扫描给你吗？\"},{\"sender\":\"商家\",\"content\":\"这样会好一点，如果我们给您订，您往返的时间久没办法控制\"},{\"sender\":\"用户\",\"content\":\"大概多少人一个团\"},{\"sender\":\"商家\",\"content\":\"1.有效护照原件（须有半年以上有效期及除备注页不少于2页正反空白面)。\\\\n2.半年内的近期2寸白底彩色照片2张(白底)。\"},{\"sender\":\"商家\",\"content\":\"需要原件寄给我们呢\"},{\"sender\":\"商家\",\"content\":\"30人左右\"},{\"sender\":\"用户\",\"content\":\"怎么昨天问又说扫描就可以了\"},{\"sender\":\"商家\",\"content\":\"不好意思哈\"},{\"sender\":\"商家\",\"content\":\"以这个为准哈\"},{\"sender\":\"商家\",\"content\":\"是要寄的\"},{\"sender\":\"用户\",\"content\":\"酒店现在可以知道是哪个了吗\"},{\"sender\":\"商家\",\"content\":\"参考酒店:克拉甘酒店/豪丽胜酒店\"},{\"sender\":\"用户\",\"content\":\"现在报6.8号的团还来得及吗？\"},{\"sender\":\"用户\",\"content\":\"签证大概要提前多久\"},{\"sender\":\"商家\",\"content\":\"具体要以出团当天安排的为准，但都是同级别的\"},{\"sender\":\"商家\",\"content\":\"要给您落实一下哦，您几位\"},{\"sender\":\"用户\",\"content\":\"2位\"},{\"sender\":\"商家\",\"content\":\"好的，请稍等\"},{\"sender\":\"商家\",\"content\":\"\\\\n6月8日的收满了\u2028\"},{\"sender\":\"商家\",\"content\":\"22号可以\"},{\"sender\":\"用户\",\"content\":\"22号也变3030了？\"},{\"sender\":\"商家\",\"content\":\"是的，而且还要清位置\"},{\"sender\":\"用户\",\"content\":\"你们还有其他沙巴的团吗\"},{\"sender\":\"商家\",\"content\":\"6月初的都没位置了\"},{\"sender\":\"用户\",\"content\":\"6.8的会有人退的吗\"},{\"sender\":\"用户\",\"content\":\"还能增加两个位吗\"},{\"sender\":\"商家\",\"content\":\"亲，加不了了，您是一定要8号，还是说可以左右呢\"},{\"sender\":\"用户\",\"content\":\"左右都可以\"},{\"sender\":\"用户\",\"content\":\"最好8号！\"},{\"sender\":\"用户\",\"content\":\"十几号\"},{\"sender\":\"用户\",\"content\":\"还有其他团吗\"},{\"sender\":\"商家\",\"content\":\"6.10号可以吗\"},{\"sender\":\"用户\",\"content\":\"可以啊\"},{\"sender\":\"用户\",\"content\":\"团的内容也一样吗？\"},{\"sender\":\"商家\",\"content\":\"不一样\"},{\"sender\":\"商家\",\"content\":\"我发您文档您看一下\"},{\"sender\":\"用户\",\"content\":\"好的\"},{\"sender\":\"商家\",\"content\":\"请稍等\"},{\"sender\":\"商家\",\"content\":\"{\\\\n    \\\"FILEID\\\": \\\"4b4abe0641de440ea0c47f5bdeff5a1a\\\",\\\\n    \\\"FILEMD5\\\": \\\"9c2b1db00dec8f46c4f9910949ab25e6\\\",\\\\n    \\\"FileName\\\": \\\"玩转沙巴-香港亚航.doc\\\",\\\\n    \\\"FileSize\\\": \\\"3.59MB\\\",\\\\n    \\\"HttpUrl\\\": \\\"/file/v2/download/temp/9c2b1db00dec8f46c4f9910949ab25e6?name=82f05269e9014b208e91c61458809a56.doc&file=file/9c2b1db00dec8f46c4f9910949ab25e6.doc&fileName=file/9c2b1db00dec8f46c4f9910949ab25e6.doc\\\"\\\\n}\\\\n\"},{\"sender\":\"商家\",\"content\":\"这个比您看到的那个要贵将近1000元，具体价格要明天才能落实\"},{\"sender\":\"用户\",\"content\":\"6·8的团还有？\"},{\"sender\":\"用户\",\"content\":\"怎么我看到还能拍\"},{\"sender\":\"商家\",\"content\":\"没有了哦\"},{\"sender\":\"商家\",\"content\":\"没有了\"}],\"wrappername\":\"康辉旅行社\",\"chat_time\":\"2016-05-24 13:13:13\",\"uniform_location\":\"马来西亚\",\"user\":\"bxyampg6501\",\"servicer\":\"qhmb3720\",\"product_id\":\"9590259\"}]";
        String formatTestString = UserPrivacys.convertHTML(test);
        System.err.println("====>"+formatTestString);
    }

    @Test
    public void splitStringTest(){
        String note = "客人:(aa,bb,cc) 的押金金额更新为";
        String names = note.substring(note.indexOf("(")+1,note.lastIndexOf(")"));
        System.err.println("==>"+names);
    }

    @Test
    public void sortTest(){

        List<List<HashMap<String,String>>> listList = Lists.newArrayList();
        HashMap<String,String> map = Maps.newHashMap();
        map.put("AAAA","BBBB");

        List<HashMap<String,String>> list3 = Lists.newArrayList();
        list3.add(map);
        list3.add(map);
        listList.add(list3);

        List<HashMap<String,String>> list4 = Lists.newArrayList();
        list4.add(map);
        listList.add(list4);

        List<HashMap<String,String>> list = Lists.newArrayList();
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        listList.add(list);

        List<HashMap<String,String>> list1 = Lists.newArrayList();
        list1.add(map);
        list1.add(map);
        list1.add(map);
        listList.add(list1);

        List<HashMap<String,String>> list2 = Lists.newArrayList();
        list2.add(map);
        list2.add(map);
        list2.add(map);
        list2.add(map);
        list2.add(map);
        listList.add(list2);

        this.sortA(listList);

        System.err.println("==>"+listList);

    }

    public void sortA(List<List<HashMap<String,String>>> listList){
        for(int i=1;i<listList.size();i++){
            for(int j=i;j>0;j--){
                if(listList.get(j).size() > listList.get(j-1).size()){
                    List<HashMap<String,String>> list = listList.get(j);
                    listList.set(j,listList.get(j-1));
                    listList.set(j-1,list);
                }
            }
        }
    }

    public void sort(int data[]) {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    int temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                }
            }
        }
    }

}
