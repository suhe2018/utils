package com.puhui.dc.utils.utils;

/**
 * Created by rongjianmin on 2015/7/21.
 */
public enum NegtiveInfoEnum {

    /**
     * 逾期，催收，欠债，法院，拖欠，欺诈，起诉，诉讼 ，失信等
     */

    NEGATIVE_INFO("逾期,催收,欠债,法院,拖欠,欺诈,起诉,诉讼 ,失信");

    private final String value;

    NegtiveInfoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
