package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class PageNumCountDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -143532115064961648L;
	
	
	private int totalNumForPlan;
	
	private int totalCorrectNumForPlan;
	
	private int totalIncorrectNumForPlan;
	
	private int totalNumForExpense;
	
	private int totalCorrectNumForExpense;
	
	private int totalIncorrectNumForExpense;

	public int getTotalNumForPlan() {
		return totalNumForPlan;
	}

	public void setTotalNumForPlan(int totalNumForPlan) {
		this.totalNumForPlan = totalNumForPlan;
	}

	public int getTotalCorrectNumForPlan() {
		return totalCorrectNumForPlan;
	}

	public void setTotalCorrectNumForPlan(int totalCorrectNumForPlan) {
		this.totalCorrectNumForPlan = totalCorrectNumForPlan;
	}

	public int getTotalIncorrectNumForPlan() {
		return totalIncorrectNumForPlan;
	}

	public void setTotalIncorrectNumForPlan(int totalIncorrectNumForPlan) {
		this.totalIncorrectNumForPlan = totalIncorrectNumForPlan;
	}

	public int getTotalNumForExpense() {
		return totalNumForExpense;
	}

	public void setTotalNumForExpense(int totalNumForExpense) {
		this.totalNumForExpense = totalNumForExpense;
	}

	public int getTotalCorrectNumForExpense() {
		return totalCorrectNumForExpense;
	}

	public void setTotalCorrectNumForExpense(int totalCorrectNumForExpense) {
		this.totalCorrectNumForExpense = totalCorrectNumForExpense;
	}

	public int getTotalIncorrectNumForExpense() {
		return totalIncorrectNumForExpense;
	}

	public void setTotalIncorrectNumForExpense(int totalIncorrectNumForExpense) {
		this.totalIncorrectNumForExpense = totalIncorrectNumForExpense;
	}

	
}
