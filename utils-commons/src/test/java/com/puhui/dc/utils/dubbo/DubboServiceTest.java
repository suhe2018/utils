package com.puhui.dc.utils.dubbo;

import com.puhui.blacklist.api.BlacklistAPIService;
import com.puhui.blacklist.api.vo.BlacklistDicInfo;
import com.puhui.dc.utils.confs.YamlConfigurable;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

//import com.puhui.rpc.model.dic.ProvinceAndCity;
//import com.puhui.rpc.service.dic.AnalyserService;

/**
 * Created by puhui on 2015/8/21.
 */
public class DubboServiceTest {

    @Test
    public void testGetService() throws Exception {
        DubboConf dubboConf = YamlConfigurable.getInstance("dubbo/analyser_service.yaml", DubboConf.class);
//        AnalyserService service = DubboService.getService(dubboConf, AnalyserService.class);
//        ProvinceAndCity provinceAndCity = service.analyseAddress("北京市朝阳区");
//        Assert.assertEquals("北京市", provinceAndCity.getCity());
//        Assert.assertEquals("北京市",provinceAndCity.getProvince());

        //黑名单服务
        BlacklistAPIService blacklistAPIService = DubboService.getService(YamlConfigurable.getInstance("dubbo/black_service.yaml", DubboConf.class), BlacklistAPIService.class);
        List<BlacklistDicInfo> blacklistDicInfos = blacklistAPIService.queryBlacklistKeyTypeInfo();
        Assert.assertTrue(blacklistDicInfos.size()>0);


    }
}