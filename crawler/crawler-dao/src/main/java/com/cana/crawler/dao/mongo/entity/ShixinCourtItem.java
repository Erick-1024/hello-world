package com.cana.crawler.dao.mongo.entity;

import java.io.Serializable;

public class ShixinCourtItem implements Serializable{

	private static final long serialVersionUID = 9021728396475542079L;
	
	//执行主体。自然人或者组织
	private String name;
	//执行法院
	private String courtName;
	//执行依据文号
	private String gistId;
	//立案时间,格式： yyyy-MM-dd
	private String regDate;
	//案号
	private String caseCode;
	//被执行人的履行情况
	private String performance;
	//失信被执行人行为具体情形
	private String disruptTypeName;
	//生效法律文书确定的义务
	private String duty;
	//发布时间,格式： yyyy-MM-dd
	private String publishDate;
    //原始文本
	private String rawText;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getGistId() {
		return gistId;
	}
	public void setGistId(String gistId) {
		this.gistId = gistId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getDisruptTypeName() {
		return disruptTypeName;
	}
	public void setDisruptTypeName(String disruptTypeName) {
		this.disruptTypeName = disruptTypeName;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getRawText() {
		return rawText;
	}
	public void setRawText(String rawText) {
		this.rawText = rawText;
	}
	

}
