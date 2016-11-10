package com.cana.vbam.common.account.dto;

import java.io.Serializable;

public class AccountBalancesAndNumberDTO implements Serializable{

	private static final long serialVersionUID = -7897430783547641595L;
	
	private String allBalances;//全部账户总余额
	
	private String generalNoSupervisionBalances;//一般账户（未监管）总余额
	private String generalNoSupervisionNumber;//一般账户（未监管）数量
	
	private String generalHaveSupervisionBalances;//一般账户（监管）总余额
	private String generalHaveSupervisionNumber;//一般账户（监管）数量
	
	private String specialNoSupervisionBalances;//专用账户（未监管）总余额(保理商不存在)
	private String specialNoSupervisionNumber;//专用账户（未监管）数量(保理商不存在)
	
	private String specialHaveSupervisionBalances;//专用账户（监管）总余额
	private String specialHaveSupervisionNumber;//专用账户（监管）数量
	
	public String getAllBalances() {
		return allBalances;
	}
	public void setAllBalances(String allBalances) {
		this.allBalances = allBalances;
	}
	public String getGeneralNoSupervisionBalances() {
		return generalNoSupervisionBalances;
	}
	public void setGeneralNoSupervisionBalances(String generalNoSupervisionBalances) {
		this.generalNoSupervisionBalances = generalNoSupervisionBalances;
	}
	public String getGeneralNoSupervisionNumber() {
		return generalNoSupervisionNumber;
	}
	public void setGeneralNoSupervisionNumber(String generalNoSupervisionNumber) {
		this.generalNoSupervisionNumber = generalNoSupervisionNumber;
	}
	public String getGeneralHaveSupervisionBalances() {
		return generalHaveSupervisionBalances;
	}
	public void setGeneralHaveSupervisionBalances(String generalHaveSupervisionBalances) {
		this.generalHaveSupervisionBalances = generalHaveSupervisionBalances;
	}
	public String getGeneralHaveSupervisionNumber() {
		return generalHaveSupervisionNumber;
	}
	public void setGeneralHaveSupervisionNumber(String generalHaveSupervisionNumber) {
		this.generalHaveSupervisionNumber = generalHaveSupervisionNumber;
	}
	public String getSpecialNoSupervisionBalances() {
		return specialNoSupervisionBalances;
	}
	public void setSpecialNoSupervisionBalances(String specialNoSupervisionBalances) {
		this.specialNoSupervisionBalances = specialNoSupervisionBalances;
	}
	public String getSpecialNoSupervisionNumber() {
		return specialNoSupervisionNumber;
	}
	public void setSpecialNoSupervisionNumber(String specialNoSupervisionNumber) {
		this.specialNoSupervisionNumber = specialNoSupervisionNumber;
	}
	public String getSpecialHaveSupervisionBalances() {
		return specialHaveSupervisionBalances;
	}
	public void setSpecialHaveSupervisionBalances(String specialHaveSupervisionBalances) {
		this.specialHaveSupervisionBalances = specialHaveSupervisionBalances;
	}
	public String getSpecialHaveSupervisionNumber() {
		return specialHaveSupervisionNumber;
	}
	public void setSpecialHaveSupervisionNumber(String specialHaveSupervisionNumber) {
		this.specialHaveSupervisionNumber = specialHaveSupervisionNumber;
	}
	

}
