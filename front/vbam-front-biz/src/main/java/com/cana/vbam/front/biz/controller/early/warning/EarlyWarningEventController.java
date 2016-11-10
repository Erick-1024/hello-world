package com.cana.vbam.front.biz.controller.early.warning;

import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.early.warning.api.IEarlyWarningApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.early.warning.dto.CanelEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.FrontExceptionHandler;

@Controller
@RequestMapping("/earlywarning/event")
public class EarlyWarningEventController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IEarlyWarningApi earlyWarningApiImpl;
	
	@Resource
	private IUserApi userApiImpl;
	
	@Resource
	private ICreditApi creditApiImpl;
	
	@RequestMapping(value = "/addList", method = { RequestMethod.GET })
	public String gotoEarlyWarningAddList(Model model) {
		logger.info("进入新增预警主页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.values());
		return "page/earlywarning/event/addEventList";
	}
	
	@RequestMapping(value = "/tip/{memberId}/{outCustomerId}/{earlywarningLevel}", method = { RequestMethod.GET })
	public String tip(Model model, @PathVariable String memberId, @PathVariable String outCustomerId, @PathVariable String earlywarningLevel) {
		model.addAttribute("earlyWarningEventDetailDTOs", earlyWarningApiImpl.getSingleEarlyWarningEventCollect(Arrays.asList(new String[]{EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name()}), Constants.TRAVELZEN_FINANCE_PRODUCT_ID, memberId, outCustomerId));
		return "page/earlywarning/EarlyWarningTip";
	}
	
	@RequestMapping(value = "/detail/{earlywarningEventId}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> queryEarlyWarningDetail(@PathVariable String earlywarningEventId) {
		try {
			return ObjectResult.success("获取预警事件详情成功", earlyWarningApiImpl.queryEarlyWarningEventDetail(earlywarningEventId));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/query/earlyWarningTypeList", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> queryEarlyWarningTypeList(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		try {
			earlyWarningCommonRequest.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			return ObjectResult.success("获取预警汇总列表成功", earlyWarningApiImpl.queryEarlyWarningTypeList(earlyWarningCommonRequest));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/earlyWarningEventHistory/{earlywarningEventCategory}/{memberId}/{outCustomerId}/{type}/{thirdItem}", method = { RequestMethod.GET })
	public String gotoEarlyWarningEventHistoryListPage(Model model, @PathVariable String earlywarningEventCategory, @PathVariable String memberId, @PathVariable String outCustomerId, @PathVariable String type, @PathVariable String thirdItem) {
		model.addAttribute("memberId", memberId);
		model.addAttribute("outCustomerId", outCustomerId);
		model.addAttribute("companyName", userApiImpl.queryCustomerDetail(memberId).getCompanyName());
		model.addAttribute("outCustomerName", creditApiImpl.queryOutCustomerName(Constants.TRAVELZEN_FINANCE_PRODUCT_ID, memberId, outCustomerId));
		model.addAttribute("type", type);
		model.addAttribute("currentEarlywarningEventCategory", earlywarningEventCategory);
		model.addAttribute("thirdItem", thirdItem);
		return "page/earlywarning/event/EarlyWarningEventHistory";
	}
	
	@RequestMapping(value = "/query/earlyWarningEventHistory", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningEventHistoryResponse> queryEarlyWarningEventHistory(EarlyWarningEventHistoryRequest earlyWarningEventHistoryRequest) {
		try {
			earlyWarningEventHistoryRequest.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			return earlyWarningApiImpl.queryEarlyWarningEventHistory(earlyWarningEventHistoryRequest);
		} catch (Exception e) {
			logger.error("获取预警事件历史列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/cancel", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> cancelEarlyWarningEvent(CanelEarlyWarningEventRequest canelEarlyWarningEventRequest) {
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			canelEarlyWarningEventRequest.setUserId(userSessionDTO.getId());
			canelEarlyWarningEventRequest.setRealName(userSessionDTO.getUsername());
			earlyWarningApiImpl.canaelEarlyWarningEvent(canelEarlyWarningEventRequest);
			return ObjectResult.success();
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> addEarlyWarningEvent(@RequestBody EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			earlyWarningManualEventDTO.setUserId(userSessionDTO.getId());
			earlyWarningManualEventDTO.setRealName(userSessionDTO.getUsername());
			earlyWarningManualEventDTO.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			earlyWarningApiImpl.addEarlyWarningManualEvent(earlyWarningManualEventDTO);
			return ObjectResult.success();
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
}
