package com.cana.vbam.front.biz.controller.yundaex;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditAuditListDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditLimitDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListMinDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListQueryDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditQueryDTO;
import com.cana.yundaex.api.IYundaexCreditApi;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

/**
 * 韵达项目－额度列表页
 * @author xiaoyu
 *
 */
@Controller
@RequestMapping(value = "/yundaex/limit")
public class YdLimitController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IYundaexCreditApi yundaexCreditApi;

	@RequestMapping(value = "/customerList", method = RequestMethod.GET)
	public String showCustomerLimitList() {
		return "page/yundaex/limit/customerList";
	}

	@RequestMapping(value = "/customerList", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<YundaexCreditLimitDTO> searchCustomerLimitList(YundaexCreditQueryDTO queryDTO) {
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			if (userSessionDTO.getUserType() == UserType.FINACE) {
				String masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId()
						: userSessionDTO.getMasterId();
				queryDTO.setMemberId(masterId);
			}
			PageList<YundaexCreditLimitDTO> response = yundaexCreditApi.getYundaexCreditList(queryDTO);
			return ListResult.success(response.getRecords(), response.getTotalRecords());
		} catch (Exception e) {
			logger.info("获取额度列表异常:" + e);
			return ListResult.fail("获取列表异常");
		}
	}
	
	@RequestMapping(value="/creditList", method= RequestMethod.GET)
	public String showCreditList(){
		return "page/yundaex/limit/creditList";
	}
	
	
	@RequestMapping(value="/creditList", method= RequestMethod.POST)
	@ResponseBody
	public ListResult<YundaexCreditListMinDTO> searchCreditList(YundaexCreditListQueryDTO creditQueryDTO){
		try {
			PageList<YundaexCreditListMinDTO> response = yundaexCreditApi.getYundaexCreditStateList(creditQueryDTO);
			return ListResult.success(response.getRecords(), response.getTotalRecords());
		} catch (Exception e) {
			logger.info("获取授信列表异常:" + e);
			return ListResult.fail("获取列表异常");
		}
	}
	
	@RequestMapping(value = "/resend", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> resend(@RequestParam String userId){
		try{
			yundaexCreditApi.sendActivationLink(userId);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw WebException.instance("发送激活链接失败");
		}
		return ObjectResult.success("发送激活链接成功");
	}
	
	// -------------- 授信审核 creditAuditList --------------
	@RequestMapping(value="/creditAuditList", method= RequestMethod.GET)
	public String creditAuditList(){
		return "page/yundaex/limit/creditAuditList";
	}
	
	
	@RequestMapping(value="/creditAuditList", method= RequestMethod.POST)
	@ResponseBody
	public ListResult<YundaexCreditAuditListDTO> searchCreditAuditList(YundaexCreditListQueryDTO creditQueryDTO){
		try {
			PageList<YundaexCreditAuditListDTO> response = yundaexCreditApi.getCreditAuditList(creditQueryDTO);
			return ListResult.success(response.getRecords(), response.getTotalRecords());
		} catch (Exception e) {
			logger.info("获取授信列表异常:" + e);
			return ListResult.fail("获取列表异常");
		}
	}
	
	/**
	 * 通过
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/pass", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> pass(@RequestParam String id){
		try{
			yundaexCreditApi.creditAuditPass(id);
		}catch(WebException e){
			logger.error(e.getMessage(), e);
			ObjectResult.fail(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			ObjectResult.fail("通过失败");
		}
		return ObjectResult.success("通过成功");
	}
	
	
	/**
	 * 驳回
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> reject(@RequestParam String id){
		try{
			yundaexCreditApi.creditAuditReject(id);
		}catch(WebException e){
			logger.error(e.getMessage(), e);
			ObjectResult.fail(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			ObjectResult.fail("驳回失败");
		}
		return ObjectResult.success("驳回成功");
	}
	
}
