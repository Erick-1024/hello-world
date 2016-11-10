package com.cana.asset.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.service.IUnderlyingAssetImportService;
import com.cana.asset.service.transaction.IUnderlyingAssetTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.underlyingasset.dto.EditUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetExcelDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetExcelRedisDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.RedisUtils;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;

@Service
public class UnderlyingAssetImportService implements IUnderlyingAssetImportService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IUnderlyingAssetTransactionService underlyingAssetTransactionService;

	@Resource
	private IMemberQueryApi memberQueryApi;

	@Resource
	private SequenceGenerator seqGen;

	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	@Override
	public void importUnderlyingAssetExcel2Redis(List<UnderlyingAssetExcelDTO> loanExcelList, String operatorId, String rediskey){
		UserVo userVo = memberQueryApi.findUserById(operatorId);
		if(null == userVo || StringUtils.isBlank(rediskey) || CollectionUtils.isEmpty(loanExcelList))
			throw WebException.instance("参数异常");
		
		UnderlyingAssetExcelRedisDTO redisDTO = null;
		List<UnderlyingAssetExcelDTO> passUnderlyingAssetList = null;
		List<UnderlyingAssetExcelDTO> notPassUnderlyingAssetList = null;
		Set<String> underlyingAssetIds = null;
		String key = RedisUtils.generateUnderlyingAssetRedisKeyByOperator(rediskey, operatorId);
		Object excelRedis = redisCache.read(key);
		if(null != excelRedis){
			redisDTO = (UnderlyingAssetExcelRedisDTO)excelRedis;
			passUnderlyingAssetList = redisDTO.getPassUnderlyingAssetList();
			notPassUnderlyingAssetList = redisDTO.getNotPassUnderlyingAssetList();
			underlyingAssetIds = redisDTO.getUnderlyingAssetIds();
		}else{
			redisDTO = new UnderlyingAssetExcelRedisDTO();
			passUnderlyingAssetList = Lists.newArrayList();
			notPassUnderlyingAssetList = Lists.newArrayList();
			underlyingAssetIds = new HashSet<>();
		}
		for(UnderlyingAssetExcelDTO loanInfoExcelDTO : loanExcelList){
			try{
				if(StringUtils.isBlank(loanInfoExcelDTO.getUnderlyingAssetId()))
					throw WebException.instance("基础资产编号不能为空");
				if(underlyingAssetIds.contains(loanInfoExcelDTO.getUnderlyingAssetId()))
					throw WebException.instance("基础资产编号已存在");
				checkUnderlyingAssetIsValid(loanInfoExcelDTO);
				underlyingAssetTransactionService.checkImportUnderlyingAssetRequest(userVo, loanInfoExcelDTO);

				underlyingAssetIds.add(loanInfoExcelDTO.getUnderlyingAssetId());
				passUnderlyingAssetList.add(loanInfoExcelDTO);
			}catch (WebException e) {
				loanInfoExcelDTO.setCheckFailedMessage(e.getMessage());
				notPassUnderlyingAssetList.add(loanInfoExcelDTO);
			}
		}
		redisDTO.setPassUnderlyingAssetList(passUnderlyingAssetList);
		redisDTO.setNotPassUnderlyingAssetList(notPassUnderlyingAssetList);
		redisDTO.setUnderlyingAssetIds(underlyingAssetIds);
		redisCache.save(key, redisDTO);
	}
	
	private void checkUnderlyingAssetIsValid(UnderlyingAssetExcelDTO loanInfoExcelDTO) {
		String settleStatusFlag = StringUtils.trimToEmpty(loanInfoExcelDTO.getSettleStatusFlag());
		if (settleStatusFlag.equals("已结清") || settleStatusFlag.equals("是"))
			loanInfoExcelDTO.setSettleStatus(SettleStatus.SETTLED.name());
		else if (settleStatusFlag.equals("") || settleStatusFlag.equals("未结清") || settleStatusFlag.equals("否"))
			loanInfoExcelDTO.setSettleStatus(SettleStatus.UNSETTLE.name());
		else
			throw WebException.instance("结清标示不合法，必须为是／否／已结清／未结清");

		InterestRateUnit interestRateUnit = InterestRateUnit.YEAR;
		if (StringUtils.isNotBlank(loanInfoExcelDTO.getInterestRateUnitDesc()))
			interestRateUnit = InterestRateUnit.getValue(loanInfoExcelDTO.getInterestRateUnitDesc());
		if (interestRateUnit == null)
			throw WebException.instance("利率类型不合法，必须为日／月／年");
		loanInfoExcelDTO.setInterestRateUnit(interestRateUnit.name());
		
		try {
			if (StringUtils.isNotBlank(loanInfoExcelDTO.getForwardDaysStr()))
				loanInfoExcelDTO.setForwardDays(Integer.parseInt(StringUtils.trim(loanInfoExcelDTO.getForwardDaysStr())));
		} catch (Exception e) {
			throw WebException.instance("提前还款天数不为数字");
		}
		String forwardFlag = StringUtils.trimToNull(loanInfoExcelDTO.getForwardFlag());
		if ((forwardFlag == null || forwardFlag.equals("否"))) {
			if (loanInfoExcelDTO.getForwardDays() != 0)
				throw WebException.instance("提前还款天数必需等于0");
		} else if (forwardFlag.equals("是")) {
			if (loanInfoExcelDTO.getForwardDays() <= 0)
				throw WebException.instance("提前还款天数必需大于0");
		} else {
			throw WebException.instance("提前还款标示不合法，必须为是／否");
		}

		try {
			if (StringUtils.isNotBlank(loanInfoExcelDTO.getExtensionDaysStr()))
				loanInfoExcelDTO.setExtensionDays(Integer.parseInt(StringUtils.trim(loanInfoExcelDTO.getExtensionDaysStr())));
		} catch (Exception e) {
			throw WebException.instance("展期天数不为数字");
		}
		String extensionFlag = StringUtils.trimToNull(loanInfoExcelDTO.getExtensionFlag());
		if ((extensionFlag == null || extensionFlag.equals("否"))) {
			if (loanInfoExcelDTO.getExtensionDays() != 0)
				throw WebException.instance("展期天数必需等于0");
		} else if (extensionFlag.equals("是")) {
			if (loanInfoExcelDTO.getExtensionDays() <= 0)
				throw WebException.instance("展期天数必需大于0");
		} else {
			throw WebException.instance("展期标示不合法，必须为是／否");
		}

		try {
			if (StringUtils.isNotBlank(loanInfoExcelDTO.getOverdueDaysStr()))
				loanInfoExcelDTO.setOverdueDays(Integer.parseInt(StringUtils.trim(loanInfoExcelDTO.getOverdueDaysStr())));
		} catch (Exception e) {
			throw WebException.instance("违约天数不为数字");
		}
		String overdueFlag = StringUtils.trimToNull(loanInfoExcelDTO.getOverdueFlag());
		if ((overdueFlag == null || overdueFlag.equals("否"))) {
			if (loanInfoExcelDTO.getOverdueDays() != 0)
				throw WebException.instance("违约天数必需等于0");
		} else if (overdueFlag.equals("是")) {
			if (loanInfoExcelDTO.getOverdueDays() <= 0)
				throw WebException.instance("违约天数必需大于0");
		} else {
			throw WebException.instance("是否成为违约基础资产标示不合法，必须为是／否");
		}
	}
	
	@Override
	public ListResult<UnderlyingAssetExcelDTO> getUnderlyingAssetFromRedis(String redisKey, String operatorId, boolean passed,
			int page, int pageSize) {
		page = page < 1 ? 1 : page;
		pageSize = page < 1 ? 10 : pageSize;

		Object object = redisCache.read(RedisUtils.generateUnderlyingAssetRedisKeyByOperator(redisKey, operatorId));
		if(object == null)
			return ListResult.fail("无数据");
		UnderlyingAssetExcelRedisDTO  redisDTO = (UnderlyingAssetExcelRedisDTO) object;

		List<UnderlyingAssetExcelDTO> underlyingAssetList = passed ? redisDTO.getPassUnderlyingAssetList() : redisDTO.getNotPassUnderlyingAssetList();
		int totalNum = underlyingAssetList.size();
		underlyingAssetList = underlyingAssetList.subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, totalNum));
		return ListResult.success(underlyingAssetList, totalNum);
	}
	
	private int getStartIndex(int page, int pageSize){
		return (page - 1) * pageSize;
	}
	
	private <T> int getEndIndex(int page, int pageSize, int size) {
		return page * pageSize < size ? page * pageSize : size;
	}

	@Override
	public void importUnderlyingAssetExcel2DB(String operatorId, String rediskey) {
		UserVo userVo = memberQueryApi.findUserById(operatorId);
		if(null == userVo || StringUtils.isBlank(rediskey))
			throw WebException.instance("参数异常");

		Object object = redisCache.read(RedisUtils.generateUnderlyingAssetRedisKeyByOperator(rediskey, operatorId));
		if(object == null)
			throw WebException.instance("无数据");
		UnderlyingAssetExcelRedisDTO redisDTO = (UnderlyingAssetExcelRedisDTO) object;

		List<UnderlyingAssetExcelDTO> loanInfoList = redisDTO.getPassUnderlyingAssetList();
		if(CollectionUtils.isEmpty(loanInfoList))
			throw WebException.instance("无校验通过的基础资产");
		for (UnderlyingAssetExcelDTO loanInfoExcelDTO : loanInfoList) {
			try {
				checkUnderlyingAssetIsValid(loanInfoExcelDTO);
			} catch (WebException e) {
				logger.info(e.getMessage());
				throw WebException.instance("数据发生变更, 请重新导入");
			}
		}
		List<EditUnderlyingAssetRequest> requestList = Lists.newArrayList();
		requestList.addAll(loanInfoList);
		underlyingAssetTransactionService.importUnderlyingAsset(userVo, requestList);
	}

	@Override
	public String generateUnderlyingAssetRedisKey() {
		return DateTimeUtil.datetime12()+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_UNDERLYING_ASSET_REDIS_KEY, 4);
	}
}
