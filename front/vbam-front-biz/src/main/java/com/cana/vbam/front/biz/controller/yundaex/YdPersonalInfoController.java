package com.cana.vbam.front.biz.controller.yundaex;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IAssetApi;
import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.signature.api.ICertApplyApi;
import com.cana.signature.api.ISignatureApi;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.signature.dto.CertApplyDTO;
import com.cana.vbam.common.signature.dto.CertResultDTO;
import com.cana.vbam.common.signature.enums.IdentityType;
import com.cana.vbam.common.signature.enums.SignType;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.front.biz.utils.WordUtil;
import com.cana.yundaex.api.IYundaexAuditApi;
import com.cana.yundaex.api.IYundaexContractSituationApi;
import com.cana.yundaex.api.IYundaexPersonalInfoApi;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoAuditDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoQueryCriteria;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoRequestDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoResultDTO;
import com.cana.yundaex.common.enums.PersonalInfoAuditStatus;
import com.cana.yundaex.common.enums.PersonalInfoType;
import com.cana.yundaex.common.util.Constants;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;

/**
 * @author hu
 *
 */
@Controller
@RequestMapping(value = "/yundaex/personal")
public class YdPersonalInfoController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private final String CREDIT_CONTRACT_PATH = "/template/yundaex/letterOfAuthorization.doc";
	private final String DUTY_CONTRACT_PATH = "/template/yundaex/dutyGuaranteeContract.doc";
	
	@Resource
	private IYundaexPersonalInfoApi personalInfoApi;
	
	@Resource
	private IYundaexAuditApi ydAuditApi;
	
	@Resource
	private ICertApplyApi certApplyApi;
	
	@Resource
    private IAssetApi assetApi;
	@Resource
	private IAssetProjectManageApi assetProjectApi;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IYundaexContractSituationApi contractApi;
	
    @Resource
    private ISignatureApi signatureApiImpl; 
	
	@RequestMapping(value = "/facade/put", method = RequestMethod.GET)
	public String putPersonalInfo(@RequestParam String id, @RequestParam String code, Model model){
		PersonalInfoResultDTO resultDTO = personalInfoApi.findPersonalInfoById(id);
		if(null == resultDTO){
			throw WebException.instance("用户不存在");
		}
		if(resultDTO.getSecurityCodeExpirationTime().compareTo(new Date()) < 0){
			throw WebException.instance("链接已失效，请联系平台管理员");
		}
		if(StringUtils.isBlank(code) || !code.equals(resultDTO.getSecurityCode())){
			throw WebException.instance("安全码不匹配");
		}
		model.addAttribute("id", id);
		model.addAttribute("code", code);
		model.addAttribute("realName", resultDTO.getRealName());
		model.addAttribute("type",PersonalInfoType.valueOf(resultDTO.getType()));
		return "/page/yundaex/personal/putInformation";
	}
	
	@RequestMapping(value = "/facade/put", method = RequestMethod.POST)
	public String putPersonalInfo(PersonalInfoRequestDTO requestDTO, @RequestParam String code){
		checkUserInfo(requestDTO.getId(), code);
		personalInfoApi.updatePersonalInfo(requestDTO);
		return "/page/yundaex/personal/putInformationSuccess";
	}
	
	@RequestMapping(value = "/gotoListPage", method = RequestMethod.GET)
	public String gotoPersonalInfoList(){
		return "/page/yundaex/personal/auditList";
	}
	
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public PageResult<PersonalInfoResultDTO> queryPersonalInfoList(PersonalInfoQueryCriteria queryCriteria){
		
		return personalInfoApi.findPersonalInfoByCondition(queryCriteria);
	}
	
	@RequestMapping(value = "/gotoDetailPage", method = RequestMethod.GET)
	public String gotoPersonalInfoDetail(@RequestParam String id, Model model){
		PersonalInfoResultDTO resultDTO = personalInfoApi.findPersonalInfoById(id);
		model.addAttribute("personalInfoDTO", resultDTO);
		model.addAttribute("cusApplyDTO", ydAuditApi.getUserBaseInfo(resultDTO.getRelatedCustomerId()));
		model.addAttribute("creditLimit", MoneyArithUtil.convertMoneyToString(personalInfoApi.getCreditLimitInfo(resultDTO.getRelatedCustomerId())));
		return "/page/yundaex/personal/detailPage";
	}
	
	@RequestMapping(value = "/gotoAuditPage", method = RequestMethod.GET)
	public String gotoPersonalInfoAuditPage(@RequestParam String id, Model model){
		PersonalInfoResultDTO resultDTO = personalInfoApi.findPersonalInfoById(id);
		model.addAttribute("personalInfoDTO", resultDTO);
		model.addAttribute("cusApplyDTO", ydAuditApi.getUserBaseInfo(resultDTO.getRelatedCustomerId()));
		model.addAttribute("creditLimit", MoneyArithUtil.convertMoneyToString(personalInfoApi.getCreditLimitInfo(resultDTO.getRelatedCustomerId())));
		return "/page/yundaex/personal/auditPage";
	}
	
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> auditPersonalInfo(@RequestParam String id, String auditStatus){
		try{
			PersonalInfoAuditDTO auditDTO = new PersonalInfoAuditDTO();
			auditDTO.setId(id);
			auditDTO.setAuditStatus(PersonalInfoAuditStatus.valueOf(auditStatus));
			auditDTO.setAuditorId(SecurityContextUtils.getOperatorId());
			auditDTO.setAuditorName(SecurityContextUtils.getUserFromSession().getUsername());
			personalInfoApi.auditPersonalInfo(auditDTO);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw WebException.instance("审核失败");
		}
		return ObjectResult.success("审核成功");
	}
	
	@RequestMapping(value = "/resend", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> resend(@RequestParam String id){
		try{
			personalInfoApi.resendSubmitInfoLink(id);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw WebException.instance("重发失败");
		}
		return ObjectResult.success("重发成功");
	}
	
	@RequestMapping(value = "/facade/informationConfirm", method = RequestMethod.GET)
	public String informationConfirm(@RequestParam String id, @RequestParam String code, Model model){
		PersonalInfoResultDTO infoDTO = checkUserInfo(id, code);
		
		model.addAttribute("personDTO", infoDTO);
		model.addAttribute("cusApplyDTO",ydAuditApi.getUserBaseInfo(infoDTO.getRelatedCustomerId()));
		model.addAttribute("id", id);
		model.addAttribute("code",code);
		return "/page/yundaex/personal/confirmInformation";
	}
	
	@RequestMapping(value= "/facade/apply/cert", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> applyCert(String p10, @RequestParam String id, @RequestParam String code){
		if(StringUtils.isBlank(p10)){
			logger.info("p10为空");
			return ObjectResult.fail("申请书生成失败,请联系管理员。联系电话：021-53866655!");
		}
		PersonalInfoResultDTO infoDTO = null;
		try{
			infoDTO = checkUserInfo(id, code);
		}catch(WebException e){
			logger.error("参数校验失败", e);
			return ObjectResult.fail(e.getMessage());
		}
		try{
			CertResultDTO certResult = null;
			if(StringUtils.isBlank(infoDTO.getCertSubjectDn())){
				CertApplyDTO certApplyDTO = new CertApplyDTO();
				certApplyDTO.setP10(p10);
				certApplyDTO.setUserName(infoDTO.getRealName());
				certApplyDTO.setIdentType(IdentityType.RESIDENT_IDENTITY_CARD.number());
				certApplyDTO.setIdentNo(infoDTO.getResidentIdentityCardNo());
				certApplyDTO.setEmail(infoDTO.getMail());
				certResult = certApplyApi.applyCert(certApplyDTO);

				personalInfoApi.updatePersonalSubjectDn(id, certResult.getSubjectDN());
			}else{
				certResult = certApplyApi.reissueCert(infoDTO.getCertSubjectDn(), p10);
			}
			return ObjectResult.success("申请证书成功", certResult.getSignatureCert());
		} catch(Exception e){
			logger.error("申请证书或补发失败!", e);
			return ObjectResult.fail("安装证书错误，请联系管理员。联系电话：021-53866655!");
		}
	}
	
	@RequestMapping(value = "/facade/confirmInfomation", method = RequestMethod.POST)
	public String confirmInfomation(@RequestParam String id, @RequestParam String code, Model model){
		PersonalInfoResultDTO infoDTO = checkUserInfo(id, code);
		
		model.addAttribute("personDTO", infoDTO);
		model.addAttribute("contractId", contractApi.getContractSituationById(infoDTO.getRelatedCustomerId()).getProtocolNo());
		model.addAttribute("id", id);
		model.addAttribute("code",code);
		return "/page/yundaex/personal/signContract";
	}
	
	@RequestMapping(value="/facade/gotoSignContract")
	public String confirmContract(@RequestParam String id, @RequestParam String code, @RequestParam String isRead, @RequestParam String contractId, Model model){
		PersonalInfoResultDTO infoDTO = checkUserInfo(id, code);
		
		model.addAttribute("personDTO", infoDTO);
		model.addAttribute("contractId", contractId);
		model.addAttribute("isRead",isRead);
		model.addAttribute("id", id);
		model.addAttribute("code",code);
		return "/page/yundaex/personal/signContract";
	}

	@RequestMapping(value = "/facade/readContract")
    public String readContract(@RequestParam String id, @RequestParam String code, @RequestParam String type,@RequestParam String contractId,@RequestParam int isRead,Model model) {
		PersonalInfoResultDTO infoDTO = checkUserInfo(id, code);
		ProjectDTO project = assetProjectApi.getProjectDetail(Constants.YUNDAEX_ASSET_PROJECT_ID);
    	CustomerDetailDTO factorCustomerDTO = userApi.queryCustomerDetail(project.getProjectFactors().get(0).getCompanyId());
    	CustomerDetailDTO finaceCustomerDTO = userApi.queryCustomerDetail(infoDTO.getRelatedCustomerId());
    	model.addAttribute("realName", infoDTO.getRealName());
    	model.addAttribute("contractNo", contractId);
		model.addAttribute("factorCompanyName",factorCustomerDTO.getCompanyName());
		model.addAttribute("finaceCompanyName",finaceCustomerDTO.getCompanyName());
		model.addAttribute("id", id);
		model.addAttribute("code",code);
		if(PersonalInfoType.ACCOUNT_HOLDER.name().equals(type)){
			model.addAttribute("isRead",isRead | 1);//二进制与
			return "/page/yundaex/personal/creditContract";
		}else if(PersonalInfoType.CONTROLLER.name().equals(type)){
			model.addAttribute("isRead",isRead | 2);//二进制与
			return "/page/yundaex/personal/dutyContract";
		}else{
			throw WebException.instance("参数错误");
		}
	}
	
//	@RequestMapping(value = "/downloadContract")
//	public ModelAndView downloadContract(HttpServletResponse response, HttpServletRequest request){
//	    String outFileName = "应收款债权转让协议";
//	    Map<String, String> dataMap = packageWordData(request);
//	    try {
//		    outFileName = new String(outFileName.getBytes(), "utf-8");
//		    // 设置头
//		    response.setCharacterEncoding("utf-8");
//		    response.setContentType("multipart/form-data");
//		    // 文件名的中文乱码问题及其浏览器的兼容问题
//		    if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) { // IE
//		        outFileName = java.net.URLEncoder.encode(outFileName, "UTF-8");
//		    } else {
//		        outFileName = new String(outFileName.getBytes("UTF-8"), "ISO8859-1"); // 其他
//		    }
//		    response.setHeader("Content-Disposition", "attachment;filename=" + outFileName + ".doc");
//		    String templatePath = getClass().getResource("/template/contractTemplate.doc").getFile();
//	    
//	        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath,dataMap);
//	        response.getOutputStream().write(wordContent);
//	    } catch (IOException e) {
//	        logger.error("生成合同内容失败",e);
//	    } finally {
//	    	try{
//	    		response.flushBuffer();
//	    	}catch (IOException e) {
//    	        logger.error("清空response缓存失败",e);
//    	    }
//	    }
//	    return null;
//	}
	
	@RequestMapping("/facade/getContractData")
	@ResponseBody
	public ListResult<byte[]> getContractData(@RequestParam String id, @RequestParam String code, @RequestParam String contractId){
		PersonalInfoResultDTO infoDTO = checkUserInfo(id, code);
		List<byte[]> wordContentList = Lists.newArrayList();
		Map<String, String> dataMap = packageWordData(infoDTO, contractId);
		if(!PersonalInfoType.CONTROLLER.name().equals(infoDTO.getType())){
			String templatePath = getClass().getResource(CREDIT_CONTRACT_PATH).getFile();
	        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath,dataMap);
	        wordContentList.add(wordContent);
		}
		if(!PersonalInfoType.ACCOUNT_HOLDER.name().equals(infoDTO.getType())){
			String templatePath = getClass().getResource(DUTY_CONTRACT_PATH).getFile();
	        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath,dataMap);
	        wordContentList.add(wordContent);
		}
        return ListResult.success("success",  wordContentList, wordContentList.size());
	}
	
	@RequestMapping(value = "/facade/completeContract")
	@ResponseBody
	public ObjectResult<Boolean> completeContract(@RequestParam String id, @RequestParam String code, @RequestParam String contractId, String signDatas) {
		String [] signData = signDatas.split(",");
	    PersonalInfoResultDTO infoDTO = checkUserInfo(id, code);
		Map<String, String> dataMap = packageWordData(infoDTO, contractId);
		List<byte[]> wordContentList = Lists.newArrayList();
		List<String> contractNameList = Lists.newArrayList();
		String certSubjectDN = infoDTO.getCertSubjectDn();
	       
		if(!PersonalInfoType.CONTROLLER.name().equals(infoDTO.getType())){
			String templatePath = getClass().getResource(CREDIT_CONTRACT_PATH).getFile();
	        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath,dataMap);
	        wordContentList.add(wordContent);
	        contractNameList.add("授权委托书");
		}
		if(!PersonalInfoType.ACCOUNT_HOLDER.name().equals(infoDTO.getType())){
			String templatePath = getClass().getResource(DUTY_CONTRACT_PATH).getFile();
	        byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath,dataMap);
	        wordContentList.add(wordContent);
	        contractNameList.add("个人连带担保责任保证书");
		}
		if(wordContentList.size() != signData.length){
			return ObjectResult.fail("参数有误");
		}
	    try{
	    	for(int i=0; i<wordContentList.size(); i++){
	    		String personalContractId = assetApi.generateContractId();
	    		signatureApiImpl.verifySignMessage(signData[i].getBytes(), certSubjectDN, wordContentList.get(i), SignType.ATTACH, personalContractId);
	    		upload2MediaAndUpdateContract(personalContractId, infoDTO, wordContentList.get(i), contractNameList.get(i),"doc");
	    	}
		}catch(Exception e){
	    	logger.error(e.getMessage(),e);
	    	return ObjectResult.fail("合同签名失败，请联系管理员。联系电话：021-53866655");
        }
	    try {
	    	contractApi.signContract(id, infoDTO.getRelatedCustomerId());
	        return ObjectResult.success("您的合同已完成电子签名。");
	    } catch (Exception e) {
	    	logger.error(e.getMessage(),e);
	    	return ObjectResult.fail("签署合同失败，请重新签署合同！");
	    }
	}
	
	/**
	 * 用于组装word页面需要的数据
	 * @return
	 */
	private Map<String, String> packageWordData(PersonalInfoResultDTO infoDTO, String contractId){
	    
	    // 用于组装word页面需要的数据
	    ProjectDTO project = assetProjectApi.getProjectDetail(Constants.YUNDAEX_ASSET_PROJECT_ID);
    	CustomerDetailDTO factorCustomerDTO = userApi.queryCustomerDetail(project.getProjectFactors().get(0).getCompanyId());
    	CustomerDetailDTO finaceCustomerDTO = userApi.queryCustomerDetail(infoDTO.getRelatedCustomerId());
	    Map<String, String> dataMap = new HashMap<String, String>();
	    dataMap.put("${realName!}", infoDTO.getRealName());
	    dataMap.put("${contractNo!}", contractId);
	    dataMap.put("${factorCompanyName!}",factorCustomerDTO.getCompanyName());
	    dataMap.put("${finaceCompanyName!}",finaceCustomerDTO.getCompanyName());
	    return dataMap;
	}
	
	private PersonalInfoResultDTO checkUserInfo(String id, String code){
		PersonalInfoResultDTO infoDTO = personalInfoApi.findPersonalInfoById(id);
		if(null == infoDTO){
			throw WebException.instance("用户不存在");
		}
		if(infoDTO.getSecurityCodeExpirationTime().compareTo(new Date()) < 0){
			throw WebException.instance("链接已失效，请联系平台管理员");
		}
		if(StringUtils.isBlank(code) || !code.equals(infoDTO.getSecurityCode())){
			throw WebException.instance("安全码不匹配");
		}
		return infoDTO;
	}
	
	private void upload2MediaAndUpdateContract(String contractId, PersonalInfoResultDTO infoDTO, byte[] wordContent, String fileName, String fileSuffix) throws Exception{
	    ProjectDTO project = assetProjectApi.getProjectDetail(Constants.YUNDAEX_ASSET_PROJECT_ID);
    	String factorId = project.getProjectFactors().get(0).getCompanyId();
        String mediaId = MediaClientUtil.upload(wordContent,MediaType.IMAGE, fileName+"."+fileSuffix);
		
        ContractInfoDTO contractInfoDTO = new ContractInfoDTO();
		contractInfoDTO.setContractId(contractId);
		contractInfoDTO.setProductId(Constants.YUNDAEX_ASSET_PROJECT_ID);
		contractInfoDTO.setMemberId(infoDTO.getId());
		contractInfoDTO.setFactorId(factorId);
		contractInfoDTO.setMediaId(mediaId);
		contractInfoDTO.setFileName(fileName);
		contractInfoDTO.setFileSuffix(fileSuffix);
		contractInfoDTO.setEffectiveDate(DateTimeUtil.getTodayStr());
		contractInfoDTO.setDueDate(DateTimeUtil.date10(DateTimeUtil.addYear(new DateTime(), 3)));
		assetApi.updateContractByMemberIdAndProductId(contractInfoDTO);
	} 
}
