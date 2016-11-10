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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.ILoanInfoApi;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisIntegration;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.VerifyStatus;
import com.cana.vbam.common.utils.RedisUtils;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.util.ExcelIEUtil;

/**
 * 还款信息录入Controller
 * @author dev3
 *
 */
@Controller
@RequestMapping(value = "/loanInfo/input")
public class LoanInfoInputController {
	
	@Resource
	public ILoanInfoApi loanInfoApi;
	
	@Resource 
	private IUserApi userApi;
	
	private static final Logger LGR = LoggerFactory.getLogger(LoanInfoInputController.class);

	@RequestMapping(value="")
	public String gotoSelectPage() {
		LGR.info("跳转放款信息录入选择页面成功");
		return "page/repayment/input/loanInfoSelect";
	}
	
	@RequestMapping(value="/select")
	public String gotoInputPage(BusinessMode businessMode, InputMethod inputMethod, Model model){
		model.addAttribute("key", loanInfoApi.generateRecordToRedis(businessMode, inputMethod, SecurityContextUtils.getOperatorId()));
		if(businessMode == BusinessMode.FACTORANDFINACE){
			if(inputMethod == InputMethod.EXCEL){
				LGR.info("跳转放款信息EXCEL录入页面成功");
				return "redirect:/loanInfo/input/gotoLoanInfoFromExcel";
			}
			if(inputMethod == InputMethod.MANUAL){
				LGR.info("跳转放款信息手动录入页面成功");
				return "redirect:/loanInfo/input/manual";
			}
		}
		LGR.error("请求参数不合法，跳转放款信息录入页面失败");
		throw WebException.instance("参数不合法");
	}
	
	@RequestMapping(value="/manual")
	public String showManualInputPage(@RequestParam String key, Model model) throws Exception {
		LoanInfoRedisIntegration loanInfoRedisIntegration = loanInfoApi.queryLoanInfoRedisIntegrationFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()));
		if(null == loanInfoRedisIntegration || InputMethod.MANUAL != loanInfoRedisIntegration.getInputMethod()){
			LGR.error("redis中未找到有效的放款信息（为空或录入方式不正确）");
			throw WebException.instance("未找到放款信息");
		}
		LGR.info("redis中找到有效的放款信息");
		model.addAttribute("dateUnits", DateUnit.values());
		model.addAttribute("repaymentTypes", RepaymentType.values());
		model.addAttribute("currencys", Currency.values());
		model.addAttribute("interestRateUnits", InterestRateUnit.values());
		return "page/repayment/input/loanInfoFromManual";
	}
	
	@RequestMapping(value="verifyLoanNo")
	@ResponseBody
	public ObjectResult<Boolean> verifyLoanNo(String value) {
		try {
			return ObjectResult.success("查询放款编号成功", loanInfoApi.isLoanNoExist(value, SecurityContextUtils.getCustomerId()));
		} catch (Exception e) {
			return ObjectResult.fail("查询放款编号失败");
		}
	}
	
	@RequestMapping(value="/queryRepaymentAccounts")
	@ResponseBody
	public ListResult<AccountDTO> queryRepaymentAccounts(String financeCompany) {
		ListResult<AccountDTO> result = new ListResult<AccountDTO>();
		result.setData(loanInfoApi.queryRepaymentAccounts(SecurityContextUtils.getCustomerId(), financeCompany));
		result.setStatus(AjaxResponseStatus.SUCCESS);
		LGR.info("请求后台查询监管账户成功");
		return result;
	}
	
	@RequestMapping(value="/singleSave")
	@ResponseBody
	public ObjectResult<String> saveLoanInfo(@RequestParam String key, LoanInfoRedisDTO loanInfoRedisDTO){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			loanInfoApi.singleSaveToRedis(SecurityContextUtils.getCustomerId(), RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()), loanInfoRedisDTO);
			LoanInfoRedisDTO savedLoanInfo = loanInfoApi.queryOneLoanInfoFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()), loanInfoRedisDTO.getLoanNo());
			if(VerifyStatus.NEGATIVE.name().equals(savedLoanInfo.getVerifyStatus())){
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage(savedLoanInfo.getVerifyFailReason());
				LGR.info("保存到redis成功，但后台校验未通过，原因是：" + savedLoanInfo.getVerifyFailReason());
			}else {
				result.setStatus(AjaxResponseStatus.SUCCESS);
				LGR.info("保存到redis成功");
			}
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("保存失败");
			LGR.error("保存到redis失败", e);
		} 
		return result;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public ObjectResult<String> modifyLoanInfo(@RequestParam String key, LoanInfoRedisDTO loanInfoRedisDTO){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			loanInfoApi.modifyLoanInfoFromRedis(SecurityContextUtils.getCustomerId(), RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()), loanInfoRedisDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("保存失败");
			LGR.error("保存到redis失败", e);
		}
		return result;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public ObjectResult<String> deleteLoanInfo(@RequestParam String key, String loanNo){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			loanInfoApi.deleteLoanInfoFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()), loanNo);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("保存失败");
			LGR.error("保存到redis失败", e);
		}
		return result;
	}
	
	@RequestMapping(value="/query/{status}")
	@ResponseBody
	public ListResult<LoanInfoRedisDTO> queryLoanInfoFromRedis(@RequestParam String key, @PathVariable String status, int page, int pageSize){
		return loanInfoApi.queryLoanInfoFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()), status, page, pageSize);
	}
	
	@RequestMapping(value="/queryOne")
	@ResponseBody
	public ObjectResult<LoanInfoRedisDTO> queryLoanInfoFromRedis(@RequestParam String key, String loanNo) throws Exception{
		ObjectResult<LoanInfoRedisDTO> result = new ObjectResult<LoanInfoRedisDTO>();
		try {
			result.setData(loanInfoApi.queryOneLoanInfoFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()), loanNo));
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询详情失败");
			LGR.error("查询详情失败", e);
		}
		return result;
	}
	
	@RequestMapping(value="/showSuccess")
	public String showSuccessPage(@RequestParam String key, Model model)throws Exception{
		LoanInfoRedisIntegration loanInfoRedisIntegration = loanInfoApi.queryLoanInfoRedisIntegrationFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()));
		model.addAttribute("totalSuccess", loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs() == null ? 0 : loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs().size());
		model.addAttribute("totalFail", loanInfoRedisIntegration.getFailedLoanInfoRedisDTOs() == null ? 0 : loanInfoRedisIntegration.getFailedLoanInfoRedisDTOs().size());
		model.addAttribute("fromWhere", "manul");
		model.addAttribute("key", loanInfoApi.generateRecordToRedis(BusinessMode.FACTORANDFINACE, InputMethod.MANUAL, SecurityContextUtils.getOperatorId()));
		return "page/repayment/input/loanInfoSuccess";
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public ObjectResult<Void> saveLoanInfoToDB(@RequestParam String key, Model model) throws Exception {
		ObjectResult<Void> result = new ObjectResult<>();
		LoanInfoRedisIntegration loanInfoRedisIntegration = loanInfoApi.queryLoanInfoRedisIntegrationFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()));
		if(CollectionUtils.isEmpty(loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs())){
			result.setStatus(AjaxResponseStatus.WARNING);
			result.setMessage("请至少输入一条放款信息");
			LGR.info("手动录入的放款信息为空，保存失败");
			return result;
		}
		try {
			loanInfoApi.saveLoanInfoListToDB(loanInfoRedisIntegration, SecurityContextUtils.getCustomerId());
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("保存放款信息到数据库失败");
			LGR.error("保存放款信息到数据库失败", e);
			return result;
		}
		result.setStatus(AjaxResponseStatus.SUCCESS);
		return result;
	}

	/**
	 * 跳转到还款信息录入Excel页面
	 * @return
	 */
	@RequestMapping(value="gotoLoanInfoFromExcel")
	public String gotoLoanInfoFromExcel(@RequestParam String key,Model model)
	{
		model.addAttribute("key",key);
		return "page/repayment/input/loanInfoFromExcel";
	}
	
	/**
	 * 导入放款信息Excel的按钮
	 * @param excel
	 * @return
	 */
	@RequestMapping(value="importLoanInfoExcel",method=RequestMethod.POST)
	@ResponseBody
	public void importLoanInfoExcel(@RequestParam MultipartFile excel,String redisKey,HttpServletResponse httpServletResponse)throws IOException
	{

		String error = "error";
		String success = "success";
		httpServletResponse.setContentType("text/html");
		if(excel != null)
		{
			try
			{
				List<LoanInfoRedisDTO> list = new ArrayList<>();
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				String factorId = SecurityContextUtils.getCustomerId();
				//读取第一个sheet和 17个单元格
				list = analyzeExcel(inputStream, fileName,18,0);
				if (null == list)
				{
					LGR.info("Excel数据为空！");
					httpServletResponse.getWriter().write(error);
					return;
				}
				list = addRequiredFiledToDTO(list, factorId);//添加必要字段
				//list 存入 redis 数据库
				loanInfoApi.batchSaveToRedis(SecurityContextUtils.getCustomerId(),RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey, SecurityContextUtils.getOperatorId()), list);
				httpServletResponse.getWriter().write(success);
			} catch (Exception e)
			{
				LGR.error("读取Excel文件异常",e);
				httpServletResponse.getWriter().write(error);
			}
		}
		else
		{
			LGR.info("Excel文件为空！");
			httpServletResponse.getWriter().write(error);
		}
	}
	
	/**
	 * 将通过的放款信息存入数据库
	 * @param redisKey
	 * @return
	 */
	@RequestMapping(value="saveLoanInfoFromExcel",method=RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> saveLoanInfoFromExcle(@RequestParam String redisKey)
	{
		ObjectResult<String> result = new ObjectResult<>();
		try
		{
			LoanInfoRedisIntegration loanInfoRedisIntegration = loanInfoApi.queryLoanInfoRedisIntegrationFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey, SecurityContextUtils.getOperatorId()));
			if(CollectionUtils.isEmpty(loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs()))
			{
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage("没有通过的放款信息，请重新上传导入Excel!");
				return result;
			}
			loanInfoApi.saveLoanInfoListToDB(loanInfoRedisIntegration, SecurityContextUtils.getCustomerId());
			LGR.info("保存通过放款信息到数据库成功");
			result.setStatus(AjaxResponseStatus.SUCCESS);
			result.setMessage("确认成功！");
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("保存通过放款信息到数据库失败",e);
			result.setStatus(AjaxResponseStatus.FAILED);
			if("默认还款规则不存在".equals(e.getMessage()))
			{
				result.setMessage("默认还款规则不存在");
				return result;
			}
			result.setMessage("确认失败！");
		}
		return result;
	}
	
	/**
	 * 设置某条放款信息的还款帐号
	 * @param redisKey
	 * @param loanInfoRedisDTO
	 * @return
	 */
	@RequestMapping(value="saveAccountNoToLoanInfo",method=RequestMethod.POST)
	@ResponseBody
	public String saveAccountNoToLoanInfo(@RequestParam String redisKey,LoanInfoRedisDTO loanInfoRedisDTO)
	{
		
		String SUCCESS_SAVE = "设置成功";
		String ERROR_SAVE = "设置失败";
		StringUtil.trim(redisKey);
		StringUtil.trimObjectFields(loanInfoRedisDTO);
		try 
		{
			loanInfoApi.updateLoanInfoAccountNo(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey, SecurityContextUtils.getOperatorId()), loanInfoRedisDTO);
			LGR.info("更新放款信息的还款帐号成功！");
		} catch (Exception e)
		{
			LGR.error("更新放款信息的还款帐号失败！", e);
			return ERROR_SAVE;
		} 
		return SUCCESS_SAVE;
	}
	
	/**
	 * 跳转到保存成功页面
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/gotoSaveSuccessPage")
	public String gotoSaveSuccessPage(@RequestParam String key, Model model)throws Exception{
		LoanInfoRedisIntegration loanInfoRedisIntegration = loanInfoApi.queryLoanInfoRedisIntegrationFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()));
		model.addAttribute("totalSuccess", loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs() == null ? 0 : loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs().size());
		model.addAttribute("totalFail", loanInfoRedisIntegration.getFailedLoanInfoRedisDTOs() == null ? 0 : loanInfoRedisIntegration.getFailedLoanInfoRedisDTOs().size());
		model.addAttribute("fromWhere", "excel");
		model.addAttribute("key", loanInfoApi.generateRecordToRedis(BusinessMode.FACTORANDFINACE, InputMethod.EXCEL, SecurityContextUtils.getOperatorId()));
		loanInfoApi.deleteRecordFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()));
		return "page/repayment/input/loanInfoSuccess";
	}
	
	@RequestMapping(value="/countCorrectData")
	@ResponseBody
	public ObjectResult<Void> countCorrectData(@RequestParam String key) throws Exception {
		ObjectResult<Void> result = new ObjectResult<>();
		LoanInfoRedisIntegration loanInfoRedisIntegration = loanInfoApi.queryLoanInfoRedisIntegrationFromRedis(RedisUtils.generateLoanInfoRedisKeyByOperator(key, SecurityContextUtils.getOperatorId()));
		if(CollectionUtils.isEmpty(loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs())){
			result.setStatus(AjaxResponseStatus.WARNING);
			result.setMessage("请至少输入一条放款信息");
			LGR.info("手动录入的放款信息为空，保存失败");
		}
		return result;
	}
	
	/*---------------------------------------------以下是Controller中的私有方法---------------------------------------*/
	
	private List<LoanInfoRedisDTO> addRequiredFiledToDTO(List<LoanInfoRedisDTO> loanInfoRedisDTOs,String factorId) throws Exception
	{
		String factorCompany = userApi.queryCustomerDetail(factorId).getCompanyName();
		for(LoanInfoRedisDTO loanInfoRedisDTO : loanInfoRedisDTOs)
		{
			//设置资金方公司名称
			loanInfoRedisDTO.setFactorCompany(factorCompany);
			//设置还款帐号和还款帐号Id
			if(StringUtils.isNotBlank(loanInfoRedisDTO.getFinanceCompany()))
			{
				AccountDTO accountDTO = loanInfoApi.getDefaultAccount(factorId, loanInfoRedisDTO.getFinanceCompany());
				if(null != accountDTO)
				{
//					loanInfoRedisDTO.setAccountId(accountDTO.getAccountId());
					loanInfoRedisDTO.setAccountNo(accountDTO.getAccountNo());
					loanInfoRedisDTO.setAccountSupervisionId(accountDTO.getAccountSupervisionId());
				}
			}
		}
		return loanInfoRedisDTOs;
	}
	/**
	 * 从导入的Excel的输入流中读取文件中的信息
	 * @param inputStream
	 * @param fileName Excel的文件名
	 * @return 返回 LoanInfoRedisDTO的list
	 * @throws Exception
	 */
	private List<LoanInfoRedisDTO> analyzeExcel(InputStream inputStream,String fileName,int columnTotalNum,int sheetNum) throws Exception 
	{
		List<LoanInfoRedisDTO> list = new ArrayList<LoanInfoRedisDTO>();
		
		List<List<String>> listAll = ExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum, sheetNum, 3);
		if(null == listAll || listAll.size()<=0)
		{
			LGR.info("Excel文件的输入流为空！");
			return null;
		}
		for(List<String> eachList : listAll)
		{
			int i=0;
			LoanInfoRedisDTO loanInfoRedisDTO = new LoanInfoRedisDTO();
			//放款编号
			loanInfoRedisDTO.setLoanNo(eachList.get(i++));
			//业务合同号
			loanInfoRedisDTO.setBusinessContractNo(eachList.get(i++));
			//融资客户
			loanInfoRedisDTO.setFinanceCompany(eachList.get(i++));
			//核心企业
			loanInfoRedisDTO.setCoreCompanyName(eachList.get(i++));
			//凭证号码
			loanInfoRedisDTO.setVoucherNo(eachList.get(i++));
			//币别
			loanInfoRedisDTO.setCurrency(eachList.get(i++));
			//业务产品
			loanInfoRedisDTO.setBusinessProduct(eachList.get(i++));
			//应收账款金额	
			loanInfoRedisDTO.setReceivablesAmount(eachList.get(i++));
			//应收账款余额
			loanInfoRedisDTO.setReceivablesBalance(eachList.get(i++));
			//融资金额
			loanInfoRedisDTO.setFinanceAmount(eachList.get(i++));
			//融资余额
			loanInfoRedisDTO.setFinanceBalance(eachList.get(i++));
			//利率单位
			loanInfoRedisDTO.setInterestRateUnit(eachList.get(i++));
			//利率
			loanInfoRedisDTO.setInterestRate(eachList.get(i++));
			//放款日
			loanInfoRedisDTO.setLoanDate(eachList.get(i++));
			//到期日
			loanInfoRedisDTO.setDueDate(eachList.get(i++));
			//期限单位
			loanInfoRedisDTO.setLoanPeriodUnit(eachList.get(i++));
			//贷款期限
			loanInfoRedisDTO.setLoanPeriod(eachList.get(i++));
			//还款方式
			loanInfoRedisDTO.setRepaymentMethod(eachList.get(i++));
			//加入list中
			list.add(loanInfoRedisDTO);
		}
		return list;
	}	
}
