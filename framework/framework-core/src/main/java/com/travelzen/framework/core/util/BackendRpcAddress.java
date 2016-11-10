package com.travelzen.framework.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 服务端的rpc地址
 * 
 * @author renshui
 * 
 */
public class BackendRpcAddress {

	// 主机ip
	private String host;

	// 主机端口
	private int port;

	public BackendRpcAddress(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getHost() == null) ? 0 : getHost().hashCode());
		result = prime * result + getPort();
		return result;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		BackendRpcAddress other = (BackendRpcAddress) that;
		return (this.getHost() == null ? other.getHost() == null : this.getHost().equals(other.getHost()))
				&& (this.getPort() == other.getPort());
	}
	
	@Override
	public String toString(){
		return String.format("[host=%s, port=%s]", host, port);
	}
	
	/**
	 * 将host1:port1,host2:port2....格式的地址转化为标准地址
	 * @param rpcAddresses
	 * @return
	 */
	public static List<BackendRpcAddress> asList(String rpcAddresses){
		List<BackendRpcAddress> backendRpcAddressList = new ArrayList<>();
		if(StringUtils.isBlank(rpcAddresses))
			return backendRpcAddressList;
		String[] items = rpcAddresses.split(",");
		for(String item : items){
			item = StringUtils.trimToEmpty(item);
			String[] hostAndPort = item.split(":");
			if(hostAndPort.length == 2)
				backendRpcAddressList.add(new BackendRpcAddress(StringUtils.trimToEmpty(hostAndPort[0]), new Integer(hostAndPort[1])));
		}
		return backendRpcAddressList;
	}
}
