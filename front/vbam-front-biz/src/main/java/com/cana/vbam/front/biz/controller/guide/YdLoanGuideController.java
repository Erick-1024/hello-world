package com.cana.vbam.front.biz.controller.guide;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.signature.api.ISignatureApi;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.signature.enums.SignType;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyQueryDTO;
import com.cana.vbam.front.biz.utils.WordUtil;
import com.cana.yundaex.api.IYundaexContractSituationApi;
import com.cana.yundaex.api.IYundaexInterstRateApi;
import com.cana.yundaex.api.IYundaexLoanApplyApi;
import com.cana.yundaex.common.dto.InterestRateDTO;
import com.cana.yundaex.common.dto.contract.ContractSituationResultDTO;
import com.cana.yundaex.common.enums.RepaymentMethod;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

@Controller
@RequestMapping(value = "/yundaex/loan")
public class YdLoanGuideController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IYundaexInterstRateApi yundaexInterstRateApi;
	
	@Resource
	private IYundaexContractSituationApi contractApi;
	
	@Resource
	private ISignatureApi signatureApiImpl;
	
    @Resource
    private IUserApi userApi;
    
    @Resource
	private IYundaexLoanApplyApi yundaexloanApplyApi;
    
    @Resource
	private IAssetProjectManageApi assetProjectApi;
    
    @Resource
    private IYundaexContractSituationApi ydcontractApi;
	
	private final String YD_SINGLE_LOAN_PATH = "/template/yundaex/singleLoanTemplate.doc";
	
	/**
	 * 检验用款申请信息
	 * @return
	 */
	@RequestMapping(value = "/checkLoanApply")
	@ResponseBody
	public ObjectResult<String> checkLoanApply(String applyAmt, String proId) {
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			String masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId() : userSessionDTO.getMasterId();
			// 检验数据
			checkLoanApplyData(applyAmt, masterId);
			return ObjectResult.success("成功");
		} catch (Exception e) {
			logger.error("参数校验失败", e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	/**
	 * 融资商引导信息确认页面的确认按钮
	 * @param isCreartNewAccount
	 * @param supervisionAccountNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/confirmInfomation", method = RequestMethod.GET)
	public String confirmContract(@RequestParam String applyAmt,@RequestParam String proId, Model model){
		model.addAttribute("applyAmt", applyAmt);
		model.addAttribute("proId", proId);
		return "/page/guide/yundaex/loanSignContract";
	}

	/**
	 * 跳转到签署合同页面
	 * @param isRead 是否读合同超过10s
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/gotoSignContract", method = RequestMethod.GET)
	public String confirmContract(@RequestParam String isRead,@RequestParam String applyAmt,@RequestParam String proId, Model model){
		model.addAttribute("applyAmt", applyAmt);
		model.addAttribute("isRead", isRead);
		model.addAttribute("proId", proId);
		return "/page/guide/yundaex/loanSignContract";
	}
	
	/**
	 * 在线阅读合同
	 * @param supervisionAccountNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/readContract")
	public String readContract(@RequestParam String applyAmt, @RequestParam String proId, Model model) {
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		String masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId() : userSessionDTO.getMasterId();
		InterestRateDTO rate = yundaexInterstRateApi.getInterestRateById(proId);
		String repaymentMethod = rate.getRepaymentMethod();
		String rateStr = String.valueOf(MoneyArithUtil.convertInterestRateToString(rate.getInterestRate())) + "/日";
		String loanPeriod = RepaymentMethod.getValues(rate.getRepaymentMethod()).deadLine().desc();
		ContractSituationResultDTO contractDTO = contractApi.getContractSituationById(masterId);
		int countLoan = yundaexloanApplyApi.countYundaexLoanInfoRecord(masterId) + 1;
		String batchNo = contractDTO.getProtocolNo() + "-" + countLoan;
		
		model.addAttribute("batchNo", batchNo); // 合同号批次
		model.addAttribute("companyName", userSessionDTO.getCompanyName()); // 乙方名称
		model.addAttribute("protocolNo", contractDTO.getProtocolNo()); // 编号
		model.addAttribute("applyAmt", applyAmt); // 放款金额
		model.addAttribute("rate", rateStr); // 利率
		model.addAttribute("loanPeriod", loanPeriod); // 期限
		model.addAttribute("repaymentMethod", repaymentMethod); // 还款方式
		model.addAttribute("proId", proId);
		
		return "/page/guide/yundaex/loanContract";
	}
	
	/**
	 * 签署合同word流
	 * @param request
	 * @return
	 */
	@RequestMapping("/getContractData")
	@ResponseBody
	public ObjectResult<byte[]> getContractData(HttpServletRequest request){
		Map<String, String> dataMap = packageWordData(request);
		String templatePath = getClass().getResource(YD_SINGLE_LOAN_PATH).getFile();
	    
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
		String applyAmt = request.getParameter("applyAmt"); // 申请金额
	    String proId = request.getParameter("proId"); // 产品ID
	    UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
	    
	    Map<String, String> dataMap = packageWordData(request);
	    String templatePath = getClass().getResource(YD_SINGLE_LOAN_PATH).getFile();
	    String certSubjectDN = userApi.queyCusomerCertDN(SecurityContextUtils.getCustomerId());
        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath, dataMap);
        
		String contractId = null;
		try {
			contractId = ydcontractApi.generateSingleLoanNumber();
		} catch (Exception e) {
			logger.error("生成合同表Id失败", e);
		}
		
	    try{
		    signatureApiImpl.verifySignMessage(signData.getBytes(), certSubjectDN, wordContent, SignType.ATTACH, contractId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
	    	return ObjectResult.fail("合同签名失败，请联系管理员。联系电话：021-53866655");
        }
	    try {
	    	YundaexLoanApplyQueryDTO applyQueryDTO = new YundaexLoanApplyQueryDTO();
	    	applyQueryDTO.setProId(proId); // 产品编号 
	    	applyQueryDTO.setLoanAmt(applyAmt); // 申请金额
	    	applyQueryDTO.setProtocolNo(dataMap.get("${protocolNo!}")); // 保理合同ID
	    	applyQueryDTO.setContractId(contractId); // 单笔合同ID
	    	applyQueryDTO.setWordContent(wordContent); // word
	    	yundaexloanApplyApi.creditLoanApply(applyQueryDTO, userSessionDTO);
	    } catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
	    }
	    
	    return ObjectResult.success("您的合同已完成电子签名。");
	}
	
	/**
	 * 用于组装word页面需要的数据
	 * @return
	 */
	private Map<String, String> packageWordData(HttpServletRequest request){
		String applyAmt = request.getParameter("applyAmt");
		String proId = request.getParameter("proId");

		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		String masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId() : userSessionDTO.getMasterId();
		InterestRateDTO rate = yundaexInterstRateApi.getInterestRateById(proId);
		String repaymentMethod = rate.getRepaymentMethod();
		String rateStr = String.valueOf(MoneyArithUtil.convertInterestRateToString(rate.getInterestRate())) + "/日";
		String loanPeriod = RepaymentMethod.getValues(rate.getRepaymentMethod()).deadLine().desc();
		ContractSituationResultDTO contractDTO = contractApi.getContractSituationById(masterId);
		int countLoan = yundaexloanApplyApi.countYundaexLoanInfoRecord(masterId) + 1;
		String batchNo = contractDTO.getProtocolNo() + "-" + countLoan;

		StringBuffer repaymentMethodStr = new StringBuffer();
		if (RepaymentMethod.ORDER.name().equals(repaymentMethod)) {
			repaymentMethodStr.append("\b\b☑1月，随借随还");
		} else {
			repaymentMethodStr.append("\b\b□1月，随借随还");
		}
		if (RepaymentMethod.MONTHLY.name().equals(repaymentMethod)) {
			repaymentMethodStr.append("\r\n\b☑3月，按月付息，到期还本并结清剩余利息");
		} else {
			repaymentMethodStr.append("\r\n\b□3月，按月付息，到期还本并结清剩余利息");
		}
		if (RepaymentMethod.EQUALALL.name().equals(repaymentMethod)) {
			repaymentMethodStr.append("\r\n\b☑6月，等额本息");
		} else {
			repaymentMethodStr.append("\r\n\b□6月，等额本息");
		}
		if (RepaymentMethod.EQUALPRINCIPAL.name().equals(repaymentMethod)) {
			repaymentMethodStr.append("\r\n\b☑6月，等额本金");
		} else {
			repaymentMethodStr.append("\r\n\b□6月，等额本金");
		}

		// 用于组装word页面需要的数据
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("${batchNo!}", batchNo);
		dataMap.put("${companyName!}", userSessionDTO.getCompanyName());
		dataMap.put("${protocolNo!}", contractDTO.getProtocolNo());
		dataMap.put("${applyAmt!}", applyAmt);
		dataMap.put("${rate!}", rateStr);
		dataMap.put("${loanPeriod!}", loanPeriod);
		dataMap.put("${repaymentMethod!}", repaymentMethodStr.toString());

		return dataMap;
	}
	
	/**
	 * 检验用款申请记录
	 */
	private void checkLoanApplyData(String applyAmt, String masterId) {
		if (!(DateUtils.checkTimeInterval(9, 0, 17, 0) && DateUtils.checkWeekDay())) {
			logger.info("当前时间不能进行用款申请，当前时间{}", DateUtils.format(new Date(), 1));
			throw WebException.instance("当前时间不能进行用款申请");
		}

		Long loanAmt = 0L;
		if (StringUtils.isNotBlank(applyAmt)) {
			loanAmt = MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(applyAmt));
		}
		if (loanAmt == null || loanAmt <= 0) {
			logger.info("用款申请-申请放款金额不能填写为0");
			throw WebException.instance("用款申请-申请放款金额不能填写为0");
		}
		// 检查额度是否足够
		yundaexloanApplyApi.checkLimitBalanceEnough(masterId, loanAmt);
	}
}
