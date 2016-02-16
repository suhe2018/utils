package com.puhui.dc.utils.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VerifyTools {
    public static final int MONTHS = 4;

    // 默认触碰编码
    public static final String DEFAULT_FINGER_ON_CODE = "-99999";

    // 默认非触碰编码
    public static final String DEFAULT_NO_FINGER_ON_CODE = "00000";
    // 消费描述需屏蔽字符串
    public static final String TRANS_DESC_STR = "滞纳金,循环利息,还款,转入,清算,短信宝,分期付款,跨行,快捷支付,银联";
    // 滞纳金计算需排除的字段
    public static final String TRANS_DESC_ZNJ = "滞纳金撤销,滞纳金冲销,滞纳金返还,冲减滞纳金,滞纳金返还,滞纳金免除,滞纳金调整 ";
    // 日期格式到天
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
    // 日期格式到秒
    public static final String DATE_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";
    //游戏类商品
    public static final String GAME_PRICE = "纵游一卡通,自动战争,诛仙,中青宝网一卡通,战网一卡通,游戏银子,游龙一卡通,悠游一卡通,英雄联盟,银丰会所,迅游一天卡,晓游棋牌,晓游积分,戏杰一卡通,问道,网易一卡通,万游一卡通,完美一卡通,完美点券,天下通一卡通,天希一卡通,天天炫斗,天天酷跑,天天富翁,天成一卡通,坦克世界,搜狐一卡通,数通一卡通,盛科一卡通,盛付通一卡通,神魔大陆,三国争霸,润趣一卡通,热血传奇,全民飞机大战,起凡一卡通,麒麟一卡通,棋牌游戏,跑跑卡丁车,魔域,魔兽,魔骑士游戏,魔币,梦三国,梦幻西游,冒险岛,萝卜一卡通,老k棋牌,癞子山庄,昆仑一卡通,快乐圈开心币,快乐圈道具卡,空中网一卡通,久游一卡通,久游休闲卡,久游休闲币,九合一卡通,劲舞,金山一卡通,街头篮球,将军令,欢乐|豆,很好记一卡通,复活币礼包,反恐精英,电魂一卡通,点卷,地下城与勇士,代练,穿越火线,捕鱼,冰川一卡通,DOTA,DNF,4399一卡通,游戏币,征途,赤壁,骏网,盛大,天宏,欢乐豆,金游世界,金游银子,游戏在线,电玩金币,西游充值,游戏茶苑,71豆广告联盟,咕果广告卡";
    //骗贷类软件
    public static final String PIANDAI_SOFTWARE = "电脑模拟器,移动刷卡器,鑫海融投资,提额,易贷技术,虚拟定位,宏泰投资,注册技术,融资技术,贷款,固话呼叫转移,蓝旋投资,乐富i刷,借贷,快速审核,快速审批,平安易贷,代发工资,企业邮箱挂靠,闪电借款,信贷实务,信威金融,信用卡攻略,信用卡申请攻略,信用卡内部资料,信用卡秘籍,信用卡申请经验,信用卡套即时,工资流水,工资证明,征信,信用卡技术,办理技术,电话代接,办理教程,办理转接,回访,白户";

    public static boolean egameprice(String game) {
        String a[] = GAME_PRICE.split(",");
        for (int i = 0; i < a.length; i++) {
            if (game==a[i] && game.equals(a[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean pdsoftware(String software) {
        String a[] = PIANDAI_SOFTWARE.split(",");
        for (int i = 0; i < a.length; i++) {
            if (software==a[i] && software.equals(a[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean listIsNotNull(List list) {
        if (null != list && list.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean mapIsNotNull(Map map) {
        if (null != map && map.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * BigDecimal null to 0
     *
     * @param newBalanceUSD
     * @return
     * @author rongjianmin
     */
    public static BigDecimal bgNullTo0(BigDecimal bgvalue) {
        if (bgvalue == null || "".equals(bgvalue)) {
            bgvalue = new BigDecimal("0");
        }
        return bgvalue;
    }

    /**
     * @param str  要检查的字符串
     * @param mate 需要匹配的字符(以,分割)
     * @return
     */
    public static boolean isContainStr(String str, String mate) {

        boolean flag = false;// 不包含
        if (!StringUtils.isNull(str) && !StringUtils.isNull(mate)) {
            String[] array = mate.split(",");
            for (String s : array) {
                if (str.contains(s)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 检查时间对应的小时是否在对应范围内
     *
     * @param hour      时间对应的小时
     * @param begin,end 查看hour是否在 begin与end之间
     * @param boundary  比较范围时，比较运算符是用"1":">=,<=" 还是用"0":">,<"进行计算
     * @return
     */
    public static boolean isInRange(int hour, int begin, int end, int boundary) {

        boolean flag = false;//不属于范围
        if (1 == boundary) {
            if (hour >= begin && hour <= end) {
                flag = true;
            }
        } else if (0 == boundary) {
            if (hour > begin && hour < end) {
                flag = true;
            }
        }
        return flag;
    }


    public static String formateDate(Date date) {
        String datesdf = null;
        if (null != date) {
            datesdf = new SimpleDateFormat(DATE_FORMAT_DAY).format(date);
        }
        return datesdf;
    }
}
