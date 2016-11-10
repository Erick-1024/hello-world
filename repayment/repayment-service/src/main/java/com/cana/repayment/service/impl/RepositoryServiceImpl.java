package com.cana.repayment.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskItemMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExtensionProductDetailMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPenaltyProductDetailMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentProductMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleDistributeDetailMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskExample;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItemExample;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;
import com.cana.repayment.dao.po.RepaymentExtensionProductDetail;
import com.cana.repayment.dao.po.RepaymentExtensionProductDetailExample;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.dao.po.RepaymentPenaltyProductDetail;
import com.cana.repayment.dao.po.RepaymentPenaltyProductDetailExample;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.repayment.dao.po.RepaymentProduct;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleCollectExample;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetailExample;
import com.cana.repayment.service.IRepositoryService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.repayment.product.TravelzenFinanceProduct;
import com.cana.vbam.common.repayment.product.YundaexFinanceProduct;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;

/**
 * 数据访问服务
 * @author renshui
 *
 */
@Service("repaymentRepositoryServiceImpl")
public class RepositoryServiceImpl implements IRepositoryService{
	
	@Resource
	private RepaymentPlanMapper planMapper;
	
	@Resource
	private RepaymentLoanInfoMapper loanInfoMapper;

	@Resource
	private RepaymentExpenseMapper expenseMapper;
	
	@Resource
	private LoanInfoConfigMapper loanInfoConfigMapper;
	
	@Resource
	private RepaymentDailyBatchTaskMapper taskMapper;
	
	@Resource
	private RepaymentDailyBatchTaskItemMapper taskItemMapper;
	
	@Resource
	private RepaymentSingleCollectMapper singleCollectMapper;
	
	@Resource
	private RepaymentSingleDistributeDetailMapper singleDistributeDetailMapper;
	
	@Resource
	private RepaymentExtensionProductDetailMapper extensionProductDetailMapper;
	
	@Resource
	private RepaymentPenaltyProductDetailMapper penaltyProductDetailMapper;
	
	@Resource
	private RepaymentProductMapper productMapper;
	
	public List<RepaymentPlan> getPlansByLoanInfoId(String loanInfoId){
		RepaymentPlanExample example = new RepaymentPlanExample();
		example.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		example.setOrderByClause("repayment_period");
		return planMapper.selectByExample(example);
	}

	@Override
	public List<RepaymentExpense> getExpensesByLoanInfoId(String loanInfoId) {
		RepaymentExpenseExample example = new RepaymentExpenseExample();
		example.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		example.setOrderByClause("repayment_date");
		return expenseMapper.selectByExample(example);
	}

	@Override
	public LoanInfoConfig getRepaymentConfigByLoanInfoId(String loanInfoId) {
		return loanInfoConfigMapper.selectByPrimaryKey(loanInfoId);
	}

	@Override
	public RepaymentDailyBatchTask getRepaymentDailyBatchTask(String loanInfoId, String date) {
		RepaymentDailyBatchTaskExample example = new RepaymentDailyBatchTaskExample();
		example.createCriteria().andLoanInfoIdEqualTo(loanInfoId).andDateEqualTo(date);
		List<RepaymentDailyBatchTask> taskList = taskMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(taskList))
			return taskList.get(0);
		return null;
	}

	@Override
	public List<RepaymentDailyBatchTaskItem> getTaskItemsByTaskId(String taskId) {
		RepaymentDailyBatchTaskItemExample example = new RepaymentDailyBatchTaskItemExample();
		example.createCriteria().andTaskIdEqualTo(taskId);
		example.setOrderByClause("sequence");
		return taskItemMapper.selectByExample(example);
	}

	@Override
	public List<RepaymentSingleDistributeDetail> getRepaymentItemsByRepaymentId(String id) {
		RepaymentSingleDistributeDetailExample example = new RepaymentSingleDistributeDetailExample();
		example.createCriteria().andRepaymentSingleCollectIdEqualTo(id);
		return singleDistributeDetailMapper.selectByExample(example);
	}

	@Override
	public RepaymentExtensionProductDetail getExtensionChargeGenerateDetailByPlanIdAndDate(String id, String date) {
		RepaymentExtensionProductDetailExample example = new RepaymentExtensionProductDetailExample();
		example.createCriteria().andRepaymentPlanIdEqualTo(id).andDateEqualTo(date);
		List<RepaymentExtensionProductDetail> rows = extensionProductDetailMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(rows))
			return null;
		else return rows.get(0);
	}

	@Override
	public RepaymentPenaltyProductDetail getPenaltyDetailByPlanIdAndDate(String planId, String date) {
		RepaymentPenaltyProductDetailExample example = new RepaymentPenaltyProductDetailExample();
		example.createCriteria().andRepaymentPlanIdEqualTo(planId).andDateEqualTo(date);
		List<RepaymentPenaltyProductDetail> rows = penaltyProductDetailMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(rows))
			return null;
		else return rows.get(0);
	}

	@Override
	public TravelzenFinanceProduct getTravelzenFinanceProduct() {
		RepaymentProduct product = productMapper.selectByPrimaryKey(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		TravelzenFinanceProduct detail = new Gson().fromJson(product.getDetail(), TravelzenFinanceProduct.class);
		detail.setProductId(product.getId());
		detail.setProductName(product.getName());
		return detail;
	}
	@Override
	public YundaexFinanceProduct getYundaexFinanceProduct() {
		RepaymentProduct product = productMapper.selectByPrimaryKey(Constants.YUNDAEX_FINANCE_PRODUCT_ID);
		YundaexFinanceProduct detail = new Gson().fromJson(product.getDetail(), YundaexFinanceProduct.class);
		detail.setProductId(product.getId());
		detail.setProductName(product.getName());
		return detail;
	}

	@Override
	public boolean isExistProduct(String productId) {
		return productMapper.selectByPrimaryKey(productId) != null;
	}
	
	@Override
	public List<RepaymentSingleCollect> getRepaymentSummarysByLoanInfoId(String loanInfoId) {
		RepaymentSingleCollectExample example = new RepaymentSingleCollectExample();
		example.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		example.setOrderByClause("repayment_date, create_time");
		return singleCollectMapper.selectByExample(example);
	}

	@Override
	public List<RepaymentPlanBO> batchLoadRepaymentPlanBOs(List<String> planIds) throws Exception{
		
		if(CollectionUtils.isEmpty(planIds))
			return new ArrayList<>();
		
		RepaymentPlanExample example = new RepaymentPlanExample();
		example.createCriteria().andIdIn(planIds);
		List<RepaymentPlan> plans =  planMapper.selectByExample(example);

		
		List<RepaymentPlanBO> planBOs = new ArrayList<>();
		
		for(String planId : planIds){
			RepaymentPlan found = null;
			for(RepaymentPlan plan : plans){
				if(plan.getId().equals(planId)){
					found = plan;
					break;
				}
			}
			planBOs.add(found == null ? null : new RepaymentPlanBO(found));
		}
		
		return planBOs;
	}

	@Override
	public List<RepaymentLoanInfoBO> batchLoadRepaymentLoanInfoBOsByLoanInfoIds(List<String> loanInfoIds) throws Exception {
		
		if(CollectionUtils.isEmpty(loanInfoIds))
			return new ArrayList<>();
		
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andIdIn(loanInfoIds);
		List<RepaymentLoanInfo> loanInfos =  loanInfoMapper.selectByExample(example);
		
		List<RepaymentLoanInfoBO> loanInfoBOs = new ArrayList<>();
		
		for(String loanInfoId : loanInfoIds){
			RepaymentLoanInfo found = null;
			for(RepaymentLoanInfo loanInfo : loanInfos){
				if(loanInfo.getId().equals(loanInfoId)){
					found = loanInfo;
					break;
				}
			}
			loanInfoBOs.add(found == null ? null : new RepaymentLoanInfoBO(found));
		}
		
		return loanInfoBOs;
	}

	@Override
	public List<RepaymentLoanInfoBO> batchLoadRepaymentLoanInfoBOsByRepaymentPlanBOs(List<RepaymentPlanBO> planBOs)
			throws Exception {
		
		if(CollectionUtils.isEmpty(planBOs))
			return new ArrayList<>();
		
		List<String> loanInfoIds = new ArrayList<>();
		for(RepaymentPlanBO planBO : planBOs)
			loanInfoIds.add(planBO.getLoanInfoId());
		
		List<RepaymentLoanInfoBO> loanInfoBOs = batchLoadRepaymentLoanInfoBOsByLoanInfoIds(loanInfoIds);
		
		for (RepaymentPlanBO planBO : planBOs) {
			RepaymentLoanInfoBO found = null;
			for (RepaymentLoanInfoBO loanInfoBO : loanInfoBOs) {
				if (loanInfoBO.getId().equals(planBO.getLoanInfoId())) {
					found = loanInfoBO;
					break;
				}
			}
			if(found != null)
				planBO.injectLoanInfoBO(found);
		}
		
		return loanInfoBOs;
	}

	@Override
	public RepaymentLoanInfoBO getLoanInfoBOByLoanInfoIdAndFinanceId(String loanInfoId, String financeId) {
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andIdEqualTo(loanInfoId)
								.andFinanceIdEqualTo(financeId);
		
		List<RepaymentLoanInfo> loanInfoList = loanInfoMapper.selectByExample(example);
		
		if(CollectionUtils.isEmpty(loanInfoList))
			return null;
		
		return new RepaymentLoanInfoBO(loanInfoList.get(0));
		
	}

}
