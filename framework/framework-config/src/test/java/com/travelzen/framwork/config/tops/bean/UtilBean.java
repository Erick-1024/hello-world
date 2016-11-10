package com.travelzen.framwork.config.tops.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UtilBean {


	@Value("#{mongoProp.xxx}")
	private String mongoUri;

	@Override
	public String toString() {
		return new StringBuilder("=========")
					.append("mongoUri:").append(this.mongoUri).toString();
	}
}
