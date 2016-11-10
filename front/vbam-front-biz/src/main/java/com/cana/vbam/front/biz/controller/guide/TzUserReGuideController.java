package com.cana.vbam.front.biz.controller.guide;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.asset.api.IAssetApi;
import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.credit.api.ICreditApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.signature.api.ISignatureApi;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.FactorInfo;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.consts.CreditConstants;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListQueryDTO;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.signature.enums.SignType;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.front.biz.utils.WordUtil;
import com.cana.vbam.front.biz.vo.guide.TzUserGuideContext;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.redis.client.SpringRedisClient;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;

/**
 * 真旅新版合同上线之前的企业客户重新签合同页面
 * 
 * 当所有存量企业客户重新签署合同之后，此Controller将不再使用了
 * @author XuMeng
 *
 */
@Controller
@RequestMapping(value = "/reguide")
public class TzUserReGuideController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private IUserApi userApi;
    @Resource
    private IAccountApi accountApi;
    @Resource
    private ICreditApi creditApi;
    @Resource
    private IAssetApi assetApi;
    @Resource
    private IAssetProjectManageApi projectManageApi;
    @Resource
    private ISignatureApi signatureApiImpl;

    private final SpringRedisClient redisCache = SpringRedisClient.getInstance();

	/**
	 * 企业客户重新签订合同企业
	 */
	@RequestMapping(value="/signContract")
	public String confirmContract(Model model) {

		// 检查是否已经签署过合同
		CustomerDetailDTO customerDTO = userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
		ContractInfoDTO contract = assetApi.getContractInfoByMemberId(SecurityContextUtils.getCustomerId(), Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		checkCustomerNeedResignTzContract(customerDTO, contract);

		// 取出监管银行账号
		TzUserGuideContext guideContext = redisCache.get(TzUserGuideContext.getRedisKey());
		if (guideContext == null) {
			guideContext = new TzUserGuideContext();
			guideContext.setIndividual(false);
		}
		guideContext.setSupervisionAccountNo(contract.getAccountNo());

		try {
			if (StringUtils.isBlank(guideContext.getContractId())) {
				guideContext.setContractId(assetApi.generateContractId());
			}
		} catch (Exception e) {
			logger.error("生成合同表Id失败",e);
			throw WebException.instance("生成合同编号失败");
		}
		
		redisCache.save(TzUserGuideContext.getRedisKey(), guideContext, CreditConstants.USER_GUIDE_CONTEXT_EXPIRE_TIME);

		model.addAttribute("supervisionAccountNo", guideContext.getSupervisionAccountNo());
		model.addAttribute("contractId", guideContext.getContractId());
		model.addAttribute("contractName", guideContext.isIndividual() ? CreditConstants.INDIVIDUAL_CONTRACT_NAME : CreditConstants.COMPANY_CONTRACT_NAME);
		model.addAttribute("customerDTO", customerDTO);
		return "/page/guide/travelzen/resign/comfirmInfomation";
	}

	private void checkCustomerNeedResignTzContract(CustomerDetailDTO customerDTO, ContractInfoDTO contract) {
		if (customerDTO.getUserType() != UserType.FINACE || StringUtils.isNotBlank(customerDTO.getIdentityCardNo()))
			throw WebException.instance("你不是信旅宝企业客户，无需重新签署");

		if (contract == null)
			throw WebException.instance("您还未签署过信旅宝合同，无需重新签署");

		if (StringUtils.equals(contract.getFactorId(), CreditConstants.getTzFactorId(false)))
			throw WebException.instance("您已经重新签署了信旅宝合同，谢谢");
	}

	/**
	 * 跳转到签署合同页面
	 * @param isRead 是否读合同超过10s
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/gotoSignContract")
	public String gotoSignContract(@RequestParam(required = false) String isRead, Model model){
		TzUserGuideContext guideContext = redisCache.get(TzUserGuideContext.getRedisKey());
		if (guideContext == null)
			throw WebException.instance("签合同已过期，请重新登陆");
		model.addAttribute("contractId", guideContext.getContractId());
		model.addAttribute("contractName", guideContext.isIndividual() ? CreditConstants.INDIVIDUAL_CONTRACT_NAME : CreditConstants.COMPANY_CONTRACT_NAME);
		model.addAttribute("isRead", isRead);
		model.addAttribute("supervisionAccountNo", guideContext.getSupervisionAccountNo());
		return "/page/guide/travelzen/resign/signContract";
	}

	/**
	 * 在线阅读合同
	 * @param supervisionAccountNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/readContract")
    public String readContract(Model model) {
		TzUserGuideContext guideContext = redisCache.get(TzUserGuideContext.getRedisKey());
		if (guideContext == null)
			throw WebException.instance("签合同已过期，请重新登陆");

		Map<String, String> datas = generateWordData(guideContext);
		for (Map.Entry<String, String> item : datas.entrySet()) {
			model.addAttribute(item.getKey(), item.getValue());
		}
		model.addAttribute("contractName", guideContext.isIndividual() ? CreditConstants.INDIVIDUAL_CONTRACT_NAME : CreditConstants.COMPANY_CONTRACT_NAME);
		return "/page/guide/travelzen/resign/readCompanyContractV2";
	}
	
	@RequestMapping("/getContractData")
	@ResponseBody
	public ObjectResult<byte[]> getContractData(HttpServletRequest request){
		TzUserGuideContext guideContext = redisCache.get(TzUserGuideContext.getRedisKey());
		if (guideContext == null)
			return ObjectResult.fail("签合同已过期，请重新登陆");

		String templatePath = getContractTemplatePath(guideContext.isIndividual());
		Map<String, String> dataMap = generateWordData(guideContext);
		String wordContent;
		try {
			wordContent = WordUtil.generateWordContent(dataMap, templatePath);
			return ObjectResult.success("success",  wordContent.getBytes());
		} catch (IOException e) {
			logger.error("生成合同失败", e);
			return ObjectResult.fail("生成合同失败");
		}

	}
	
	/**
	 * 客户确认签署合同
	 */
	@RequestMapping(value = "/completeContract")
	@ResponseBody
	public ObjectResult<Boolean> completeContract(HttpServletRequest request, String signData, String source) {

		TzUserGuideContext guideContext = redisCache.get(TzUserGuideContext.getRedisKey());
		if (guideContext == null)
			return ObjectResult.fail("签合同已过期，请重新登陆");

		CustomerDetailDTO customerDTO = userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
		ContractInfoDTO contract = assetApi.getContractInfoByMemberId(SecurityContextUtils.getCustomerId(), Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		checkCustomerNeedResignTzContract(customerDTO, contract);

		String contractName = guideContext.isIndividual() ? CreditConstants.INDIVIDUAL_CONTRACT_NAME : CreditConstants.COMPANY_CONTRACT_NAME;
		String supervisionAccountNo = guideContext.getSupervisionAccountNo();
	    String contractId = guideContext.getContractId();
	    String factorId = CreditConstants.getTzFactorId(guideContext.isIndividual());
	    String certSubjectDN = userApi.queyCusomerCertDN(SecurityContextUtils.getCustomerId());

	    String templatePath = getContractTemplatePath(guideContext.isIndividual());
        
	    try{
	    	Map<String, String> dataMap = generateWordData(guideContext);
	    	String wordContent = WordUtil.generateWordContent(dataMap, templatePath);

		    signatureApiImpl.verifySignMessage(signData.getBytes(), certSubjectDN, wordContent.getBytes(), SignType.ATTACH, contractId);

		    AccountDTO accountDTO = accountApi.getOwnAccountByNo(SecurityContextUtils.getCustomerId(), supervisionAccountNo);
		    if (accountDTO.getSupervisionStatus() == AccountSupervisionStatus.HAVE_SUPERVISION)
		    	accountApi.removeSupervision(accountDTO.getFactorId(), Arrays.asList(accountDTO.getAccountId()));
    		accountApi.createSupervisionWithoutAudit(SecurityContextUtils.getCustomerId(), supervisionAccountNo, factorId);
	        List<AccountDTO> accounts = accountApi.getAccountByNos(SecurityContextUtils.getCustomerId(), supervisionAccountNo);
	        upload2MediaAndUpdateContract(contractId, contractName, accounts.get(0), wordContent.getBytes(), factorId);
	        return ObjectResult.success("您的合同已完成电子签名。");
	    } catch (Exception e) {
	    	logger.error(e.getMessage(),e);
	    	return ObjectResult.fail("合同签名失败，请联系管理员。联系电话：021-53866655");
	    }
	}

	private String getContractTemplatePath(boolean individual) {
		String templatePath = individual ? CreditConstants.INDIVIDUAL_CONTRACT_TEMPLATE_PATH : CreditConstants.COMPANY_CONTRACT_TEMPLATE_PATH;
		return getClass().getResource(templatePath).getFile();
	}
	
	/**
	 * 用于组装word页面需要的数据
	 * @return
	 */
	private Map<String, String> generateWordData(TzUserGuideContext guideContext){
		ProjectInfo project = projectManageApi.getProjectInfo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		String factorId = CreditConstants.getTzFactorId(guideContext.isIndividual());
		FactorInfo factor = getFactorInfoFromProject(project, factorId);

		if (factor == null || StringUtils.isBlank(factor.getAccountNo()))
			throw WebException.instance("项目中没有设置资金方信息");

    	CustomerDetailDTO factorCustomerDTO = userApi.queryCustomerDetail(factorId);
    	CustomerDetailDTO finaceCustomerDTO = userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
    	CustomerLimitListQueryDTO customerLimitListQueryDTO = new CustomerLimitListQueryDTO();
    	customerLimitListQueryDTO.setMemberId(SecurityContextUtils.getCustomerId());
//    	PageList<CustomerLimitListResponseDTO> limits = creditApi.getCustomerLimitList(customerLimitListQueryDTO);
//    	String creditLimit = "";
//    	String creditLimitChinese = "";
//    	if (limits != null && CollectionUtils.isNotEmpty(limits.getRecords())) {
//    		creditLimit = limits.getRecords().get(0).getTotalLimit();
//    		creditLimitChinese = MoneyToChineseUtil.cent2Chinese(limits.getRecords().get(0).getTotalLimitLongValue());
//    	}
    	String creditLimit = "2,000,000.00";
    	String creditLimitChinese = "贰佰万";
	    Map<String, String> dataMap = new HashMap<String, String>();
	    dataMap.put("contractId", guideContext.getContractId());
	    dataMap.put("individualLoanPersonName", CreditConstants.INDIVIDUAL_LOAN_PERSON_NAME);
	    dataMap.put("individualLoanPersonIdentity", CreditConstants.INDIVIDUAL_LOAN_PERSON_IDENTITY);
	    dataMap.put("factorCompanyName",factorCustomerDTO.getCompanyName());
	    dataMap.put("factorBusinessLicenceCode",factorCustomerDTO.getBusinessLicenceCode());
	    dataMap.put("finaceCompanyName", finaceCustomerDTO.getCompanyName());
	    dataMap.put("finaceBusinessLicenceCode",finaceCustomerDTO.getBusinessLicenceCode());
	    dataMap.put("creditLimit", creditLimit);
	    dataMap.put("creditLimitChinese", creditLimitChinese);
	    dataMap.put("interestRateRange", "0.03%/日~0.05%/日");
	    dataMap.put("legalPerson", finaceCustomerDTO.getLegalPerson());
	    dataMap.put("transferInAccountNo", AccountNoUtil.formatBankAccountNo(factor.getAccountNo()));
	    dataMap.put("transferInAccountName", factor.getCompanyName());
	    dataMap.put("transferInAccountBankName", "中信银行上海静安支行");
	    dataMap.put("supervisionAccountNo", AccountNoUtil.formatBankAccountNo(guideContext.getSupervisionAccountNo()));
	    if (StringUtils.isBlank(finaceCustomerDTO.getIdentityCardNo())) {
	    	dataMap.put("supervisionAccountName", finaceCustomerDTO.getCompanyName());
	    } else {
	    	dataMap.put("supervisionAccountName", factorCustomerDTO.getCompanyName());
	    }
	    dataMap.put("supervisionAccountBankName", "中信银行上海静安支行");
	    DateTime now = DateTime.now();
	    dataMap.put("year", String.valueOf(now.getYear()));
	    dataMap.put("month", String.valueOf(now.getMonthOfYear()));
	    dataMap.put("day", String.valueOf(now.getDayOfMonth()));
	    return dataMap;
	}

	private FactorInfo getFactorInfoFromProject(ProjectInfo project, String factorId) {
		if (project == null)
			throw WebException.instance("项目不存在");
		if (CollectionUtils.isEmpty(project.getFactors()))
			throw WebException.instance("项目中资金方不存在");
		for (FactorInfo factor : project.getFactors())
			if (StringUtils.equals(factor.getCompanyId(), factorId))
				return factor;
		return null;
	}

	/**
	 * 將word合同文件上传到媒体服务器并保存到合同表
	 */
	private void upload2MediaAndUpdateContract(String contractId, String contractName, AccountDTO account,
			byte[] wordContent, String factorId) throws Exception {
    	String fileName = contractName;
		String fileSuffix = "doc";
        String mediaId = MediaClientUtil.upload(wordContent, MediaType.IMAGE, fileName + "." + fileSuffix);
		
        ContractInfoDTO contractInfoDTO = new ContractInfoDTO();
		contractInfoDTO.setContractId(contractId);
		contractInfoDTO.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		contractInfoDTO.setMemberId(SecurityContextUtils.getCustomerId());
		contractInfoDTO.setFactorId(factorId);
		contractInfoDTO.setMediaId(mediaId);
		contractInfoDTO.setFileName(fileName);
		contractInfoDTO.setFileSuffix(fileSuffix);
		contractInfoDTO.setAccountNo(account.getAccountNo());
		contractInfoDTO.setAccountSupervisionId(account.getAccountSupervisionId());
		contractInfoDTO.setEffectiveDate(DateTimeUtil.getTodayStr());
		contractInfoDTO.setDueDate(DateTimeUtil.date10(DateTimeUtil.addYear(new DateTime(), CreditConstants.CONTRACT_EFFECTIVE_YEARS)));
		assetApi.updateContractByMemberIdAndProductId(contractInfoDTO);
	}
	
}
