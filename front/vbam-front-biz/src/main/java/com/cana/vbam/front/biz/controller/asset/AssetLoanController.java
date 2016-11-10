package com.cana.vbam.front.biz.controller.asset;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.asset.api.IAssetCreditApi;
import com.cana.asset.api.IAssetFactorBusinessApi;
import com.cana.asset.api.IAssetInvApi;
import com.cana.asset.api.IAssetLoanInfoApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.BusinessAndCreditDTO;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoQueryDTO;
import com.cana.vbam.common.asset.enums.ProjectRepaymentMethodsEnum;
import com.cana.vbam.common.asset.loan.dto.AssetLoanDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanInfoExcelDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanListRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPaidListRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPlanExcelDTO;
import com.cana.vbam.common.asset.loan.dto.EditAssetLoanRequest;
import com.cana.vbam.common.asset.loan.dto.GenerateLoanPlanRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.cana.vbam.front.biz.utils.AssetExcelIEUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping("/asset/loan")
public class AssetLoanController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IAssetLoanInfoApi assetLoanInfoApiImpl;
	@Resource
	private IAssetFactorBusinessApi assetFactorBusinessApiImpl;
	@Resource
	private IAssetCreditApi assetCreditApiImpl;
	@Resource
	private IAssetInvApi assetInvApiImpl;
	
	@RequestMapping(value = "/goto/loanList", method = { RequestMethod.GET })
	public String gotoLoanList() {
		logger.info("进入放款列表页面");
		return "page/asset/loan/loanList";
	}
	
	@RequestMapping(value = "/get/loanList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getLoanList(AssetLoanListRequest assetLoanListRequest) {
		try {
			assetLoanListRequest.setUserId(SecurityContextUtils.getOperatorId());
			return assetLoanInfoApiImpl.getLoanList(assetLoanListRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/get/paidList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getLoanPaidList(AssetLoanPaidListRequest assetLoanPaidListRequest) {
		try {
			assetLoanPaidListRequest.setUserId(SecurityContextUtils.getOperatorId());
			return assetLoanInfoApiImpl.getLoanPaidList(assetLoanPaidListRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/goto/loanDetail", method = { RequestMethod.GET })
	public String gotoLoanDetail(String id, Model model) {
		logger.info("进入放款详情页面");
		String userId = SecurityContextUtils.getOperatorId();
		AssetLoanDTO assetLoanDTO = assetLoanInfoApiImpl.getLoanDetail(id, userId);
		model.addAttribute("assetLoanDTO", assetLoanDTO);
		model.addAttribute("creditDTO", assetCreditApiImpl.getCreditByBusinessContractNo(assetLoanDTO.getBusinessContractNo(), assetLoanDTO.getCustomerId(), userId));
		return "page/asset/loan/loanDetail";
	}
	
	/**
	 * 放款申请－手工输入
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createAssetLoan", method = { RequestMethod.GET })
	public String gotoAddLoanByHand(Model model) {
		logger.info("进入【放款申请－手工输入】页面");
		model.addAttribute("isEditLoan", false);
		addLoanEditModelAttribut(model);
		return "page/asset/loan/addLoanByHand";
	}
	
	/**
	 * 通过业务合同号获取数据
	 * @param businessContractNo
	 * @return
	 */
	@RequestMapping(value = "/get/businessInfo", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> getBusinessInfo(String businessContractNo) {
		BusinessAndCreditDTO businessAndCreditDTO = new BusinessAndCreditDTO();
		try {
			String operatorId = SecurityContextUtils.getOperatorId();
			//放款编号
			businessAndCreditDTO.setLoanInfoId(assetLoanInfoApiImpl.generateAssetLoanInfoId(businessContractNo));
			//业务数据
			FactorBusinessInfoDTO factorBusinessInfoDTO = assetFactorBusinessApiImpl.queryFactorBusinessInfoDTO(businessContractNo,operatorId);
			if(factorBusinessInfoDTO == null)
				throw WebException.instance("业务合同号无效");
			businessAndCreditDTO.setFactorBusinessInfoDTO(factorBusinessInfoDTO);
			//额度数据
			CreditDTO creditDTO = assetCreditApiImpl.getCreditByBusinessContractNo(businessContractNo, factorBusinessInfoDTO.getFinanceCustomerId(), operatorId);
			if(creditDTO==null || creditDTO.getAvailableLimit() == 0)
				throw WebException.instance("没有可用额度");
			businessAndCreditDTO.setCreditDTO(creditDTO);
			return ObjectResult.success("通过业务合同号获取数据成功", businessAndCreditDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 通过交易对手ID获取未使用过的应收账款列表
	 * @param counterpartyId
	 * @return
	 */
	@RequestMapping(value = "/get/unLoanInvoiceList", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> getUnLoanInvoiceList(InvoiceInfoQueryDTO invoiceInfoQueryDTO) {
		try{
			invoiceInfoQueryDTO.setUserId(SecurityContextUtils.getOperatorId());
			return ObjectResult.success("通过交易对手ID获取未使用过的应收账款成功", assetInvApiImpl.getUnloanInvoiceInfoDTOs(invoiceInfoQueryDTO));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 自动生成还款计划
	 */
	@RequestMapping(value = "/generateLoanPlan", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> generateLoanPlanDTO(GenerateLoanPlanRequest request){
		try{
			List<LoanPlanDTO> loanPlanDTOs = assetLoanInfoApiImpl.generateLoanPlanDTO(request);
			return ListResult.success("自动生成还款计划成功",loanPlanDTOs,loanPlanDTOs.size());
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/deleteAssetLoan", method = { RequestMethod.GET })
	@ResponseBody
	public ObjectResult<?> deteleAssetLoan(String loanInfoId) {
		try{
			assetLoanInfoApiImpl.deleteLoanById(SecurityContextUtils.getOperatorId(), loanInfoId);
			return ObjectResult.success("创建放款成功");
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 手工输入　创建放款
	 * @param editAssetLoanRequest
	 * @return
	 */
	@RequestMapping(value = "/createAssetLoan", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> creditAssetLoan(@RequestBody EditAssetLoanRequest editAssetLoanRequest) {
		try{
			return ObjectResult.success("创建放款成功", assetLoanInfoApiImpl.createAssetLoan(SecurityContextUtils.getOperatorId(), editAssetLoanRequest));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 放款修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editAssetLoan", method = { RequestMethod.GET })
	public String gotoEditLoanByHand(String id, Model model) {
		logger.info("进入【放款修改】页面");
		String operatorId = SecurityContextUtils.getOperatorId();
		AssetLoanDTO assetLoanDTO = assetLoanInfoApiImpl.getLoanDetail(id, operatorId);
		model.addAttribute("assetLoanDTO", assetLoanDTO);
		model.addAttribute("isEditLoan", true);
		addLoanEditModelAttribut(model);
		//业务数据
		FactorBusinessInfoDTO factorBusinessInfoDTO = assetFactorBusinessApiImpl.queryFactorBusinessInfoDTO(assetLoanDTO.getBusinessContractNo(),operatorId);
		model.addAttribute("counterpartyDTOList", factorBusinessInfoDTO.getCounterpartyDTOList());
		List<String> accountNoList = new ArrayList<>();
		accountNoList.add(factorBusinessInfoDTO.getFactoringAccount());
		if(!StringUtils.equals(factorBusinessInfoDTO.getFactoringAccount(), factorBusinessInfoDTO.getSettlementAccount()))
			accountNoList.add(factorBusinessInfoDTO.getSettlementAccount());
		model.addAttribute("accountNoList", accountNoList);
		//额度数据
		CreditDTO creditDTO = assetCreditApiImpl.getCreditByBusinessContractNo(assetLoanDTO.getBusinessContractNo(), factorBusinessInfoDTO.getFinanceCustomerId(), operatorId);
		model.addAttribute("creditDTO", creditDTO);
		return "page/asset/loan/addLoanByHand";
	}
	
	@RequestMapping(value = "/updateAssetLoan", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> editAssetLoan(@RequestBody EditAssetLoanRequest editAssetLoanRequest) {
		try{
			return ObjectResult.success("更新放款成功", assetLoanInfoApiImpl.updateAssetLoan(SecurityContextUtils.getOperatorId(), editAssetLoanRequest));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "gotoLoanInfoImport", method = RequestMethod.GET)
	public String gotoLoanInfoImportPage(Model model){
		model.addAttribute("rediskey", assetLoanInfoApiImpl.getLoanInfoRediskey());
		return "page/asset/loan/loanInfoImport";
	}
	
	/**
	 * 导入Excel的按钮
	 * @param excel
	 * @return
	 */
	@RequestMapping(value="importLoanInfoExcel", method=RequestMethod.POST)
	@ResponseBody
	public void importLoanInfoExcel(@RequestParam MultipartFile excel,@RequestParam String rediskey, HttpServletResponse httpServletResponse)throws IOException{
		String result = "true";		
		if(excel != null){
			try{
				List<AssetLoanInfoExcelDTO> list;
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				//读取第一个sheet和 23个单元格
				list = analyzeLoanInfoExcel(inputStream, fileName, 23, 0);
				if (null == list){
					logger.info("Excel数据为空！");
					result = "FAILED";
				}
				//list 存入 redis 数据库
				assetLoanInfoApiImpl.importExcelLoanInfo2Redis(list, SecurityContextUtils.getOperatorId(), rediskey);
			} catch (Exception e) {
				logger.error("读取Excel文件异常",e);
				result = "FAILED";
			}
		}
		else {
			logger.info("Excel文件为空！");
			result = "FAILED";
		}
		httpServletResponse.getWriter().write(result);
	}
	
	@RequestMapping(value = "getLoanInfoFromExcel", method=RequestMethod.POST)
	@ResponseBody
	public ListResult<AssetLoanInfoExcelDTO> getLoanInfoFromRedis(@RequestParam String rediskey, @RequestParam boolean passed,
			@RequestParam int page, @RequestParam int pageSize){

		return assetLoanInfoApiImpl.getLoanInfoFromRedis(rediskey, SecurityContextUtils.getOperatorId(), passed, page, pageSize);

	}
	
	@RequestMapping(value = "importExcelLoanInfoList", method=RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> importLoanInfoExcel2DB(@RequestParam String rediskey){
		try{
			
			assetLoanInfoApiImpl.importLoanInfoExcel2DB(SecurityContextUtils.getOperatorId(), rediskey);
			return ObjectResult.success("放款导入成功");
		}catch(WebException e){
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "gotoLoanPlanImport", method = RequestMethod.GET)
	public String gotoLoanPlanImportPage(Model model){
		model.addAttribute("rediskey", assetLoanInfoApiImpl.getLoanPlanRediskey());
		return "page/asset/loan/loanPlanImport";
	}
	
	/**
	 * 导入Excel的按钮
	 * @param excel
	 * @return
	 */
	@RequestMapping(value="importLoanPlanExcel", method=RequestMethod.POST)
	@ResponseBody
	public void importLoanPlanExcel(@RequestParam MultipartFile excel,@RequestParam String rediskey, HttpServletResponse httpServletResponse)throws IOException{
		String result = "true";		
		if(excel != null){
			try{
				List<AssetLoanPlanExcelDTO> list;
				String fileName = excel.getOriginalFilename();
				InputStream inputStream = excel.getInputStream();
				//读取第一个sheet和 23个单元格
				list = analyzeLoanPlanExcel(inputStream, fileName, 15, 0);
				if (null == list){
					logger.info("Excel数据为空！");
					result = "FAILED";
				}
				//list 存入 redis 数据库
				assetLoanInfoApiImpl.importExcelLoanPlan2Redis(list, SecurityContextUtils.getOperatorId(), rediskey);
			} catch (Exception e) {
				logger.error("读取Excel文件异常",e);
				result = "FAILED";
			}
		}
		else {
			logger.info("Excel文件为空！");
			result = "FAILED";
		}
		httpServletResponse.getWriter().write(result);
	}
	
	@RequestMapping(value = "getLoanPlanFromExcel", method=RequestMethod.POST)
	@ResponseBody
	public ListResult<AssetLoanPlanExcelDTO> getLoanPlanFromRedis(@RequestParam String rediskey, @RequestParam boolean passed,
			@RequestParam int page, @RequestParam int pageSize){

		return assetLoanInfoApiImpl.getLoanPlanFromRedis(rediskey, SecurityContextUtils.getOperatorId(), passed, page, pageSize);

	}
	
	@RequestMapping(value = "importExcelLoanPlanList", method=RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> importLoanPlanExcel2DB(@RequestParam String rediskey){
		try{
			
			assetLoanInfoApiImpl.importLoanPlanExcel2DB(SecurityContextUtils.getOperatorId(), rediskey);
			return ObjectResult.success("还款计划导入成功");
		}catch(WebException e){
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	private List<AssetLoanInfoExcelDTO> analyzeLoanInfoExcel(InputStream inputStream, String fileName, int columnTotalNum, int sheetNum) throws Exception {
        List<AssetLoanInfoExcelDTO> list = Lists.newArrayList();
        List<List<String>> listAll = AssetExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum, sheetNum, 3);
		if(null == listAll || listAll.size()<=0)
		{
			logger.info("Excel文件的输入流为空！");
			return null;
		}
		for(List<String> eachList : listAll){
			int i = 0;
			AssetLoanInfoExcelDTO invDTO = new AssetLoanInfoExcelDTO();
			invDTO.setLoanInfoId(StringUtils.trim(eachList.get(i++)));
			invDTO.setContractNo(StringUtils.trim(eachList.get(i++)));
			invDTO.setFinanceName(StringUtils.trim(eachList.get(i++)));
			invDTO.setCurrency(StringUtils.trim(eachList.get(i++)));
			invDTO.setBusinessProduct(StringUtils.trim(eachList.get(i++)));
			invDTO.setCounterpartyName(StringUtils.trim(eachList.get(i++)));
			invDTO.setInvoiceNo(StringUtils.trim(eachList.get(i++)));
			invDTO.setNominvoiceAmt(StringUtils.trim(eachList.get(i++)));
			invDTO.setInvoiceAmt(StringUtils.trim(eachList.get(i++)));
			invDTO.setFinanceAmount(StringUtils.trim(eachList.get(i++)));
			invDTO.setFinanceBalance(StringUtils.trim(eachList.get(i++)));
			invDTO.setInterestRateUnitDesc(StringUtils.trim(eachList.get(i++))); 
			invDTO.setInterestRate(StringUtils.trim(eachList.get(i++)));
			invDTO.setDayCountConventionStr(StringUtils.trim(eachList.get(i++)));
			invDTO.setLoanDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setDueDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setLoanPeriodUnitDesc(StringUtils.trim(eachList.get(i++)));
			invDTO.setLoanPeriodStr(StringUtils.trim(eachList.get(i++)));
			invDTO.setPenaltyRate(StringUtils.trim(eachList.get(i++)));
			invDTO.setRepaymentTypeDesc(StringUtils.trim(eachList.get(i++)));
			invDTO.setRepaymentAccountNo(StringUtils.trim(eachList.get(i++)));
			EditAssetLoanRequest.Expense expense = new EditAssetLoanRequest.Expense();
			expense.setSubject(StringUtils.trim(eachList.get(i++)));
			expense.setAmount(StringUtils.trim(eachList.get(i++)));
			invDTO.setExpenses(Lists.newArrayList(expense));
			list.add(invDTO);
		}
		return list;
	}
	
	private List<AssetLoanPlanExcelDTO> analyzeLoanPlanExcel(InputStream inputStream, String fileName, int columnTotalNum, int sheetNum) throws Exception {
        List<AssetLoanPlanExcelDTO> list = Lists.newArrayList();
        List<List<String>> listAll = AssetExcelIEUtil.readFromInputStream(inputStream, fileName, columnTotalNum, sheetNum, 3);
		if(null == listAll || listAll.size()<=0)
		{
			logger.info("Excel文件的输入流为空！");
			return null;
		}
		for(List<String> eachList : listAll){
			int i = 0;
			AssetLoanPlanExcelDTO invDTO = new AssetLoanPlanExcelDTO();
			invDTO.setLoanInfoId(StringUtils.trim(eachList.get(i++)));
			invDTO.setFinanceName(StringUtils.trim(eachList.get(i++)));
			invDTO.setFinanceAmount(StringUtils.trim(eachList.get(i++)));
			invDTO.setFinanceBalance(StringUtils.trim(eachList.get(i++)));
			invDTO.setLoanDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setDueDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setPeriod(StringUtils.trim(eachList.get(i++)));
			invDTO.setValueDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setSettleInterestDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setRepaymentDate(StringUtils.trim(eachList.get(i++)));
			invDTO.setAccountPrincipal(StringUtils.trim(eachList.get(i++)));
			invDTO.setAccountInterest(StringUtils.trim(eachList.get(i++)));
			invDTO.setAccountOverdue(StringUtils.trim(eachList.get(i++)));
			invDTO.setAccountAmount(StringUtils.trim(eachList.get(i++)));
			invDTO.setSettleStatusDesc(StringUtils.trim(eachList.get(i++)));
			list.add(invDTO);
		}
		return list;
	}
	
	private void addLoanEditModelAttribut(Model model) {
		model.addAttribute("currencyTypeList", Arrays.asList(Currency.values()));
		model.addAttribute("repaymentMethodList", Arrays.asList(ProjectRepaymentMethodsEnum.values()));
		model.addAttribute("dateUnitList", Arrays.asList(DateUnit.values()));
		model.addAttribute("interestBaseDaysList", new String[]{"360", "365"});
		model.addAttribute("interestRateUnitList", Arrays.asList(InterestRateUnit.values()));
		model.addAttribute("settleStatusList", Arrays.asList(SettleStatus.values()));
	}
	
}
