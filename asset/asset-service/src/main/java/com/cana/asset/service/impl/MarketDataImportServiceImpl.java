package com.cana.asset.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.AssetMarketDataProjectMapper;
import com.cana.asset.dao.po.AssetMarketDataProject;
import com.cana.asset.dao.po.AssetMarketDataProjectExample;
import com.cana.asset.service.IMarketDataImportService;
import com.cana.asset.service.transaction.IABSMarketDataTransactionService;
import com.cana.asset.service.transaction.util.ValidateRules;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.MarketDataProductExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataProjectExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataRedisDTO;
import com.cana.vbam.common.asset.enums.SupervisionAgencyEnum;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;

@Service
public class MarketDataImportServiceImpl implements IMarketDataImportService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private AssetMarketDataProjectMapper marketDataProjectMapper;
	
	@Resource
	private IABSMarketDataTransactionService marketDataTransactionService;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Resource
	private SequenceGenerator seqGen;
	
	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	@Override
	public int importMarketDataExcel2Redis(List<MarketDataProjectExcelDTO> projectExcelList,
			List<MarketDataProductExcelDTO> productExcelList, String operatorId, String rediskey) {
		UserVo userVo = memberQueryApi.findUserById(operatorId);
		if(null == userVo || StringUtils.isBlank(rediskey))
			throw WebException.instance("参数异常");
		
		String key = RedisUtils.genertateAssetMarketDataRedisKeyByOperator(rediskey,operatorId);
		Object excelRedis = redisCache.read(key);
		List<MarketDataProjectExcelDTO> projectExcelListRedis;
		MarketDataRedisDTO redisDTO;
		if(null != excelRedis){
			redisDTO = (MarketDataRedisDTO)excelRedis;
			projectExcelListRedis = redisDTO.getProjectExcelList();
		}else{
			redisDTO = new MarketDataRedisDTO();
			projectExcelListRedis = Lists.newArrayList();
		}
		checkProjectExcel(projectExcelList, productExcelList, projectExcelListRedis);
		redisDTO.setProjectExcelList(projectExcelListRedis);
		redisCache.save(key, redisDTO);
		return projectExcelListRedis.size();
	}

	@Override
	public void importMarketDataExcel2DB(String operatorId, String rediskey) {
		UserVo userVo = memberQueryApi.findUserById(operatorId);
		if(null == userVo || StringUtils.isBlank(rediskey))
			throw WebException.instance("参数异常");
		String key = RedisUtils.genertateAssetMarketDataRedisKeyByOperator(rediskey,operatorId);
		Object excelRedis = redisCache.read(key);
		if(null == excelRedis){
			throw WebException.instance("数据为空");
		}
		List<MarketDataProjectExcelDTO> projectExcelList = ((MarketDataRedisDTO)excelRedis).getProjectExcelList();
		if(CollectionUtils.isEmpty(projectExcelList)){
			throw WebException.instance("数据为空");
		}
		if(CollectionUtils.isNotEmpty(checkDBExist(projectExcelList))){
			throw WebException.instance("数据发生变动，请重新导入");
		}
		marketDataTransactionService.importMarketData(projectExcelList);
	}

	@Override
	public String generateMarketDataRedisKey() {
		return DateTimeUtil.datetime12()+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_MARKET_DATA_REDIS_KEY, 4);
	}

	private List<AssetMarketDataProject> checkDBExist(List<MarketDataProjectExcelDTO> projectExcelList){
		List<String> projectNameList = Lists.newArrayList();
		for(MarketDataProjectExcelDTO projectDTO: projectExcelList){
			if(StringUtils.isNotBlank(projectDTO.getProjectName())){
				projectNameList.add(projectDTO.getProjectName());
			}
		}
		AssetMarketDataProjectExample example = new AssetMarketDataProjectExample();
		example.createCriteria().andProjectNameIn(projectNameList);
		List<AssetMarketDataProject> projectPoList = marketDataProjectMapper.selectByExample(example);
		return projectPoList;
	}
	
	private boolean checkProjectNameExist(List<AssetMarketDataProject> projectPoList, String projectName){
		if(CollectionUtils.isEmpty(projectPoList)){
			return false;
		}
		for(AssetMarketDataProject project : projectPoList){
			if(StringUtils.equals(projectName, project.getProjectName())){
				return true;
			}
		}
		return false;
	}
	
	private void checkProjectExcel(List<MarketDataProjectExcelDTO> projectExcelList
			, List<MarketDataProductExcelDTO> productExcelList, List<MarketDataProjectExcelDTO> projectExcelListRedis){
		List<AssetMarketDataProject> projectPoList = checkDBExist(projectExcelList);
		
		for(MarketDataProjectExcelDTO marketDataProjectExcelDTO : projectExcelList){
			if(StringUtils.isBlank(marketDataProjectExcelDTO.getProjectName())){
				logger.info("项目名称为空:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			
			if(checkProjectNameExist(projectPoList, marketDataProjectExcelDTO.getProjectName())){
				logger.info("项目数据库已存在:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			
			if(projectExcelListRedis.contains(marketDataProjectExcelDTO)){
				logger.info("项目重复:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			
			
			if(StringUtils.isBlank(marketDataProjectExcelDTO.getValueDate()) || 
					!validateDate(marketDataProjectExcelDTO.getValueDate())){
				logger.info("计息日不正确:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}else{
				marketDataProjectExcelDTO.setValueDateStr(DateTimeUtil.date10(parseDate10(marketDataProjectExcelDTO.getValueDate())));
			}
			if(StringUtils.isBlank(marketDataProjectExcelDTO.getOriginator())){
				logger.info("发起机构为空:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			if(StringUtils.isBlank(marketDataProjectExcelDTO.getIssueTotalAmount()) || 
					!ValidateRules.regexAmountFlaotCheck(marketDataProjectExcelDTO.getIssueTotalAmount()) || 
					MoneyArithUtil.convertStringToMoneyBigDecimal(marketDataProjectExcelDTO.getIssueTotalAmount()).compareTo(BigDecimal.ZERO) < 0){
				
				logger.info("发行总金额不正确:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			
			if(StringUtils.isBlank(marketDataProjectExcelDTO.getSupervisionAgency())){
				logger.info("监管机构为空:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			SupervisionAgencyEnum agnecy = SupervisionAgencyEnum.getEnum(marketDataProjectExcelDTO.getSupervisionAgency());
			if(null == agnecy){
				logger.info("监管机构不匹配:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}else{
				marketDataProjectExcelDTO.setSupervisionAgencyEnum(agnecy);
			}
			
			if(StringUtils.isBlank(marketDataProjectExcelDTO.getUnderlyingAssetType())){
				logger.info("基础资产类型为空:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
//			UnderlyingAssetType assetType = UnderlyingAssetType.getEnum(marketDataProjectExcelDTO.getUnderlyingAssetType());
//			if(null == assetType){
//				logger.info("基础资产类型不匹配(设置为[其他]):[{}]", new Gson().toJson(marketDataProjectExcelDTO));
//				marketDataProjectExcelDTO.setAssetType(UnderlyingAssetType.OTHER);
//			}else
//				marketDataProjectExcelDTO.setAssetType(assetType);
			
//			if(StringUtils.isBlank(marketDataProjectExcelDTO.getIssuer())){
//				logger.info("发行人为空:[]", new Gson().toJson(marketDataProjectExcelDTO));
//				continue;
//			}
			int issueMonth;
			try{
				issueMonth = Integer.parseInt(marketDataProjectExcelDTO.getIssueMonth());
			}catch(Exception e){
				logger.info("发行月份不为数字:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			if(1<=issueMonth && issueMonth<=12){
				marketDataProjectExcelDTO.setIssueMonthNum(issueMonth);
			}else{
				logger.info("发行月份不正确:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			if("无".equals(marketDataProjectExcelDTO.getAaaAverageInterestRate())){
				marketDataProjectExcelDTO.setAaaAverageInterestRate(null);
			}
			if(StringUtils.isNotBlank(marketDataProjectExcelDTO.getAaaAverageInterestRate()) && 
					ValidateRules.regexPercentCheck(marketDataProjectExcelDTO.getAaaAverageInterestRate())){
				marketDataProjectExcelDTO.setAaaAverageInterestRate(MoneyArithUtil.
						convertStringToInterestRate(marketDataProjectExcelDTO.getAaaAverageInterestRate()).toString());
			}
			if(StringUtils.isNotBlank(marketDataProjectExcelDTO.getAaaAverageInterestRate()) &&
					(!ValidateRules.regexAmountFlaotCheck(marketDataProjectExcelDTO.getAaaAverageInterestRate())
					|| new BigDecimal(marketDataProjectExcelDTO.getAaaAverageInterestRate()).compareTo(BigDecimal.ZERO) < 0)){
				logger.info("AAA平均利率不正确:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			
			if("无".equals(marketDataProjectExcelDTO.getPriorityAverageInterestRate())){
				marketDataProjectExcelDTO.setPriorityAverageInterestRate(null);
			}
			if(StringUtils.isNotBlank(marketDataProjectExcelDTO.getPriorityAverageInterestRate()) &&
					ValidateRules.regexPercentCheck(marketDataProjectExcelDTO.getPriorityAverageInterestRate())){
				marketDataProjectExcelDTO.setPriorityAverageInterestRate(MoneyArithUtil.
						convertStringToInterestRate(marketDataProjectExcelDTO.getPriorityAverageInterestRate()).toString());
			}
			if (StringUtils.isNotBlank(marketDataProjectExcelDTO.getPriorityAverageInterestRate()) && (!ValidateRules.regexAmountFlaotCheck(marketDataProjectExcelDTO.getPriorityAverageInterestRate())
					|| new BigDecimal(marketDataProjectExcelDTO.getPriorityAverageInterestRate()).compareTo(BigDecimal.ZERO) < 0)){
				logger.info("优先权利率不正确:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			List<MarketDataProductExcelDTO> projectProductExcelList = Lists.newArrayList();
			for(MarketDataProductExcelDTO productExcelDTO : productExcelList){
				if(CollectionUtils.isNotEmpty(projectProductExcelList)){
					if(StringUtils.isNotBlank(productExcelDTO.getProjectName()) ||
							StringUtils.isNotBlank(productExcelDTO.getValueDate()) ||
							StringUtils.isNotBlank(productExcelDTO.getUnderlyingAssetType())){
						marketDataProjectExcelDTO.addProductExcelList(projectProductExcelList);
						projectProductExcelList.clear();
						continue;
					}
				}else{
					if(!StringUtils.equals(marketDataProjectExcelDTO.getProjectName(), productExcelDTO.getProjectName()) || 
							!StringUtils.equals(marketDataProjectExcelDTO.getValueDate(), productExcelDTO.getValueDate()) ||
							!StringUtils.equals(marketDataProjectExcelDTO.getUnderlyingAssetType(), productExcelDTO.getUnderlyingAssetType())){
						continue;
					}
				}
				if(checkProductIsValid(productExcelDTO))
					projectProductExcelList.add(productExcelDTO);
			}
			if(CollectionUtils.isNotEmpty(projectProductExcelList)){
				marketDataProjectExcelDTO.addProductExcelList(projectProductExcelList);
			}
			if(CollectionUtils.isEmpty(marketDataProjectExcelDTO.getProductExcelList())){
				logger.info("没有产品详情:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			BigDecimal totalAmount = BigDecimal.ZERO;
			for(MarketDataProductExcelDTO productDTO : marketDataProjectExcelDTO.getProductExcelList()){
				totalAmount = totalAmount.add(MoneyArithUtil.convertStringToMoneyBigDecimal(productDTO.getIssueAmount()));
			}
			if(totalAmount.compareTo(MoneyArithUtil.convertStringToMoneyBigDecimal(marketDataProjectExcelDTO.getIssueTotalAmount())) != 0){
				logger.info("发行总金额与各产品金额之和不等:[{}]", new Gson().toJson(marketDataProjectExcelDTO));
				continue;
			}
			
			projectExcelListRedis.add(marketDataProjectExcelDTO);
		}
		
	}
	
	private boolean checkProductIsValid(MarketDataProductExcelDTO productExcelDTO){
		if(StringUtils.isBlank(productExcelDTO.getProductName())){
			logger.info("产品名称为空:[{}]", new Gson().toJson(productExcelDTO));
			return false;
		}
		if(StringUtils.isBlank(productExcelDTO.getIssueAmount()) || 
			!ValidateRules.regexAmountFlaotCheck(productExcelDTO.getIssueAmount())
			|| MoneyArithUtil.convertStringToMoneyBigDecimal(productExcelDTO.getIssueAmount()).compareTo(BigDecimal.ZERO) < 0){
			logger.info("发行规模不正确:[{}]", new Gson().toJson(productExcelDTO));
			return false;
		}
		
		return true;
	}
	
	private boolean validateDate(String date) {
		if (date == null || "".equals(date.trim())) return false;
		try {
			DateTime d = parseDate10(date);
			return (d != null);
		} catch (Exception e) {
			return false;
		}
	}
	
	private DateTime parseDate10(String date) {
		DateTimeFormatter format1 = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTimeFormatter format2 = DateTimeFormat.forPattern("yyyy/MM/dd");
		try{
			return format1.parseDateTime(date);
		}catch(Exception e1){
			try{
				return format2.parseDateTime(date);
			}catch(Exception e2){
				return null;
			}
		}
	}
}
