package com.sfy.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sfy.util.json.JsonMapper;
import com.sfy.util.time.DateTimeUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: ArrayListTest.java
 * @Date: 2016/01/08
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ArrayListTest {

    @Test
    public void removeTest(){
        List<String> list = Lists.newArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        list.add("gg");
        list.add("hh");
        list.add("ii");
        list.add("jj");
        list.add("kk");
        list.add("ll");
        list.add("mm");
        list.add("nn");
        list.add("oo");
        list.add("pp");
        list.add("qq");
        list.add("rr");
        list.add("ss");
        list.add("tt");
        list.add("uu");
        list.add("vv");
        list.add("ww");

        System.err.println("原来集合大小："+list.size());
        System.err.println("原来集合内容："+list);
        int x = list.size() % 3 == 0 ? list.size()/3 : list.size()/3+1;
        for(int i=1;i<=x;i++){
            int t = 3*(i-1);
//            if(i==x){
//                t = list.size() - (3*(i-1));
//            }
            List<String> newList = this.deleteMapNode(list,t);
            System.err.println("=====>"+newList);
        }
        System.err.println("删除后集合的大小："+list.size());
        System.err.println("删除后集合的内容："+list);
    }

    private List<String> deleteMapNode(List<String> list, int index) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<String> tempList = new ArrayList<>();
        for (int i = index; i < list.size(); i++) {
            tempList.add(list.get(i));
        }
        return tempList;
    }

    @Test
    public void insertSqlTest(){
        String mobile="13888906104,15810577521,15876540448,13825059818,13810168143,15989274383,";
        mobile +="18666111852,18688860092,15805811265,18011464959,13340831913,15754971023,17721080366,13819139671,18502271683,18317133176,15755185148,13603721383,15813330057,18053236682,13784977852,13916145725,18846077661,18208431137,18208431137,13802430391,15802313801,18618417329,18645015599,13911686491,18670385072,13761226604,13534782038,15624952966,18740460507,15816076454,18622960855,13818774569,13125538732,13801969563,13808854913,13801969563,13801969563,15889738543,15352602256,13037885881,18910433883,15600000084,18825302690,18160030001,13718881828,18810600240,13543439220,13564551253,18628360916,13637904809,13512771067,15166038980,13438997828,13801969563,13751066766,15166038980,18110917744,18608241007,13790809796,13757983444,15711207069,13808121602,13808121602,13808121602,13846510999,13881926799,18677039612,18689727297,13306019293,13662325002,13662325002,15870028991,18113945169,15828529547,18825025243,18990563337,13735368714,18009319061,13534593443,13202829668,15665000756,13916165520,18201014087,15810656022,15988891850,18709711690,15615553125,13610334620,18245477499,13564720145,13925457940,13158506063,13507993856,13830293256,15308061970,15549863000,18167122593,15086856008,15606992979,15108412312,13637515331,15540889288,13876081519,18823423989";
        List<String> list = Splitter.on(",").splitToList(mobile);
        String content="感谢参与“有红牛自在游”去哪儿519疯游节!送您红牛官方旗舰店10元优惠券,数量有限快抢http://qnr.io/vdhtkp";
        for(String str : list){
            String sql = "insert into b2c_sms_queue (number, content, account, group_id) values ('"+str+"','"+content+"','qunar_vacation','vacation');";
            System.err.println(sql);
        }
    }

    @Test
    public void methodTest(){
        int count = 199/200;
        System.err.println("count="+count);
    }

    @Test
    public void diffStringTest(){
        String dbString="100432361,100432364,100439401,100440010,100444928,100447989,100492886,100498532,100565515,100583827,100604736,100647906,1006484,100653712,100656208,100664840,100669284,1127341,1132383,1141178,1143590,1149971,1163915,1164370,1166616,1195296,1203220,1215386,1229043,1235101,1241549,1242627,1243088,1246131,1246975,1247844,1248547,1248595,1251774,1257222,1357433,1358422,1358427,1364187,1369163,1371753,1374079,1376171,1379690,1381551,1382019,1382517,1384836,1386217,1388444,1388909,1390910,1393842,1396862,1401275,1401538,1401651,1407468,986867,986877,987009,987378,987400,987404,987512,987588,987811,987882,988172,988225,988228,988316,988346,988402,988463,988497,988585,988596,988654,988674,988689,988766,988803,988840,988871,988921,989055,989100,989122,989176,989183,989196,989361,989434,989445,989505,989529,989595,989597,989631,989673,989677,989738,989761,989766,989779,989782,989827,989855,989863,989888,989890,989896,989915,989947,989976,990051,990084,990136,990153,990271,990292,990341,990401,990461,990523,990533,990537,990539,990555,990669,990680,990744,990900,990957,990962,991113,991116,991153,991162,991180,991227,991228,991317,991377,991454,991466,991519,991696,991703,992077,992130,992351,992642,992652,992713,992764,992927,993923,995077";
        List<String> dbStringList = Splitter.on(",").splitToList(dbString);
        String sourceString = "1247844,1401651,100647906,1141178,992713,1229043,992764,989890,989896,988228,988225,990533,989888,990539,989055,990537,989855,990523,989863,989827,1241549,990555,1381551,100432364,100432361,992652,987009,1248547,1384836,987811,1257222,1382517,986867,988172,986877,988921,1248595,987882,1386217,992642,100664840,989976,990401,1149971,989196,989183,989176,1388444,989947,990461,100439401,989122,1379690,989100,989915,100447989,100440010,1364187,100498532,991113,991116,990744,988463,988497,991162,100492886,1246131,988402,1374079,100604736,1358427,1358422,100565515,995077,992351,993923,991703,988346,1132383,1195296,1393842,991180,990680,1407468,1203220,988316,990669,989361,987512,987588,988674,100444928,988654,990084,1006484,1371753,990136,990153,990962,990957,989434,988689,989445,990900,100583827,991696,987400,987404,1382019,989505,1369163,992130,989595,989597,1243088,992077,990051,988585,989529,991519,988596,1215386,988803,988871,988840,1163915,989631,1235101,989677,989673,1143590,991466,990341,1376171,991454,987378,1251774,100656208,1390910,1164370,989761,989766,1242627,1357433,988766,989738,1401275,990292,989779,989782,990271,991317,1166616,991377";
        List<String> sourceStringList = Splitter.on(",").splitToList(sourceString);
        List<String> list = Lists.newArrayList();
        for(String str : dbStringList){
            if(!sourceStringList.contains(str)){
                list.add(str);
            }
        }
        System.err.println("==>"+list);
    }

    @Test
    public void splitterTest(){
        String test = "a |  b | c";
        String newString = CharMatcher.WHITESPACE.removeFrom(test);
        System.err.println("===>"+newString);
        List<String> list = Splitter.on("|").splitToList(test);
        System.err.println(list);
    }

    @Test
    public void splitterListTest(){
        List<String> list = Lists.newArrayList();
        for(int i=0;i<49;i++){
            list.add(i+"");
        }
        List<List<String>> targetList = this.splitterSourceList(list,11);
        for(List<String> stringList : targetList){
            System.err.println(stringList);
        }
    }

    private List<List<String>> splitterSourceList(List<String> sourceList,Integer PAGE_SIZE){
        if(CollectionUtils.isEmpty(sourceList)){
            return null;
        }
        List<List<String>> targetList = Lists.newArrayList();
        if(sourceList.size() < PAGE_SIZE){
            targetList.add(sourceList);
            return targetList;
        }
        int pageCount = (sourceList.size() + PAGE_SIZE - 1) /PAGE_SIZE;
        for(int i=0;i<pageCount;i++){
            int currentRow = i * PAGE_SIZE;
            if(i == pageCount -1){
                List<String> lastList = Lists.newArrayList();
                for(int j=currentRow;j<sourceList.size();j++){
                    lastList.add(sourceList.get(j));
                }
                targetList.add(lastList);
            }else {
                List<String> list = Lists.newArrayList();
                for(int j=currentRow;j<PAGE_SIZE + currentRow;j++){
                    list.add(sourceList.get(j));
                }
                targetList.add(list);
            }
        }
        return targetList;
    }

    @Test
    public void calculateTest(){
        List<Long> baseNumberList = Lists.newArrayList();
        long x = 1;
        for(int i=0;i<32;i++){
            baseNumberList.add(x << i);
        }
        System.err.println("==>"+baseNumberList);

        List<Long> targetNumberList = Lists.newArrayList();
        long num = 33;
        for(Long baseNumber : baseNumberList){
            if((num | baseNumber) == num){
                targetNumberList.add(baseNumber);
            }
        }

        System.err.println("--->"+targetNumberList);
    }

    @Test
    public void jsonTest(){
        JsonTest jsonTest = new JsonTest();
        jsonTest.setOrderId("324134142134");
        jsonTest.setCode("24");
        JsonTest.PriceInfo priceInfo = new JsonTest.PriceInfo();
        priceInfo.setAdultPrice(800000l);
        priceInfo.setChildPrice(600000l);
        priceInfo.setRoomSendCount(2);
        priceInfo.setRoomSendPrice(200000l);
        jsonTest.setPriceInfo(priceInfo);
        System.err.println("==>"+ JsonMapper.mapString(jsonTest));
    }

    @Test
    public void convertDateTest(){
        String time = "/Date(473990400000-0000)/";
        String regx = "/Date\\((-*\\d+)-\\d+\\)/";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(time);
        if(matcher.find()){
            System.err.println("===>"+matcher.group(1));
        }
        String formatTime = DateTimeUtil.formatTime(Long.parseLong(matcher.group(1)),"yyyy-MM-dd");
        System.err.println("===>"+formatTime);
        System.err.println("===>"+DateTimeUtil.dateOf(formatTime));
    }

    @Setter @Getter
    public static class JsonTest{
        @NotBlank(message = "订单号不能为空")
        private String orderId;
        @NotBlank(message = "变更编码不能为空")
        private String code;
        private PriceInfo priceInfo;

        @Setter
        @Getter
        public static class PriceInfo{
            private Long adultPrice;
            private Long childPrice;
            private Integer roomSendCount;
            private Long roomSendPrice;
        }
    }

    @Test
    public void toJsonTest(){
        Map<String,String> map = Maps.newHashMap();
//        map.put("220016","您的{{OrderType}}订单{{OrderID}}扣款失败，请点击 {{Url}} 尽快至我的订单重新付款。失败原因：{{Reason}}。");
//        map.put("220018","{{OrderType}}订单{{OrderID}}已支付成功。{{OtherExplanation}}我的订单 {{ShortLink}}");
//        map.put("220024","很遗憾，您的未支付订单{{OrderID}}已过最晚保留时间。请重新下单。");
//        map.put("220026","您的订单{{OrderID}}，{{PkgName}}已确认，请注意截止收材料时间{{DeadLine}}。我的订单：{{ShortLink}}");
//        map.put("220027","您的度假订单{{OrderID}}，我们的行程已经成团啦。出团通知书最晚于出发前1天提供，请提前准备好您的行装，悠闲假期即将开始。点此 {{ShortLink}} 查看您的行程信息。");
//        map.put("220028","您的度假订单{{OrderID}}，目前尚未成团，您可选择继续等待，或在我的去哪儿取消订单 {{ShortLink}} ，谢谢。\n");
//        map.put("220033","您的{{Ordertype}}订单{{OrderID}}已确认。{{OtherExplanation}}我的订单{{ShortLink}}");
//        map.put("220040","{{Name}}：您的订单{{OrderID}}，电子合同已生成，您可在我的订单查看。如对合同没有疑义，短信回复TYHT{{OrderID}}，{{OtherExplanation}}即视为您同意并接受合同内容。订单确认预订成功后，如出发前2天您还未确认，您的合同将自动生效。\n");
//        map.put("220041","您的悠闲旅行将于明日开启。{{OtherExplanation}}。");
//        map.put("220039","您预订的{{OtherExplanation}}下单失败。已预授权的信用额度将会解冻；");
//        map.put("220050","{{Name}}：您的订单{{OrderId}}，电子旅游合同（协议）已生效。");
//        map.put("220054","您的{{OrderType}}订单{{OrderID}}尚有未支付款项，请尽快点击 {{ShortLink}} 至我的订单付款，{{OtherExplanation}}。如您已付款或预约上门收款请忽略。");
//        map.put("220064","您的订单{{OrderID}}，{{PkgName}}已付款，办签所需材料已发送至邮箱{{EmailAddr}}，若您的通行证还未准备好，请您在1个工作日内优先提供出行人的身份证和照片，在我的订单 {{ShortLink}} 上传电子版材料进行预审。其他办理入台证材料截止收材料时间：{{ReceiveDate}}");
//        map.put("220066","【XXX<规则：读取送签领馆>】签证指纹采集日期：X月X日<规则：读取指纹取样日期>（具体时间将在采集日期前两个工作日通知）。地址：XXXXXX <规则：读取配置表中相应送签地、送签领馆的地址+地址补充备注>。注：12周岁以下儿童【需要/无需<规则：读取配置表中相应送签领馆的规则>】前往（除芬兰），所有区域禁止使用手机、摄像、拍照及携带食物。采集日期由领馆决定，无法更改，请您务必准时出席。如您无法按时前往，将视作您放弃行程，并产生违约金，谢谢！");
//        map.put("220067","【XXX<规则：读取送签领馆>】签证指纹采集时间：X月X日XX:XX<规则：读取指纹取样日期及时间>（请提前15分钟到）。地址：XXXXXX<规则：读取配置表中相应送签地、送签领馆的地址+地址补充备注>。陪同人员联系方式：XXXXXXXXXXX<规则：读取配置表中现场陪签人员联系电话>（此联系方式仅限生物取样当天集合时联系，其余问题请拨打10106666咨询）。注：取样人员当天必须着深色衣服（仅限法国、瑞士），不要佩戴隐形眼镜或美瞳,12周岁以下儿童【需要/无需<规则：读取配置表中相应送签领馆的规则>】前往（除芬兰），另，由于停车位紧张，建议搭乘公共交通前往。所有区域禁止使用手机、摄像、拍照及携带食物。全团完成采集大约1-3小时，到达现场后请耐心等待并听从工作人员安排。采集日期由领馆决定，无法更改，请您务必准时出席。如您无法按时前往，将视作您放弃行程，并产生违约金，谢谢！");
//        map.put("220071","您的旅游订单{{OrderID}}，预订的航班号{{OriginFlightNo}}，起飞{{OriginDepartureTime}}，抵达{{OriginArrivalTime}}。航空公司通知变更为航班号{{NewFlightNo}}，起飞{{NewDepartureTime}}，抵达{{NewArrivalTime}}，航站楼{{Terminal}}。{{Compensation}}接受变更航班请回复 TYHB{{TYOrderID}} ，立即为您确认机位；如您不同意请回复 JJHB{{JJOrderID}} ，我们将安排专人于1小时内电话联系您。");
//        map.put("220072","手机自助查询、操作，简单又方便，请点击 {{ShortLink}} 登录我的订单，在订单中进行自助申请。");
//        map.put("220073","您的度假订单{{OrderID}}，目前尚未成团，去哪儿希望您继续等待，或在我的订单中取消订单 {{ShortLink}} ，谢谢。");
//        map.put("220074","小伙伴，我们在{{DeliverTime}}为{{GuestInfo}}送签，预计{{EstIssueDate}}出签，请耐心等待签证结果噢。点此 {{ShortLink}} 查看您的签证办理进度。");
//        map.put("220075","小伙伴，恭喜{{ProductName}}顺利出签啦，去哪儿预祝您享受美好的悠闲假期噢，好期待。点此 {{ShortLink}} 查看您的签证办理进度。");
//        map.put("220076","温馨提醒：您所预订的{{Month}}月{{Date}}日出发的{{Name}}，您可以登录我的订单查看和下载出团通知书 {{ShortLink}}");
//        map.put("220077","度假订单{{OrderID}} ，小伙伴们已集结完毕，我们的团成团啦。请提前准备好您的行装，悠闲假期即将开始。点此 {{ShortLink}} 查看您的行程信息。");
//        map.put("220078","尊敬的{{GuestName}}：您预订的{{DepartureDate}}出发的旅游线路需要前往领馆进行指纹采集。请您于{{RegTime}}前往{{RegPlace}}进行登记。{{OtherInfo}}采集日期由领馆决定，无法更改，请您届时务必准时出席，否则无法出行。谢谢！");
//        map.put("220079","您预订的行程{{ProductName}}，已经成团啦。请提前准备好您的行装，悠闲假期即将开始");
//        map.put("220081","温馨提示：订单{{OrderID}}，{{OtherInfo}}，通过“我的订单”上传的签证材料未通过审核，请重新上传。未通过原因详见“我的订单” {{ShortLink}}");
//        map.put("220085","尊敬的{{Guest}}：您预订的{{DepartureDate}}出发的旅游线路需要前往领馆进行面签，请您于{{InterviewDate}}前往{{InterviewPlace}}进行面签。");
//        map.put("220086","联运段航班已确认，去程航班：{{FirstOutboundInfo}}，{{SecondOutboundInfo}}，{{ThirdOutboundInfo}}；回程航班：{{FirstInboundInfo}}，{{SecondInboundInfo}}，{{ThirdInboundInfo}}。乘坐联运航班需您提前到达集合城市，请自行承担由此产生的食宿费用。");

        map.put("220016","您的{{OrderType}}订单{{OrderID}}扣款失败，请点击 https://qnr.io/yz6llr 尽快至我的订单重新付款。失败原因：{{Reason}}。");
        map.put("220018","{{OrderType}}订单{{OrderID}}已支付成功。{{OtherExplanation}}我的订单 https://qnr.io/yz6llr");
        map.put("220024","很遗憾，您的未支付订单{{OrderID}}已过最晚保留时间。请重新下单。");
        map.put("220026","您的订单{{OrderID}}，{{PkgName}}已确认，请注意截止收材料时间{{DeadLine}}。我的订单：{{ShortLink}}");
        map.put("220027","您的度假订单{{OrderID}}，我们的行程已经成团啦。出团通知书最晚于出发前1天提供，请提前准备好您的行装，悠闲假期即将开始。点此 https://qnr.io/yz6llr 查看您的行程信息。");
        map.put("220028","您的度假订单{{OrderID}}，目前尚未成团，您可选择继续等待，或在我的去哪儿取消订单 https://qnr.io/yz6llr ，谢谢。");
        map.put("220033","您的{{Ordertype}}订单{{OrderID}}已确认。{{OtherExplanation}}我的订单https://qnr.io/yz6llr");
        map.put("220040","{{Name}}：您的订单{{OrderID}}，电子合同已生成，您可在我的订单查看。");
        map.put("220041","您的悠闲旅行将于明日开启。{{OtherExplanation}}。");
        map.put("220039","您预订的{{OtherExplanation}}下单失败。已预授权的信用额度将会解冻；");
        map.put("220050","{{Name}}：您的订单{{OrderId}}，电子旅游合同（协议）已生效。");
        map.put("220054","您的{{OrderType}}订单{{OrderID}}尚有未支付款项，请尽快点击 https://qnr.io/yz6llr 至我的订单付款，{{OtherExplanation}}。如您已付款或预约上门收款请忽略。");
        map.put("220064","您的订单{{OrderID}}，{{PkgName}}已付款，办签所需材料已发送至邮箱{{EmailAddr}}，若您的通行证还未准备好，请您在1个工作日内优先提供出行人的身份证和照片，在我的订单 https://qnr.io/yz6llr 上传电子版材料进行预审。其他办理入台证材料截止收材料时间：{{ReceiveDate}}");
        map.put("220066","【XXX<规则：读取送签领馆>】签证指纹采集日期：X月X日<规则：读取指纹取样日期>（具体时间将在采集日期前两个工作日通知）。地址：XXXXXX <规则：读取配置表中相应送签地、送签领馆的地址+地址补充备注>。注：12周岁以下儿童【需要/无需<规则：读取配置表中相应送签领馆的规则>】前往（除芬兰），所有区域禁止使用手机、摄像、拍照及携带食物。采集日期由领馆决定，无法更改，请您务必准时出席。如您无法按时前往，将视作您放弃行程，并产生违约金，谢谢！");
        map.put("220067","【XXX<规则：读取送签领馆>】签证指纹采集时间：X月X日XX:XX<规则：读取指纹取样日期及时间>（请提前15分钟到）。地址：XXXXXX<规则：读取配置表中相应送签地、送签领馆的地址+地址补充备注>。陪同人员联系方式：XXXXXXXXXXX<规则：读取配置表中现场陪签人员联系电话>（此联系方式仅限生物取样当天集合时联系，其余问题请拨打10106666咨询）。注：取样人员当天必须着深色衣服（仅限法国、瑞士），不要佩戴隐形眼镜或美瞳,12周岁以下儿童【需要/无需<规则：读取配置表中相应送签领馆的规则>】前往（除芬兰），另，由于停车位紧张，建议搭乘公共交通前往。所有区域禁止使用手机、摄像、拍照及携带食物。全团完成采集大约1-3小时，到达现场后请耐心等待并听从工作人员安排。采集日期由领馆决定，无法更改，请您务必准时出席。如您无法按时前往，将视作您放弃行程，并产生违约金，谢谢！");
        map.put("220071","您的旅游订单{{OrderID}}，预订的航班号{{OriginFlightNo}}，起飞{{OriginDepartureTime}}，抵达{{OriginArrivalTime}}。航空公司通知变更为航班号{{NewFlightNo}}，起飞{{NewDepartureTime}}，抵达{{NewArrivalTime}}，航站楼{{Terminal}}。{{Compensation}}接受变更航班请回复 TYHB{{TYOrderID}} ，立即为您确认机位；如您不同意请回复 JJHB{{JJOrderID}} ，我们将安排专人于1小时内电话联系您。");
        map.put("220072","手机自助查询、操作，简单又方便，请点击 https://qnr.io/yz6llr 登录我的订单，在订单中进行自助申请。");
        map.put("220073","您的度假订单{{OrderID}}，目前尚未成团，去哪儿希望您继续等待，或在我的订单中取消订单 https://qnr.io/yz6llr ，谢谢。");
        map.put("220074","小伙伴，我们在{{DeliverTime}}为{{GuestInfo}}送签，预计{{EstIssueDate}}出签，请耐心等待签证结果噢。点此 {{ShortLink}} 查看您的签证办理进度。");
        map.put("220075","小伙伴，恭喜{{ProductName}}顺利出签啦，去哪儿预祝您享受美好的悠闲假期噢，好期待。点此 {{ShortLink}} 查看您的签证办理进度。");
        map.put("220076","温馨提醒：您所预订的{{Month}}月{{Date}}日出发的{{Name}}，您可以登录我的订单查看和下载出团通知书 https://qnr.io/yz6llr");
        map.put("220077","度假订单{{OrderID}} ，小伙伴们已集结完毕，我们的团成团啦。请提前准备好您的行装，悠闲假期即将开始。点此 https://qnr.io/yz6llr 查看您的行程信息。");
        map.put("220078","尊敬的{{GuestName}}：您预订的{{DepartureDate}}出发的旅游线路需要前往领馆进行指纹采集。请您于{{RegTime}}前往{{RegPlace}}进行登记。{{OtherInfo}}采集日期由领馆决定，无法更改，请您届时务必准时出席，否则无法出行。谢谢！");
        map.put("220079","您预订的行程{{ProductName}}，已经成团啦。请提前准备好您的行装，悠闲假期即将开始");
        map.put("220081","温馨提示：订单{{OrderID}}，{{OtherInfo}}，通过“我的订单”上传的签证材料未通过审核，请重新上传。未通过原因详见“我的订单” https://qnr.io/yz6llr");
        map.put("220085","尊敬的{{Guest}}：您预订的{{DepartureDate}}出发的旅游线路需要前往领馆进行面签，请您于{{InterviewDate}}前往{{InterviewPlace}}进行面签。");
        map.put("220086","联运段航班已确认，去程航班：{{FirstOutboundInfo}}，{{SecondOutboundInfo}}，{{ThirdOutboundInfo}}；回程航班：{{FirstInboundInfo}}，{{SecondInboundInfo}}，{{ThirdInboundInfo}}。乘坐联运航班需您提前到达集合城市，请自行承担由此产生的食宿费用。");

        CtripOrderSmsInfo smsInfo = new CtripOrderSmsInfo();
        CtripOrderSmsInfo.SmsData data = new CtripOrderSmsInfo.SmsData();
        smsInfo.setData(data);
        CtripOrderSmsInfo.ContentInfo contentInfo = new CtripOrderSmsInfo.ContentInfo();
        data.setContentInfo(contentInfo);
        contentInfo.setDeliverTime(System.currentTimeMillis()+"");
        contentInfo.setGuestInfo("Test");
        contentInfo.setEstIssueDate(System.currentTimeMillis()+"");

        this.replaceVariable(smsInfo,map.get("220074"));

//        String result = JsonMapper.mapString(map);
//        System.err.println("===>"+result);
    }

    //小伙伴，我们在{{DeliverTime}}为{{GuestInfo}}送签，预计{{EstIssueDate}}出签，请耐心等待签证结果噢。点此 {{ShortLink}} 查看您的签证办理进度。
    private String replaceVariable(CtripOrderSmsInfo smsInfo,String content){
        if(StringUtils.isBlank(content)){
            return StringUtils.EMPTY;
        }
        String regx = "\\{\\{(\\w+)\\}\\}";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(content);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            System.err.println("====>"+matcher.group());
            String value = getValueByReflect(smsInfo,matcher.group(1));
            if(StringUtils.isNotBlank(value)){
                matcher.appendReplacement(sb, value);
            }
        }
        matcher.appendTail(sb);
        System.err.println("---->"+content);
        System.err.println("---->"+sb);
        return null;
    }

    private String getValueByReflect(CtripOrderSmsInfo smsInfo,String fileName){
        try {
            Class clazz = smsInfo.getClass();
            Class[] classes = clazz.getDeclaredClasses();
            if(null == classes || classes.length<1){
                return StringUtils.EMPTY;
            }
            for(Class cl : classes){
                Field[] fieldss = cl.getDeclaredFields();
                if(null == fieldss || fieldss.length < 1){
                    continue;
                }
                Field targetField = null;
                for(Field field : fieldss){
                    if(StringUtils.equalsIgnoreCase(field.getName(),fileName)){
                        targetField = field;
                        break;
                    }
                }
                if(targetField == null){
                    continue;
                }
                Method method = cl.getMethod("get"+change(targetField.getName()),null);
                String value = StringUtils.EMPTY;
                if(cl.isInstance(smsInfo)){
                    value = (String) method.invoke(smsInfo,null);
                }else if(cl.isInstance(smsInfo.getData())){
                    value = (String) method.invoke(smsInfo.getData(),null);
                } else if(cl.isInstance(smsInfo.getData().getContentInfo())){
                    value = (String) method.invoke(smsInfo.getData().getContentInfo(),null);
                } else if(cl.isInstance(smsInfo.getData().getChannelInfo())){
                    value = (String) method.invoke(smsInfo.getData().getChannelInfo(),null);
                }
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
        return StringUtils.EMPTY;
    }

    private String change(String src) {
        if (StringUtils.isNotBlank(src)) {
            StringBuffer sb = new StringBuffer(src);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return null;
        }
    }

    @Test
    public void mapObjectTest(){
        String test="{\n" +
                "    \"orderId\":\"2159010917\",\n" +
                "    \"code\":\"220026\",\n" +
                "    \"data\":{\n" +
                "        \"ChannelInfo\":{\n" +
                "            \"OrderID\":2159010917,\n" +
                "            \"UID\":\"xxx\",\n" +
                "            \"EID\":\"Auto_Job\",\n" +
                "            \"ExpiredTime\":\"\",\n" +
                "            \"MobilePhone\":\"13888888888\",\n" +
                "            \"ScheduleTime\":\"\",\n" +
                "            \"UIDList\":[\n" +
                "                \"13925045259\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"Content\":{\n" +
                "            \"PkgName\":\"俄罗斯个人旅游签证（香港送签）\",\n" +
                "            \"DeadLine\":\"1484803369622\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        CtripOrderSmsInfo smsInfo = JsonMapper.mapObject(test,CtripOrderSmsInfo.class);
        System.err.println("===>"+JsonMapper.mapString(smsInfo));
    }


    @Setter @Getter
    public static class CtripOrderSmsInfo {

        @JsonProperty("orderId")
        private String orderId;
        private String code;
        private SmsData data;

        @Setter @Getter
        public static class SmsData{
            @JsonProperty("ChannelInfo")
            private ChannelInfo channelInfo;
            @JsonProperty("Content")
            private ContentInfo contentInfo;
        }

        @Setter @Getter
        public static class ChannelInfo{
            @JsonProperty("OrderID")
            private String orderId;
            @JsonProperty("UID")
            private String uid;
            @JsonProperty("EID")
            private String eid;
            @JsonProperty("ExpiredTime")
            private String expiredTime;
            @JsonProperty("MobilePhone")
            private String mobilePhone;
            @JsonProperty("ScheduleTime")
            private String scheduleTime;
            @JsonProperty("UIDList")
            private List<String> uidList;
        }

        @Setter @Getter
        public static class ContentInfo{
            private String first;
            @JsonProperty("PkgName")
            private String pkgName;
            @JsonProperty("Remark")
            private String remark;
            @JsonProperty("OrderType")
            private String orderType;
            @JsonProperty("OtherExplanation")
            private String otherExplanation;
            @JsonProperty("Url")
            private String url;
            @JsonProperty("ShortLink")
            private String shortLink;
            @JsonProperty("AppUrl")
            private String appUrl;
            @JsonProperty("OnlineUrl")
            private String onlineUrl;
            @JsonProperty("Reason")
            private String reason;
            @JsonProperty("DeadLine")
            private String deadLine;
            @JsonProperty("Name")
            private String name;
            @JsonProperty("EmailAddr")
            private String emailAddr;
            @JsonProperty("ReceiveDate")
            private String receiveDate;
            @JsonProperty("OriginFlightNo")
            private String originFlightNo;
            @JsonProperty("OriginDepartureTime")
            private String originDepartureTime;
            @JsonProperty("OriginArrivalTime")
            private String originArrivalTime;
            @JsonProperty("NewFlightNo")
            private String newFlightNo;
            @JsonProperty("NewDepartureTime")
            private String newDepartureTime;
            @JsonProperty("Terminal")
            private String terminal;
            @JsonProperty("Compensation")
            private String compensation;
            @JsonProperty("TYOrderID")
            private String tyOrderID;
            @JsonProperty("JJOrderID")
            private String jjOrderID;
            @JsonProperty("DeliverTime")
            private String deliverTime;
            @JsonProperty("GuestInfo")
            private String guestInfo;
            @JsonProperty("EstIssueDate")
            private String estIssueDate;
            @JsonProperty("ProductName")
            private String productName;
            @JsonProperty("Month")
            private String month;
            @JsonProperty("Date")
            private String date;
            @JsonProperty("GuestName")
            private String guestName;
            @JsonProperty("DepartureDate")
            private String departureDate;
            @JsonProperty("RegTime")
            private String regTime;
            @JsonProperty("RegPlace")
            private String regPlace;
            @JsonProperty("OtherInfo")
            private String otherInfo;
            @JsonProperty("InterviewDate")
            private String interviewDate;
            @JsonProperty("InterviewPlace")
            private String interviewPlace;
            @JsonProperty("FirstOutboundInfo")
            private String firstOutboundInfo;
            @JsonProperty("SecondOutboundInfo")
            private String secondOutboundInfo;
            @JsonProperty("ThirdOutboundInfo")
            private String thirdOutboundInfo;
            @JsonProperty("FirstInboundInfo")
            private String firstInboundInfo;
            @JsonProperty("SecondInboundInfo")
            private String secondInboundInfo;
            @JsonProperty("ThirdInboundInfo")
            private String thirdInboundInfo;
        }
    }


}
