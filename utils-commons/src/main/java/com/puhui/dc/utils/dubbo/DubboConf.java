package com.puhui.dc.utils.dubbo;

/**
 * Created by puhui on 2015/8/21.
 */
public class DubboConf {
    /**
     * zookeeper地址
     */
    public String address;
    /**
     * application 名称
     */
    public String appName;
    /**
     * 使用者
     */
    public String appOwner;
    /**
     * 超时时间 毫秒
     */
    public int timeout;
    /**
     * 长连接数量
     */
    public int connections;
    /**
     * 传输协议dubbo
     */
    public String protocolName;
    /**
     * 表示为了防止同时过来大量连接而被干掉，限制最大为10000
     */
    public int accepts;
    /**
     *
     */
    public String monitorProtocol;

    @Override
    public String toString() {
        return "DubboConf{" +
                "address='" + address + '\'' +
                ", appName='" + appName + '\'' +
                ", appOwner='" + appOwner + '\'' +
                ", timeout=" + timeout +
                ", connections=" + connections +
                ", protocolName='" + protocolName + '\'' +
                ", accepts=" + accepts +
                ", monitorProtocol='" + monitorProtocol + '\'' +
                '}';
    }
}
