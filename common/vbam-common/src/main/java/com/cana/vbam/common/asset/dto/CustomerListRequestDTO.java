package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.asset.enums.CustomerTypeEnum;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:37:24
 */
public class CustomerListRequestDTO implements Serializable{
	
	
	private static final long serialVersionUID = -8521238657696895930L;

	//分页参数
	private int page;
	
	private int pageSize;
	
	//客户名称			
    private String customerName;
    
    //保理商ｉｄ
    private String  factorId;
    
    //用户ｉｄ
    private String userId;
    
    /**
     *企业资料提交状态
     */
    private List<String> enterpriseMaterialState;
   


	//客户类型(融资申请人，交易对手，担保方 @CustomerTypeEnum)
    private CustomerTypeEnum customerType;

	public CustomerTypeEnum getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerTypeEnum customerType) {
		this.customerType = customerType;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getEnterpriseMaterialState() {
		return enterpriseMaterialState;
	}

	public void setEnterpriseMaterialState(List<String> enterpriseMaterialState) {
		this.enterpriseMaterialState = enterpriseMaterialState;
	}


	
}
