package com.puhui.dc.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 线程安全的DateFormat
 * Created by duming on 2015/8/5.
 */
public class ConcurrentDateFormat {

    /**
     * 每个线程自有的转换
     */
    private static class BaseThreadLocalDateFormat extends ThreadLocal<DateFormat> {

        @Override
        public DateFormat get() {
            return super.get();
        }

        @Override
        public void remove() {
            super.remove();
        }

        @Override
        public void set(DateFormat value) {
            super.set(value);
        }
    }

    /**
     * 都有那些日期模板
     */
    public enum Templates {
        /**
         * yyyy-MM-dd HH:mm:ss.SSS
         */
        yyyyMMddHHmmssSSS(new BaseThreadLocalDateFormat() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            }
        },"[0-9]{4}(-[0-9]{2}){2} [0-9]{2}(:[0-9]{2}){2}\\.[0-9]{3}"),
        /**
         * yyyy-MM-dd HH:mm:ss
         */
        yyyyMMddHHmmss(new BaseThreadLocalDateFormat() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        },"[0-9]{4}(-[0-9]{2}){2} [0-9]{2}(:[0-9]{2}){2}"),
        /**
         * yyyy-MM-dd
         */
        yyyyMMdd(new BaseThreadLocalDateFormat() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd");
            }
        },"[0-9]{4}(-[0-9]{2}){2}");
        private ThreadLocal<DateFormat> threadLocal;
        private Pattern datePattern;

        Templates(ThreadLocal<DateFormat> threadLocal,String datePatternStr) {
            this.threadLocal = threadLocal;
            datePattern=Pattern.compile(datePatternStr);
        }
    }

    /**
     * string to date
     *
     * @param dateString
     * @param dateFormatTemplate
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateString, Templates dateFormatTemplate) throws ParseException {
        return dateFormatTemplate.threadLocal.get().parse(dateString);
    }

    /**
     * date to string
     *
     * @param date
     * @param dateFormatTemplate
     * @return
     */
    public static String format(Date date, Templates dateFormatTemplate) {
        return dateFormatTemplate.threadLocal.get().format(date);
    }

    /**
     * 日期模式匹配
     */
    public static Date dateModel(String dateStr)throws ParseException{
        if(null != dateStr){
            Matcher yyyyMMdd = Templates.yyyyMMdd.datePattern.matcher(dateStr);
            boolean yyyyMMddm =  yyyyMMdd.matches();
            if(yyyyMMddm){
                return  ConcurrentDateFormat.parse(dateStr,ConcurrentDateFormat.Templates.yyyyMMdd);
            }

            Matcher yyyyMMddHHmmss = Templates.yyyyMMddHHmmss.datePattern.matcher(dateStr);
            boolean yyyyMMddHHmmssm = yyyyMMddHHmmss.matches();
            if(yyyyMMddHHmmssm){
                return  ConcurrentDateFormat.parse(dateStr,ConcurrentDateFormat.Templates.yyyyMMddHHmmss);
            }

            Matcher yyyyMMddHHmmssSSS = Templates.yyyyMMddHHmmssSSS.datePattern.matcher(dateStr);
            boolean yyyyMMddHHmmssSSSm =  yyyyMMddHHmmssSSS.matches();
            if(yyyyMMddHHmmssSSSm){
                return  ConcurrentDateFormat.parse(dateStr,ConcurrentDateFormat.Templates.yyyyMMdd);
            }
        }
        return null;
    }
}
