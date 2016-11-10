package com.cana.repayment.service.transaction.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.RepaymentTableLockMapper;
import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentRuleMapper;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.transaction.ILoanInfoTransactionService;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service("loanInfoTransactionServiceImpl")
public class LoanInfoTransactionServiceImpl implements ILoanInfoTransactionService{

	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private RepaymentTableLockMapper repaymentTableLockMapper;
	
	@Resource
	private RepaymentRuleMapper repaymentRuleMapper;
	
	@Resource
	private LoanInfoConfigMapper loanInfoConfigMapper;
	
	@Resource 
	private RepaymentTableLockMapper tableLockMapper;
	
	@Resource
	private IRepaymentServiceHelper serviceHelper;

	@Override
	public void saveLoanInfoListToDB(List<RepaymentLoanInfo> loanInfoList) throws Exception
	{
//		String factorCompany = userApi.queryCustomerDetail(factorId).getCompanyName();//获得保理商公司名称
//		List<LoanInfoRedisDTO> loanInfoRedisDTOs = loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs();  
//		String businessMode = loanInfoRedisIntegration.getBusinessMode().name();
//		String inputMethod = loanInfoRedisIntegration.getInputMethod().name();
//		for(LoanInfoRedisDTO loanInfoRedisDTO : loanInfoRedisDTOs){
//			//将loanInfoRedisDTO 转成 RepaymentLoanInfo
//			RepaymentLoanInfo repaymentLoanInfo = convertLoanInfoRedisDTOToRepaymentLoanInfo(loanInfoRedisDTO);
//			String financeId = userApi.getFinanceIdByName(loanInfoRedisDTO.getFinanceCompany(), UserType.FINACE);
//			/*repaymentLoanInfo 添加必要的字段*/
//			CustomerDetailDTO coreCompany = userApi.queryCustomerByCompanyName(loanInfoRedisDTO.getCoreCompanyName(), UserType.CORECOMPANY);
//			if(null != coreCompany){
//				repaymentLoanInfo.setCoreCompanyId(coreCompany.getId());
//			}
//			repaymentLoanInfo.setFactorId(factorId);//保理商Id
//			repaymentLoanInfo.setFactorCompany(factorCompany);//保理商公司名称
//			repaymentLoanInfo.setFinanceId(financeId);//融资客户Id
//			repaymentLoanInfo.setBusinessMode(businessMode);//业务模式（保理商+融资商等）
//			repaymentLoanInfo.setInputMethod(inputMethod);//录入方式（excal、手动）
//			repaymentLoanInfo.setSettleStatus(SettleStatus.UNSETTLE.name());
//			repaymentLoanInfo.setCreateTime(new Date());//创建时间
//			repaymentLoanInfo.setId(generateRepaymentLoanInfoId());//生成Id
//			repaymentLoanInfo.setChangeType(LoanInfoChangeType.created.name());//变更类型
//			repaymentLoanInfoMapper.insertSelective(repaymentLoanInfo);//插入到数据库
//			RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(tableLockMapper.lockLoanInfoById(repaymentLoanInfo.getId()));
//			// 新增快照
//			loanInfoBO.createSnapshot();
//			
//			serviceHelper.createLoanInfoConfig(loanInfoBO);
//		}
		for(RepaymentLoanInfo repaymentLoanInfo : loanInfoList){
			repaymentLoanInfoMapper.insertSelective(repaymentLoanInfo);//插入到数据库
			RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(tableLockMapper.lockLoanInfoById(repaymentLoanInfo.getId()));
			// 新增快照
			loanInfoBO.createSnapshot();
			serviceHelper.createLoanInfoConfig(loanInfoBO, (BigDecimal)null);
		}
	}

	@Override
	public void updateAccountNoToDB(LoanInfoRedisDTO loanInfoRedisDTO) throws Exception
	{
		StringUtil.trimObjectFields(loanInfoRedisDTO);
		if(StringUtils.isBlank(loanInfoRedisDTO.getId()))	
		{	
			throw WebException.instance("放款信息Id不能为空");	
		}	
		RepaymentLoanInfo repaymentLoanInfo = repaymentTableLockMapper.lockRepaymentLoanInfoById(loanInfoRedisDTO.getId());
		if(null == repaymentLoanInfo)
		   throw WebException.instance("未查询到对应的放款信息");
		repaymentLoanInfo.setAccountNo(loanInfoRedisDTO.getAccountNo());
		repaymentLoanInfo.setAccountSupervisionId(loanInfoRedisDTO.getAccountSupervisionId());	
		repaymentLoanInfo.setUpateTime(new Date());	
		repaymentLoanInfoMapper.updateByPrimaryKeySelective(repaymentLoanInfo);
	}
}
