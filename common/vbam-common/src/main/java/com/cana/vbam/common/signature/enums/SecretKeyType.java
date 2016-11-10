package com.cana.vbam.common.signature.enums;

/**
 * @author hu
 *
 */
public enum SecretKeyType {

	RSA_1024("RSA","1024"),
	RSA_2048("RSA", "2048"),
	RSA_4096("RSA", "4096"),
	SM2_256("SM2", "256");
	
	private String keyAlg;
	
	private String keyLength;
	
	private SecretKeyType(String keyAlg, String keyLength){
		this.keyAlg = keyAlg;
		this.keyLength = keyLength;
	}
	
	public String keyAlg(){
		return keyAlg;
	}
	
	public String keyLength(){
		return keyLength;
	}
}
