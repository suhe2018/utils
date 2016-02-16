package com.puhui.dc.utils.utils;




import com.puhui.blacklist.api.common.BlacklistQueryFieldType;
import com.puhui.blacklist.api.vo.BlacklistQueryInfo;

import java.util.ArrayList;
import java.util.List;



/**
 * @ClassName: BlackListHolder
 * @Description: 黑名单控制类
 * @author rongjianmin
 * @date 2015年7月23日
 */
public class BlackListProxy extends BlackListHolder {

    /**
     * @param blh
     * @Title: matchBlackAndGrayListByIdAndMobile
     * @Description: 通过身份证号、手机号查询黑名单灰名单(其中包括调用陆金所查询黑名单)
     * @param idNo
     * @param mobile
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    public int matchBlackAndGrayListByIdAndMobile(String idNo, String mobile, BlackListHolder blh) {
        List<BlacklistQueryInfo> param = new ArrayList<BlacklistQueryInfo>();
        BlacklistQueryInfo qId = new BlacklistQueryInfo();
        qId.setQueryField(BlacklistQueryFieldType.ID_NO);
        qId.setQueryValue(idNo);
        param.add(qId);
        BlacklistQueryInfo qMobile = new BlacklistQueryInfo();
        qMobile.setQueryField(BlacklistQueryFieldType.MOBILE);
        qMobile.setQueryValue(mobile);
        param.add(qMobile);
        return this.dubboMatchBlackAndGreylist(param, false, blh) == 1 ? 1 : this.httpClintBlacklistLJS(idNo, mobile);
    }

    /**
     * @Title: matchBlackListByMobile
     * @Description: 通过手机号查询黑名单
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    public int matchBlackListByMobile(String mobile, BlackListHolder blh) {
        List<BlacklistQueryInfo> param = new ArrayList<BlacklistQueryInfo>();
        BlacklistQueryInfo query = new BlacklistQueryInfo();
        query.setQueryField(BlacklistQueryFieldType.MOBILE);
        query.setQueryValue(mobile);
        param.add(query);
        return this.dubboMatchBlacklist(param, false, blh);
    }

    /**
     * @Title: matchGreyAndFmListByMobile
     * @Description: 通过手机号查询灰名单负面信息库
     * @param mobile
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    public int matchGreyAndFmListByMobile(String mobile, BlackListHolder blh) {
        List<BlacklistQueryInfo> param = new ArrayList<BlacklistQueryInfo>();
        BlacklistQueryInfo query = new BlacklistQueryInfo();
        query.setQueryField(BlacklistQueryFieldType.MOBILE);
        query.setQueryValue(mobile);
        param.add(query);
        return this.dubboMatchGreylist(param, true, blh);
    }

    /**
     * @Title: matchBlackAndGreyAndFmListByMobile
     * @Description: 通过手机号查询黑名单灰名单负面信息库
     * @param mobile
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    public int matchBlackAndGreyAndFmList(String mobile, BlackListHolder blh, BlacklistQueryFieldType type) {
        List<BlacklistQueryInfo> param = new ArrayList<BlacklistQueryInfo>();
        BlacklistQueryInfo query = new BlacklistQueryInfo();
        query.setQueryField(type);
        query.setQueryValue(mobile);
        param.add(query);
        return this.dubboMatchBlackAndGreylist(param, true, blh);
    }
    /**
     * @Title: matchBlackAndGreyAndFmListByMobile
     * @Description: 通过手机号查询黑名单灰名单负面信息库
     * @param mobile
     * @return
     * @author rongjianmin
     * @date 2015年7月23日
     * @throws
     */
    public int matchBlackAndGreyAndPmList(String mobile, BlackListHolder blh, List<BlacklistQueryFieldType> list) {
        List<BlacklistQueryInfo> param = new ArrayList<BlacklistQueryInfo>();

        for (BlacklistQueryFieldType BlacklistQueryFieldType :list){
            BlacklistQueryInfo query = new BlacklistQueryInfo();

            query.setQueryField(BlacklistQueryFieldType);
            query.setQueryValue(mobile);
            param.add(query);
        }
        return this.dubboMatchBlackAndGreylist(param, true, blh);
    }

}
