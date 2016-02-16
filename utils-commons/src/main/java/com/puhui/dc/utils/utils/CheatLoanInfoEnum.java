package com.puhui.dc.utils.utils;

/**
 * Created by rongjianmin on 2015/7/21.
 */
public enum CheatLoanInfoEnum {
    /**
     * 电脑模拟器,移动刷卡器,鑫海融投资,提额,易贷技术,虚拟定位,宏泰投资,注册技术,融资技术,贷款,固话呼叫转移,蓝旋投资,乐富i刷,借贷,快速审核
     * ,快速审批,平安易贷,代发工资,企业邮箱挂靠,闪电借款,信贷实务,信威金融,信用卡攻略,信用卡申请攻略,信用卡内部资料,信用卡秘籍,信用卡申请经验
     * ,信用卡套即时,工资流水,工资证明,征信,信用卡技术,办理技术,电话代接,办理教程,办理转接,回访,学生白户,白户大学生,白户学生,支付套现
     */

    CHEAT_LOAN_INFO_ENUM(
            "电脑模拟器,移动刷卡器,鑫海融投资,提额,易贷技术,虚拟定位,宏泰投资,注册技术,融资技术,贷款,固话呼叫转移,蓝旋投资,乐富i刷,借贷,快速审核,快速审批,平安易贷,代发工资,企业邮箱挂靠,闪电借款,信贷实务,信威金融,信用卡攻略,信用卡申请攻略,信用卡内部资料,信用卡秘籍,信用卡申请经验,信用卡套即时,工资流水,工资证明,征信,信用卡技术,办理技术,电话代接,办理教程,办理转接,回访,学生白户,白户大学生,白户学生,支付套现");

    private final String value;

    CheatLoanInfoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
        String[] value = CheatLoanInfoEnum.CHEAT_LOAN_INFO_ENUM.getValue().split(",");
        System.out.println(value.toString());
    }
}
