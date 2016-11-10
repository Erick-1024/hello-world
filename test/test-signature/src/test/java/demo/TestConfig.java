package demo;

import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

public class TestConfig {
    // 连接超时时间 毫秒
    public static final int connectTimeout = 3000;
    // 读取超时时间 毫秒
    public static final int readTimeout = 30000;
    // URL（http、https方式）
    public static final String url = "http://192.168.1.13:8445/raWeb/CSHttpServlet";
    // 服务器ip（socket、ssl socket方式）
    public static final String ip = "192.168.1.13";
    // 服务器端口（socket、ssl socket方式）
    public static final int port = 9040;

    // 通信证书配置
    public static final String keyStorePath = "config/yanzhengT.jks";
    public static final String keyStorePassword = "111111";
    // 信任证书链配置
    public static final String trustStorePath = "config/yanzhengT.jks";
    public static final String trustStorePassword = "Abcd1234";

    // 客户端与RA之间为短链接
    // 该方法仅作为demo示例，使用时直接创建RAClient对象即可
    // 连接参数不变时，多次调用可使用同一RAClient对象，无需重新创建
    public static RAClient getRAClient() throws RATKException {
        int type = 3;
        RAClient client = null;
        switch (type) {
        case 1:
            // 初始化为http连接方式，指定url
            client = new RAClient(url, connectTimeout, readTimeout);
            break;
        case 2:
            // 初始化为https连接方式，指定url，另需配置ssl的证书及信任证书链
            client = new RAClient(url, connectTimeout, readTimeout);
            client.initSSL(keyStorePath, keyStorePassword, trustStorePath, trustStorePassword);
            // 如需指定ssl协议、算法、证书库类型，使用如下方式
            // client.initSSL(keyStorePath, keyStorePassword, trustStorePath, trustStorePassword, "SSL", "IbmX509", "IbmX509", "JKS", "JKS");
            break;
        case 3:
            // 初始化为socket 连接方式，指定ip和端口
            client = new RAClient(ip, port, connectTimeout, readTimeout);
            break;
        case 4:
            // 初始化为ssl socket 连接方式，指定ip和端口，另需配置ssl的证书及信任证书链
            client = new RAClient(ip, port, connectTimeout, readTimeout);
            client.initSSL(keyStorePath, keyStorePassword, trustStorePath, trustStorePassword);
            // 如需指定ssl协议、算法、证书库类型，使用如下方式
            // client.initSSL(keyStorePath, keyStorePassword, trustStorePath, trustStorePassword, "SSL", "IbmX509", "IbmX509", "JKS", "JKS");
            break;
        default:
            break;
        }
        return client;
    }
}
