package com.cana.vbam.front.biz.controller.yundaex;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyListQueryDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyMinDTO;
import com.cana.vbam.common.yundaex.dto.apply.YundaexCustomerAuditResultDTO;
import com.cana.account.api.IAccountApi;
import com.cana.front.common.util.ImageLegitimacyUtil;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.account.dto.BankBranchInfoDTO;
import com.cana.vbam.common.account.dto.BranchNameQueryCriteria;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.yundaex.api.IYundaexAuditApi;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyAddRequestDTO;
import com.cana.yundaex.common.enums.YundaexInspectionRecord;
import com.cana.yundaex.common.enums.YundaexJudge;
import com.cana.yundaex.common.enums.YundaexLoanType;
import com.cana.yundaex.common.enums.YundaexStationAddress;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;

/**
 * 韵达项目－额度审核列表页
 * @author xiaoyu
 *
 */
@Controller
@RequestMapping(value = "/yundaex/audit")
public class YdLimitAuditController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IYundaexAuditApi yundaexAuditApi;
	
	@Resource
	private IAccountApi accountApi;

	
	@RequestMapping(value = "gotoAddData")
	public String gotoAddData(@RequestParam String id,Model model){
		YdCustomerApplyDetailDTO ydCustomerApplyDetailDTO = yundaexAuditApi.getCustomerApplyInfo(id);
		model.addAttribute("customerApplyDetailDTO", ydCustomerApplyDetailDTO);
		model.addAttribute("yundaexJudge",YundaexJudge.values());
		model.addAttribute("yundaexLoanType",YundaexLoanType.values());
		model.addAttribute("yundaexInspectionRecord",YundaexInspectionRecord.values());
		return "page/yundaex/audit/addInfo";
	}
	
	//提交审核补充资料
	@RequestMapping(value="submit",method=RequestMethod.POST)
	public String submitAuditInformation(YdCustomerApplyAddRequestDTO ydCustomerApplyAddRequestDTO,RedirectAttributes attr){
		logger.info("提交审核补充资料");
		yundaexAuditApi.saveAdditionInfo(ydCustomerApplyAddRequestDTO);
		attr.addAttribute("id", ydCustomerApplyAddRequestDTO.getId());
		return "redirect:audit";
		//return "page/yundaex/audit/list";
	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String gotoList() {
		logger.info("进入韵达额度审核列表页面");
		return "page/yundaex/audit/list";
	}

	@RequestMapping(value = "/searchList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<YdCustomerApplyMinDTO> searchList(YdCustomerApplyListQueryDTO ydCustomerApplyListQueryDTO) {
		try {
			PageList<YdCustomerApplyMinDTO> pageLists = yundaexAuditApi.getCustomerApplyList(ydCustomerApplyListQueryDTO);
			return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
		} catch (Exception e) {
			logger.error("获取客户额度审核列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/detail", method = { RequestMethod.GET })
	public String detail(@RequestParam String id,Model model) {
		YdCustomerApplyDetailDTO customerApplyDetailDTO = yundaexAuditApi.getCustomerApplyInfo(id);
		model.addAttribute("customerApplyDetailDTO", customerApplyDetailDTO);
		logger.info("进入韵达审核列表详情页面");
		return "page/yundaex/audit/detail";
	}
	
	@RequestMapping(value = "/audit", method = { RequestMethod.GET })
	public String gotoAuditPage(@RequestParam String id, Model model) {
		YdCustomerApplyDetailDTO customerApplyDetailDTO = yundaexAuditApi.getCustomerApplyInfo(id);
		model.addAttribute("customerApplyDetailDTO",customerApplyDetailDTO);
		model.addAttribute("yundaexStationAddress",YundaexStationAddress.values());
		logger.info("进入韵达额度审核页面");
		return "page/yundaex/audit/audit";
	}
	
	@RequestMapping(value = "/audit", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> audit(YundaexCustomerAuditResultDTO resultDTO) {
		try {
			resultDTO.setAuditorId(SecurityContextUtils.getUserDTOFromSession().getId());
			yundaexAuditApi.auditYundaexCustomer(resultDTO);
		} catch (WebException e) {
			logger.info("提交额度人工审核结果错误：", e);
			return ObjectResult.fail(e.getMessage());
		} 
		catch (Exception e) {
			logger.info("提交额度人工审核结果错误：", e);
			return ObjectResult.fail("提交审核异常，请联系管理员");
		}
		return ObjectResult.success();
	}
	
	//上传图片
	@RequestMapping(value="save",method=RequestMethod.POST)
	public void uploadImage(MultipartFile image, HttpServletResponse httpServletResponse) throws IOException{
		String imageId = "";
		try {
			image = ImageLegitimacyUtil.verifyImage(image);
			imageId = MediaClientUtil.upload(image.getBytes(), MediaType.IMAGE);
		} catch(WebException we){
			logger.error(we.getMessage(), we);
			imageId = "LARGE:" + we.getMessage();
		} catch (Exception e) {
			logger.error("图片上传失败", e);
			imageId = "FAILED";
		}
		httpServletResponse.setContentType("text/html");
		httpServletResponse.getWriter().write(imageId);
	}
	
	//上传文件
	@RequestMapping(value="saveFile",method=RequestMethod.POST)
	public void uploadFile(MultipartFile file, HttpServletResponse httpServletResponse) throws IOException{
		String mediaId = "";
		try {
			mediaId = MediaClientUtil.upload(file.getBytes(), MediaType.IMAGE, file.getOriginalFilename());
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			mediaId = "FAILED";
		}
		httpServletResponse.setContentType("text/html");
		httpServletResponse.getWriter().write(mediaId);
	}
	
	//根据省 -查询城市信息
	@RequestMapping(value="/queryCities")
	@ResponseBody
	public ListResult<String> queryCities(String province){
		ListResult<String> result = new ListResult<>();
		try{
			List<String> cityList = accountApi.getCitiesByProvince(province);
			result.setData(cityList);
			result.setStatus(AjaxResponseStatus.SUCCESS);
			return result;
		}catch(WebException e){
			logger.error("错误原因",e.getMessage());
			logger.error("",e);
			result.setStatus(AjaxResponseStatus.FAILED);
			return result;
		}catch(Exception e){
			logger.error("",e);
			return result;
		}
	}

	//根据城市信息-查询支行信息
	@RequestMapping(value="/queryBranches")
	@ResponseBody
	public ListResult<BankBranchInfoDTO> queryBranches(BranchNameQueryCriteria queryCriteria){
		try{
			return accountApi.queryBranchInfo(queryCriteria);
		}catch(WebException e){
			logger.error("错误原因",e.getMessage());
			logger.error("",e);
			return ListResult.fail(e.getMessage());
		}catch(Exception e){
			logger.error("",e);
			return ListResult.fail("未知异常");
		}
	}
}
