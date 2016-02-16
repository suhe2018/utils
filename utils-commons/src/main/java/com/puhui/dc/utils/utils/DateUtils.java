package com.puhui.dc.utils.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rongjianmin on 2015/7/21.
 */
public class DateUtils {
    /**
     * 获得两个日期之间的月份
     * 
     * @author rongjianmin
     * @param openDate
     * @param applyTime
     * @return
     */
    public static double getIntervalMonths(String openDate, String applyTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar firstDate = Calendar.getInstance();// 申请时间（后时间）
        Calendar secDate = Calendar.getInstance();// 前时间
        double months = 0;// 相隔多少月
        try {
            if (applyTime != null || !"".equals(applyTime)) {
                firstDate.setTime(df.parse(applyTime));
            }
            Date opDate = df.parse(openDate);
            secDate.setTime(opDate);
            long firstDateMill = firstDate.getTimeInMillis();
            long secDateMill = secDate.getTimeInMillis();
            long days = (firstDateMill - secDateMill) / (1000 * 60 * 60 * 24);// 相隔天数
            months = days / 30.0;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return months;
    }

    /**
     * 计算n月（天）前的时间
     * 
     * @param applyTime
     *            传入的时间如2015-05-05
     * @param i
     *            传入的月份(天)间隔数 6
     * @param flag
     *            DAY or MONTH
     * @return 返回2014-11-05
     * @author rongjianmin
     */
    public static String getDateFormat(String applyTime, int i, String flag) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String date = null;
        if (flag != null && flag.equals("DAY")) {
            try {
                c.setTime(df.parse(applyTime));
                c.set(Calendar.DATE, c.get(Calendar.DATE) - i);
                date = df.format(c.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag != null && flag.equals("MONTH")) {
            try {
                c.setTime(df.parse(applyTime));
                c.set(Calendar.MONTH, c.get(Calendar.MONTH) - i);
                date = df.format(c.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * BigDecimal null to 0
     * 
     * @author rongjianmin
     * @param newBalanceUSD
     * @return
     */
    public static BigDecimal bgNullTo0(BigDecimal bgvalue) {
        if (bgvalue == null || "".equals(bgvalue)) {
            bgvalue = new BigDecimal("0");
        }
        return bgvalue;
    }

    public static void main(String[] args) {
        double months = getIntervalMonths("2015-05-05", "2015-09-6");
        System.out.println(months);
    }

}
