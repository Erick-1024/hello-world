package com.cana.vbam.front.biz.controller.early.warning;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.early.warning.api.IEarlyWarningApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.early.warning.dto.AuditEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListResponse;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.EarlywarningReviewState;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.FrontExceptionHandler;

@Controller
@RequestMapping("/earlywarning/review")
public class EarlyWarningEventReviewController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IEarlyWarningApi earlyWarningApiImpl;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String gotoEarlyWarningEventReviewList(Model model) {
		logger.info("进入预警审核列表页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.values());
		model.addAttribute("earlywarningEventActions", EarlywarningEventAction.values());
		model.addAttribute("earlywarningReviewStates", EarlywarningReviewState.values());
		return "page/earlywarning/review/list";
	}
	
	@RequestMapping(value = "/query/earlyWarningEventReviewList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningEventReviewListResponse> queryEarlyWarningEventReviewList(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest) {
		try {
			earlyWarningEventReviewListRequest.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			return earlyWarningApiImpl.queryEarlyWarningEventReview(earlyWarningEventReviewListRequest);
		} catch (Exception e) {
			logger.error("获取预警事件审核列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/query/earlyWarningEventReview/{earlywarningEventReviewId}", method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ObjectResult<?> queryEarlyWarningEventReview(@PathVariable String earlywarningEventReviewId) {
		try {
			return ObjectResult.success("获取预警审核信息成功", earlyWarningApiImpl.queryEarlyWarningEventReview(earlywarningEventReviewId));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}

	@RequestMapping(value = "/audit", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> queryEarlyWarningEventReview(AuditEarlyWarningEventRequest auditEarlyWarningEventRequest) {
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			auditEarlyWarningEventRequest.setUserId(userSessionDTO.getId());
			auditEarlyWarningEventRequest.setRealName(userSessionDTO.getUsername());
			earlyWarningApiImpl.auditEarlyWarningEventReview(auditEarlyWarningEventRequest);
			return ObjectResult.success();
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
}
