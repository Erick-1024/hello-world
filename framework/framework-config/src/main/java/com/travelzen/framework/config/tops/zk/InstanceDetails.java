package com.travelzen.framework.config.tops.zk;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("details")
public class InstanceDetails {
	private String rpcUrl;
	private String description;
	
	public InstanceDetails() {
	}

	public InstanceDetails(String rpcUrl) {
		this.rpcUrl = rpcUrl;
	}

	public String getRpcUrl() {
		return rpcUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRpcUrl(String rpcUrl) {
		this.rpcUrl = rpcUrl;
	}

}
