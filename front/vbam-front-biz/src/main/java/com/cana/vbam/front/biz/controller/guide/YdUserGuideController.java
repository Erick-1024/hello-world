package com.cana.vbam.front.biz.controller.guide;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cana.account.api.IAccountApi;
import com.cana.asset.api.IAssetApi;
import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.credit.api.ICreditApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.signature.api.ICertApplyApi;
import com.cana.signature.api.ISignatureApi;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountSelfCreateDTO;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.asset.dto.ProjectFactorDTO;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.signature.enums.SignType;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.front.biz.utils.WordUtil;
import com.cana.yundaex.api.IYundaexAuditApi;
import com.cana.yundaex.api.IYundaexContractSituationApi;
import com.cana.yundaex.api.IYundaexPersonalInfoApi;
import com.cana.yundaex.common.dto.contract.ContractSituationRequestDTO;
import com.cana.yundaex.common.enums.ContractSignState;
import com.cana.yundaex.common.enums.PersonalInfoType;
import com.cana.yundaex.common.util.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;

/**
 * @author hu
 *
 */
@Controller
@RequestMapping(value = "/guide/yundaex")
public class YdUserGuideController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String SYS_ACCOUNT_BRANCH = "中信银行上海静安支行";
    private final String YD_CONTRACT_TEMPLATE_PATH = "/template/yundaex/financeContractTemplate.doc";
    @Resource
    private IUserApi userApi;
    @Resource
    private IAccountApi accountApi;
    @Resource
    private ICreditApi creditApi;
    @Resource
    private IAssetApi assetApi;
    
    @Resource
    private ISignatureApi signatureApiImpl; 
    
    @Resource
    private IYundaexAuditApi ydAuditApi;
    @Resource
    private IYundaexContractSituationApi ydcontractApi;
    @Resource
    private IYundaexPersonalInfoApi ydPersonalInfoApi;
    @Resource
	private IAssetProjectManageApi assetProjectApi;
	
	/**
	 * 融资商引导信息确认页面的确认按钮
	 * @param isCreartNewAccount
	 * @param supervisionAccountNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/confirmInfomation", method = RequestMethod.GET)
	public String confirmContract(@RequestParam Boolean isCreateNewAccount,@RequestParam String supervisionAccountNo, Model model){
		String contractId = null;
		try {
			contractId = ydcontractApi.generateFinanceContractSerialNumber(Constants.SERIAL_NUMBER_PREFIX);
		} catch (Exception e) {
			logger.error("生成合同表Id失败",e);
		}
		
		if(isCreateNewAccount == true){
			String customerId = SecurityContextUtils.getCustomerId();
	        AccountSelfCreateDTO info = new AccountSelfCreateDTO();
	        info.setCustomerId(customerId);
	        info.setAccountNumber(1);
            List<String> accountNos = accountApi.createAccountBySelf(info);
            supervisionAccountNo = accountNos.get(0);
		}
		model.addAttribute("supervisionAccountNo",supervisionAccountNo);
		model.addAttribute("contractId", contractId);
		return "/page/guide/yundaex/signContract";
	}
	
	/**
	 * 跳转到签署合同页面
	 * @param isRead 是否读合同超过10s
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/gotoSignContract", method = RequestMethod.GET)
	public String confirmContract(@RequestParam String isRead,@RequestParam String supervisionAccountNo,@RequestParam String contractId, Model model){
		model.addAttribute("contractId", contractId);
		model.addAttribute("isRead",isRead);
		model.addAttribute("supervisionAccountNo",supervisionAccountNo);
		return "/page/guide/yundaex/signContract";
	}

	/**
	 * 在线阅读合同
	 * @param supervisionAccountNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/readContract")
    public String readContract(@RequestParam String supervisionAccountNo,@RequestParam String contractId,Model model) {
		ProjectDTO project = assetProjectApi.getProjectDetail(Constants.YUNDAEX_ASSET_PROJECT_ID);
		ProjectFactorDTO factorDTO = project.getProjectFactors().get(0);
//    	CustomerDetailDTO factorCustomerDTO = userApi.queryCustomerDetail(project.getProjectFactors().get(0).getCompanyId());
//    	CustomerDetailDTO finaceCustomerDTO = userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
    	YdCustomerApplyDetailDTO cusApplyDTO = ydAuditApi.getUserBaseInfo(SecurityContextUtils.getCustomerId());
    	model.addAttribute("contractId", contractId);
		model.addAttribute("factorCompanyName",factorDTO.getCompanyName());
		model.addAttribute("finaceCompanyName",cusApplyDTO.getStationName());
		model.addAttribute("finaceCompanyLegalPerson",cusApplyDTO.getLegalPerson());
        model.addAttribute("finaceCompanyAddress",cusApplyDTO.getDetailAddress());
        model.addAttribute("factorFinancingRadio", Constants.FACTOR_FINANCING_RADIO);
        model.addAttribute("financeCreditMax", Constants.FINANCE_CREDIT_MAX);
        model.addAttribute("creditDuePeriod", Constants.CREDIT_DUE_PERIOD);
        model.addAttribute("financeRadio", Constants.FINANCE_RADIO);
        model.addAttribute("factorRadio", Constants.FACTOR_RADIO);
        model.addAttribute("contractDuePeriod", Constants.CONTRACT_DUE_PERIOD);
        model.addAttribute("supervisionAccountNo",supervisionAccountNo);
        model.addAttribute("supervisionAccountBranch",SYS_ACCOUNT_BRANCH);
        model.addAttribute("custPhone",cusApplyDTO.getCustPhone());
        model.addAttribute("payAccountNo", cusApplyDTO.getPayAccount());
        model.addAttribute("payAccountAddress", cusApplyDTO.getPayAccountAddress());
        model.addAttribute("payAccountName", cusApplyDTO.getPayAccountName());
        model.addAttribute("tranferInAccountNo", factorDTO.getAccountNo());
		return "/page/guide/yundaex/contract";
	}
	
	/**
	 * 下载合同
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadContract")
	public ModelAndView downloadContract(HttpServletResponse response, HttpServletRequest request){
	    String outFileName = "国内保理业务合同-v1.3信韵融";
	    Map<String, String> dataMap = packageWordData(request);
	    try {
		    outFileName = new String(outFileName.getBytes(), "utf-8");
		    // 设置头
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("multipart/form-data");
		    // 文件名的中文乱码问题及其浏览器的兼容问题
		    if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) { // IE
		        outFileName = java.net.URLEncoder.encode(outFileName, "UTF-8");
		    } else {
		        outFileName = new String(outFileName.getBytes("UTF-8"), "ISO8859-1"); // 其他
		    }
		    response.setHeader("Content-Disposition", "attachment;filename=" + outFileName + ".doc");
		    String templatePath = getClass().getResource(YD_CONTRACT_TEMPLATE_PATH).getFile();
	    
	        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath,dataMap);
	        response.getOutputStream().write(wordContent);
	    } catch (IOException e) {
	        logger.error("生成合同内容失败",e);
	    } finally {
	    	try{
	    		response.flushBuffer();
	    	}catch (IOException e) {
    	        logger.error("清空response缓存失败",e);
    	    }
	    }
	    return null;
	}
	
	@RequestMapping("/getContractData")
	@ResponseBody
	public ObjectResult<byte[]> getContractData(HttpServletRequest request){
		Map<String, String> dataMap = packageWordData(request);
		String templatePath = getClass().getResource(YD_CONTRACT_TEMPLATE_PATH).getFile();
	    
        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath,dataMap);
        return ObjectResult.success("success",  wordContent);
	}
	
	/**
	 * 完成合同
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/completeContract")
	@ResponseBody
	public ObjectResult<Boolean> completeContract(HttpServletRequest request, String signData, String source) {
		String supervisionAccountNo = request.getParameter("supervisionAccountNo");
	    String contractId = request.getParameter("contractId");
	    ProjectDTO project = assetProjectApi.getProjectDetail(Constants.YUNDAEX_ASSET_PROJECT_ID);
		ProjectFactorDTO factorDTO = project.getProjectFactors().get(0);
    	Map<String, String> dataMap = packageWordData(request);
	    String templatePath = getClass().getResource(YD_CONTRACT_TEMPLATE_PATH).getFile();
	    String certSubjectDN = userApi.queyCusomerCertDN(SecurityContextUtils.getCustomerId());
        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath, dataMap);
        
	    try{
		    signatureApiImpl.verifySignMessage(signData.getBytes(), certSubjectDN, wordContent, SignType.ATTACH, contractId);
	    }catch(Exception e){
	    	logger.error(e.getMessage(),e);
	    	return ObjectResult.fail("合同签名失败，请联系管理员。联系电话：021-53866655");
        }
	    String customerId = SecurityContextUtils.getCustomerId();
	    try {
	    	
	    	accountApi.createSupervisionWithoutAudit(customerId, supervisionAccountNo, factorDTO.getCompanyId());
	        AccountDTO account = accountApi.getOwnAccountByNo(customerId, supervisionAccountNo);
	        upload2MediaAndUpdateContract(contractId,account,wordContent, factorDTO.getCompanyId());
//	        creditApi.activateCreditLimit(SecurityContextUtils.getOperatorId());
	        PersonalInfoType type = ydPersonalInfoApi.sendPersonalInfoLink(customerId);
	        createContractSituation(contractId, customerId, type);
	        userApi.updateUserGuideStatus(customerId, UserGuideStatus.NEED_GENERATE_CONTRACT_YUNDAEX, UserGuideStatus.COMFIRMED_CONTRACT_YUNDAEX);
	        
	    } catch (Exception e) {
	    	logger.error(e.getMessage(),e);
	    	return ObjectResult.fail("签署合同失败，请重新签署合同！");
	    }
	    
	    try{
	    	ydcontractApi.sendFinanceContractSignedMessage(customerId);
	    }catch(Exception e){
	    	logger.error(e.getMessage(), e);
	    }
	    return ObjectResult.success("您的合同已完成电子签名。");
	}
	
	/**
	 * 用于组装word页面需要的数据
	 * @return
	 */
	private Map<String, String> packageWordData(HttpServletRequest request){
		String supervisionAccountNo = request.getParameter("supervisionAccountNo");
		System.out.println(supervisionAccountNo);
	    String contractId = request.getParameter("contractId");
	    System.out.println(contractId);
	    // 用于组装word页面需要的数据
	    ProjectDTO project = assetProjectApi.getProjectDetail(Constants.YUNDAEX_ASSET_PROJECT_ID);
		ProjectFactorDTO factorDTO = project.getProjectFactors().get(0);
//    	CustomerDetailDTO factorCustomerDTO = userApi.queryCustomerDetail(factorId);
    	YdCustomerApplyDetailDTO cusApplyDTO = ydAuditApi.getUserBaseInfo(SecurityContextUtils.getCustomerId());
    	Map<String, String> dataMap = new HashMap<String, String>();
	    dataMap.put("${contractId!}",contractId);
	    dataMap.put("${factorCompanyName!}",factorDTO.getCompanyName());
	    dataMap.put("${finaceCompanyName!}",cusApplyDTO.getStationName());
	    dataMap.put("${finaceCompanyLegalPerson!}",cusApplyDTO.getLegalPerson());
	    dataMap.put("${finaceCompanyAddress!}", cusApplyDTO.getDetailAddress());
	    dataMap.put("${factorFinancingRadio!}", Constants.FACTOR_FINANCING_RADIO);
	    dataMap.put("${financeCreditMax!}", Constants.FINANCE_CREDIT_MAX);
	    dataMap.put("${creditDuePeriod!}", Constants.CREDIT_DUE_PERIOD);
	    dataMap.put("${financeRadio!}", Constants.FINANCE_RADIO);
	    dataMap.put("${factorRadio!}", Constants.FACTOR_RADIO);
	    dataMap.put("${contractDuePeriod!}", Constants.CONTRACT_DUE_PERIOD);
	    dataMap.put("${custPhone!}", cusApplyDTO.getCustPhone());
	    dataMap.put("${supervisionAccountNo!}",supervisionAccountNo);
	    dataMap.put("${supervisionAccountBranch!}",SYS_ACCOUNT_BRANCH);
	    dataMap.put("${payAccountNo!}",cusApplyDTO.getPayAccount());
	    dataMap.put("${payAccountAddress!}",cusApplyDTO.getPayAccountAddress());
	    dataMap.put("${payAccountName!}",cusApplyDTO.getPayAccountName());
	    dataMap.put("${tranferInAccountNo!}",factorDTO.getAccountNo());
	    
	    return dataMap;
	}
	
	/**
	 * 將word合同文件上传到媒体服务器并保存到合同表
	 * @param contractId
	 * @param wordContent
	 * @throws Exception
	 */
	private void upload2MediaAndUpdateContract(String contractId,AccountDTO account,byte[] wordContent,String factorId) throws Exception{
    	String fileName = "国内保理业务合同-v1.3信韵融";
		String fileSuffix = "doc";
        String mediaId = MediaClientUtil.upload(wordContent,MediaType.IMAGE, fileName+"."+fileSuffix);
		
        ContractInfoDTO contractInfoDTO = new ContractInfoDTO();
		contractInfoDTO.setContractId(contractId);
		contractInfoDTO.setProductId(Constants.YUNDAEX_ASSET_PROJECT_ID);
		contractInfoDTO.setMemberId(SecurityContextUtils.getCustomerId());
		contractInfoDTO.setFactorId(factorId);
		contractInfoDTO.setMediaId(mediaId);
		contractInfoDTO.setFileName(fileName);
		contractInfoDTO.setFileSuffix(fileSuffix);
		contractInfoDTO.setAccountNo(account.getAccountNo());
		contractInfoDTO.setAccountSupervisionId(account.getAccountSupervisionId());
		contractInfoDTO.setEffectiveDate(DateTimeUtil.getTodayStr());
		contractInfoDTO.setDueDate(DateTimeUtil.date10(DateTimeUtil.addYear(new DateTime(), Integer.parseInt(Constants.CONTRACT_DUE_PERIOD))));
		assetApi.updateContractByMemberIdAndProductId(contractInfoDTO);
	} 
	
	private void createContractSituation(String contractId, String customerId, PersonalInfoType type){
		YdCustomerApplyDetailDTO cusApplyDTO = ydAuditApi.getUserBaseInfo(customerId);
    	
		ContractSituationRequestDTO requestDTO = new ContractSituationRequestDTO();
		requestDTO.setFinanceContractSignState(ContractSignState.SIGNED.name());
		requestDTO.setProtocolNo(contractId);
		requestDTO.setStationName(cusApplyDTO.getStationName());
		requestDTO.setPayAccountName(cusApplyDTO.getPayAccountName());
		requestDTO.setPayAccountNo(cusApplyDTO.getPayAccount());
		requestDTO.setPayAccountBank(cusApplyDTO.getPayAccountAddress());
		requestDTO.setCustomerId(customerId);
		requestDTO.setPayLianHangNo(cusApplyDTO.getLianHangNo());
		if(type == null){
			requestDTO.setSignCompleteTime(new Date());
		}else{
			if(!PersonalInfoType.CONTROLLER.equals(type)){
				requestDTO.setCreditContractSignState(ContractSignState.UNSIGNED.name());
			}
			if(!PersonalInfoType.ACCOUNT_HOLDER.equals(type)){
				requestDTO.setDutyContractSignState(ContractSignState.UNSIGNED.name());
			}
		}
        ydcontractApi.createOrUpdateContractSituation(requestDTO);

	}
}
