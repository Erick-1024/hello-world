package com.cana.crawler.dao.mongo.entity;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Index;
import com.github.jmkgreen.morphia.annotations.Indexes;

/**
 * 联行号完整数据
 * 
 * @author renshui
 *
 */
@Entity("LianhanghaoCompleteData")
@Indexes(@Index(value="lianhanghao", unique=true))
public class LianhanghaoCompleteData extends BaseMorphiaEntity {
	
	private static final long serialVersionUID = 1365951610151650459L;

	private String bankName;
	
	private String bankCode;
	
	private String province;
	
	private String provinceCode;
	
	private String city;
	
	private String cityCode;
	
	private String lianhanghao;
	
	private String branchName;

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

	public String getLianhanghao() {
		return lianhanghao;
	}

	public void setLianhanghao(String lianhanghao) {
		this.lianhanghao = lianhanghao;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
}
