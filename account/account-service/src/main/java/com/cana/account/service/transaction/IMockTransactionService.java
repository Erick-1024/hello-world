package com.cana.account.service.transaction;

public interface IMockTransactionService {
	
	public void addNewProperty(String propertyName, String propertyValue) throws Exception;
	
	/**
	 * 验证required new 传播级别生效了
	 * @param propertyName
	 * @param propertyValue
	 * @throws Exception
	 */
	public void requiredNewEffective(String propertyName, String propertyValue) throws Exception;
	
	public void lock() throws Exception;
	
}
