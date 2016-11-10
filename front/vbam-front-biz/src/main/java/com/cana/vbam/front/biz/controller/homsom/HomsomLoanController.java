package com.cana.vbam.front.biz.controller.homsom;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IHomsomApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.homsom.dto.LoanAuditDetailRequest;
import com.cana.vbam.common.homsom.dto.LoanAuditListRequest;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.LoanState;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelUtils;

@Controller
@RequestMapping("homsom/loan")
public class HomsomLoanController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IHomsomApi homsomApiImpl;
	
	@RequestMapping(value = "/goto/list/{channel}", method = { RequestMethod.GET })
	public String gotolist(Model model, @PathVariable Channel channel) {
		logger.info("进入放款审核列表页面");
		switch (channel) {
		case HOMSOM:
			model.addAttribute("title", "恒顺国旅项目");
			break;
		case SHFH:
			break;
		default:
			break;
		}
		model.addAttribute("channel", channel);
		model.addAttribute("loanStates", LoanState.valuesByChannel(channel));
		return "page/homsom/loan/list";
	}

	@RequestMapping(value = "/get/list", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getList(@RequestBody LoanAuditListRequest loanAuditListRequest) {
		try {
			return homsomApiImpl.getLoanAuditList(loanAuditListRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/get/detail/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getDetail(LoanAuditDetailRequest loanAuditDetailRequest, @PathVariable Channel channel) {
		try {
			loanAuditDetailRequest.setChannel(channel);
			return homsomApiImpl.getLoanAuditDetailList(loanAuditDetailRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}

	@RequestMapping(value = "/confirm/loan/{channel}", method = {RequestMethod.GET})
	@ResponseBody
	public ObjectResult<?> confirmLoan(String date, @PathVariable Channel channel) {
		try {
			homsomApiImpl.confirmLoan(date, channel);
			return ObjectResult.success("放款操作成功！");
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/export/{channel}", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, LoanAuditListRequest loanAuditListRequest, @PathVariable Channel channel) throws UnsupportedEncodingException {
		loanAuditListRequest.setChannel(channel);
		String excelTitle = "【恒顺国旅】放款信息";
		String fileName = excelTitle+".xls";
		if (request.getHeader("User-Agent").toUpperCase().matches(".*((MSIE)|(TRIDENT)|(EDGE)).*"))
			fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
		else
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		response.reset();
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
		response.setCharacterEncoding("utf-8");
		try {
			OutputStream out = response.getOutputStream();
			String[] excelHeaders = {"放款日期", "放款申请金额", "实际放款金额", "状态"};
			ExcelUtils.exportExcel(excelTitle, excelHeaders, homsomApiImpl.getExcelDTOs(loanAuditListRequest), out, false);
		} catch (IOException e) {
			logger.error("生成【恒顺国旅】放款信息文件失败", e);
			throw WebException.instance("生成【恒顺国旅】放款信息文件失败");
		}
	}
	
}
