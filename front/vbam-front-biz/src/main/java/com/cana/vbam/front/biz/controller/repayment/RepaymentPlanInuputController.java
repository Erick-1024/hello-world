package com.cana.vbam.front.biz.controller.repayment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.ILoanInfoApi;
import com.cana.repayment.api.IRepaymentPlanApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoSearchCriteriaDTO;
import com.cana.vbam.common.repayment.dto.PageNumCountDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanInfoRedisIntegration;
import com.cana.vbam.common.repayment.dto.RepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.utils.RedisUtils;
import com.travelzen.framework.util.ExcelIEUtil;

/**
 *
 * @since Dec 4, 20155:27:07 PM
 * @author dev1
 *
 */
@Controller
@RequestMapping("/repayment/plan/input")
public class RepaymentPlanInuputController {
	
	private static final Logger LGR = LoggerFactory.getLogger(RepaymentPlanInuputController.class);  
	
	@Resource
	private IRepaymentPlanApi repaymentPlanApi;
	
	@Resource
	private ILoanInfoApi loanInfoApi;
	
	@RequestMapping("/planSelect")
	public String gotoSelectPage(){
		return "/page/repayment/input/planSelect";
	}
	
	@RequestMapping("/planInput")
	public String gotoInputPage(BusinessMode businessMode, InputMethod inputMethod, Model model) throws Exception{
		if(null != businessMode && null != inputMethod){
			if(businessMode == BusinessMode.FACTORANDFINACE){
				if(inputMethod == InputMethod.EXCEL){
					String redisKey = repaymentPlanApi.saveRepaymentPlanRedisWithModeAndMethod(businessMode.name(), inputMethod.name(), "", SecurityContextUtils.getOperatorId());
					return "redirect:/repayment/plan/input/gotoPlanExcelInput?redisKey=" + redisKey;
				}else if(inputMethod == InputMethod.MANUAL){
					model.addAttribute("businessMode", businessMode);
					model.addAttribute("inputMethod", inputMethod);
					return "redirect:/repayment/plan/input/gotoLoanInfoList";
				}
			}
			
		}
		return "/page/repayment/input/planSelect";
	}
	
	@RequestMapping("/gotoLoanInfoList")
	public String gotoLoanInfoList(BusinessMode businessMode, InputMethod inputMethod, Model model){
		model.addAttribute("businessMode", businessMode);
		model.addAttribute("inputMethod", inputMethod);
		return "/page/repayment/input/planLoanInfoSelect";
	}
	
	@RequestMapping("/queryLoanInfoList")
	@ResponseBody
	public ListResult<LoanInfoRedisDTO> queryLoanInfoList(LoanInfoSearchCriteriaDTO searchDTO){
		ListResult<LoanInfoRedisDTO> result = new ListResult<LoanInfoRedisDTO>();
		try {
			result = loanInfoApi.queryLoanInfoListFromDB(SecurityContextUtils.getCustomerId(), searchDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询放款信息列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询放款信息列表失败");
			LGR.error("查询放款信息列表失败", e);
		}
		return result;
	}
	
	@RequestMapping("/gotoPlanManualInputSelect")
	public String gotoManualInputPage(@RequestParam String loanInfoId, BusinessMode businessMode, InputMethod inputMethod, Model model) throws Exception{
		if(null != businessMode && null != inputMethod){
			String redisKey = repaymentPlanApi.saveRepaymentPlanRedisWithModeAndMethod(businessMode.name(), inputMethod.name(), loanInfoId, SecurityContextUtils.getOperatorId());
			model.addAttribute("loanInfoId", loanInfoId);
			model.addAttribute("redisKey", redisKey);
			return "redirect:/repayment/plan/input/gotoPlanManualInput";
		}
		model.addAttribute("businessMode", businessMode);
		model.addAttribute("inputMethod", inputMethod);
		return "redirect:/repayment/plan/input/gotoLoanInfoList";
	}
	
	@RequestMapping("/gotoPlanManualInput")
	public String gotoManualInput(String loanInfoId, String redisKey, Model model) throws Exception{
		
		repaymentPlanApi.prepareForManualInput(RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId()), loanInfoId, SecurityContextUtils.getCustomerId());
		model.addAttribute("redisKey", redisKey);
		model.addAttribute("loanInfoId", loanInfoId);
//		model.addAttribute("loanInfoRedisDTO", loanInfoApi.queryLoanInfodetailFromDB(id));
		model.addAttribute("loanInfoElements",loanInfoApi.getLoanInfoElements(loanInfoId));
		return "/page/repayment/input/planManualInput";
	}
	
	@RequestMapping("/gotoPlanExcelInput")
	public String gotoExcelInput(String redisKey, Model model){
		model.addAttribute("redisKey", redisKey);
		
		return "/page/repayment/input/planExcelInput";
	}
	
	@RequestMapping("/createPlanToRedis")
	@ResponseBody
	public ObjectResult<String> createPlanInfoRedis(String redisKey, RepaymentPlanRedisDTO repaymentPlanRedisDTO,String id){
		ObjectResult<String> result = new ObjectResult<String>();
		if(StringUtils.isNotBlank(redisKey)){
			try {
				repaymentPlanApi.saveRepaymentPlanRedisDTOSingleLine(
						RedisUtils.generateRepaymentPlanRedisKeyByOperator(id , redisKey, SecurityContextUtils.getOperatorId()),
						repaymentPlanRedisDTO,SecurityContextUtils.getCustomerId(),id);
				result.setStatus(AjaxResponseStatus.SUCCESS);
			} catch (Exception e) {
				LGR.error(e.getMessage());
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage(e.getMessage());
			}
		}
		return result;
	}
	
	@RequestMapping("/getSinglePlanFromRedis")
	@ResponseBody
	public ObjectResult<RepaymentPlanRedisDTO> getSinglePlanFromRedis(String loanInfoId, String redisKey, String id){
		ObjectResult<RepaymentPlanRedisDTO> result = new ObjectResult<RepaymentPlanRedisDTO>();
		RepaymentPlanRedisDTO repaymentPlanRedisDTO = null;
		if(StringUtils.isNotBlank(redisKey) && StringUtils.isNotBlank(id)){
			try {
				repaymentPlanRedisDTO = repaymentPlanApi.queryRepaymentPlanRedisDTOSingleLine(RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId())
						, id);
				if(repaymentPlanRedisDTO != null){
					result.setStatus(AjaxResponseStatus.SUCCESS);
					result.setData(repaymentPlanRedisDTO);
				}else{
					result.setStatus(AjaxResponseStatus.FAILED);
					result.setMessage("信息不存在");
				}
			} catch (Exception e) {
				LGR.error(e.getMessage());
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage(e.getMessage());
			}
		}
		return result;
	}
	
	@RequestMapping("/updateSinglePlanFromRedis")
	@ResponseBody
	public ObjectResult<String> updateSinglePlanFromRedis(String redisKey,RepaymentPlanRedisDTO repaymentPlanRedisDTO,String loanInfoId){
		ObjectResult<String> result = new ObjectResult<String>();
		if(StringUtils.isNotBlank(redisKey)){
			try {
				repaymentPlanApi.updateRepaymentPlanRedisDTOSingleLine(
						RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId()),
						repaymentPlanRedisDTO,SecurityContextUtils.getCustomerId(),loanInfoId);
				result.setStatus(AjaxResponseStatus.SUCCESS);
			} catch (Exception e) {
				LGR.error(e.getMessage());
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage(e.getMessage());
			}
		}
		return result;
	}
	
	@RequestMapping("/deleteSinglePlanFromRedis")
	@ResponseBody
	public ObjectResult<String> deleteSinglePlanFromRedis(String loanInfoId, String redisKey, String id){
		ObjectResult<String> result = new ObjectResult<String>();
		if(StringUtils.isNotBlank(redisKey) && StringUtils.isNotBlank(id)){
			try {
				repaymentPlanApi.deleteRepaymentPlanRedisDTOSingleLine(
						RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId()), id);
				result.setStatus(AjaxResponseStatus.SUCCESS);
				} catch (Exception e) {
					LGR.error(e.getMessage());
					result.setStatus(AjaxResponseStatus.FAILED);
					result.setMessage(e.getMessage());
				}
		}
		return result;
	}
	
	@RequestMapping("/createExpenseToRedis")
	@ResponseBody
	public ObjectResult<String> createExpenseInfoRedis(@RequestParam String loanInfoId, @RequestParam String redisKey, RepaymentExpenseRedisDTO repaymentExpenseRedisDTO){
		ObjectResult<String> result = new ObjectResult<String>();
		if(StringUtils.isNotBlank(redisKey)){
			try {
				repaymentPlanApi.saveRepaymentExpenseRedisDTOSingleLine(
						RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId()),
						repaymentExpenseRedisDTO,SecurityContextUtils.getCustomerId());
				result.setStatus(AjaxResponseStatus.SUCCESS);
			} catch (Exception e) {
				LGR.error(e.getMessage());
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage(e.getMessage());
			}
		}
		return result;
	}
	
	@RequestMapping("/getSingleExpenseFromRedis")
	@ResponseBody
	public ObjectResult<RepaymentExpenseRedisDTO> getSingleExpenseFromRedis(String loanInfoId, String redisKey, String id){
		ObjectResult<RepaymentExpenseRedisDTO> result = new ObjectResult<RepaymentExpenseRedisDTO>();
		if(StringUtils.isNotBlank(redisKey) && StringUtils.isNotBlank(id)){
			try {
				result.setData(repaymentPlanApi.queryRepaymentExpenseRedisDTOSingleLine(
						RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId()), id));
				result.setStatus(AjaxResponseStatus.SUCCESS);
			} catch (Exception e) {
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage("redis查询费用记录出错");
				LGR.error("redis查询费用记录出错", e);
			}
		}
		return result;
	}
	
	@RequestMapping("/updateSingleExpenseFromRedis")
	@ResponseBody
	public ObjectResult<String> updateSingleExpenseFromRedis(@RequestParam String redisKey,RepaymentExpenseRedisDTO repaymentExpenseRedisDTO){
		ObjectResult<String> result = new ObjectResult<String>();
		if(StringUtils.isNotBlank(redisKey)){
			try {
				repaymentPlanApi.updateRepaymentExpenseRedisDTOSingleLine(
						RedisUtils.generateRepaymentPlanRedisKeyByOperator(repaymentExpenseRedisDTO.getRepaymentInfoId(), redisKey, SecurityContextUtils.getOperatorId()),
						repaymentExpenseRedisDTO,SecurityContextUtils.getCustomerId());
				result.setStatus(AjaxResponseStatus.SUCCESS);
			} catch (Exception e) {
				LGR.error(e.getMessage());
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage(e.getMessage());
			}
		}
		return result;
	}
	
	@RequestMapping("/deleteSingleExpenseFromRedis")
	@ResponseBody
	public ObjectResult<String> deleteSingleExpenseFromRedis(String loanInfoId, String redisKey, String id){
		ObjectResult<String> result = new ObjectResult<String>();
		if(StringUtils.isNotBlank(redisKey) && StringUtils.isNotBlank(id)){
			try {
				repaymentPlanApi.deleteRepaymentExpenseRedisDTOSingleLine(
						RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId()), id);
				result.setStatus(AjaxResponseStatus.SUCCESS);
			} catch (Exception e) {
				LGR.error(e.getMessage());
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage(e.getMessage());
			}
		}
		return result;
	}
	
	/**
	 * 导入放款信息Excel的Controller
	 * @param excel
	 * @return
	 */
	@RequestMapping(value="importRepaymentPlanExcel",method=RequestMethod.POST)
	@ResponseBody
	public void importRepaymentPlanExcel(@RequestParam MultipartFile excel,@RequestParam String redisKey,HttpServletResponse httpServletResponse) throws IOException{
		String error = "error";
		String success = "success";
		List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOList = new ArrayList<>();
		List<RepaymentExpenseRedisDTO> repaymentExpenseRedisDTOList = new ArrayList<RepaymentExpenseRedisDTO>();
		httpServletResponse.setContentType("text/html");
		if(excel != null){
			try{
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				InputStream inputStreamForFeeList = excel.getInputStream();
				//读取第一个sheet
				repaymentPlanRedisDTOList = readRepaymentPlanFromInputStream(inputStream, fileName,15,0);
				repaymentExpenseRedisDTOList = readRepaymentExpenseFromInputStream(inputStreamForFeeList, fileName,6,1);
			} catch (Exception e){
				LGR.error("读取Excel文件异常",e);
				httpServletResponse.getWriter().write(error);
			}
			try {
				repaymentPlanApi.saveRepaymentPlanInfoRedisIntegration(
						RedisUtils.generateRepaymentPlanRedisKeyByOperator("", redisKey, SecurityContextUtils.getOperatorId()),
						repaymentPlanRedisDTOList, repaymentExpenseRedisDTOList,SecurityContextUtils.getCustomerId());
			} catch (Exception e) {
				LGR.error(e.getMessage());
				httpServletResponse.getWriter().write(error);
			}
			httpServletResponse.getWriter().write(success);
		}else{
			LGR.info("Excel文件为空！");
			httpServletResponse.getWriter().write(error);
		}
	}
	
	@RequestMapping("/getPlanByRedisKey")
	@ResponseBody
	public ListResult<RepaymentPlanRedisDTO> getPlanByRedisKey(String redisKey,String dataType,int page, int pageSize,String id, Model model){
		ListResult<RepaymentPlanRedisDTO> result = new ListResult<RepaymentPlanRedisDTO>();
		try {
			RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = repaymentPlanApi.getRepaymentPlanInfoRedisIntegration(RedisUtils.generateRepaymentPlanRedisKeyByOperator(id, redisKey, SecurityContextUtils.getOperatorId()));
			if(StringUtils.equals("1", dataType)){
				if(StringUtils.equals(InputMethod.MANUAL.name(), repaymentPlanInfoRedisIntegration.getInputMethod())){
					List<RepaymentPlanRedisDTO> repaymentPlanExist = repaymentPlanApi.queryRepaymentPlanExist(id);
					if(CollectionUtils.isNotEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect()) ){
						repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect().addAll(repaymentPlanExist);
					}else{
						repaymentPlanInfoRedisIntegration.setRepaymentPlanCorrect(repaymentPlanExist);
					}
				}
				if(CollectionUtils.isNotEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect())){
					result.setTotalNum(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect().size());
					result.setData(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect())));
				}else{
					result.setTotalNum(0);
					result.setData(null);
				}
				result.setStatus(AjaxResponseStatus.SUCCESS);
			}else if(StringUtils.equals("2", dataType)){
				if(CollectionUtils.isNotEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect())){
					result.setTotalNum(repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect().size());
					result.setData(repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect())));
				}else{
					result.setTotalNum(0);
					result.setData(null);
				}
				result.setStatus(AjaxResponseStatus.SUCCESS);
			}
		} catch (Exception e) {
			LGR.error(e.getMessage());
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/getExpenseByRedisKey")
	@ResponseBody
	public ListResult<RepaymentExpenseRedisDTO> getExpenseByRedisKey(String redisKey,String dataType,int page, int pageSize,String id, Model model) throws Exception{
		ListResult<RepaymentExpenseRedisDTO> result = new ListResult<RepaymentExpenseRedisDTO>();
		try {
			RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = repaymentPlanApi.getRepaymentPlanInfoRedisIntegration(RedisUtils.generateRepaymentPlanRedisKeyByOperator(id, redisKey, SecurityContextUtils.getOperatorId()));
			if(StringUtils.equals("1", dataType)){
				if(StringUtils.equals(InputMethod.MANUAL.name(), repaymentPlanInfoRedisIntegration.getInputMethod())){
					List<RepaymentExpenseRedisDTO> repaymentExpenseExist = repaymentPlanApi.queryRepaymentExpenseRedisDTOExist(id);
					if(CollectionUtils.isNotEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect()) ){
						repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect().addAll(repaymentExpenseExist);
					}else{
						repaymentPlanInfoRedisIntegration.setRepaymentExpenseCorrect(repaymentExpenseExist);
					}
				}
				if(CollectionUtils.isNotEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect())){
					result.setData(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect())));
					result.setTotalNum(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect().size());
				}else{
					result.setTotalNum(0);
					result.setData(null);
				}
			}else if(StringUtils.equals("2", dataType)){
				if(CollectionUtils.isNotEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect())){
					result.setData(repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect().subList(getStartIndex(page, pageSize),getEndIndex(page, pageSize, repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect())));
					result.setTotalNum(repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect().size());
				}else{
					result.setTotalNum(0);
					result.setData(null);
				}
			}
		} catch (Exception e) {
			LGR.error(e.getMessage());
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/getNumByRedisKey")
	@ResponseBody
	public ObjectResult<PageNumCountDTO> getNumFromRedis(String loanInfoId, String redisKey){
		ObjectResult<PageNumCountDTO> result = new ObjectResult<PageNumCountDTO>();
		PageNumCountDTO pageNumCountDTO = new PageNumCountDTO();
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = null;
		try {
			//设置Excel导入显示成功失败的条数
			repaymentPlanInfoRedisIntegration = repaymentPlanApi.getRepaymentPlanInfoRedisIntegration(RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId()));
			pageNumCountDTO.setTotalNumForPlan(CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect()) ? 0:repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect().size()
					+ (CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect()) ? 0:repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect().size()));
			pageNumCountDTO.setTotalCorrectNumForPlan(CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect()) ? 0:repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect().size());
			pageNumCountDTO.setTotalIncorrectNumForPlan(CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect()) ? 0:repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect().size());
			pageNumCountDTO.setTotalNumForExpense(CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect()) ? 0:repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect().size()
					+ (CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect()) ? 0:repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect().size()));
			pageNumCountDTO.setTotalCorrectNumForExpense(CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect()) ? 0:repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect().size());
			pageNumCountDTO.setTotalIncorrectNumForExpense(CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect()) ? 0:repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect().size());
			result.setData(pageNumCountDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			LGR.error(e.getMessage());
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/calculateRepaymentPeriod")
	@ResponseBody
	public ObjectResult<String> calculateRepaymentPeriod(String redisKey, String id){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			result = repaymentPlanApi.getRepaymentPeriod(RedisUtils.generateRepaymentPlanRedisKeyByOperator(id, redisKey, SecurityContextUtils.getOperatorId()), id);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/saveCorrectRepaymentPlanAndExpense")
	public String saveCorrectRepaymentPlanAndExpense(String redisKey, String loanInfoIdForSave,Model model) throws Exception{
		repaymentPlanApi.saveRepaymentPlanAndExpense(RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoIdForSave, redisKey, SecurityContextUtils.getOperatorId()),SecurityContextUtils.getCustomerId(),loanInfoIdForSave);
		model.addAttribute("loanInfoId", null == loanInfoIdForSave ? "" : loanInfoIdForSave);
		model.addAttribute("redisKey", redisKey);
		return "redirect:/repayment/plan/input/planResult";
	}
	
	@RequestMapping("/planResult")
	public String planResult( @RequestParam String loanInfoId, @RequestParam String redisKey, Model model) throws Exception{
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = repaymentPlanApi.getRepaymentPlanInfoRedisIntegration(RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, redisKey, SecurityContextUtils.getOperatorId()));
		model.addAttribute("correctRepaymentPlanNum", CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect()) ? "0":repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect().size());
		model.addAttribute("incorrectRepaymentPlanNum", CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect()) ? "0":repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect().size());
		model.addAttribute("correctRepaymentExpenseNum", CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect()) ? "0":repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect().size());
		model.addAttribute("incorrectRepaymentExpenseNum", CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect()) ? "0":repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect().size());
		return "/page/repayment/input/planSaveResult";
	}
	
	
	private List<RepaymentPlanRedisDTO> readRepaymentPlanFromInputStream(InputStream inputStream,String fileName,int columnTotalNum,int sheetNum) throws Exception {
		List<RepaymentPlanRedisDTO> list = new ArrayList<RepaymentPlanRedisDTO>();
		List<List<String>> listAll = ExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum, sheetNum, 3);
		if(null == listAll|| listAll.size()<=0){
			LGR.info("Excel文件的输入流为空！");
			return list;
		}
		for(List<String> eachList : listAll){
			int i=0;
			RepaymentPlanRedisDTO repaymentPlanRedisDTO = new RepaymentPlanRedisDTO();
			//还款编号
			repaymentPlanRedisDTO.setLoanNo(eachList.get(i++));
			//融资客户
			repaymentPlanRedisDTO.setFinanceCompany(eachList.get(i++));
			//融资金额
			repaymentPlanRedisDTO.setFinanceAmount(eachList.get(i++));
			//融资余额
			repaymentPlanRedisDTO.setFinanceBalance(eachList.get(i++));
			//放款日
			repaymentPlanRedisDTO.setLoanDate(eachList.get(i++));
			//到期日
			repaymentPlanRedisDTO.setDueDate(eachList.get(i++));
			//期数
			repaymentPlanRedisDTO.setRepaymentPeriod(eachList.get(i++));
			//收益计算日
			repaymentPlanRedisDTO.setValueDate(eachList.get(i++));
			//收益分配日
			repaymentPlanRedisDTO.setSettleInterestDate(eachList.get(i++));
			//还款日
			repaymentPlanRedisDTO.setRepaymentDate(eachList.get(i++));
			//应还本金
			repaymentPlanRedisDTO.setAccountRepaymentPrincipal(eachList.get(i++));
			//应还收益
			repaymentPlanRedisDTO.setAccountRepaymentInterest(eachList.get(i++));
			//应还服务费
			repaymentPlanRedisDTO.setAccountRepaymentServiceCharge(eachList.get(i++));
			//应还总金额
			repaymentPlanRedisDTO.setAccountRepaymentTotal(eachList.get(i++));
			//结清状态
			repaymentPlanRedisDTO.setSettleStatus(eachList.get(i++));
			//加入list中
			list.add(repaymentPlanRedisDTO);
		}
		return list;
	}
	
	private List<RepaymentExpenseRedisDTO> readRepaymentExpenseFromInputStream(InputStream inputStream,String fileName,int columnTotalNum,int sheetNum) throws Exception {
		List<RepaymentExpenseRedisDTO> list = new ArrayList<RepaymentExpenseRedisDTO>();
		List<List<String>> listAll = ExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum, sheetNum, 3);
		if(null == listAll|| listAll.size()<=0){
			LGR.info("Excel文件的输入流为空！");
			return list;
		}
		for(List<String> eachList : listAll){
			int i = 0;
			RepaymentExpenseRedisDTO repaymentExpenseRedisDTO = new RepaymentExpenseRedisDTO();
			//还款编号
			repaymentExpenseRedisDTO.setLoanNo(eachList.get(i++));
			//融资客户
			repaymentExpenseRedisDTO.setFinanceCompany(eachList.get(i++));
			//费用名目
			repaymentExpenseRedisDTO.setExpenseSubject(eachList.get(i++));
			//还款日
			repaymentExpenseRedisDTO.setRepaymentDate(eachList.get(i++));
			//应还金额
			repaymentExpenseRedisDTO.setRepaymentAmount(eachList.get(i++));
			//结清状态
			repaymentExpenseRedisDTO.setSettleStatus(eachList.get(i++));
			//加入list中
			list.add(repaymentExpenseRedisDTO);
		}
		
		return list;
	}

	private int getStartIndex(int page, int pageSize){
		return (page - 1) * pageSize;
	}
	
	private int getEndIndex(int page, int pageSize, List<?> curList) {
		return page * pageSize < curList.size() ? page * pageSize : curList.size();
	}
}
