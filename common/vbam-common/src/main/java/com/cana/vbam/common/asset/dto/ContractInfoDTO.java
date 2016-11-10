package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * 合同信息
 * @author tangyh
 *
 */
public class ContractInfoDTO implements Serializable{

	private static final long serialVersionUID = 3912223398441456008L;
	
	/**
	 * 合同ID
	 */
	private String contractId;
	
	/**
	 * 产品ID
	 */
	private String productId;
	
	/**
	 * 企业Id
	 */
	private String memberId;
	
	/**
     *保理商ID
     */
    private String factorId;
    
	/**
	 * 合同的mediaId
	 */
	private String mediaId;
	
	/**
	 * 合同的文件名称
	 */
	private String fileName;
	
	/**
	 * 合同的后缀名（doc）
	 */
	private String fileSuffix;
	
	/**
     *监管账号
     */
    private String accountNo;

    /**
     *监管关系ID
     */
    private String accountSupervisionId;

    /**
     *合同生效日
     */
    private String effectiveDate;

    /**
     *合同到期日
     */    
    private String dueDate;
    
    //合同列表展示页面所需字段
    /**
     * 文件类型(比如："文本文档")
     */
	private String fileType;
	
	/**
	 * 合同创建时间
	 */
	private String createDate;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountSupervisionId() {
		return accountSupervisionId;
	}

	public void setAccountSupervisionId(String accountSupervisionId) {
		this.accountSupervisionId = accountSupervisionId;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
