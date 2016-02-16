package com.puhui.dc.utils.number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据类型帮助类
 * Created by puhui on 2015/8/21.
 */
public class NumUtils {
    private static final Pattern NotNumberChar = Pattern.compile("[\\D]+");

    /**
     * 将字符串的数字都提取出来
     *
     * @param input 输入的字符串
     * @return 输出的只包含数字的字符串
     */
    public static String extractNumberFromStr(String input) {
        if (input == null) {
            return null;
        }
        return NotNumberChar.matcher(input).replaceAll("");
    }

    private static final Pattern ExtractSecond=Pattern.compile("(([\\d]+)时)?(([\\d]+)分)?(([\\d]+)秒)?");

    /**
     * 计算通话时间到秒
     * @param input 输入的通话记录, 例如:1小时6分49秒
     * @return 以秒计, 例如:4009
     */
    public static int computeDuration(String input) {
        if (input == null) {
            return 0;
        }
        Matcher matcher=ExtractSecond.matcher(input);
        int result=0;
        if(matcher.find()){
            if(matcher.group(2)!=null){
                String hours=matcher.group(2);
                result+=Integer.parseInt(hours)*3600;
            }
            if(matcher.group(4)!=null){
                String hours=matcher.group(4);
                result+=Integer.parseInt(hours)*60;
            }
            if(matcher.group(6)!=null){
                String hours=matcher.group(6);
                result+=Integer.parseInt(hours);
            }
        }else {
            throw  new RuntimeException("error happened when computeDuration, pattern not match :"+input);
        }
        return result;
    }
}
