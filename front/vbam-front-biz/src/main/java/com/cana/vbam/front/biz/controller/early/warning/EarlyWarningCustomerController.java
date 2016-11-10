package com.cana.vbam.front.biz.controller.early.warning;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.early.warning.api.IEarlyWarningApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerInformationResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerResponse;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelUtils;

@Controller
@RequestMapping("/earlywarning/customer")
public class EarlyWarningCustomerController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IEarlyWarningApi earlyWarningApiImpl;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String gotoEarlyWarningCustomerList(Model model) {
		logger.info("进入贷后预警信息列表页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.values());
		return "page/earlywarning/customer/earlywarningCustomerList";
	}
	
	@RequestMapping(value = "/query/earlyWarningCustomer", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningCustomerResponse> queryEarlyWarningCustomer(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		try {
			earlyWarningCommonRequest.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			return earlyWarningApiImpl.queryEarlyWarningCustomer(earlyWarningCommonRequest);
		} catch (Exception e) {
			logger.error("获取贷后预警信息列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/information/list", method = { RequestMethod.GET })
	public String gotoEarlyWarningCustomerInformationList(Model model) {
		logger.info("进入预警信息列表页面");
		model.addAttribute("earlywarningLevels", EarlywarningLevel.values());
		return "page/earlywarning/customer/earlywarningCustomerInformationList";
	}
	
	@RequestMapping(value = "/query/earlyWarningCustomerInformation", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<EarlyWarningCustomerInformationResponse> queryEarlyWarningCustomerInformation(EarlyWarningCommonRequest earlyWarningCustomerRequest) {
		try {
			earlyWarningCustomerRequest.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			return earlyWarningApiImpl.queryEarlyWarningCustomerInformation(earlyWarningCustomerRequest);
		} catch (Exception e) {
			logger.error("获取预警信息列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/Typelist", method = { RequestMethod.GET })
	public String gotoEarlyWarningCustomerTypeList(EarlyWarningCommonRequest earlyWarningCommonRequest, Model model) {
		logger.info("进入调整预警主页面");
		earlyWarningCommonRequest.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		model.addAttribute("typeListResponse", earlyWarningApiImpl.queryEarlyWarningTypeList(earlyWarningCommonRequest));
		return "page/earlywarning/customer/earlywarningCustomerTypeList";
	}

	@RequestMapping(value="/export/earlywarning")
	public void exportStatement(EarlyWarningCommonRequest earlyWarningCommonRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String excelTitle = "贷后预警";
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
			String[] excelHeaders = {"序号", "客户编号", "客户名称", "外部客户名称", "额度(元)", "剩余额度（元）", "风险等级", "反担保覆盖率（实际）", "反担保覆盖率（标准）", "销售变化率（实际）", "销售变化率（标准）", "销售回款率（实际）", "销售回款率（标准）", "连续逾期次数（实际）", "连续逾期次数（标准）", "累计逾期次数（实际）", "累计逾期次数（标准）", "被法院执行（企业）（实际）", "被法院执行（企业）（标准）", "被法院执行（个人）（实际）", "被法院执行（个人）（标准）", "负面信息（实际）", "负面信息（标准）", "预警措施"};
			earlyWarningCommonRequest.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			ExcelUtils.exportExcel(excelTitle, excelHeaders, earlyWarningApiImpl.getEarlyWarningCustomerExcel(earlyWarningCommonRequest), out, true);
		} catch (IOException e) {
			logger.error("生成对贷后预警文件失败", e);
			throw WebException.instance("您请求的文件不存在");
		}
	}
	
}
