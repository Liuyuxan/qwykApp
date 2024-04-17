package com.qywk.common.core.utils;

import cn.hutool.core.date.DateUtil;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 教室使用数据表
 * @date 2023/03/9
 * @author zc
 */
public class DateTimeUtils {
    /**
     * 判断是上学期还是下学期
     * 上学期：9月初到1月初，四个月。
     * 下学期：2月底到7月初，大概四个月。
     *
     * @return 返回当前学期（1上学期；2下学期）
     */
    public static int schoolTerm() {
        // 获取当前月份
        int month = DateUtil.month(new Date());
        if (month >= 9 || month ==1) {
            return 1;
        } else {
            return 2;
        }
    }
    public static int schoolTerm(Date date) {
        // 获取当前月份
        int month = DateUtil.month(date);
        if (month >= 9 || month ==1) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 计算学年
     *  如果是第一学期，今年到下一年
     *  如果是第二学期，上年到今年
     *
     * @return 返回当前学年（如：‘2020-2021’为2020到2021学年）
     */
    public static String schoolYear() {
        // 获取当前年
        int year = DateUtil.year(new Date());
        if (1 == schoolTerm()&&DateUtil.month(new Date())==1) {
            return (year - 1) + "-" + year;
        } else if(1 == schoolTerm()&&DateUtil.month(new Date())>=9){
            return year + "-" + (year- 1);
        }else {
            return (year - 1) + "-" + year;
        }
    }

    public static String schoolYear(Date date) {
        // 获取当前年
        int year = DateUtil.year(date);
        int month=DateUtil.month(date);
        if (1 == schoolTerm()&&month==1) {
            return (year - 1) + "-" + year;
        } else if(1 == schoolTerm()&&month>=9){
            return year + "-" + (year- 1);
        }else{
            return (year - 1) + "-" + year;
        }
    }

    /**
    * 判断闰年
     * @return 返回 1 或 0
    * */
    public static int isLeap(int year){
        return (year%400 ==0|| (year %4 == 0&& year % 100 != 0))?1:0;
    }

    /**
     *  转换 阴历日期
     *
     *  转换规则 : 想要将公历转换为农历，可以根据公式：公元年数—1901（或1977）=R+4Q；那么农历日期=10.6（1+R）
     * +14Q+年内日期序数—29.5n（n、R、Q都是自然数，R<4）。这个公式算法对于很多数学不好的人而言是很费脑子的
     * */
    public static String getLunar(int year,int month,int day){
        // int year,int month,int day

        String lunDay_ = "";
        int[] months = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        // 农历特殊日期的处理

        String[] firstDays = {"腊月","一","二","三","四","五","六","七","八","九","十","十一","腊月"};
        String[] lurDays = {"三十","初一","初二","初三","初四","初五",
                "初六","初七","初八","初九","初十",
                "十一","十二","十三","十四","十五",
                "十六","十七","十八","十九","二十",
                "廿一","廿二","廿三","廿四","廿五",
                "廿六","廿七","廿八","廿九","三十"};
        // 当年序号数
        int res = 0 ;
        for(int i =1 ; i < month; i++){
            // 判断是否为闰年
            if(i ==2 ) res += ((year%4==0&&year%100!=0)||year%400==0)?1:0;
            res += months[i];
        }
        res += day;
        int Q = (year-1977) / 4;
        int R = (year-1977) % 4;
        // 带入公式计算改日期对应的阴历日期
        double tmp =  ((float)(10.6 * ( 1 + R ) + 14 * Q  + res ));
        // 不用 4舍5入 直接下取整
        int lunDay = (int)(tmp % 29.5);
        // 映射对应的阴历日期

        if(lunDay == 1) lunDay_ = firstDays[month-1]+"月";
        else lunDay_ = lurDays[lunDay];
        return lunDay_;
    }
    /**
     * 判断 每年的二十四节气对应日期
     *  能适用于 21世纪
     *  以 1900年 1 月 0 日(星期日) 为 基准 (假象值)
     * */

   public static String[] GetSolarTerms(int year){
        // 计算 当年第一天 距 1900 年 1月 1日 的日期值
        double dis_res = 0;
        String[] solars = new String[24];
        for(int i = 1900; i< year ; i++){
            dis_res += 365 + isLeap(i);
        }
        final int[] months = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        // 判断当前年份是否为闰年
        months[2] += isLeap(year);

        int k = 0;
        for(int i  = 0; i <=23 ;i ++){
            // 带入公式 获取 每个节气 积日 (与 1900年 1月 0日 相差的 日期数)
            double F = 365.242 * (year - 1900 ) + 6.2 + 15.22 * i - 1.9 * Math.sin(0.262 *i );
            F = F - dis_res;
            String solarDay = year+"-";
            // 得出月 和 日 对应的是新历
            for(int j = 1; j<= 12 ; j++){
                if(F >= months[j]){
                    F  -= months[j];
                }else {
                    solarDay += ""+ (j<=9?"0"+j:j)+"-";
                    break;
                }
            }
            solarDay += ""+((int)F<=9?"0"+(int)F:(int)F);
            solars[k++] = solarDay;
        }
       // 带入公式 获取积日 最终获取 24 节气
        return solars;
    }

    /**
     * 获取今天是星期几
     *
     * */
    public static String GetWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String[] weeks = new String[]{"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return weeks[index];
    }




    /**
     *
     * @param date
     * @return
     */
    @SneakyThrows
    public static Date myDateFormat(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(simpleDateFormat.format(date));
    }
    @SneakyThrows
    public static Date myDateTimeFormat(Date dateTime)  {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(simpleDateFormat.format(dateTime));
    }
}
