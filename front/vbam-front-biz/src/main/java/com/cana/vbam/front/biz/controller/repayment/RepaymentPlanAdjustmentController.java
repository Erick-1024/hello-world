package com.cana.vbam.front.biz.controller.repayment;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.ILoanInfoApi;
import com.cana.repayment.api.IRepaymentPlanAdjustmentApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.repayment.dto.AccountRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentSingleDistributeDetailDTO;
import com.cana.vbam.common.utils.RedisUtils;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping(value = "/repayment/adjustment")
public class RepaymentPlanAdjustmentController {

	private static final Logger LGR = LoggerFactory.getLogger(RepaymentPlanInuputController.class);  
	
	@Resource
	private IRepaymentPlanAdjustmentApi repaymentPlanAdjustmentApi;
	
	@Resource
	private ILoanInfoApi loanInfoApi;
	
	@RequestMapping("/toAdjustment")
	public String gotoAdjustmentPage(@RequestParam String loanInfoId,Model model) throws Exception{
		String redisKey = repaymentPlanAdjustmentApi.getPlanAndExpenseToRedis(SecurityContextUtils.getOperatorId(), loanInfoId, SecurityContextUtils.getCustomerId());
		model.addAttribute("loanInfoElements",loanInfoApi.getLoanInfoElements(loanInfoId));
		model.addAttribute("redisKey", redisKey);
		model.addAttribute("loanInfoId", loanInfoId);
		model.addAttribute("userType", SecurityContextUtils.getUserDTOFromSession().getUserType().name());
		return "/page/repayment/offline/repaymentPlanAdjustment";
	}
	
	@RequestMapping("/getPaidRepaymentPlan")
	@ResponseBody
	public ListResult<PaidRepaymentPlanRedisDTO> getPaidRepaymentInfo(String redisKey,int page,int pageSize){
		ListResult<PaidRepaymentPlanRedisDTO> result = new ListResult<PaidRepaymentPlanRedisDTO>();
		try {
			result = repaymentPlanAdjustmentApi.queryPaidRepaymentPlan(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey, SecurityContextUtils.getOperatorId()), page, pageSize);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/getAccountRepaymentPlan")
	@ResponseBody
	public ListResult<AccountRepaymentPlanRedisDTO> getAccountRepaymentInfo(String redisKey,int page,int pageSize){
		ListResult<AccountRepaymentPlanRedisDTO> result = new ListResult<AccountRepaymentPlanRedisDTO>();
		try {
			result = repaymentPlanAdjustmentApi.queryAccountRepaymentPlan(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey, SecurityContextUtils.getOperatorId()), page, pageSize);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/getPaidRepaymentExpense")
	@ResponseBody
	public ListResult<PaidRepaymentExpenseRedisDTO> getPaidRepaymentExpense(String redisKey,int page,int pageSize){
		ListResult<PaidRepaymentExpenseRedisDTO> result = new ListResult<PaidRepaymentExpenseRedisDTO>();
		try {
			result = repaymentPlanAdjustmentApi.queryPaidRepaymentExpense(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()), page, pageSize);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/autoAllocationCharge")
	@ResponseBody
	public ObjectResult<String> autoAllocation(String redisKey,String loanInfoId,String charge){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			result = repaymentPlanAdjustmentApi.autoAllocationCharge(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()), loanInfoId, charge);
			
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/getAccountRepaymentPlanSingleLine")
	@ResponseBody
	public ObjectResult<AccountRepaymentPlanRedisDTO> getAccountRepaymentPlanSingleLine(String redisKey,String planId){
		ObjectResult<AccountRepaymentPlanRedisDTO> result = new ObjectResult<AccountRepaymentPlanRedisDTO>();
		try {
			AccountRepaymentPlanRedisDTO accountRepaymentPlan = repaymentPlanAdjustmentApi.queryAccountRepaymentPlanFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()), planId);
			result.setData(accountRepaymentPlan);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/getPaidRepaymentIncrement")
	@ResponseBody
	public ObjectResult<PaidRepaymentPlanRedisDTO> getPaidRepaymentIncrement(String redisKey,String planId){
		ObjectResult<PaidRepaymentPlanRedisDTO> result = new ObjectResult<PaidRepaymentPlanRedisDTO>();
		try {
			PaidRepaymentPlanRedisDTO paidRepaymentIncrement = repaymentPlanAdjustmentApi.queryPaidRepaymentIncrementFromPlan(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()), planId);
			result.setData(paidRepaymentIncrement);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping("/getHistoryRepaymentDetail")
	@ResponseBody
	public ListResult<RepaymentSingleDistributeDetailDTO> getHistoryRepaymentDetail(String planId){
		ListResult<RepaymentSingleDistributeDetailDTO> result = new ListResult<RepaymentSingleDistributeDetailDTO>();
		try {
			result = repaymentPlanAdjustmentApi.queryHistoryRepaymentDetailFromDB(planId);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/updateCurrentRepaymentExpense")
	@ResponseBody
	public ObjectResult<String> updateCurrentRepaymentExpense(String redisKey,String expenseId,String paidAmount,String repaymentDate){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			repaymentPlanAdjustmentApi.calculateRepaymentExpense(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()), expenseId, paidAmount, repaymentDate);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/accountExpenseEdit")
	@ResponseBody
	public ObjectResult<String> expenseEdit(String redisKey,String expenseId,String accountExpenseAmount){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			repaymentPlanAdjustmentApi.accountRepaymentExpenseEdit(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()), expenseId, accountExpenseAmount);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/updateAccountRepaymentInfo")
	@ResponseBody
	public ObjectResult<String> updateAccountRepaymentInfo(String redisKey,AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			repaymentPlanAdjustmentApi.accountRepaymentPlanEdit(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()), accountRepaymentPlanRedisDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/paidRepaymentInfoAdjustment")
	@ResponseBody
	public ObjectResult<String> paidRepaymentInfoAdjustment(String redisKey,PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			repaymentPlanAdjustmentApi.paidRepaymentPlanAdjustment(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()), paidRepaymentPlanRedisDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/savePlanAndExpenseSummaryAndDetail")
	public String savePlanAndExpenseSummaryAndDetail(String redisKey,String loanInfoId, String flag,String changeType, Model model) throws Exception{
		repaymentPlanAdjustmentApi.saveRepaymentSummaryAndDetailToDB(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()),
				loanInfoId, SecurityContextUtils.getOperatorId(), flag, changeType);
		model.addAttribute("loanInfoId", loanInfoId);
		if(StringUtils.isNotBlank(flag) && flag.equals("0")){
			return "redirect:/repayment/adjustment/gotoAdjustmentResult";
		}
		return "redirect:/repayment/adjustment/gotoOfflineResult";
	}
	
	@RequestMapping("/gotoOfflineResult")
	public String gotoOfflineResult(@RequestParam String loanInfoId, Model model){
		model.addAttribute("loanInfoId", loanInfoId);
		return "/page/repayment/offline/repaymentPlanAdjustmentResult";
	}
	
	@RequestMapping("/gotoAdjustmentResult")
	public String gotoAdjustmentResult(@RequestParam String loanInfoId, Model model){
		model.addAttribute("loanInfoId", loanInfoId);
		return "/page/repayment/adjustment/repaymentAdjustmentResult";
	}
	
	@RequestMapping("/gotoAdjustment")
	public String gotoRepaymentAdjustmentPage(@RequestParam String loanInfoId, Model model) throws Exception{
		String redisKey = repaymentPlanAdjustmentApi.getPlanAndExpenseToRedis(SecurityContextUtils.getOperatorId(), loanInfoId, SecurityContextUtils.getCustomerId());
		model.addAttribute("loanInfoElements",loanInfoApi.getLoanInfoElements(loanInfoId));
		model.addAttribute("redisKey", redisKey);
		model.addAttribute("loanInfoId", loanInfoId);
		model.addAttribute("userType", SecurityContextUtils.getUserDTOFromSession().getUserType().name());
		return "/page/repayment/adjustment/repaymentAdjustment";
	}
	
	@RequestMapping("/submitValidate")
	@ResponseBody
	public ObjectResult<String> submitValidate(String redisKey){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			repaymentPlanAdjustmentApi.submitValidate(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()));
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("/redirectValidate")
	@ResponseBody
	public ObjectResult<String> redirectValidate(String redisKey){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			repaymentPlanAdjustmentApi.redirectValidate(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()));
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("系统错误");
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
}
