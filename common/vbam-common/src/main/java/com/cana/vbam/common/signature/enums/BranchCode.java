package com.cana.vbam.common.signature.enums;

/**
 * @author hu
 * 分支机构代码
 */
public enum BranchCode {

	CANABRANCHCODE("678");
	
	private String number;
	
	private BranchCode(String number){
		this.number= number;
	}
	
	public String number(){
		return number;
	}
}
