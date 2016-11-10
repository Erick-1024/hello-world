package com.cana.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.account.dao.mapper.gen.BankBranchInfoMapper;
import com.cana.account.dao.po.BankBranchInfo;
import com.cana.account.dao.po.BankBranchInfoExample;
import com.cana.account.dao.po.BankBranchInfoExample.Criteria;
import com.cana.account.service.IAccountService;
import com.cana.account.service.transaction.IAccountTransactionService;
import com.cana.account.service.utils.AccountIDGenerator;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.account.dto.BankBranchInfoDTO;
import com.cana.vbam.common.account.dto.BranchNameQueryCriteria;
import com.cana.vbam.common.bankgate.dto.request.BankAccountCreateDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.dianping.cat.Cat;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class AccountServiceImpl implements IAccountService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Gson gson = new Gson();
	@Resource
	private BankgateApi bankgateApi;
	@Resource
	private IAccountTransactionService accountTransactionService;
	
	@Resource
	private BankBranchInfoMapper bankBranchInfoMapper;
	
	private static final String catKeyPrefix = "账户请求网关";
	private static final String catKeyApplySuccess = catKeyPrefix+"开户成功";
	private static final String catKeyApplyError = catKeyPrefix+"开户失败";

	/**
	 * 新建一个网关开户请求数据对象
	 * 
	 * @param companyName
	 * @param businessSeq
	 * @return
	 */
	private BankAccountCreateDTO newBankAccountSignInfo(String companyName, String businessSeq, String transDate) {
		BankAccountCreateDTO infoDTO = new BankAccountCreateDTO();
		infoDTO.setAccountName(companyName);
		infoDTO.setBusinessSeq(businessSeq);
		infoDTO.setTransDate(transDate);
		return infoDTO;
	}

	@Override
	public List<Pair<String, String>> batchCreateBankAccount(int num, String companyName) {
		List<Pair<String, String>> result = Lists.newArrayList();
		String transDate = DateTimeUtil.date8();
		for (int i = 0; i < num; ++i) {
			String businessSeq = AccountIDGenerator.generateBusinessSeq();
			BankAccountCreateDTO signInfo = newBankAccountSignInfo(companyName, businessSeq, transDate);
			BankAccountDTO resultDTO = null;
			try {
				resultDTO = bankgateApi.createBankAccount(signInfo);
			} catch (Exception e) {
				logger.error("调用网关接口开户失败!", e);
			}
			logger.info("账户请求网关创建账号返回结果：{}", gson.toJson(resultDTO));
			if (resultDTO != null && resultDTO.getStatus().equals(BankTranStatus.success)) {
			    result.add(Pair.of(businessSeq, resultDTO.getAccountNo()));
			} else {
			    logger.error("创建银行账户失败！创建成功的账号少于需要的账号!创建成功的账号为:{}",gson.toJson(result));
			    Cat.logMetricForCount(catKeyApplyError);
	            throw WebException.instance("创建银行账户失败！");
			}
		}
		if (CollectionUtils.isEmpty(result)) {
			logger.error("创建银行账户失败！");
			Cat.logMetricForCount(catKeyApplyError);
			throw WebException.instance("创建银行账户失败！");
		}
		Cat.logMetricForCount(catKeyApplySuccess);
		return result;
	}

	@Override
	public List<BankBranchInfoDTO> queryqueryBranchInfo(BranchNameQueryCriteria queryCriteria) {
		BankBranchInfoExample example = new BankBranchInfoExample();
		Criteria createCriteria = example.createCriteria();
		conditionAssemble(queryCriteria, createCriteria);
		List<BankBranchInfo> bankBranchInfoList = bankBranchInfoMapper.selectByExample(example);
		List<BankBranchInfoDTO> BankBranchInfoDTOList = bankBranchInfoDTOConvert(bankBranchInfoList);
		return BankBranchInfoDTOList;
	}
	
	private void conditionAssemble(BranchNameQueryCriteria queryCriteria, Criteria createCriteria){
		if(StringUtils.isBlank(queryCriteria.getBankName())){
			throw WebException.instance("银行名称不能为空");
		}
		createCriteria.andBankNameEqualTo(queryCriteria.getBankName());
		if(StringUtils.isBlank(queryCriteria.getProvince())){
			throw WebException.instance("省份不能为空");
		}
		createCriteria.andProvinceEqualTo(queryCriteria.getProvince());
		if(StringUtils.isBlank(queryCriteria.getCity())){
			throw WebException.instance("市不能为空");
		}
		createCriteria.andCityEqualTo(queryCriteria.getCity());
		if(StringUtils.isNoneBlank(queryCriteria.getKeyWord())){
			createCriteria.andBranchNameLike("%"+queryCriteria.getKeyWord()+"%");
		}
	}
	
	private List<BankBranchInfoDTO> bankBranchInfoDTOConvert(List<BankBranchInfo> bankBranchInfoList){
		List<BankBranchInfoDTO> bankBranchInfoDTOList = Lists.newArrayList();
		for(BankBranchInfo BankBranchInfo : bankBranchInfoList){
			BankBranchInfoDTO bankBranchInfoDTO = new BankBranchInfoDTO();
			BeanUtils.copyProperties(BankBranchInfo, bankBranchInfoDTO);
			bankBranchInfoDTOList.add(bankBranchInfoDTO);
		}
		return bankBranchInfoDTOList;
	}

}
