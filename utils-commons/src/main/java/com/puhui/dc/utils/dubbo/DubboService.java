package com.puhui.dc.utils.dubbo;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.container.log4j.Log4jContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by puhui on 2015/8/21.
 */
public class DubboService {
    private static final Logger LOG = LoggerFactory.getLogger(DubboService.class);

    /**
     * 获取dubbo服务
     * @param dubboConf dubbo服务的配置文件
     * @param clazz 服务接口
     * @param <T> 动态的服务接口
     * @return
     */
    public static <T> T getService(DubboConf dubboConf, Class<T> clazz) {
        ApplicationConfig application = new ApplicationConfig();

       // MonitorConfig monitor = new MonitorConfig();

        //monitor.setProtocol(dubboConf.monitorProtocol);

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName(dubboConf.protocolName);
        protocol.setAccepts(dubboConf.accepts);

        Log4jContainer log4jContainer = new Log4jContainer();
        application.setName(dubboConf.appName);
        application.setOwner(dubboConf.appOwner);

        RegistryConfig registry = new RegistryConfig();
        // 该类很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        registry.setAddress(dubboConf.address);
        registry.setTimeout(dubboConf.timeout);
        ReferenceConfig<T> reference = new ReferenceConfig<T>();
        reference.setTimeout(dubboConf.timeout);
        reference.setConnections(dubboConf.connections);
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
//        reference.setMonitor(monitor);
        reference.setInterface(clazz);

        return reference.get();
    }
}
