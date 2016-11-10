package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;

public class UnderlyingAssetQueryDTO implements Serializable{
	
	private static final long serialVersionUID = -6871574753471046328L;

	/**
	 * 放款编号
	 */
	private String loanNo;
	
	/**
	 * 业务合同号
	 */
	private String businessContractNo;
	
	/**
	 * 借款人名称
	 */
	private String customerName;
	
	/**
	 * 起息日(开始)
	 */
	private String loanDateStart;
	
	/**
	 * 起息日(结束)
	 */
	private String loanDateEnd;
	
	/**
	 * 到期日(开始)
	 */
	private String dueDateStart;
	
	/**
	 * 到期日(结束)
	 */
	private String dueDateEnd;
	
    /**
     * 专项计划编号
     */
    private String specialProgramId;
    
    /**
     *资产池状态，未入池，备选池，入池
     */
    private UnderlyingAssetPoolStatus assetPoolStatus;
	
	/**
	 * 是否加载额外数据
	 */
	private Boolean loadExtraData = Boolean.FALSE;
	
	/**
	 * 运算参数
	 */
	private int andOperationNum;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 运算结果
	 */
	private int resultNum;
	
	private int page;
	
	private int pageSize;

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLoanDateStart() {
		return loanDateStart;
	}

	public void setLoanDateStart(String loanDateStart) {
		this.loanDateStart = loanDateStart;
	}

	public String getLoanDateEnd() {
		return loanDateEnd;
	}

	public void setLoanDateEnd(String loanDateEnd) {
		this.loanDateEnd = loanDateEnd;
	}

	public String getDueDateStart() {
		return dueDateStart;
	}

	public void setDueDateStart(String dueDateStart) {
		this.dueDateStart = dueDateStart;
	}

	public String getDueDateEnd() {
		return dueDateEnd;
	}

	public void setDueDateEnd(String dueDateEnd) {
		this.dueDateEnd = dueDateEnd;
	}

	public Boolean getLoadExtraData() {
		return loadExtraData;
	}

	public void setLoadExtraData(Boolean loadExtraData) {
		this.loadExtraData = loadExtraData;
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

	public String getSpecialProgramId() {
		return specialProgramId;
	}

	public void setSpecialProgramId(String specialProgramId) {
		this.specialProgramId = specialProgramId;
	}

	public UnderlyingAssetPoolStatus getAssetPoolStatus() {
		return assetPoolStatus;
	}

	public void setAssetPoolStatus(UnderlyingAssetPoolStatus assetPoolStatus) {
		this.assetPoolStatus = assetPoolStatus;
	}

	public int getAndOperationNum() {
		return andOperationNum;
	}

	public void setAndOperationNum(int andOperationNum) {
		this.andOperationNum = andOperationNum;
	}

	public int getResultNum() {
		return resultNum;
	}

	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
