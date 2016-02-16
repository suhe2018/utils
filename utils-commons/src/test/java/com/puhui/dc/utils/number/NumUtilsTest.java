package com.puhui.dc.utils.number;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by puhui on 2015/8/21.
 */
public class NumUtilsTest {

    @Test
    public void testExtractNumberFromStr() throws Exception {
        Assert.assertEquals("1358190123000024", NumUtils.extractNumberFromStr("拨打1358190123转000024"));
    }

    @Test
    public void testComputeCommunicationDuration() throws Exception {
        Assert.assertEquals(409, NumUtils.computeDuration("6分49秒"));
        Assert.assertEquals(49, NumUtils.computeDuration("49秒"));
        Assert.assertEquals(4009, NumUtils.computeDuration("1时6分49秒"));
        Assert.assertEquals(3649, NumUtils.computeDuration("1时49秒"));
        Assert.assertEquals(360, NumUtils.computeDuration("6分"));
        Assert.assertEquals(360, NumUtils.computeDuration("6分0秒"));
    }
}