package com.cana.repayment.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.member.api.IUserApi;
import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.service.ILoanInfoService;
import com.cana.repayment.service.transaction.ILoanInfoTransactionService;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisIntegration;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class LoanInfoServiceImpl implements ILoanInfoService{

	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource
	private LoanInfoConfigMapper loanInfoConfigMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private ILoanInfoTransactionService loanInfoTransactionService;

	@Override
	public List<RepaymentLoanInfo> queryLoanInfoListFromDB(RepaymentLoanInfoExample repaymentLoanInfoExample){
		return repaymentLoanInfoMapper.selectByExample(repaymentLoanInfoExample);
	}
	
	public int queryLoanInfoCountFromDB(RepaymentLoanInfoExample repaymentLoanInfoExample){
		return repaymentLoanInfoMapper.countByExample(repaymentLoanInfoExample);
	}

	@Override
	public RepaymentLoanInfo queryLoanInfodetailFromDB(String id) throws Exception {
		return repaymentLoanInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public LoanInfoConfig queryLoanInfoConfigFromDB(String id) throws Exception
	{
		return loanInfoConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean isLoanNoExist(RepaymentLoanInfoExample repaymentLoanInfoExample){
		return repaymentLoanInfoMapper.countByExample(repaymentLoanInfoExample) > 0;
	}

	@Override
	public void convertLoanInfoList(LoanInfoRedisIntegration loanInfoRedisIntegration, String factorId)	throws Exception {
		// TODO Auto-generated method stub
		String factorCompany = userApi.queryCustomerDetail(factorId).getCompanyName();//获得保理商公司名称
		List<RepaymentLoanInfo> loanInfoList = new ArrayList<RepaymentLoanInfo>();  
		String businessMode = loanInfoRedisIntegration.getBusinessMode().name();
		String inputMethod = loanInfoRedisIntegration.getInputMethod().name();
		for(LoanInfoRedisDTO loanInfoRedisDTO : loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs()){
			//将loanInfoRedisDTO 转成 RepaymentLoanInfo
			RepaymentLoanInfo repaymentLoanInfo = convertLoanInfoRedisDTOToRepaymentLoanInfo(loanInfoRedisDTO);
			String financeId = userApi.getFinanceIdByName(loanInfoRedisDTO.getFinanceCompany(), UserType.FINACE);
			/*repaymentLoanInfo 添加必要的字段*/
			CustomerDetailDTO coreCompany = userApi.queryCustomerByCompanyName(loanInfoRedisDTO.getCoreCompanyName(), UserType.CORECOMPANY);
			if(null != coreCompany){
				repaymentLoanInfo.setCoreCompanyId(coreCompany.getId());
			}
			repaymentLoanInfo.setFactorId(factorId);//保理商Id
			repaymentLoanInfo.setFactorCompany(factorCompany);//保理商公司名称
			repaymentLoanInfo.setFinanceId(financeId);//融资客户Id
			repaymentLoanInfo.setBusinessMode(businessMode);//业务模式（保理商+融资商等）
			repaymentLoanInfo.setInputMethod(inputMethod);//录入方式（excal、手动）
			repaymentLoanInfo.setSettleStatus(SettleStatus.UNSETTLE.name());
			repaymentLoanInfo.setCreateTime(new Date());//创建时间
			repaymentLoanInfo.setId(generateRepaymentLoanInfoId());//生成Id
			repaymentLoanInfo.setChangeType(LoanInfoChangeType.created.name());//变更类型
			loanInfoList.add(repaymentLoanInfo);
		}
		loanInfoTransactionService.saveLoanInfoListToDB(loanInfoList);
	}
	
	private String generateRepaymentLoanInfoId() throws Exception {
		return DateTimeUtil.date8() + seqGen.getNextSeq(Constants.REPAYMENT_LOAN_INFO_ID, 5);
	}
	
	/**
	 * 将loanInfoRedisDTO 转成 RepaymentLoanInfo
	 * @param loanInfoRedisDTO
	 * @return
	 */
	private RepaymentLoanInfo convertLoanInfoRedisDTOToRepaymentLoanInfo(LoanInfoRedisDTO loanInfoRedisDTO){
		StringUtil.trimObjectFields(loanInfoRedisDTO);//去空格
		RepaymentLoanInfo repaymentLoanInfo = new RepaymentLoanInfo();
		BeanUtils.copyProperties(loanInfoRedisDTO, repaymentLoanInfo);
		
		//转换币种
		repaymentLoanInfo.setCurrency(Currency.getValue(loanInfoRedisDTO.getCurrency()).name());
		//应收账款金额
		repaymentLoanInfo.setReceivablesAmount(MoneyArithUtil.convertStringToMoney(loanInfoRedisDTO.getReceivablesAmount()));
		
		//应收账款余额
		repaymentLoanInfo.setReceivablesBalance(MoneyArithUtil.convertStringToMoney(loanInfoRedisDTO.getReceivablesBalance()));
		
		//融资金额
		repaymentLoanInfo.setFinanceAmount(MoneyArithUtil.convertStringToMoney(loanInfoRedisDTO.getFinanceAmount()));
		
		//融资余额
		repaymentLoanInfo.setFinanceBalance(MoneyArithUtil.convertStringToMoney(loanInfoRedisDTO.getFinanceBalance()));
		//利率单位
		repaymentLoanInfo.setInterestRateUnit(StringUtils.isBlank(loanInfoRedisDTO.getInterestRateUnit())?"":InterestRateUnit.getValue(loanInfoRedisDTO.getInterestRateUnit()).name());
		//利率
		repaymentLoanInfo.setInterestRate(MoneyArithUtil.convertStringToInterestRate(loanInfoRedisDTO.getInterestRate()));
		
		//放款期限单位
		repaymentLoanInfo.setLoanPeriodUnit(DateUnit.getValue(loanInfoRedisDTO.getLoanPeriodUnit()).name());
		
		//还本付息方式
		repaymentLoanInfo.setRepaymentMethod(RepaymentType.getValue(loanInfoRedisDTO.getRepaymentMethod()).name());
//		还款期数计算
		switch (RepaymentType.valueOf(repaymentLoanInfo.getRepaymentMethod())) {
		case ORDER:
		case MATURITY:
			repaymentLoanInfo.setRepaymentPeriod(1);
			break;

		default:
			if(DateUnit.YEAR.name().equals(repaymentLoanInfo.getLoanPeriodUnit())){
				repaymentLoanInfo.setRepaymentPeriod(Integer.parseInt(repaymentLoanInfo.getLoanPeriod()) * 12);
			}else if(DateUnit.MONTH.name().equals(repaymentLoanInfo.getLoanPeriodUnit())){
				repaymentLoanInfo.setRepaymentPeriod(Integer.parseInt(repaymentLoanInfo.getLoanPeriod()));
			}else {
				DateTime loanDate = DateTime.parse(repaymentLoanInfo.getLoanDate());
				DateTime dueDate = DateTime.parse(repaymentLoanInfo.getDueDate());
				int days = Integer.parseInt(repaymentLoanInfo.getLoanPeriod());
				int num = MoneyArithUtil.roundUp(MoneyArithUtil.divide(new BigDecimal(days), new BigDecimal(28), 20), 0).intValue();
				for (int i = 0; i <= num; i++) {
					if(loanDate.plusMonths(i).compareTo(dueDate) >= 0){
						repaymentLoanInfo.setRepaymentPeriod(i);
						break;
					}
					continue;
				}
			}
			break;
		}
		return repaymentLoanInfo;
	}

	@Override
	public List<RepaymentLoanInfo> queryLoanInfoByCondition(RepaymentLoanInfoExample repaymentLoanInfoExample)	throws Exception {
		return repaymentLoanInfoMapper.selectByExample(repaymentLoanInfoExample);
	}
}
