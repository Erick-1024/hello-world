package com.travelzen.mongo.vo;

public class AccesserInfo {

	public final String userKey;
	public final String ip;

	public AccesserInfo(String userKey, String ip) {
		this.userKey = userKey;
		this.ip = ip;
	}

}