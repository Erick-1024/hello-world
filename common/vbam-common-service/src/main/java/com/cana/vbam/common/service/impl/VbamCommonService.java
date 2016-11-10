package com.cana.vbam.common.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.CommonTableLockMapper;
import com.cana.common.dao.mapper.gen.CanaRSAKeyMapper;
import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.CanaRSAKey;
import com.cana.common.dao.po.Properties;
import com.cana.common.dao.po.PropertiesExample;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusDataDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.bankgate.enums.TradeStatusFlag;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.testcenter.dto.WithdrawStateDTO;
import com.cana.vbam.common.utils.Constants;
import com.google.common.collect.Lists;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.redis.client.SpringRedisClient;
import com.travelzen.framework.security.RSAKeyPair;
import com.travelzen.framework.security.RSAUtil;

@Service("commonService")
public class VbamCommonService implements IVbamCommonService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String REDIS_KEY_VIRTUAL_DATE = "virtual_date";

	private static final String REDIS_KEY_HOUR_OFFSET = "hour_offset";
	
	private static final String REDIS_KEY_ACCOUNT_VIRTUAL_BALANCE_PREFIX = "ACCOUNT_VIRTUAL_BALANCE_PREFIX:";

	private static final String REDIS_KEY_WITHDRAW_STATE_PREFIX = "WITHDRAW_STATE_PREFIX:";

	private static final String REDIS_KEY_SET_ALL_ACCOUNT_VIRTUAL_BALANCE_FLAG = "redis_key_set_all_account_virtual_balance_flag";
	
	private static SpringRedisClient redisClient = SpringRedisClient.getInstance();
	 @Resource
	 private CanaRSAKeyMapper canaRSAKeyMapper;

	@Resource
	private PropertiesMapper propertiesMapper;
	
	@Resource
	private CommonTableLockMapper commonTableLockMapper;

	/**
	 * 返回当前日期，格式为： yyyy-MM-dd
	 * 
	 * @return
	 */
	public String getCurrentDate() {
		if (isProdEnv())
			return DateTimeUtil.date10();
		else
			return getVirtualDate();
	}

	/**
	 * 返回当前时间， 格式：MM:ss
	 * 
	 * @return
	 */
	public String getCurrentTime() {
		if (isProdEnv())
			return DateTimeFormat.forPattern("HH:mm").print(new DateTime());
		else
			return getVirtualTime();
	}

	@Override
	public DateTime getCurrentDateTime() {
		String currentDateTime = getCurrentDate() + " " + getCurrentTime() + ":00";
		return DateTimeUtil.parseDate(DateTimeUtil.DATE_TIME_PATTERN, currentDateTime);
	}

	/**
	 * 获取虚拟时间
	 * 
	 * @return
	 */
	private String getVirtualTime() {
		String hourOffset = redisClient.get(REDIS_KEY_HOUR_OFFSET);
		if (StringUtils.isBlank(hourOffset))
			return DateTimeFormat.forPattern("HH:mm").print(new DateTime());
		else
			return DateTimeFormat.forPattern("HH:mm").print(new DateTime().plusHours(Integer.parseInt(hourOffset)));
	}

	/**
	 * 获取虚拟日期
	 * 
	 * @return
	 */
	private String getVirtualDate() {
		String virtualDate = redisClient.get(REDIS_KEY_VIRTUAL_DATE);
		if (StringUtils.isNotBlank(virtualDate))
			return virtualDate;
		else
			return DateTimeUtil.date10();
	}

	/**
	 * 判断是否是生成环境
	 * 
	 * @return
	 */
	public boolean isProdEnv() {
		return "/tops/prod".equals(TopsConfReader.getConfContent("properties/zkService.properties", "zkBasePath", ConfScope.R));
	}

	/**
	 * 保存虚拟的日期和时间
	 * 
	 * @param virtualDate
	 * @param hourOffset
	 */
	public void saveVirtualDateTime(String virtualDate, String hourOffset) {
		redisClient.save(REDIS_KEY_VIRTUAL_DATE, virtualDate, 24 * 60 * 60);
		redisClient.save(REDIS_KEY_HOUR_OFFSET, hourOffset, 24 * 60 * 60);
	}

	@Override
	public Pair<String, String> getVirtualDateTime() {
		return Pair.of((String) redisClient.get(REDIS_KEY_VIRTUAL_DATE),
				(String) redisClient.get(REDIS_KEY_HOUR_OFFSET));
	}

	@Override
	public void saveVirtualBalance(String accountNo, Long balance, Integer expireTime) {
		redisClient.save(REDIS_KEY_ACCOUNT_VIRTUAL_BALANCE_PREFIX + accountNo, balance, expireTime * 3600);
	}

	@Override
	public Long getVirtualBalance(String accountNo) {
		return redisClient.get(REDIS_KEY_ACCOUNT_VIRTUAL_BALANCE_PREFIX + accountNo);
	}

	@Override
	public WithdrawStateDTO getWithdrawState(String bizSeq) {
		return redisClient.get(REDIS_KEY_WITHDRAW_STATE_PREFIX + bizSeq);
	}

	@Override
	public void saveWithdrawState(String businessSeq, String state) {
		WithdrawStateDTO withdrawStateDTO = new WithdrawStateDTO();
		TradeStatusResultDTO tradeStatusResultDTO = new TradeStatusResultDTO();
		List<TradeStatusDataDTO> tradeStatusDatas = Lists.newArrayList();
		TradeStatusDataDTO tradeStatusDataDTO = new TradeStatusDataDTO();
		tradeStatusDataDTO.setTradeStatusFlag(TradeStatusFlag.success);
		if(StringUtils.equals(state, "success")){
			tradeStatusDataDTO.setStatus(BankTranStatus.success);
		}else{
			tradeStatusDataDTO.setStatus(BankTranStatus.fail);
		}
		tradeStatusDataDTO.setStatusText("提现打转");
		tradeStatusDatas.add(tradeStatusDataDTO);
		tradeStatusResultDTO.setTradeStatusDatas(tradeStatusDatas);
		tradeStatusResultDTO.setStatus(BankTranStatus.success);
		withdrawStateDTO.setTradeStatusResultDTO(tradeStatusResultDTO);
		withdrawStateDTO.setTradeStatusFlag(TradeStatusFlag.valueOf(state));
		redisClient.save(REDIS_KEY_WITHDRAW_STATE_PREFIX + businessSeq, withdrawStateDTO, 24 * 3600);
	}

	@Override
	public void generateRSAKey(String institutionId) {
		RSAKeyPair pair = null;
		try {
			pair = RSAUtil.generateKeyPair();
			String privateKey = pair.getPrivateKeyJava();
			String publicKey = pair.getPublicKeyJava();
			CanaRSAKey canaRSAKey = new CanaRSAKey();
			canaRSAKey.setInstitutionId(institutionId);
			canaRSAKey.setPrivateKey(privateKey);
			canaRSAKey.setPublicKey(publicKey);
			 canaRSAKeyMapper.insertSelective(canaRSAKey);
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw e;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean isInitReportTaskDone() {
		String propertiesName = Constants.TASK_PREFIX_INIT_FINANCE_REPORT + getCurrentDate();
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		if(null == properties){
			properties = new Properties();
			properties.setName(propertiesName);
			properties.setValue(Boolean.FALSE.toString());
			propertiesMapper.insertSelective(properties);
			return false;
		} else {
			return Boolean.valueOf(properties.getValue());
		}
	}

	@Override
	public boolean isSetCreditExpireTaskDone() {
		String propertiesName = Constants.TASK_SET_CREDIT_EXPIRE + getCurrentDate();
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		if(null == properties){
			properties = new Properties();
			properties.setName(propertiesName);
			properties.setValue(Boolean.FALSE.toString());
			propertiesMapper.insertSelective(properties);
			return false;
		} else {
			return Boolean.valueOf(properties.getValue());
		}
	}

	@Override
	public boolean isSetCreditNormalTaskDone() {
		String propertiesName = Constants.TASK_SET_CREDIT_NORMAL + getCurrentDate();
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		if(null == properties){
			properties = new Properties();
			properties.setName(propertiesName);
			properties.setValue(Boolean.FALSE.toString());
			propertiesMapper.insertSelective(properties);
			return false;
		} else {
			return Boolean.valueOf(properties.getValue());
		}
	}

	@Override
	public boolean isFundReportTaskDone() {
		String propertiesName = Constants.REPORT_FUND_MONTHLY + DateTimeUtil.month7(new DateTime(getCurrentDate()).minusMonths(1));
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		if(null == properties){
			properties = new Properties();
			properties.setName(propertiesName);
			properties.setValue(Boolean.FALSE.toString());
			propertiesMapper.insertSelective(properties);
			return false;
		} else {
			return Boolean.valueOf(properties.getValue());
		}
	}
	
	@Override
	public void markInitReportTaskDone() {
		String propertiesName = Constants.TASK_PREFIX_INIT_FINANCE_REPORT + getCurrentDate();
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		properties.setName(propertiesName);
		properties.setValue(Boolean.TRUE.toString());
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}

	@Override
	public void markSetCreditExpireTaskDone() {
		String propertiesName = Constants.TASK_SET_CREDIT_EXPIRE + getCurrentDate();
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		properties.setName(propertiesName);
		properties.setValue(Boolean.TRUE.toString());
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}

	@Override
	public void markSetCreditNormalTaskDone() {
		String propertiesName = Constants.TASK_SET_CREDIT_NORMAL + getCurrentDate();
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		properties.setName(propertiesName);
		properties.setValue(Boolean.TRUE.toString());
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}

	@Override
	public void markFundReportTaskDone() {
		String propertiesName = Constants.REPORT_FUND_MONTHLY + DateTimeUtil.month7(new DateTime(getCurrentDate()).minusMonths(1));
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
//		if(properties == null){
//			properties = new Properties();
//		}
		properties.setName(propertiesName);
		properties.setValue(Boolean.TRUE.toString());
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}

	@Override
	public void rsaVerify(byte[] plain, String institution, byte[] sign, boolean publicKey) {
		try {
			CanaRSAKey rsaKey = canaRSAKeyMapper.selectByPrimaryKey(institution);
			if (!RSAUtil.verify(plain, publicKey ? rsaKey.getPublicKey() : rsaKey.getPrivateKey(), sign))
				throw WebException.instance(ReturnCode.TP3006);
		} catch (Exception e) {
			logger.warn("验签失败", e);
			throw WebException.instance(ReturnCode.TP3017);
		}
	}

	@Override
	public byte[] sign(byte[] plain, String institution, boolean publicKey)  throws Exception {
		CanaRSAKey rsaKey = canaRSAKeyMapper.selectByPrimaryKey(institution);
		return RSAUtil.sign(plain, publicKey ? rsaKey.getPublicKey() : rsaKey.getPrivateKey());
	}

	@Override
	public Properties lockPropertiesByName(String name) {
		return propertiesMapper.lockByPrimaryKey(name);
	}

	@Override
	public Properties addProperties(String name, String value) {
		Properties property = new Properties();
		property.setName(name);
		property.setValue(value);
		propertiesMapper.insertSelective(property);
		return property;
	}

	@Override
	public Properties getProperties(String name) {
		return propertiesMapper.selectByPrimaryKey(name);
	}

	@Override
	public List<Properties> getPropertiesByNameLike(String name) {
		PropertiesExample propertiesExample = new PropertiesExample();
		propertiesExample.createCriteria().andNameLike("%" + name + "%");
		return propertiesMapper.selectByExample(propertiesExample);
	}
	
	@Override
	public void updateProperties(Properties properties, String value) {
		properties.setValue(value);
		properties.setUpdateTime(new Date());
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}

	@Override
	public void setAllAccountVirtualBalanceFlag(Integer expireTime) {
		if(null == expireTime){
			redisClient.save(REDIS_KEY_SET_ALL_ACCOUNT_VIRTUAL_BALANCE_FLAG, true, 72 * 3600);
			return;
		}
		redisClient.save(REDIS_KEY_SET_ALL_ACCOUNT_VIRTUAL_BALANCE_FLAG, true, expireTime * 3600);
	}

	@Override
	public boolean getIsSetAllAccountVirtualBalanceFlag() {
		Boolean flag = redisClient.get(REDIS_KEY_SET_ALL_ACCOUNT_VIRTUAL_BALANCE_FLAG);
		if(flag == null){
			return false;
		}
		return flag;
	}

	@Override
	public void cancelVirtualBalanceForAllAccount() {
		Boolean flag = redisClient.get(REDIS_KEY_SET_ALL_ACCOUNT_VIRTUAL_BALANCE_FLAG);
		if(null != flag){
			redisClient.save(REDIS_KEY_SET_ALL_ACCOUNT_VIRTUAL_BALANCE_FLAG, false, 72 * 3600);
		}
	}

	@Override
	public BankAccountBalanceResultDTO getAccountBalanceByWechatFromRedis(String accountBalanceWechatId) {
		return redisClient.get(accountBalanceWechatId);
	}

	@Override
	public void saveAccountBalanceByWechatToRedis(BankAccountBalanceResultDTO bankAccountBalanceResultDTO) {
		redisClient.save(Constants.QUERY_LIMIT_FLAG, bankAccountBalanceResultDTO, 300);
	}
}
