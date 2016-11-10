package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.travelzen.framework.core.exception.WebException;

public class RepaymentPlanInfoRedisIntegration implements Serializable {

	private static final long serialVersionUID = 3727729276687409103L;
	
	/**
	 * 检查通过还款信息
	 */
	private List<RepaymentExpenseRedisDTO> repaymentExpenseCorrect;
	
	/**
	 * 检查未通过的还款信息
	 */
	private List<RepaymentExpenseRedisDTO> repaymentExpenseIncorrect;
	
	/**
	 * 检查通过费用列表
	 */
	private List<RepaymentPlanRedisDTO> repaymentPlanCorrect;
	
	/**
	 * 检查未通过费用列表
	 */
	private List<RepaymentPlanRedisDTO> repaymentPlanIncorrect;

	/**
	  *录入方式（excal、手动）
	  */
	private String inputMethod;
	
    /**
    *业务模式（保理商+融资商等）
    */
    private String businessMode;
    
    /**
     * 还款计划录入总条数
     */
    private int totalRepyamentPlanNum;
    
    /**
     * 还款计划录入通过条数
     */
    private int repyamentPlanCorrectNum;
    
    /**
     * 还款计划录入未通过条数
     */
    private int repyamentPlanIncorrectNum;
    
    /**
     * 还款费用录入总条数
     */
    private int totalRepyamentExpenseNum;
    
    /**
     * 还款费用录入通过条数
     */
    private int repyamentExpenseCorrectNum;
    
    /**
     * 还款费用录入未通过条数
     */
    private int repyamentExpenseIncorrectNum;
    
	public String getBusinessMode() {
		return businessMode;
	}

	public void setBusinessMode(String businessMode) {
		this.businessMode = businessMode;
	}

	public List<RepaymentExpenseRedisDTO> getRepaymentExpenseCorrect() {
		return repaymentExpenseCorrect;
	}

	public void setRepaymentExpenseCorrect(
			List<RepaymentExpenseRedisDTO> repaymentExpenseCorrect) {
		this.repaymentExpenseCorrect = repaymentExpenseCorrect;
	}

	public List<RepaymentExpenseRedisDTO> getRepaymentExpenseIncorrect() {
		return repaymentExpenseIncorrect;
	}

	public void setRepaymentExpenseIncorrect(
			List<RepaymentExpenseRedisDTO> repaymentExpenseIncorrect) {
		this.repaymentExpenseIncorrect = repaymentExpenseIncorrect;
	}

	public List<RepaymentPlanRedisDTO> getRepaymentPlanCorrect() {
		return repaymentPlanCorrect;
	}

	public void setRepaymentPlanCorrect(
			List<RepaymentPlanRedisDTO> repaymentPlanCorrect) {
		this.repaymentPlanCorrect = repaymentPlanCorrect;
	}

	public List<RepaymentPlanRedisDTO> getRepaymentPlanIncorrect() {
		return repaymentPlanIncorrect;
	}

	public void setRepaymentPlanIncorrect(
			List<RepaymentPlanRedisDTO> repaymentPlanIncorrect) {
		this.repaymentPlanIncorrect = repaymentPlanIncorrect;
	}

	public String getInputMethod() {
		return inputMethod;
	}

	public void setInputMethod(String inputMethod) {
		this.inputMethod = inputMethod;
	}
	
	public int getTotalRepyamentPlanNum() {
		return totalRepyamentPlanNum;
	}

	public void setTotalRepyamentPlanNum(int totalRepyamentPlanNum) {
		this.totalRepyamentPlanNum = totalRepyamentPlanNum;
	}

	public int getRepyamentPlanCorrectNum() {
		return repyamentPlanCorrectNum;
	}

	public void setRepyamentPlanCorrectNum(int repyamentPlanCorrectNum) {
		this.repyamentPlanCorrectNum = repyamentPlanCorrectNum;
	}

	public int getRepyamentPlanIncorrectNum() {
		return repyamentPlanIncorrectNum;
	}

	public void setRepyamentPlanIncorrectNum(int repyamentPlanIncorrectNum) {
		this.repyamentPlanIncorrectNum = repyamentPlanIncorrectNum;
	}

	public int getTotalRepyamentExpenseNum() {
		return totalRepyamentExpenseNum;
	}

	public void setTotalRepyamentExpenseNum(int totalRepyamentExpenseNum) {
		this.totalRepyamentExpenseNum = totalRepyamentExpenseNum;
	}

	public int getRepyamentExpenseCorrectNum() {
		return repyamentExpenseCorrectNum;
	}

	public void setRepyamentExpenseCorrectNum(int repyamentExpenseCorrectNum) {
		this.repyamentExpenseCorrectNum = repyamentExpenseCorrectNum;
	}

	public int getRepyamentExpenseIncorrectNum() {
		return repyamentExpenseIncorrectNum;
	}

	public void setRepyamentExpenseIncorrectNum(int repyamentExpenseIncorrectNum) {
		this.repyamentExpenseIncorrectNum = repyamentExpenseIncorrectNum;
	}
	
	public RepaymentPlanInfoRedisIntegration() {
		this.repaymentExpenseCorrect = new ArrayList<>();
		this.repaymentExpenseIncorrect = new ArrayList<>();
		this.repaymentPlanCorrect = new ArrayList<>();
		this.repaymentPlanIncorrect = new ArrayList<>();
	}

	public void removeSingleCorrectPlan(String id){
		RepaymentPlanRedisDTO repaymentPlanRedisDTO = new RepaymentPlanRedisDTO();
		repaymentPlanRedisDTO.setId(id);
		int index = repaymentPlanCorrect.indexOf(repaymentPlanRedisDTO);
		if(index<0){
			throw WebException.instance("未找到相应数据，删除失败");
		}
		repaymentPlanCorrect.remove(index);
	}
	
	public void removeSingleCorrectExpense(String id){
		RepaymentExpenseRedisDTO repaymentExpenseRedisDTO = new RepaymentExpenseRedisDTO();
		repaymentExpenseRedisDTO.setId(id);
		int index = repaymentExpenseCorrect.indexOf(repaymentExpenseRedisDTO);
		if(index < 0){
			throw WebException.instance("未找到相应数据，删除失败");
		}
		repaymentExpenseCorrect.remove(index);
	}
	
	public String getLastSettleInterestDate(RepaymentPlanRedisDTO repaymentPlanRedisDTOBeforValidate){
		String lastSettleInterestDate = "";
		int repaymentPeriodCurrent = Integer.parseInt(repaymentPlanRedisDTOBeforValidate.getRepaymentPeriod());
		if(repaymentPeriodCurrent == 1){
			return lastSettleInterestDate;
		}
		if(!CollectionUtils.isEmpty(repaymentPlanCorrect)){
			for(RepaymentPlanRedisDTO repaymentPlanRedisDTOTemp:repaymentPlanCorrect){
				if(StringUtils.equals(repaymentPlanRedisDTOTemp.getLoanNo(), repaymentPlanRedisDTOBeforValidate.getLoanNo()) && StringUtils.equals(repaymentPlanRedisDTOTemp.getFinanceCompany(), repaymentPlanRedisDTOBeforValidate.getFinanceCompany())){
					if(Integer.parseInt(repaymentPlanRedisDTOTemp.getRepaymentPeriod())+1 == repaymentPeriodCurrent){
						return repaymentPlanRedisDTOTemp.getSettleInterestDate();
					}
				}
			}
		}
		if(!CollectionUtils.isEmpty(repaymentPlanIncorrect)){
			for(RepaymentPlanRedisDTO repaymentPlanRedisDTOTemp:repaymentPlanIncorrect){
				if(StringUtils.equals(repaymentPlanRedisDTOTemp.getLoanNo(), repaymentPlanRedisDTOBeforValidate.getLoanNo()) && StringUtils.equals(repaymentPlanRedisDTOTemp.getFinanceCompany(), repaymentPlanRedisDTOBeforValidate.getFinanceCompany())){
					if(Integer.parseInt(repaymentPlanRedisDTOTemp.getRepaymentPeriod())+1 == repaymentPeriodCurrent){
						return repaymentPlanRedisDTOTemp.getSettleInterestDate();
					}
				}
			}
		}
		return lastSettleInterestDate;
	}
}
