package com.sfy.java.joda;

import com.google.common.collect.ImmutableList;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

/**
 * 测试Joda对日期的处理
 * @author sunfayun
 * @time 2015/04/10 14:34
 */
public class JodaTimeTest {

    /**
     * 测试获取今天的开始时间
     * 获取今天的开始时间:2015/04/10 00:00:00 星期五
     */
    @Test
    public void getTodayStartTime(){
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withTimeAtStartOfDay();
        String time = dateTime.toString(TimeFormat.TYPE_1);
        System.err.println("获取今天的开始时间:"+time);
    }

    /**
     * 测试获取今天的结束时间
     * 获取今天的结束时间:04/10/2015 11:59:59.999下午
     */
    @Test
    public void getTodayEndTime(){
        DateTime dateTime = new DateTime();
        dateTime = dateTime.millisOfDay().withMaximumValue();
        String time = dateTime.toString(TimeFormat.TYPE_2);
        System.err.println("获取今天的结束时间:"+time);
    }

    /**
     * 获取现在距离今天结束还有多长时间
     * 今天的最大时间:10-04-2015 23:59:59
     * 到今天结束还有多长时间:26233496
     */
    @Test
    public void getTimeToEnd(){
        DateTime dateTime = new DateTime();
        dateTime = dateTime.millisOfDay().withMaximumValue();
        System.err.println("今天的最大时间:"+dateTime.toString(TimeFormat.TYPE_3));
        long value = dateTime.getMillis()-new DateTime().getMillis();
        System.err.println("到今天结束还有多长时间:"+value);
    }

    /**
     * 获取两个日期的相隔天数
     * Days.daysBetween(endDateTime,startDateTime):P-365D
     * Days.daysBetween(startDateTime,endDateTime):P365D
     * 两个日期的相隔天数:365
     */
    @Test
    public void getDistanceTwoDate(){
        String startTime = "2014-04-03 12:20:22";
        String endTime = "2015-04-03 12:20:22";
        DateTime startDateTime = DateTime.parse(startTime, DateTimeFormat.forPattern(TimeFormat.TYPE_8));
        DateTime endDateTime = DateTime.parse(endTime,DateTimeFormat.forPattern(TimeFormat.TYPE_8));
        System.err.println("Days.daysBetween(endDateTime,startDateTime):"+Days.daysBetween(endDateTime,startDateTime));
        System.err.println("Days.daysBetween(startDateTime,endDateTime):"+Days.daysBetween(startDateTime, endDateTime));
        int days = Days.daysBetween(startDateTime,endDateTime).getDays();
        System.err.println("两个日期的相隔天数:"+days);
    }

    /**
     * 100天之后的日期:2015/07/19 17:32:09 星期日
     * 当前周的周一的时间:2015/04/06 17:32:09 星期一
     * 当前周的周日的时间:2015/04/12 17:32:09 星期日
     * 当前月第一天的时间:2015/04/01 17:32:09 星期三
     * 当前月最后一天的时间:2015/04/30 17:32:09 星期四
     * 当前年第一天的时间:2015/01/01 17:32:09 星期四
     * 当前年最后一天的时间:2015/12/31 17:32:09 星期四
     */
    @Test
    public void getFirstAndLastTimeMethod(){
        System.err.println("100天之后的日期:"+DateTime.now().dayOfYear().addToCopy(100).toString(TimeFormat.TYPE_1));
        System.err.println("当前周的周一的时间:"+DateTime.now().dayOfWeek().withMinimumValue().toString(TimeFormat.TYPE_1));
        System.err.println("当前周的周日的时间:"+DateTime.now().dayOfWeek().withMaximumValue().toString(TimeFormat.TYPE_1));
        System.err.println("当前月第一天的时间:"+DateTime.now().dayOfMonth().withMinimumValue().toString(TimeFormat.TYPE_1));
        System.err.println("当前月最后一天的时间:"+DateTime.now().dayOfMonth().withMaximumValue().toString(TimeFormat.TYPE_1));
        System.err.println("当前年第一天的时间:"+DateTime.now().dayOfYear().withMinimumValue().toString(TimeFormat.TYPE_1));
        System.err.println("当前年最后一天的时间:"+DateTime.now().dayOfYear().withMaximumValue().toString(TimeFormat.TYPE_1));
    }

    /**
     * 使用Locale.CHINESE格式化时间:2015/04/10 17:39:57 星期五
     */
    @Test
    public void getFormatDateTimeMethod(){
        DateTime now = new DateTime();
        System.err.println("使用Locale.CHINESE格式化时间:"+now.toString(TimeFormat.TYPE_1, Locale.CHINESE));
    }

    /**
     * 计算时间的方法
     * 当前时间加上11天的时间:2015/04/21 17:51:35 星期二
     * 当前时间减10天的时间:2015/03/31 17:51:35 星期二
     * 当前时间减10个小时的时间:2015/04/10 07:51:35 星期五
     * 当前时间加10个小时的时间:2015/04/11 03:51:35 星期六
     * 当前时间加10分钟的时间2015/04/10 18:01:35 星期五
     * 当前时间减10分钟的时间2015/04/10 17:41:35 星期五
     * 当前时间加1年的时间2016/04/10 17:51:35 星期日
     * 当前时间减1年的时间2014/04/10 17:51:35 星期四
     */
    @Test
    public void calculateDateTimeMethod(){
        DateTime now = new DateTime();
        System.err.println("当前时间加上11天的时间:"+now.plusDays(11).toString(TimeFormat.TYPE_1,Locale.CHINESE));
        System.err.println("当前时间减10天的时间:"+now.minusDays(10).toString(TimeFormat.TYPE_1));
        System.err.println("当前时间减10个小时的时间:"+now.minusHours(10).toString(TimeFormat.TYPE_1));
        System.err.println("当前时间加10个小时的时间:"+now.plusHours(10).toString(TimeFormat.TYPE_1));
        System.err.println("当前时间加10分钟的时间"+now.plusMinutes(10).toString(TimeFormat.TYPE_1));
        System.err.println("当前时间减10分钟的时间"+now.minusMinutes(10).toString(TimeFormat.TYPE_1));
        System.err.println("当前时间加1年的时间"+now.plusYears(1).toString(TimeFormat.TYPE_1));
        System.err.println("当前时间减1年的时间"+now.minusYears(1).toString(TimeFormat.TYPE_1));
    }

    /**
     * 获取两个时间之间隔了多少天,多少小时等
     * 2015/04/10 18:19:24 星期五和2016/01/03 12:23:22 星期日相隔:267天
     * 2015/04/10 18:19:24 星期五和2016/01/03 12:23:22 星期日相隔:6426小时
     * 2015/04/10 18:19:24 星期五和2016/01/03 12:23:22 星期日相隔:385563分钟
     * 2015/04/10 18:19:24 星期五和2016/01/03 12:23:22 星期日相隔:23133837秒
     */
    @Test
    public void getDifferTwoDateTime(){
        DateTime now = new DateTime();
        DateTime end = DateTime.parse("2016-01-03 12:23:22",DateTimeFormat.forPattern(TimeFormat.TYPE_8));
        Duration duration = new Duration(now,end);
        System.err.println(this.dateToString(now)+"和"+this.dateToString(end)+"相隔:"+duration.getStandardDays()+"天");
        System.err.println(this.dateToString(now)+"和"+this.dateToString(end)+"相隔:"+duration.getStandardHours()+"小时");
        System.err.println(this.dateToString(now)+"和"+this.dateToString(end)+"相隔:"+duration.getStandardMinutes()+"分钟");
        System.err.println(this.dateToString(now)+ "和" + this.dateToString(end)+"相隔:"+duration.getStandardSeconds()+"秒");
    }

    /**
     * 计算时间的方法混合使用
     * 上个月最后一天的时间是:2015/03/31 18:48:17 星期二
     * 上周一的时间:2015/03/30 18:48:17 星期一
     * 2015-01-01和2014-12-30相隔:2
     */
    @Test
    public void otherCalculateMethod(){
        DateTime localDate = DateTime.now();
        //上个月的最后一天
        DateTime lastDayOfPreviousMonth = localDate.minusMonths(1).dayOfMonth().withMaximumValue();
        System.err.println("上个月最后一天的时间是:"+this.dateToString(lastDayOfPreviousMonth));

        //上周的周一
        DateTime.Property property = localDate.minusWeeks(1).dayOfWeek();
        System.err.println("上周一的时间:"+this.dateToString(property.withMinimumValue()));

        //计算两个时间相隔几天
        System.err.println("2015-01-01和2014-12-30相隔:"+Days.daysBetween(DateTime.parse("2014-12-30"),DateTime.parse("2015-01-01")).getDays());
    }

    /**
     * 获取小时列表
     */
    @Test
    public void getHoursList(){
        List<Long> hoursList = getHoursTimeOfDate(DateTime.now());
        for(Long time : hoursList){
            System.err.println(new DateTime(time).toString(TimeFormat.TYPE_6));
        }
    }

    /**
     * 获取天列表
     */
    @Test
    public void getDaysList(){
        List<Long> daysList = getDaysOfMonth(DateTime.now());
        for(Long time : daysList){
            System.err.println(new DateTime(time).toString(TimeFormat.TYPE_5));
        }
    }

    /**
     * 获取月列表
     */
    @Test
    public void getMothList(){
        List<Long> monthList = getMonthsOfYear(DateTime.now());
        for(Long time : monthList){
            System.err.println(new DateTime(time).toString(TimeFormat.TYPE_4));
        }
    }

    /**
     * @param date
     * @return date 所在天的小时列表
     * 备注：这个方法的实际使用意义不大，因为每天都是24个小时，不会产生变化。
     * 这里的例子就是为了展示DateTime的API
     */
    public static List<Long> getHoursTimeOfDate(DateTime date) {
        final ImmutableList.Builder<Long> hourTimeList = ImmutableList.builder();

        DateTime firstHourTime = date.withTimeAtStartOfDay();
        final DateTime nextDayFirstHourTime = firstHourTime.plusDays(1);
        while (firstHourTime.isBefore(nextDayFirstHourTime)) {
            hourTimeList.add(firstHourTime.getMillis());
            firstHourTime = firstHourTime.plusHours(1);
        }
        return hourTimeList.build();

    }

    /**
     * @param date
     * @return date 所在月份的日期列表
     */
    public static List<Long> getDaysOfMonth(DateTime date) {
        final ImmutableList.Builder<Long> dayList = ImmutableList.builder();

        LocalDate firstDayOfMonth = date.toLocalDate().withDayOfMonth(1);
        final LocalDate nextMonthFirstDay = firstDayOfMonth.plusMonths(1);
        while (firstDayOfMonth.isBefore(nextMonthFirstDay)) {
            dayList.add(firstDayOfMonth.toDateTimeAtStartOfDay().getMillis());
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }
        return dayList.build();
    }

    /**
     * @param date
     * @return date 所在年份的月份列表
     * 备注：这个方法的实际使用意义不大，因为每年都是12个月，不会产生变化。
     * 这里的例子就是为了展示DateTime的API
     */
    public static List<Long> getMonthsOfYear(DateTime date) {
        final ImmutableList.Builder<Long> monthList = ImmutableList.builder();

        LocalDate firstMonth = date.toLocalDate().withDayOfMonth(1).withMonthOfYear(1);
        final LocalDate nextYearFirstMonth = firstMonth.plusYears(1);
        while (firstMonth.isBefore(nextYearFirstMonth)) {
            monthList.add(firstMonth.toDateTimeAtStartOfDay().getMillis());
            firstMonth = firstMonth.plusMonths(1);
        }
        return monthList.build();
    }



    public String dateToString(DateTime dateTime){
        return dateTime.toString(TimeFormat.TYPE_1);
    }

    interface TimeFormat{
        String TYPE_1 = "yyyy/MM/dd HH:mm:ss EE";
        String TYPE_2 = "MM/dd/yyyy hh:mm:ss.SSSa";
        String TYPE_3 = "dd-MM-yyyy HH:mm:ss";
        String TYPE_4 = "MM";
        String TYPE_5 = "dd";
        String TYPE_6 = "HH:mm:ss";
        String TYPE_7 = "yyyy年MM月dd日 HH:mm:ss EE";
        String TYPE_8 = "yyyy-MM-dd HH:mm:ss";
    }

}

