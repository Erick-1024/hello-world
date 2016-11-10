package com.cana.credit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.api.IAssetApi;
import com.cana.credit.dao.mapper.CreditLimitCustomMapper;
import com.cana.credit.dao.mapper.OutCustomerAndLimitCustomMapper;
import com.cana.credit.dao.mapper.gen.CustomerApplyMapper;
import com.cana.credit.dao.mapper.gen.OutCustomerMapper;
import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.dao.po.CustomerApplyExample;
import com.cana.credit.dao.po.OutCustomer;
import com.cana.credit.dao.po.OutCustomerExample;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitExample;
import com.cana.credit.service.ICreditLimitAuditService;
import com.cana.credit.service.ICreditLimitService;
import com.cana.credit.service.ICreditMessageService;
import com.cana.credit.service.IOutCustomerService;
import com.cana.credit.service.IRetryTaskService;
import com.cana.credit.service.convertors.CreditLimitConvertor;
import com.cana.credit.service.transaction.ICreditLimitTransactionService;
import com.cana.credit.service.utils.SendResultMessageUtil;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.credit.dto.limit.CalculateLimitResult;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfo;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfoQueryCriteria;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListQueryDTO;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListResponseDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitResponse;
import com.cana.vbam.common.credit.enums.CreditLimitGenerateState;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.credit.enums.NoticeScene;
import com.cana.vbam.common.credit.openapi.CreditNoticeParam;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.WebEnv;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;


@Service
public class CreditLimitServiceImpl implements ICreditLimitService{


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CustomerApplyMapper customerApplyMapper;
    
    @Resource
    private CreditLimitMapper creditLimitMapper;
    
    @Resource
    private CreditLimitCustomMapper creditLimitCustomMapper;
    
    @Resource
    private OutCustomerMapper outCustomerMapper;
    
    @Resource
    private IUserApi userApiImpl;

    @Resource
    private IRetryTaskService retryTaskServiceImpl;
    
    @Resource
    private ICreditLimitTransactionService creditLimitTransactionServiceImpl;
    
    @Resource
    private ICreditLimitAuditService creditLimitAuditServiceImpl;
    
    @Resource
    private OutCustomerAndLimitCustomMapper outCustomerAndLimitCustomerMapper;
    
    @Resource
	private ICreditMessageService creditMessageServiceImpl; 
    
    @Resource
	private IOutCustomerService outCusomterServiceImpl;
    
    @Resource
	private IUserApi userApi;
    
    @Resource
    private IAssetApi assetapi;
    
    @Override
    public void calculateApplyCreditLimit() {
        CustomerApplyExample example = new CustomerApplyExample();
        example.createCriteria().andCreditLimitGenerateStateEqualTo(CreditLimitGenerateState.WAIT.name());
        List<CustomerApply> customerApplys = customerApplyMapper.selectByExample(example);
        for (CustomerApply customerApply : customerApplys) {
        	String financeId = outCusomterServiceImpl.getCanaFinanceIdByOutCustomerId(Institution.travelzen.name(), customerApply.getTzCustomerId());
    		String activeLink = null;
			try {
				activeLink = userApi.generateActivacationURL(financeId);
			} catch (Exception e) {
				logger.error("生成用户激活链接失败，真旅客户id为：" + customerApply.getTzCustomerId() + "用户id为："+ financeId);
				continue;
			}
        	CalculateLimitResult limitResult = creditLimitTransactionServiceImpl.calculateApplyCreditLimit(customerApply);
        	if(limitResult.getFinalLimit() <= 0){
        		//没有额度 => 自动准入不通过
        		SendResultMessageUtil.sendAutomaticMessage(customerApply);
        	}else{
	        	//生成额度成功
	        	CreditNoticeParam creditNoticeParam = new CreditNoticeParam();
	    		creditNoticeParam.setInwhitelist(customerApply.getInWhitelist());
	    		creditNoticeParam.setNoticeScene(NoticeScene.GENERATE_LIMIT);
	    		creditNoticeParam.setLimit(MoneyUtil.formatMoney(limitResult.getFinalLimit()));
	    		if(activeLink != null) {
	    			creditNoticeParam.setActiveLink(activeLink);
	    		} else {
	    			creditNoticeParam.setLoginLink(WebEnv.getVBAMPlatformLoginUrl());
	    		}
	    		creditNoticeParam.setCompanyName(customerApply.getCompanyName());
	    		creditNoticeParam.setEmail(customerApply.getEmail());
	    		creditNoticeParam.setPhoneNumber(customerApply.getPhoneNumber());
	    		creditNoticeParam.setAccessManualState(customerApply.getAccessManualState());
	    		boolean needLoginActive = true;
	    		try {
	    			needLoginActive = !assetapi.checkContractExistByTravelzenFinanceId(financeId);
	    		}catch (Exception e) {
					logger.error("检查是否已经签署过合同失败，真旅客户id为：" + customerApply.getTzCustomerId() + "用户id为："+ financeId);
	    		}
	    		creditNoticeParam.setNeedLoginActive(needLoginActive);
				creditMessageServiceImpl.sendMailForTzCustomerApply(creditNoticeParam);
				creditMessageServiceImpl.sendSmsMessageForTzCustomerApply(creditNoticeParam);
        	}
        }
        logger.info("这次计算额度一共处理了{}个申请",customerApplys.size());
    }

    
    @Override
    public PageList<CustomerLimitListResponseDTO> getCustomerLimitList(CustomerLimitListQueryDTO queryDTO) {
        PageList<CustomerLimitListResponseDTO> response = new PageList<CustomerLimitListResponseDTO>();
        convertCreditLimitQuery(queryDTO);
        List<CustomerLimitListResponseDTO> responseDTOs=creditLimitCustomMapper.find(queryDTO);
        if(CollectionUtils.isEmpty(responseDTOs))
            return response;
        response.setRecords(responseDTOs);
        response.setTotalRecords(creditLimitCustomMapper.count(queryDTO));
        return response;
    }

    private void convertCreditLimitQuery(CustomerLimitListQueryDTO queryDTO) {
    	queryDTO.setProjectId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
    	if(StringUtils.isNotBlank(queryDTO.getCompanyName()))
    		queryDTO.setCompanyName("%"+queryDTO.getCompanyName()+"%");
    	if (StringUtils.isNotBlank(queryDTO.getLimitStart()))
            queryDTO.setLimitStartLong(MoneyUtil.yuan2Cent(queryDTO.getLimitStart()));
    	if (StringUtils.isNotBlank(queryDTO.getLimitEnd()))
            queryDTO.setLimitEndLong(MoneyUtil.yuan2Cent(queryDTO.getLimitEnd()));
    }

    @Override
    public QueryCreditLimitResponse queryCreditLimitBalance(QueryCreditLimitDTO queryDTO) {
        CreditLimitExample limitExample=new CreditLimitExample();
        OutCustomerExample customerExample=new OutCustomerExample();
        customerExample.createCriteria().andInstitutionIdEqualTo(queryDTO.getInstitution().trim()).andOutCustomerIdEqualTo(queryDTO.getCustomerId());
        List<OutCustomer> outCustomers=outCustomerMapper.selectByExample(customerExample);
        if(CollectionUtils.isEmpty(outCustomers))
            throw WebException.instance(ReturnCode.TP3016);
		limitExample.createCriteria().andProjectIdEqualTo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID)
				.andMemberIdEqualTo(outCustomers.get(0).getMemberId())
				.andOutCustomerIdEqualTo(outCustomers.get(0).getOutCustomerId())
				.andCreditModeEqualTo(CreditMode.SYNTHETICAL.name());
        List<CreditLimit> creditLimits=creditLimitMapper.selectByExample(limitExample);
        if(CollectionUtils.isEmpty(creditLimits))
            throw WebException.instance(ReturnCode.TP3012);
        return CreditLimitConvertor.convertCreditLimitBalanceDao2Dto(creditLimits.get(0));
    }


	@Override
	public List<CreditUsedLimitInfo> queryCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria) {
		return outCustomerAndLimitCustomerMapper.queryCreditUsedLimitInfo(creditUsedLimitInfoQueryCriteria);
	}


	@Override
	public int queryCountCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria) {
		return outCustomerAndLimitCustomerMapper.queryCountCreditUsedLimitInfo(creditUsedLimitInfoQueryCriteria);
	}


	@Override
	public Map<String, String> queryOutCustomerIdAndMemberId(String projectId, String companyName, String outCustomerName) {
		CreditLimitExample creditLimitExample = new CreditLimitExample();
		creditLimitExample.createCriteria().andProjectIdEqualTo(projectId).andCompanyNameEqualTo(companyName).andOutCustomerNameEqualTo(outCustomerName);
		List<CreditLimit> creditLimits = creditLimitMapper.selectByExample(creditLimitExample);
		Map<String, String> returnValue = new HashMap<>();
		for (CreditLimit creditLimit : creditLimits)
			returnValue.put(creditLimit.getOutCustomerId(), creditLimit.getMemberId());
		return returnValue;
	}


	@Override
	public String queryOutCustomerName(String productId, String memberId, String outCustomerId) {
		CreditLimitExample creditLimitExample = new CreditLimitExample();
		creditLimitExample.createCriteria().andProjectIdEqualTo(productId).andMemberIdEqualTo(memberId).andOutCustomerIdEqualTo(outCustomerId);
		List<CreditLimit> creditLimits = creditLimitMapper.selectByExample(creditLimitExample);
		if(creditLimits.size() == 0)
			throw WebException.instance("没有该客户");
		return creditLimits.get(0).getOutCustomerName();
	}
    
}
