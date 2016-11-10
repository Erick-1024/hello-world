package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * 合同查询条件
 * @author yihong.tang
 *
 */
public class ContractQueryCriteria implements Serializable {
    private static final long serialVersionUID = 7770029478823946110L;
    
    private String memberId;
    
    private String productId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
    
}
