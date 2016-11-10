package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.InputMethod;

public class LoanInfoRedisIntegration implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7327338849775047910L;

	/**
	 * 成功通过校验的放款信息
	 */
	private List<LoanInfoRedisDTO> successedLoanInfoRedisDTOs;
	
	/**
	 * 未通过校验的放款信息
	 */
	private List<LoanInfoRedisDTO> failedLoanInfoRedisDTOs;
	
    /**
    *业务模式（保理商+融资商等）
    */
    private BusinessMode businessMode;

    /**
    *录入方式（excel、手动）
    */
    private InputMethod inputMethod;
    
    public LoanInfoRedisIntegration() {
    	successedLoanInfoRedisDTOs = new ArrayList<LoanInfoRedisDTO>();
    	failedLoanInfoRedisDTOs = new ArrayList<LoanInfoRedisDTO>();
	}

	public List<LoanInfoRedisDTO> getSuccessedLoanInfoRedisDTOs() {
		return successedLoanInfoRedisDTOs;
	}

	public void setSuccessedLoanInfoRedisDTOs(List<LoanInfoRedisDTO> successedLoanInfoRedisDTOs) {
		this.successedLoanInfoRedisDTOs = successedLoanInfoRedisDTOs;
	}
	
	public void addSuccessedLoanInfoRedisDTO(LoanInfoRedisDTO successedLoanInfoRedisDTO) {
		if(null == this.successedLoanInfoRedisDTOs){
			this.successedLoanInfoRedisDTOs = new ArrayList<LoanInfoRedisDTO>();
			this.successedLoanInfoRedisDTOs.add(successedLoanInfoRedisDTO);
		}else {
			this.successedLoanInfoRedisDTOs.add(successedLoanInfoRedisDTO);
		}
	}
	
	public void modifySuccessedLoanInfoRedisDTO(LoanInfoRedisDTO successedLoanInfoRedisDTO) {
		if(null != this.successedLoanInfoRedisDTOs){
			int index = this.successedLoanInfoRedisDTOs.indexOf(successedLoanInfoRedisDTO);
			if(-1 != index)
				this.successedLoanInfoRedisDTOs.set(index, successedLoanInfoRedisDTO);
		}
	}
	
	public LoanInfoRedisDTO querySuccessedLoanInfoRedisDTO(LoanInfoRedisDTO successedLoanInfoRedisDTO) {
		if(null != this.successedLoanInfoRedisDTOs){
			int index = this.successedLoanInfoRedisDTOs.indexOf(successedLoanInfoRedisDTO);
			if(-1 == index)
				return null;
			return this.successedLoanInfoRedisDTOs.get(index);
		}
		return null;
	}

	public List<LoanInfoRedisDTO> getFailedLoanInfoRedisDTOs() {
		return failedLoanInfoRedisDTOs;
	}

	public void setFailedLoanInfoRedisDTOs(List<LoanInfoRedisDTO> failedLoanInfoRedisDTOs) {
		this.failedLoanInfoRedisDTOs = failedLoanInfoRedisDTOs;
	}
	
	public void addFailedLoanInfoRedisDTO(LoanInfoRedisDTO failedLoanInfoRedisDTO) {
		if(null == this.failedLoanInfoRedisDTOs){
			this.failedLoanInfoRedisDTOs = new ArrayList<LoanInfoRedisDTO>();
			this.failedLoanInfoRedisDTOs.add(failedLoanInfoRedisDTO);
		}else {
			this.failedLoanInfoRedisDTOs.add(failedLoanInfoRedisDTO);
		}
	}
	
	public void modifyFailedLoanInfoRedisDTO(LoanInfoRedisDTO failedLoanInfoRedisDTO) {
		if(null != this.failedLoanInfoRedisDTOs){
			int index = this.failedLoanInfoRedisDTOs.indexOf(failedLoanInfoRedisDTO);
			if(-1 != index)
				this.failedLoanInfoRedisDTOs.set(index, failedLoanInfoRedisDTO);
		}
	}
	
	public LoanInfoRedisDTO queryFailedLoanInfoRedisDTO(LoanInfoRedisDTO failedLoanInfoRedisDTO) {
		if(null != this.failedLoanInfoRedisDTOs){
			int index = this.failedLoanInfoRedisDTOs.indexOf(failedLoanInfoRedisDTO);
			if(-1 == index)
				return null;
			return this.failedLoanInfoRedisDTOs.get(index);
		}
		return null;
	}

	public BusinessMode getBusinessMode() {
		return businessMode;
	}

	public void setBusinessMode(BusinessMode businessMode) {
		this.businessMode = businessMode;
	}

	public InputMethod getInputMethod() {
		return inputMethod;
	}

	public void setInputMethod(InputMethod inputMethod) {
		this.inputMethod = inputMethod;
	}

}
