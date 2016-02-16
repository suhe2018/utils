package com.puhui.dc.utils.utils;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 资源文件管理 
 * 
 * @author yangyl
 * @version 1.0, 2015-6-26 上午11:04:08
 * 
 */
@SuppressWarnings("deprecation")
public class PropertiesUtil {
	
	
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	
	private static Properties pros= new Properties();
	//用静态块来加载一次配置文件
	/*static
	{
		// getResourceAsStream():会按classpath
		// 搜索文件,返回一个InputStream	
		InputStream in = null;
		try {
			in=PropertiesUtil.class.getClassLoader().getResourceAsStream("test/taobao.yaml");
			// 加载属性文件
			pros.load(in);
		} catch (IOException e) {
			logger.error("加载属性文件出错：", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				logger.error("InputStream close error ，error infomation :", e);
			}
		}

	}*/
	
	/**
	 * 初始化配置文件信息
	 */
	public static void initConfig(String configFile){
		
	    InputStream in = null;
        try {
            in=PropertiesUtil.class.getClassLoader().getResourceAsStream(configFile);
			 pros.load(in);
		} catch (IOException e) {
            logger.error("加载属性文件出错：", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("InputStream close error ，error infomation :", e);
            }
        }

	}
	
	
	/**
	 * 根据key获取属性值
	 * @param key 对应的key
	 * @return 返回String类型的value
	 */
	public static String getStringProperty(String key){
		return pros.getProperty(key);
	}
	/**
	 * 根据key获取属性值
	 * @param key 对应的key
	 * @return 返回int类型的value
	 */
	public static int getIntProperty(String key){
		int value=0;
		try
		{
			value=Integer.parseInt(pros.getProperty(key));
		}catch (ClassCastException e) {
			logger.error("类型转换错误!");
		}catch (Exception e) {
			logger.error("PropertiesUtil.getIntProperty is error ,error information :",e);
		}
		return value;
	}
	
	/**
	 * 根据key获取属性值
	 * @param key 对应的key
	 * @return 返回Long类型的value
	 */
	public static long getLongProperty(String key){
		long value=0;
		try
		{
			value=Long.parseLong(pros.getProperty(key));
		}catch (ClassCastException e) {
			logger.error("类型转换错误!");
		}catch (Exception e) {
			logger.error("PropertiesUtil.getLongProperty is error ,error information :",e);
		}
		return value;
	}
	
	/**
	 * 根据key获取属性值
	 * @param key 对应的key
	 * @return 返回Boolean类型的value
	 */
	public static boolean getBoolProperty(String key){
		boolean bool=false;
		try
		{
			bool=Boolean.parseBoolean(pros.getProperty(key));
		}catch (ClassCastException e) {
			logger.error("类型转换错误!");
		}catch (Exception e) {
			logger.error("PropertiesUtil.getBoolProperty is error ,error information :",e);
		}
		return bool;
	}
	/**
     * 根据key获取属性值
     * @param key 对应的key
     * @return 返回Boolean类型的value
     */
	public static Map getPropertyMap(Map propertyMap){
        Map<String, String> map = new HashMap<String, String>(); 
       
          
            propertyMap.keySet().size();
            Set<String> set = propertyMap.keySet();   
            for (String key:set) { 
                
                map.put(key,pros.getProperty(key));
            } 
            return map;
      
    }
    public static Map getAllProperty(){  
        Map map=new HashMap();  
        Enumeration enu = pros.propertyNames();  
        while (enu.hasMoreElements()) {  
            String key = (String) enu.nextElement();  
            String value = pros.getProperty(key);  
            map.put(key, value);  
        }  
        return map;  
    }  
    /** 
     * 在控制台上打印出所有属性，调试时用。 
     */  
    public static void printProperties(){  
        pros.list(System.out);  
    }  
    /** 
     * 写入properties信息 
     */  
    public static void writeProperties(String key, String value,String fileName) {  
        try {  
            OutputStream fos = new FileOutputStream(fileName);  
            pros.setProperty(key, value);  
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流  
            pros.store(fos, "『comments』Update key：" + key);  
        } catch (IOException e) {  
        }  
    } 
	public static void main(String[] args) {
	    

	    PropertiesUtil.initConfig("ognl/basic/alipay.yaml");
        Map<String,String> map = PropertiesUtil.getAllProperty();
        PropertiesUtil.printProperties();
        System.out.println(map);
	}
	
	
}

