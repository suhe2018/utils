package com.puhui.dc.utils.utils;

import java.util.List;

import com.puhui.dc.utils.confs.UrlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.puhui.blacklist.api.BlacklistAPIService;
import com.puhui.blacklist.api.common.ServiceResultType;
import com.puhui.blacklist.api.vo.BlacklistExactMatchInfoResult;
import com.puhui.blacklist.api.vo.BlacklistMatchInfo;
import com.puhui.blacklist.api.vo.BlacklistQueryInfo;
import com.puhui.blacklist.api.vo.ErrorCode;

/**
 * @ClassName: BlackListHolder
 * @Description: 黑名单控制类
 * @author rongjianmin
 * @date 2015年7月23日 下午3:50:12
 */
@Component
public class BlackListHolder {

    private static Logger logger = LoggerFactory.getLogger(BlackListHolder.class);

    private static String BLACK = "BLOCK";// 黑名单

    private static String GRAY = "GREY";// 灰名单

    private static String NEGATIVE = "FU_MIAN";// 负面信息

    private BlacklistAPIService blacklistAPIService;

    public BlacklistAPIService getBlacklistAPIService() {
        return blacklistAPIService;
    }

    public void setBlacklistAPIService(BlacklistAPIService blacklistAPIService) {
        this.blacklistAPIService = blacklistAPIService;
    }

    /**
     * @param blh
     * @Title: dubboMatchBlackAndGreylist
     * @Description: 查询黑名单(包含黑名单、灰名单)
     * @param param
     * @param isFM
     * @return
     * @author rongjianmin
     * @date 2015年7月10日
     * @throws
     */
    protected int dubboMatchBlackAndGreylist(List<BlacklistQueryInfo> param, boolean isFM, BlackListHolder blh) {
        return dubboBlacklist(param, isFM, blh) ? 1 : 0;
    }

    /**
     * @param blh
     * @Title: dubboMatchBlacklist
     * @Description: 查询黑名单(黑名单)
     * @param param
     * @param isFM
     * @return
     * @author rongjianmin
     * @date 2015年7月10日
     * @throws
     */
    protected int dubboMatchBlacklist(List<BlacklistQueryInfo> param, boolean isFM, BlackListHolder blh) {
        List<BlacklistMatchInfo> mathInfoList = dubboBlacklistMatchInfo(param, isFM, blh);
        if (mathInfoList == null) {
            return 0;
        }
        if (isFM) {
            return (isBLACK(mathInfoList) || isFU_MIAN(mathInfoList)) ? 1 : 0;
        }
        return isBLACK(mathInfoList) ? 1 : 0;
    }

    /**
     * @param blh
     * @Title: dubboMatchGreylist
     * @Description: 查询黑名单(灰名单)
     * @param param
     * @param isFM
     * @return
     * @author rongjianmin
     * @date 2015年7月10日
     * @throws
     */
    protected int dubboMatchGreylist(List<BlacklistQueryInfo> param, boolean isFM, BlackListHolder blh) {
        List<BlacklistMatchInfo> mathInfoList = dubboBlacklistMatchInfo(param, isFM, blh);
        if (mathInfoList == null) {
            return 0;
        }
        if (isFM) {
            return (isGREY(mathInfoList) || isFU_MIAN(mathInfoList)) ? 1 : 0;
        }
        return isGREY(mathInfoList) ? 1 : 0;
    }

    /**
     * @Title: httpClintBlacklistLJS
     * @Description: httpClient陆金所查询黑名单
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    protected int httpClintBlacklistLJS(String idNo, String mobile) {
        logger.info("调用陆金所和浦发银行黑名单查询接口 ,接口地址：{},接口参数 cardId={} 开始", UrlConfig.URL_RISK_LJS_MATCHBLACK, idNo);
        // 验证身份证号是否存在黑灰名单中
        HttpClientUtils hcljs = new HttpClientUtils(UrlConfig.URL_RISK_LJS_MATCHBLACK);
        hcljs.setParams("cardId", idNo);
        String idNoLjsResJSON = hcljs.doPost();
        logger.info("调用陆金所和浦发银行黑名单查询接口[身份证] ,接口地址：{},接口参数 cardId={} 三方接口返回结果{}", UrlConfig.URL_RISK_LJS_MATCHBLACK,
                idNo, idNoLjsResJSON);
        if (idNoLjsResJSON != null && idNoLjsResJSON.contains("true")) {// 身份证号匹配到黑名单
            logger.info("调用陆金所和浦发银行黑名单查询接口 ,接口地址：{},接口参数 idNo={} 结束：结果={}", UrlConfig.URL_RISK_LJS_MATCHBLACK, idNo, 1);
            return 1;
        }
//
//        logger.info("调用黑名单/灰名单查询接口 ,接口地址：{},接口参数 idNo={} mobile={} 结束：结果={}", UrlConfig.URL_RISK_MATCHBLACK, idNo,
//                mobile, 0);
        return 0;
    }

    /**
     * @param blh
     * @Title: dubboBlacklistMatchInfo
     * @Description: 调用dubbo服务查询黑名单(黑名单、灰名单)列表
     * @param param
     * @param isFM
     * @return
     * @author rongjianmin
     * @date 2015年7月10日 下午12:01:41
     * @throws
     */
    private List<BlacklistMatchInfo> dubboBlacklistMatchInfo(List<BlacklistQueryInfo> param, boolean isFM,
            BlackListHolder blh) {
        BlacklistExactMatchInfoResult result = blh.getBlacklistAPIService().matchBlacklist(param, isFM);
        ErrorCode ecId = result.getErrorCode();
        if (ServiceResultType.SUCCESS.equals(ecId.getResultType().SUCCESS)) {
            logger.info("调用dubbo服务黑名单查询接口成功！");
            return result.getBlacklistMatchInfo();
        }
        logger.info("调用dubbo服务黑名单查询接口失败，返回code={}", ecId.getReturnCode());
        return null;
    }

    /**
     * @param blh
     * @Title: dubboBlacklist
     * @Description: 调用dubbo服务查询是否在黑名单(黑名单、灰名单)中
     * @param param
     * @param isFM
     * @return
     * @author rongjianmin
     * @date 2015年7月10日
     * @throws
     */
    private boolean dubboBlacklist(List<BlacklistQueryInfo> param, boolean isFM, BlackListHolder blh) {
        BlacklistExactMatchInfoResult idResult = blh.getBlacklistAPIService().matchBlacklist(param, isFM);
        ErrorCode ecId = idResult.getErrorCode();
        if (!ServiceResultType.SUCCESS.equals(ecId.getResultType().SUCCESS)) {// 没有正常返回
            logger.info("调用dubbo服务黑名单查询接口失败，返回code={}", ecId.getReturnCode());
            return false;
        }
        logger.info("调用dubbo服务黑名单查询接口成功！");
        List<BlacklistMatchInfo> blacklistMatchInfoList = idResult.getBlacklistMatchInfo();
        if (null != blacklistMatchInfoList && blacklistMatchInfoList.size() > 0) {// 在黑名单
            return true;
        }
        return false;
    }

    /**
     * @Title: isBLACK
     * @Description: 判断是否包含黑名单
     * @param blackList
     * @return
     * @author rongjianmin
     * @date 2015年7月23日 下午4:17:15
     * @throws
     */
    private boolean isBLACK(List<BlacklistMatchInfo> blackList) {
        return isExisit(blackList, BLACK);
    }

    /**
     * @param
     * @Title: isGREY
     * @Description: 判断是否包含灰名单
     * @param blackList
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    private boolean isGREY(List<BlacklistMatchInfo> blackList) {
        return isExisit(blackList, GRAY);
    }

    /**
     * @Title: isFU_MIAN
     * @Description: 判断是否包含负面信息
     * @param blackList
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    private boolean isFU_MIAN(List<BlacklistMatchInfo> blackList) {
        return isExisit(blackList, NEGATIVE);
    }

    /**
     * @param
     * @Title: isExisit
     * @Description: 判断是否存在指定类型
     * @param matchInfo
     * @param type
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    private boolean isExisit(List<BlacklistMatchInfo> matchInfo, String type) {
        for (BlacklistMatchInfo info : matchInfo) {
            if (type.equals(info.getBlacklistCustomerInfo().getBlacklistType())) {
                return true;
            }
        }
        return false;
    }

}
