package com.cana.vbam.common.crawler.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.crawler.enums.CourtExecutionSubject;

/**
 * 查询失信执行请求
 * 
 * @author renshui
 *
 */
public class GetShixinCourtRequest implements Serializable {

	private static final long serialVersionUID = 2612881118401753729L;
	
	// 被执行人主体类型：自然人,组织
	private CourtExecutionSubject subject;
	// 姓名或者组织名称
	private String name;
	// 身份证号或者组织结构代码
	private String code;
	// 如果爬取时间在此日期之后，使用缓存，否则重新爬取。此值为null也需要重新爬取。
	private Date cacheDate;
	
	public CourtExecutionSubject getSubject() {
		return subject;
	}
	public void setSubject(CourtExecutionSubject subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCacheDate() {
		return cacheDate;
	}
	public void setCacheDate(Date cacheDate) {
		this.cacheDate = cacheDate;
	}

}
