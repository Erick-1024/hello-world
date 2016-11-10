package com.cana.vbam.common.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;

import com.cana.common.dao.po.Properties;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.testcenter.dto.WithdrawStateDTO;
import com.travelzen.framework.core.exception.WebException;

public interface IVbamCommonService {
	
	/**
	 * 返回当前日期，格式为： yyyy-MM-dd
	 * @return
	 */
    public String getCurrentDate();
    
    /**
     * 返回当前时间， 格式：MM:ss
     * @return
     */
    public String getCurrentTime();
    
    /**
     * 返回当前时间
     */
    public DateTime getCurrentDateTime();

	/**  
     * 判断是否是生成环境
     * @return
     */
	public boolean isProdEnv();
	
	/**
	 * 保存虚拟的日期和时间
	 * @param virtualDate
	 * @param hourOffset
	 */
	public void saveVirtualDateTime(String virtualDate, String hourOffset);
	
	/**
	 * 获取当前虚拟日期和时偏移
	 * @return
	 */
	public Pair<String, String> getVirtualDateTime();
	
	/**
	 * 保存当前用户的账户余额
	 * @param userName
	 * @param balance
	 * @param expireTime hours
	 */
	public void saveVirtualBalance(String accountNo, Long balance, Integer expireTime);
	
	/**
	 * 获取当前用户的账户余额
	 * @param userName
	 * @param amount
	 * @param expireTime
	 * @return 
	 */
	public Long getVirtualBalance(String accountNo);
	
	/**
	 * 查询提现状态
	 * @param bizSeq
	 * @return
	 */
	public WithdrawStateDTO getWithdrawState(String bizSeq);
	
	/**
	 * 保存提现状态信息
	 * @param queryDTO
	 */
	public void saveWithdrawState(String businessSeq, String state);
	
	public void generateRSAKey(String institutionId);
	
	/**
	 * 判断当天报表初始化任务是否完成
	 * @return
	 */
	public boolean isInitReportTaskDone();

	/**
	 * 判断当天额度设置过期是否完成
	 * @return
	 */
	public boolean isSetCreditExpireTaskDone();

	/**
	 * 判断当天额度设置生效是否完成
	 * @return
	 */
	public boolean isSetCreditNormalTaskDone();

	/**
	 * 判断当月(获取上月)的资金是否完成
	 * @return
	 */
	public boolean isFundReportTaskDone();
	
	/**
	 * 更新当天报表初始化任务的状态
	 */
	public void markInitReportTaskDone();

	/**
	 * 更新当天额度过期设置任务的状态
	 */
	public void markSetCreditExpireTaskDone();

	/**
	 * 更新当天额度生效设置任务的状态
	 */
	public void markSetCreditNormalTaskDone();

	/**
	 * 更新当月(获取上月)的资金完成
	 */
	public void markFundReportTaskDone();
	
	/**
	 * 校验RSA的数字签名
	 * @param plain 参与验签的明文
	 * @param institution 机构
	 * @param sign 参与验签的密文
	 * @param publicKey true：使用公钥进行验签；false：使用私钥进行验签
	 * @throws WebException ,如果验签失败，则抛异常
	 */
	public void rsaVerify(byte[] plain, String institution, byte[] sign, boolean publicKey);
	
	/**
	 * 对信息进行数字签名
	 * @param plain 待签名的明文
	 * @param institution 使用哪个机构的秘钥
	 * @param publicKey true：使用公钥进行签名；false：使用私钥进行签名
	 * @return 返回签名后的密文
	 * @throws Exception 
	 */
	public byte[] sign(byte[] plain, String institution, boolean publicKey) throws Exception;
	
	/**
	 * 根据属性名锁定properties记录
	 * @param name
	 */
	public Properties lockPropertiesByName(String name);
	
	/**
	 * 增加属性
	 * @param name
	 * @param value
	 * @return
	 */
	public Properties addProperties(String name, String value);
	
	/**
	 * 根据属性名获取properties记录
	 * @param name
	 * @return
	 */
	public Properties getProperties(String name);
	
	/**
	 * 通过配置名称的模糊匹配来获取匹配的配置
	 * @param name
	 * @return
	 */
	public List<Properties> getPropertiesByNameLike(String name);
	
	/**
	 * 更新properties记录
	 * @param properties
	 * @param value
	 */
	public void updateProperties(Properties properties, String value);
	
	/**
	 *  设置全局随机虚拟余额标志
	 */
	public void setAllAccountVirtualBalanceFlag(Integer expireTime);

	/**
	 *  查询是否给所有账户设置虚拟余额标志
	 */
	public boolean getIsSetAllAccountVirtualBalanceFlag();

	/**
	 * 取消设置全局随机虚拟余额标志
	 */
	public void cancelVirtualBalanceForAllAccount();

	/**
	 * 微信端获取账户余额
	 * @param accountBalanceWechatId
	 * @return
	 */
	public BankAccountBalanceResultDTO getAccountBalanceByWechatFromRedis(String accountBalanceWechatId);

	/**
	 * 微信端保存账户余额
	 * @param bankAccountBalanceResultDTO
	 */
	public void saveAccountBalanceByWechatToRedis(BankAccountBalanceResultDTO bankAccountBalanceResultDTO);
}
