package com.cana.vbam.common.repayment.product;

import java.io.Serializable;

public class YundaexFinanceProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -703174952671578488L;
	
	// 产品id
	private String productId;
	// 产品名称
	private String productName;
	
	private String finaceRoleId;

	public String getFinaceRoleId() {
		return finaceRoleId;
	}

	public void setFinaceRoleId(String finaceRoleId) {
		this.finaceRoleId = finaceRoleId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}	

}
