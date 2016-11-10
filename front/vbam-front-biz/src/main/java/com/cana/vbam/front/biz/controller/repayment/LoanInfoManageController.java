package com.cana.vbam.front.biz.controller.repayment;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.ILoanInfoApi;
import com.cana.repayment.api.IRepaymentPlanApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.LoanInfoElementsDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoSearchCriteriaDTO;
import com.cana.vbam.common.repayment.dto.RepaymentDetailsHistoryDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseDBDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanDBDTO;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.StringUtil;

/**
 * 
 * @author dev3
 *
 */
@Controller
@RequestMapping("/loanInfo/manage")
public class LoanInfoManageController
{
	private static final Logger LGR = LoggerFactory.getLogger(LoanInfoManageController.class);
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IRepaymentPlanApi repaymentPlanApi;
	
	@Resource
	private ILoanInfoApi loanInfoApi;
	
	/**
	 * 跳转到还款信息管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoLoanInfoManage")
	public String gotoLoanInfoList(Model model)
	{
		try
		{
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			model.addAttribute("userType",userSessionDTO.getUserType().name());
			LGR.info("前往放款信息列表页面。");
		} catch (Exception e)
		{
			LGR.error("获取用户信息失败！",e);
			throw WebException.instance("获取用户信息失败！");
		}
		return "/page/repayment/manage/loanInfoManage";
	}
	
	/**
	 * 获取放款信息List
	 * @param loanInfoSearchCriteriaDTO
	 * @return
	 */
	@RequestMapping("/getLoanInfoList")
	@ResponseBody
	public ListResult<LoanInfoRedisDTO> getLoanInfoList(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO){
		StringUtil.trimObjectFields(loanInfoSearchCriteriaDTO);
		ListResult<LoanInfoRedisDTO> result = new ListResult<LoanInfoRedisDTO>();
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		loanInfoSearchCriteriaDTO.setUserType(userSessionDTO.getUserType());
		try {
			result = loanInfoApi.queryLoanInfoList(SecurityContextUtils.getCustomerId(), loanInfoSearchCriteriaDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询放款信息列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询放款信息列表失败,原因："+e.getMessage());
			LGR.error("查询放款信息列表失败", e);
		}
		return result;
	}
	
	/**
	 * 跳转到还款计划详情页面
	 * @param loanId
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoRepaymentPlanDetails")
	public String gotoRepaymentPlanDetails(@RequestParam String loanId,Model model){
		try{
			String masterId = SecurityContextUtils.getCustomerId();
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			LoanInfoElementsDTO loanInfoElementsDTO = loanInfoApi.getLoanInfoElements(loanId);
			if(UserType.CANA != userSessionDTO.getUserType()){
				if(!masterId.equals(loanInfoElementsDTO.getFactorId()) && !masterId.equals(loanInfoElementsDTO.getFinanceId()) 
						&& !masterId.equals(loanInfoElementsDTO.getCoreCompanyId()))
					throw WebException.instance("您没有权限访问该页面！");
			}
			model.addAttribute("accessToActiveRepayment", loanInfoApi.accessToActiveRepayment(loanId));
			model.addAttribute("userType",userSessionDTO.getUserType().name());
			model.addAttribute("loanInfoElements",loanInfoElementsDTO);
			LGR.info("获取放款要素页面成功！");
		}catch (Exception e){
			if(e instanceof WebException)
				throw (WebException)e;
			else {
				LGR.error("获取放款要素页面失败！",e);
				throw WebException.instance("获取放款要素页面失败！");
			}
		}
		return "/page/repayment/manage/repaymentPlanDetails";
	}
	
	/**
	 * 设置某条放款信息的还款帐号到数据库
	 * @param redisKey
	 * @param loanInfoRedisDTO
	 * @return
	 */
	@RequestMapping(value="saveAccountNoToDB",method=RequestMethod.POST)
	@ResponseBody
	public String saveAccountNoToDB(LoanInfoRedisDTO loanInfoRedisDTO)
	{
		
		String SUCCESS_SAVE = "设置成功";
		String ERROR_SAVE = "设置失败";
		StringUtil.trimObjectFields(loanInfoRedisDTO);
		try 
		{
			loanInfoApi.updateAccountNoToDB(loanInfoRedisDTO);
			LGR.info("更新放款信息的还款帐号成功！");
		} catch (Exception e)
		{
			LGR.error("更新放款信息的还款帐号失败！", e);
			return ERROR_SAVE;
		} 
		return SUCCESS_SAVE;
	}
	
	/**
	 * 根据放款信息Id从数据库中获取还款计划列表
	 * @param loanInfoSearchCriteriaDTO
	 * @return
	 */
	@RequestMapping(value="getRepaymentPlanFromDB",method=RequestMethod.POST)
	@ResponseBody
	public ListResult<RepaymentPlanDBDTO> getRepaymentPlanFromDB(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO)
	{
		ListResult<RepaymentPlanDBDTO> result = new ListResult<RepaymentPlanDBDTO>();
		try {
			result = repaymentPlanApi.queryRepaymentPlanFromDB(loanInfoSearchCriteriaDTO); 
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询还款计划列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询还款计划列表失败,原因："+e.getMessage());
			LGR.error("查询还款计划列表失败", e);
		}
		return result;
	}
	
	/**
	 * 根据放款信息Id从数据库中获取费用列表
	 * @param loanInfoSearchCriteriaDTO
	 * @return
	 */
	@RequestMapping(value="getExpenseListFromDB",method=RequestMethod.POST)
	@ResponseBody
	public ListResult<RepaymentExpenseDBDTO> getExpenseListFromDB(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO)
	{
		ListResult<RepaymentExpenseDBDTO> result = new ListResult<RepaymentExpenseDBDTO>();
		try 
		{
			result = repaymentPlanApi.queryExpenseListFromDB(loanInfoSearchCriteriaDTO); 
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询费用列表成功");
		} catch (Exception e) 
		{
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询费用列表失败,原因："+e.getMessage());
			LGR.error("查询费用列表失败", e);
		}
		return result;
	}
	
	/**
	 * 获取还款详情弹窗所需的信息
	 * @param repaymentPlanId
	 * @return
	 */
	@RequestMapping(value="getRepaymentDetails",method=RequestMethod.POST)
	@ResponseBody
	public ObjectResult<RepaymentPlanDBDTO> getRepaymentDetails(@RequestParam String repaymentPlanId)
	{
		ObjectResult<RepaymentPlanDBDTO> result = new ObjectResult<RepaymentPlanDBDTO>();
		try {
			result = repaymentPlanApi.queryRepaymentDetails(repaymentPlanId);
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询还款计划列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询还款计划列表失败,原因："+e.getMessage());
			LGR.error("查询还款计划列表失败", e);
		}
		return result;
	}
	
	/**
	 * 根据放款信息Id从数据库中获取历史还款明细列表
	 * @param loanId
	 * @return
	 */
	@RequestMapping(value="getRepaymentDetailsHistory",method=RequestMethod.POST)
	@ResponseBody
	public ListResult<RepaymentDetailsHistoryDTO> getRepaymentDetailsHistory(@RequestParam String loanId)
	{
		ListResult<RepaymentDetailsHistoryDTO> result = new ListResult<RepaymentDetailsHistoryDTO>();
		try {
			result = repaymentPlanApi.queryRepaymentDetailsHistory(loanId);
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询历史还款明细列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询历史还款明细列表失败,原因："+e.getMessage());
			LGR.error("查询历史还款明细列表失败", e);
		}
		return result;
	}
}
