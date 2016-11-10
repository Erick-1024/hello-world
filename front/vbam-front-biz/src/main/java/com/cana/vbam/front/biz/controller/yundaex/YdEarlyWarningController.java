package com.cana.vbam.front.biz.controller.yundaex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.early.warning.api.IEarlyWarningApi;
import com.cana.early.warning.api.IYundaexEarlyWarningApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.early.warning.consts.EarlyWarningConsts;
import com.cana.vbam.common.early.warning.dto.AuditEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.CanelEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerInformationResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.EarlywarningReviewState;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventSubCategory;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.FrontExceptionHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelUtils;

/**
 * 韵达预警模块
 * @author sugar
 *
 */
@Controller
@RequestMapping("/yundaex/earlywarning")
public class YdEarlyWarningController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IEarlyWarningApi earlyWarningApiImpl;
	
	@Resource
	private IYundaexEarlyWarningApi yundaexEarlyWarningApiImpl;
	
	@Resource
	private IUserApi userApiImpl;
	
	@Resource
	private ICreditApi creditApiImpl;
	
	private final String yundaexProductId = Constants.YUNDAEX_FINANCE_PRODUCT_ID;
	
	/***************************************贷后预警**********************************************/
	/**
	 * 【贷后预警】列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/postLoanlist", method = { RequestMethod.GET })
	public String gotoPostLoanlist(Model model) {
		logger.info("进入贷后预警信息列表页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.getYundaexEarlyWarningLevel());
		return "page/yundaex/earlywarning/postLoanlist";
	}
	
	/**
	 * 查询【贷后预警】列表页
	 * @param earlyWarningCommonRequest
	 * @return
	 */
	@RequestMapping(value = "/query/postLoanlist", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningCustomerResponse> queryPostLoanlist(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		try {
			earlyWarningCommonRequest.setProductId(yundaexProductId);
			return yundaexEarlyWarningApiImpl.queryEarlyWarningCustomer(earlyWarningCommonRequest);
		} catch (Exception e) {
			logger.error("获取贷后预警列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	/**
	 * 预警指标——明细浮动框
	 * @param model
	 * @param memberId
	 * @param outCustomerId
	 * @param earlywarningLevel
	 * @return
	 */
	@RequestMapping(value = "/tip", method = { RequestMethod.GET })
	public String tip(Model model, String memberId, String outCustomerId) {
		model.addAttribute("earlyWarningEventDetailDTOs", yundaexEarlyWarningApiImpl.getSingleEarlyWarningEventCollect(Arrays.asList(new String[]{EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name()}), yundaexProductId, memberId, outCustomerId));
		return "page/yundaex/earlywarning/earlyWarningTip";
	}
	
	/**
	 * 导出预警信息
	 */
	@RequestMapping(value="/export")
	public void exportStatement(EarlyWarningCommonRequest earlyWarningCommonRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String excelTitle = "韵达_贷后预警";
		String fileName = excelTitle+".xls";
		if (userAgent.contains("MSIE"))
			fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
		else
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		response.reset();
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
		response.setCharacterEncoding("utf-8");
		
		try {
			OutputStream out = response.getOutputStream();
			String[] excelHeaders = {"序号", "客户编号", "客户名称", "额度(元)", "剩余额度（元）", "风险等级", "是否收到税务、工商或质检等部门处罚（实际）", "是否收到税务、工商或质检等部门处罚（标准）", "公司业务性质有无重大变化（实际）", "公司业务性质有无重大变化（标准）", "股权结构变化（实际）", "股权结构变化（标准）", "负面新闻（实际）", "负面新闻（标准）", "诉讼纠纷（实际）", "诉讼纠纷（标准）", "短期借款（实际）", "短期借款（标准）", "客户态度（实际）", "客户态度（标准）", "其它（实际）", "其它（标准）", "揽派件增长率（实际）", "揽派件增长率（标准）", "保证金余额/日资金需求（实际）", "保证金余额/日资金需求（标准）", "韵达评级（实际）", "韵达评级（标准）", "保证金余额（实际）", "保证金余额（标准）", "净现金流增长量（实际）", "净现金流增长量（标准）", "净现金流（实际）", "净现金流（标准）", "最大授信金额增幅（实际）", "最大授信金额增幅（标准）", "逾期次数（CANA）（实际）", "逾期次数（CANA）（标准）", "预警措施"};
			earlyWarningCommonRequest.setProductId(yundaexProductId);
			ExcelUtils.exportExcel(excelTitle, excelHeaders, yundaexEarlyWarningApiImpl.getEarlyWarningExcel(earlyWarningCommonRequest), out, true);
		} catch (IOException e) {
			logger.error("生成对贷后预警文件失败", e);
			throw WebException.instance("您请求的文件不存在");
		}
	}
	
	/***************************************预警信息**********************************************/
	/**
	 * 【预警信息】列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/informationList", method = { RequestMethod.GET })
	public String gotoInformationList(Model model) {
		logger.info("进入预警信息列表页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.getYundaexEarlyWarningLevel());
		return "page/yundaex/earlywarning/informationList";
	}
	
	/**
	 * 查询【预警信息】列表页
	 * @param earlyWarningCustomerRequest
	 * @return
	 */
	@RequestMapping(value = "/query/informationList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningCustomerInformationResponse> queryInformationList(EarlyWarningCommonRequest earlyWarningCustomerRequest) {
		try {
			earlyWarningCustomerRequest.setProductId(yundaexProductId);
			return yundaexEarlyWarningApiImpl.queryEarlyWarningCustomerInformation(earlyWarningCustomerRequest);
		} catch (Exception e) {
			logger.error("获取预警信息列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	/**
	 *【预警信息】->【调整预警信息】列表页
	 * @param earlyWarningCommonRequest
	 * @return
	 */
	@RequestMapping(value = "/adjustList", method = { RequestMethod.GET })
	public String gotoAdjustList(EarlyWarningCommonRequest earlyWarningCommonRequest,Model model) {
		logger.info("进入调整预警主页面");
		earlyWarningCommonRequest.setProductId(yundaexProductId);
		model.addAttribute("typeListResponse", yundaexEarlyWarningApiImpl.queryEarlyWarningTypeList(earlyWarningCommonRequest));
		return "page/yundaex/earlywarning/adjustList";
	}
	
	/**
	 *【预警信息】->【调整预警信息】->【解除事件】
	 * @param canelEarlyWarningEventRequest
	 * @return
	 */
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
	
	/***************************************审核列表**********************************************/
	/**
	 * 【审核列表】列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reviewList", method = { RequestMethod.GET })
	public String gotoReviewList(Model model) {
		logger.info("进入预警审核列表页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.getYundaexEarlyWarningLevel());
		model.addAttribute("earlywarningEventActions", EarlywarningEventAction.values());
		model.addAttribute("earlywarningReviewStates", EarlywarningReviewState.values());
		return "page/yundaex/earlywarning/reviewList";
	}
	
	/**
	 * 查询【审核列表】列表页
	 * @param earlyWarningEventReviewListRequest
	 * @return
	 */
	@RequestMapping(value = "/query/reviewList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningEventReviewListResponse> queryReviewList(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest) {
		try {
			earlyWarningEventReviewListRequest.setProductId(yundaexProductId);
			return yundaexEarlyWarningApiImpl.queryEarlyWarningEventReview(earlyWarningEventReviewListRequest);
		} catch (Exception e) {
			logger.error("获取预警事件审核列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	/**
	 * 查询【审核列表】->【详情】
	 * @param earlywarningEventId
	 * @return
	 */
	@RequestMapping(value = "query/reviewDetail", method = {RequestMethod.POST})
	@ResponseBody
	public ObjectResult<?> queryReviewDetail(String earlywarningEventId) {
		try {
			return ObjectResult.success("获取预警事件详情数据成功", yundaexEarlyWarningApiImpl.queryEarlyWarningEventDetail(earlywarningEventId));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 查询【审核列表】->【审核】
	 * @param earlywarningEventId
	 * @return
	 */
	@RequestMapping(value = "audit", method = {RequestMethod.GET})
	@ResponseBody
	public ObjectResult<?> queryReviewReview(String earlywarningEventReviewId) {
		try {
			return ObjectResult.success("获取预警事件审核数据成功", yundaexEarlyWarningApiImpl.queryEarlyWarningEventReview(earlywarningEventReviewId));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 查询【审核列表】->【详情】/【审核】的“附加数据”
	 * @param earlywarningEventReviewId
	 * @return
	 */
	@RequestMapping(value = "/query/reviewExtraData", method = {RequestMethod.POST})
	@ResponseBody
	public ObjectResult<?> queryReviewExtraData(String earlywarningEventReviewId) {
		try {
			return ObjectResult.success("获取预警详情/审核【附加数据】信息成功", yundaexEarlyWarningApiImpl.queryEarlyWarningEventReview(earlywarningEventReviewId));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 审核 操作
	 * @param auditEarlyWarningEventRequest
	 * @return
	 */
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
	
	/***************************************预警流水**********************************************/
	/**
	 * 【预警流水】列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeList", method = { RequestMethod.GET })
	public String gotoChangeList(Model model) {
		logger.info("进入预警流水列表页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.getYundaexEarlyWarningLevel());
		return "page/yundaex/earlywarning/changeList";
	}
	
	@RequestMapping(value = "/query/changeList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningLevelChangeHistoryDTO> queryEarlyWarningLevelChangeHistory(EarlyWarningLevelChangeHistoryRequest earlyWarningLevelChangeHistoryRequest) {
		try {
			earlyWarningLevelChangeHistoryRequest.setProductId(yundaexProductId);
			return earlyWarningApiImpl.queryEarlyWarningLevelChangeHistory(earlyWarningLevelChangeHistoryRequest);
		} catch (Exception e) {
			logger.error("获取预流水列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	/***************************************新增预警事件**********************************************/
	/**
	 * 【新增预警事件】列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addList", method = { RequestMethod.GET })
	public String gotoAddList(Model model) {
		logger.info("进入新增预警主页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.getYundaexEarlyWarningLevel());
		return "page/yundaex/earlywarning/addList";
	}
	
	/**
	 * 查询【新增预警事件】列表页
	 * @param earlyWarningCommonRequest
	 * @return
	 */
	@RequestMapping(value = "/query/addList", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> queryAddList(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		try {
			earlyWarningCommonRequest.setProductId(yundaexProductId);
			return ObjectResult.success("获取预警汇总列表成功", yundaexEarlyWarningApiImpl.queryEarlyWarningTypeList(earlyWarningCommonRequest));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 【新增预警事件】
	 * @param earlyWarningManualEventDTO
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> addEarlyWarningEvent(@RequestBody EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			earlyWarningManualEventDTO.setUserId(userSessionDTO.getId());
			earlyWarningManualEventDTO.setRealName(userSessionDTO.getUsername());
			earlyWarningManualEventDTO.setProductId(yundaexProductId);
			if(StringUtils.isBlank(earlyWarningManualEventDTO.getEarlywarningLevel()))
				earlyWarningManualEventDTO.setEarlywarningLevel(getLevelBySubCategory(earlyWarningManualEventDTO.getEarlywarningEventSubCategory()).name());
			yundaexEarlyWarningApiImpl.addEarlyWarningManualEvent(earlyWarningManualEventDTO);
			return ObjectResult.success();
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/***************************************其他公共部分**********************************************/
	/**
	 * 获取预计预警等级
	 * @param earlyWarningEventDetailDTO
	 * @param action
	 * @param companyName
	 * @param outCustomerName
	 * @return
	 */
	@RequestMapping(value = "/getPredictEarlyWarningLevel", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> getPredictEarlyWarningLevel(EarlyWarningEventDetailDTO earlyWarningEventDetailDTO, String action, String companyName, String outCustomerName) {
		try {
			earlyWarningEventDetailDTO.setProductId(yundaexProductId);
			if(StringUtils.isBlank(earlyWarningEventDetailDTO.getLevel()))
				earlyWarningEventDetailDTO.setLevel(getLevelBySubCategory(earlyWarningEventDetailDTO.getSubType()).name());
			Map<EarlywarningLevel, String> returnValue = earlyWarningApiImpl.getPredictEarlyWarningLevel(earlyWarningEventDetailDTO, action, companyName, outCustomerName);
			if(returnValue.containsKey(null))
				returnValue = null;
			return ObjectResult.success("获取预计预警等级成功", returnValue);
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	/**
	 * 【预警事件历史】
	 * @param model
	 * @param earlywarningEventCategory
	 * @param memberId
	 * @param outCustomerId
	 * @param type
	 * @param thirdItem 三级菜单
	 * @return
	 */
	@RequestMapping(value = "/earlyWarningEventHistory", method = { RequestMethod.GET })
	public String gotoEarlyWarningEventHistoryListPage(Model model, String earlywarningEventCategory, String memberId, String outCustomerId, String type, String thirdItem) {
		model.addAttribute("memberId", memberId);
		model.addAttribute("outCustomerId", outCustomerId);
		model.addAttribute("companyName", userApiImpl.queryCustomerDetail(memberId).getCompanyName());
		model.addAttribute("outCustomerName", creditApiImpl.queryOutCustomerName(yundaexProductId, memberId, outCustomerId));
		model.addAttribute("type", type);
		model.addAttribute("currentEarlywarningEventCategory", earlywarningEventCategory);
		model.addAttribute("thirdItem", thirdItem);
		return "page/yundaex/earlywarning/earlyWarningEventHistory";
	}
	
	/**
	 * 查询【预警事件历史】
	 * @param earlyWarningEventHistoryRequest
	 * @return
	 */
	@RequestMapping(value = "/query/earlyWarningEventHistory", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningEventHistoryResponse> queryEarlyWarningEventHistory(EarlyWarningEventHistoryRequest earlyWarningEventHistoryRequest) {
		try {
			earlyWarningEventHistoryRequest.setProductId(yundaexProductId);
			return yundaexEarlyWarningApiImpl.queryEarlyWarningEventHistory(earlyWarningEventHistoryRequest);
		} catch (Exception e) {
			logger.error("获取预警事件历史列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	/**
	 * 通过预警种类获取事件种类
	 * @param parent
	 * @return
	 */
	@RequestMapping(value = "/getSubByParent", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> getSubByParent(YundaexEarlywarningEventCategory parent) {
		List<String> subList = new ArrayList<String>();
			for(YundaexEarlywarningEventSubCategory sub : YundaexEarlywarningEventSubCategory.values()){
				if(sub.parentCategory() == parent)
					subList.add(sub.name());
			}
		return ObjectResult.success("通过预警种类获取事件种类成功", subList);
		}
	
	/**
	 * 根据事件种类获取等级
	 * @param subCategory string
	 * @return
	 * @throws Exception
	 */
	private EarlywarningLevel getLevelBySubCategory(String subCategory) throws Exception{
		if(!EnumUtils.isValidEnum(YundaexEarlywarningEventSubCategory.class,subCategory))
			throw new Exception("事件种类不合法");
		EarlywarningLevel level = EarlyWarningConsts.getLevelBySubCategory(YundaexEarlywarningEventSubCategory.valueOf(subCategory));
		if(level == null)
			throw new Exception("事件种类没有对应的等级");
		return level;
	}
	
}


	
	
