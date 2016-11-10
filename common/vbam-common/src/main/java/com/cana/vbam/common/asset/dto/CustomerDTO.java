/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cana.vbam.common.asset.enums.CustomerCityTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.customer.enums.CustomerMaterialSubmitState;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:16:28
 */
public class CustomerDTO implements Serializable {
	
	private static final long serialVersionUID = -4128706087893092893L;

	
	//客户股东
	private List<CustomerStkDTO> customerStks;
	
	//客户高管人员
	private List<CustomerMagDTO> customerMags;
	

	//客户采购
	private List<CustomerPurchaseDTO> customerPurchases;
	
	//客户销售
	private List<CustomerSaleDTO> customerSales;

	//客户融资
	private List<CustomerMasDTO> customerMass;
	
	
	/**
     *企业资料提交状态
     */
    private CustomerMaterialSubmitState enterpriseMaterialState;
    
    /**
     * 保理商ｉｄ
     */
    private String factorId;
    
    /**
     *客户ｉｄ
     */
    private String id;
    
    /**
     *客户名称			
     */
    private String customerName;

    /**
     *客户类型(融资申请人，交易对手，担保方)
     */
    private CustomerTypeEnum customerType;

    /**
     *联系人名字
     */
    private String contactName;

    /**
     *手机号码
     */
    private String mobileNo;

    /**
     *电子邮箱
     */
    private String mail;

    /**
     *公司地址
     */
    private String companyAddress;

    /**
     *经济类型
     */
    private EconomicCategoryEnum economicCategory;

    /**
     *行业
     */
    private IndustryTypeEnum industry;

    /**
     *所属地区
     */
    private CustomerCityTypeEnum city;
    

	/**
     *营业执照号码
     */
    private String businessLicenceCode;

    /**
     *营业执照有效日期
     */
    private Date businessLicenceCodeExpiryDate;

    /**
     *税务登记证号码
     */
    private String taxRegistrationCertificateCode;

    /**
     *税务登记证有效日期
     */
    private Date taxRegistrationCertificateCodeExpiryDate;

    /**
     *组织机构代码
     */
    private String organizationCode;

    /**
     *组织机构证有效日期
     */
    private Date organizationCodeExpiryDate;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *记录更新时间
     */
    private Date updateTime;
    
    
    /**
     *法定代表人
     */
    private String legalRepresentative;

    /**
     *注册资本
     */
    private BigDecimal registeredCapital;

   

    public List<CustomerStkDTO> getCustomerStks() {
		return customerStks;
	}

	public void setCustomerStks(List<CustomerStkDTO> customerStks) {
		this.customerStks = customerStks;
	}

	public List<CustomerMagDTO> getCustomerMags() {
		return customerMags;
	}

	public void setCustomerMags(List<CustomerMagDTO> customerMags) {
		this.customerMags = customerMags;
	}

	public List<CustomerPurchaseDTO> getCustomerPurchases() {
		return customerPurchases;
	}


	public List<CustomerSaleDTO> getCustomerSales() {
		return customerSales;
	}


	public List<CustomerMasDTO> getCustomerMass() {
		return customerMass;
	}

	public void setCustomerMass(List<CustomerMasDTO> customerMass) {
		this.customerMass = customerMass;
	}
    
    /**
     *客户ｉｄ
     */
    public String getId() {
        return id;
    }

    /**
     *客户ｉｄ
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *客户名称			

     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *客户名称			

     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     *客户类型(融资申请人，交易对手，担保方)
     */
    public CustomerTypeEnum getCustomerType() {
  		return customerType;
  	}

  	public void setCustomerType(CustomerTypeEnum customerType) {
  		this.customerType = customerType;
  	}
    /**
     *联系人名字
     */
    public String getContactName() {
        return contactName;
    }

  

	/**
     *联系人名字
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     *手机号码
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *手机号码
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    /**
     *电子邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     *电子邮箱
     */
    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    /**
     *公司地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     *公司地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    /**
     *经济类型
     */
    public EconomicCategoryEnum getEconomicCategory() {
		return economicCategory;
	}

	public void setEconomicCategory(EconomicCategoryEnum economicCategory) {
		this.economicCategory = economicCategory;
	}
    /**
     *行业
     */
	public IndustryTypeEnum getIndustry() {
		return industry;
	}

	public void setIndustry(IndustryTypeEnum industry) {
		this.industry = industry;
	}
    /**
     *所属地区
     */

    /**
     *营业执照号码
     */
    public String getBusinessLicenceCode() {
        return businessLicenceCode;
    }

    public CustomerCityTypeEnum getCity() {
		return city;
	}

	public void setCity(CustomerCityTypeEnum city) {
		this.city = city;
	}

	/**
     *营业执照号码
     */
    public void setBusinessLicenceCode(String businessLicenceCode) {
        this.businessLicenceCode = businessLicenceCode == null ? null : businessLicenceCode.trim();
    }

    /**
     *营业执照有效日期
     */
    public Date getBusinessLicenceCodeExpiryDate() {
        return businessLicenceCodeExpiryDate;
    }

    /**
     *营业执照有效日期
     */
    public void setBusinessLicenceCodeExpiryDate(Date businessLicenceCodeExpiryDate) {
        this.businessLicenceCodeExpiryDate = businessLicenceCodeExpiryDate;
    }

    /**
     *税务登记证号码
     */
    public String getTaxRegistrationCertificateCode() {
        return taxRegistrationCertificateCode;
    }

    /**
     *税务登记证号码
     */
    public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
        this.taxRegistrationCertificateCode = taxRegistrationCertificateCode == null ? null : taxRegistrationCertificateCode.trim();
    }

    /**
     *税务登记证有效日期
     */
    public Date getTaxRegistrationCertificateCodeExpiryDate() {
        return taxRegistrationCertificateCodeExpiryDate;
    }

    /**
     *税务登记证有效日期
     */
    public void setTaxRegistrationCertificateCodeExpiryDate(Date taxRegistrationCertificateCodeExpiryDate) {
        this.taxRegistrationCertificateCodeExpiryDate = taxRegistrationCertificateCodeExpiryDate;
    }

    /**
     *组织机构代码
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     *组织机构代码
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    /**
     *组织机构证有效日期
     */
    public Date getOrganizationCodeExpiryDate() {
        return organizationCodeExpiryDate;
    }

    /**
     *组织机构证有效日期
     */
    public void setOrganizationCodeExpiryDate(Date organizationCodeExpiryDate) {
        this.organizationCodeExpiryDate = organizationCodeExpiryDate;
    }

    /**
     *创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *记录更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *记录更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public CustomerMaterialSubmitState getEnterpriseMaterialState() {
		return enterpriseMaterialState;
	}

	public void setEnterpriseMaterialState(CustomerMaterialSubmitState enterpriseMaterialState) {
		this.enterpriseMaterialState = enterpriseMaterialState;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public void setCustomerPurchases(List<CustomerPurchaseDTO> customerPurchases) {
		this.customerPurchases = customerPurchases;
	}

	public void setCustomerSales(List<CustomerSaleDTO> customerSales) {
		this.customerSales = customerSales;
	}
	
}