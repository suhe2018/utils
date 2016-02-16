package com.puhui.dc.utils.date;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by puhui on 2015/8/14.
 */
public class BaseThreadLocalDateFormatTest {
    @Test
    public void testConvertStringToDate() throws Exception {
        Date date = new Date();
        String str = ConcurrentDateFormat.format(date, ConcurrentDateFormat.Templates.yyyyMMddHHmmssSSS);
        Date converted = ConcurrentDateFormat.parse(str, ConcurrentDateFormat.Templates.yyyyMMddHHmmssSSS);
        Assert.assertEquals(date, converted);
        String d = "2015-04-01 00:18:48";
        String expected = ConcurrentDateFormat.format(
                ConcurrentDateFormat.parse(d, ConcurrentDateFormat.Templates.yyyyMMddHHmmss),
                ConcurrentDateFormat.Templates.yyyyMMddHHmmss);
    }
    class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Test
    public void testOgnl() throws OgnlException {
        User user = new User();
        user.setName("张三");
        //相当于调用user.getUsername()方法
            String value = (String) Ognl.getValue("name", user);

        System.out.println(value);

    }

    @Test
    public void test1(){
        try{
            String date = "2015-07-03";
            Date d1 = ConcurrentDateFormat.dateModel(date);
            System.out.println(d1);
            Date d2 =  ConcurrentDateFormat.dateModel("2015-07-03 23:23:23");
            System.out.println(2);
            Date d3 =  ConcurrentDateFormat.dateModel("2015-07-03 23:23:23.123");
            System.out.println(d3);
            Date d4 =  ConcurrentDateFormat.dateModel(null);
            System.out.println(d4);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}