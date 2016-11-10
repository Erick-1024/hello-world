package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.asset.enums.CustomerCityTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:36:27
 */
public class CustomerListResponseDTO implements Serializable{
	
	private static final long serialVersionUID = -5604216296195191381L;

	//客户ｉｄ
	private String id;
	
    //客户名称			
    private String customerName;
    
    //客户类型(融资申请人，交易对手，担保方 @CustomerTypeEnum)
    private CustomerTypeEnum customerType;
    
    //客户类型描述
    private String customerTypeDesc;

    //行业
    private IndustryTypeEnum industry;
    
    //行业描述
    private String industryDesc;

    //所属地区
    private String cityDesc;
    
    
    private CustomerCityTypeEnum city;
    
    /**
     *企业资料提交状态
     */
    private String enterpriseMaterialState;

    //更新时间
    private Date updateTime;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public CustomerTypeEnum getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerTypeEnum customerType) {
		this.customerType = customerType;
	}

	public IndustryTypeEnum getIndustry() {
		return industry;
	}

	public void setIndustry(IndustryTypeEnum industry) {
		this.industry = industry;
	}

	public String getCityDesc() {
		return cityDesc;
	}

	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}

	public CustomerCityTypeEnum getCity() {
		return city;
	}

	public void setCity(CustomerCityTypeEnum city) {
		this.city = city;
	}

	public String getCustomerTypeDesc() {
		return customerTypeDesc;
	}

	public void setCustomerTypeDesc(String customerTypeDesc) {
		this.customerTypeDesc = customerTypeDesc;
	}

	public String getIndustryDesc() {
		return industryDesc;
	}

	

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEnterpriseMaterialState() {
		return enterpriseMaterialState;
	}

	public void setIndustryDesc(String industryDesc) {
		this.industryDesc = industryDesc;
	}

	public void setEnterpriseMaterialState(String enterpriseMaterialState) {
		this.enterpriseMaterialState = enterpriseMaterialState;
	}
	
    
}
