package com.cana.crawler.dao.mongo.entity;

import com.github.jmkgreen.morphia.annotations.Entity;

/**
 * 联行号基础数据
 * 
 * @author renshui
 *
 */
@Entity("LianhanghaoBaseData")
public class LianhanghaoBaseData extends BaseMorphiaEntity {
	
	private static final long serialVersionUID = 1365951610151650459L;

	private String bankName;
	
	private String bankCode;
	
	private String province;
	
	private String provinceCode;
	
	private String city;
	
	private String cityCode;
	
	private boolean crawlComplete;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public boolean isCrawlComplete() {
		return crawlComplete;
	}

	public void setCrawlComplete(boolean crawlComplete) {
		this.crawlComplete = crawlComplete;
	}
	
}
