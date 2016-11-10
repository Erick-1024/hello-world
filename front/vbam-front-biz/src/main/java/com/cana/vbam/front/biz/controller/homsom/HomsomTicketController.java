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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IHomsomApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.homsom.dto.TicketListRequest;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelUtils;

@Controller
@RequestMapping("homsom/ticket")
public class HomsomTicketController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IHomsomApi homsomApiImpl;
	
	@RequestMapping(value = "/goto/list/{channel}", method = { RequestMethod.GET })
	public String gotolist(Model model, @PathVariable Channel channel) {
		logger.info("进入客票列表页面");
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
		return "page/homsom/ticket/list";
	}

	@RequestMapping(value = "/get/list/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getList(TicketListRequest ticketListRequest, @PathVariable Channel channel) {
		try {
			ticketListRequest.setChannel(channel);
			return homsomApiImpl.getTicketList(ticketListRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/export/{channel}", method = RequestMethod.POST)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, TicketListRequest ticketListRequest, @PathVariable Channel channel) throws UnsupportedEncodingException {
		ticketListRequest.setChannel(channel);
		String excelTitle = "【恒顺国旅】客票信息";
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
			String[] excelHeaders = {"客票编号", "代理商名称", "订单编号", "购票金额", "出票日期", "航班号", "航程", "起飞时间", "乘客姓名", "状态"};
			ExcelUtils.exportExcel(excelTitle, excelHeaders, homsomApiImpl.getExcelDTOs(ticketListRequest), out, false);
		} catch (IOException e) {
			logger.error("生成【恒顺国旅】客票信息文件失败", e);
			throw WebException.instance("生成【恒顺国旅】客票信息文件失败");
		}
	}
	
}
