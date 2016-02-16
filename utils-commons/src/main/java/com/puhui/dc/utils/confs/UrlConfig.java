package com.puhui.dc.utils.confs;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rongjianmin
 */
public class UrlConfig {

    private static Logger logger = LoggerFactory.getLogger(UrlConfig.class);

    private final static Properties prop = new Properties();

    static {
        try {
            InputStream in = UrlConfig.class.getClassLoader().getResourceAsStream(
                    URLDecoder.decode("dubbo/url.properties", "UTF-8"));
            prop.load(in);
            in.close();
        } catch (Exception e) {
            logger.error("加载配置资源文件失败!{}", e.getMessage());
        }
    }


    /**
     * 陆金所和浦发银行黑名单查询
     */
    public static final String URL_RISK_LJS_MATCHBLACK = prop.getProperty("url_risk_ljs_matchBlack");

    /**
     * 汇率信息接口
     */
    public static final String BANK_EXCHANGE_RATE_URL = prop.getProperty("bank_exchange_rate_url");
}
