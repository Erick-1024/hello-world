package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class BusinessGuaranteeInfoDTO implements Serializable{
	
	private static final long serialVersionUID = -2005418412325243392L;

	/**
     * 主键
     */
    private String id;

    /**
     * 业务资料Id
     */
    private String businessInfoId;

    /**
     * 保证合同号
     */
    private String guaranteedContractNo;

    /**
     * 担保方信息
     */
    private String guarantorInfo;

    /**
     * 担保类型
     */
    private String guaranteeType;
    
    /**
     * 资料顺序
     */
    private String sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}

	public String getGuaranteedContractNo() {
		return guaranteedContractNo;
	}

	public void setGuaranteedContractNo(String guaranteedContractNo) {
		this.guaranteedContractNo = guaranteedContractNo;
	}

	public String getGuarantorInfo() {
		return guarantorInfo;
	}

	public void setGuarantorInfo(String guarantorInfo) {
		this.guarantorInfo = guarantorInfo;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
    
}
