package com.travelzen.framwork.config.tops.bean;

public class ConfigBean {

	//messaging.mongodb.uri=mongodb://tz:tz@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/messaging, flight.mongodb.uri=mongodb://flight:flight@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/flight, jn.mongodb.uri=mongodb://tz:tz@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/jn, crawler.mongodb.uri=mongodb://tz:tz@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/crawler, hotel.mongodb.uri=mongodb://tz:tz@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/hotel, report.mongodb.uri=mongodb://tz:tz@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/report, additional.mongodb.uri=mongodb://additional:additional@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/additional, visa.mongodb.uri=mongodb://tz:tz@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/visa, core.mongodb.uri=mongodb://tz:tz@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/core, gta.mongodb.uri=mongodb://tz:tz@192.168.160.76:27017,192.168.160.77:27017,192.168.160.78:27017/multiprod}]
	private String messagingMongoDbUri;

	private String company;
	
	public String getMessagingMongoDbUri() {
		return messagingMongoDbUri;
	}

	public void setMessagingMongoDbUri(String messagingMongoDbUri) {
		this.messagingMongoDbUri = messagingMongoDbUri;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("=========")
					.append("messagingMongoDbUri:").append(this.messagingMongoDbUri)
					.append(",company:").append(this.company).toString();
	}
}
