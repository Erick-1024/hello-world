package com.cana.vbam.front.biz.controller.credit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.flight.finance.common.dto.CreditTradeQueryCriteria;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.credit.dto.trade.CreditStatementDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeFlowDTO;
import com.cana.vbam.common.dto.PageResult;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelUtils;

@Controller
@RequestMapping(value="/credit")
public class CreditController {

	@Resource
	private IUserApi userApi;
	@Resource
	private ICreditApi creditApi;
	
	Logger logger=LoggerFactory.getLogger(CreditController.class);
	
	@RequestMapping(value="/statement",method=RequestMethod.GET)
	public String gotoStatementPage(){
		logger.info("进入对账单下载页");
		return "page/credit/statement";
	}
	
	@RequestMapping(value="/searchStatement",method=RequestMethod.POST)
	@ResponseBody
	public PageResult<CreditTradeFlowDTO> searchStatement(CreditTradeQueryCriteria criteria){
		PageResult<CreditTradeFlowDTO> pageResult=new PageResult<>();
		try {
			logger.info("查询对账单");
			pageResult=creditApi.queryCreditFlowList(criteria);
		} catch (Exception e) {
			logger.error("查询对账单异常，{}",e);
		}
		return pageResult;
	}
	
	@RequestMapping(value="/exportStatement")
	public void exportStatement(CreditTradeQueryCriteria criteria, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageResult<CreditStatementDTO> pageResult=new PageResult<>();
		try {
			logger.info("查询对账单(没有分页)");
			pageResult = creditApi.getCreditStatementNoPaging(criteria);
		} catch (Exception e) {
			logger.error("查询对账单异常，{}",e);
		}
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String excelTitle = "对账单";
		if(StringUtils.isNotBlank(criteria.getTradeStartDate()) && StringUtils.isNotBlank(criteria.getTradeEndDate()))
			excelTitle+="("+criteria.getTradeStartDate()+"_"+criteria.getTradeEndDate()+")";
		String fileName = excelTitle+".xls";
		if (userAgent.contains("MSIE")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
		}else{
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
		response.setCharacterEncoding("utf-8");
		
		try {
			OutputStream out = response.getOutputStream();
			String[] excelHeaders = {"序号", "真旅订单编号", "真旅客户名称", "账户业务流水号", "交易类型", "交易状态", "转账状态", "金额", "交易时间"};
			ExcelUtils.exportExcel(excelTitle, excelHeaders, pageResult.getData(), out, true);
		} catch (IOException e) {
			logger.error("生成对账单文件失败", e);
			throw WebException.instance("您请求的文件不存在");
		}
	}
}
