package com.travelzen.framework.monitor;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.util.TopsAppRegistry;

public class RequestIdentityHolder {

    private static Logger logger = LoggerFactory.getLogger(RequestIdentityHolder.class);
    private static ThreadLocal<CallInfo> holder = new ThreadLocal<>();
    private static Random RDM = new Random();
    public static String ip;
    public static String appName;

    static {
        ip = TopsAppRegistry.getLocalIP();
        appName = TopsAppRegistry.getApplicationName();
        appName = (appName != null ? appName : "NULL");
    }

    public static CallInfo get() {
        return holder.get();
    }

    public static void init() {
        CallInfo callInfo = new CallInfo();
        callInfo.setRpid(genReqId());
        callInfo.setIp(ip);
        callInfo.setProduct(appName);
        callInfo.setTime(new Date().getTime());
        set(callInfo);
    }

    /**
     * 此处设置只有一种情况，就是一个thrift请求进来之后，自己又起了一个线程来处理请求，即没有使用thrift的线程池。
     * <p/>
     * 特别说明：
     * <p/>
     * 如没有特别需求，别修改CallInfo中的信息，否者调用链信息不准确
     *
     * @param callinfo
     */
    public static void set(CallInfo callinfo) {
        holder.set(callinfo);
        org.slf4j.MDC.put("rpid", String.format("[rpid=%s]", callinfo.getRpid()));
    }

    public static void remove() {
        holder.remove();
        org.slf4j.MDC.remove("rpid");
    }

    /**
     * 向内读取数据时，将获取到的CallInfo设置到ThreadLocal
     *
     * @return
     */
    public static void setOnRead(CallInfo callInfo) {
        if (callInfo == null) {
            logger.warn("调用方缺失CallInfo信息");
        } else {
            List<String> ips = callInfo.getIps();
            List<String> products = callInfo.getProducts();
            List<Long> times = callInfo.getTimes();
            int index = 0;
            for (int i = 0; i < ips.size(); i++) {
                String ipTmp = ips.get(i);
                String productTmp = products.get(i);
                if (ip.equals(ipTmp) && appName.equals(productTmp)) {
                    index = i;
                }
            }
            if (index > 0) {
                for (int i = index; i < ips.size(); i++) {
                    ips.remove(i);
                    products.remove(i);
                    times.remove(i);
                }
            }
            callInfo.setIp(ip);
            callInfo.setProduct(appName);
            callInfo.setTime(new Date().getTime());
            set(callInfo);
        }
    }

    private static String genReqId() {
		return String.format("%08x", RDM.nextInt());
	}
}
