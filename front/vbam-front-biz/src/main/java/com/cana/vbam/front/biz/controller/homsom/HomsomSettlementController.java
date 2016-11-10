package com.cana.vbam.front.biz.controller.homsom;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.asset.api.IHomsomApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.homsom.dto.HomsomBuyBackTicketExcelListRequest;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketExcelListRequest;
import com.cana.vbam.common.homsom.dto.PaymentDataRequestDTO;
import com.cana.vbam.common.homsom.dto.StatementExcelDTO;
import com.cana.vbam.common.homsom.dto.SubmitSettlementRequestDTO;
import com.cana.vbam.common.homsom.dto.TicketLoanRequestDTO;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.PaymentType;
import com.cana.vbam.common.homsom.enums.SettlementState;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelIEUtil;
import com.travelzen.framework.util.ExcelUtils;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;

@Controller
@RequestMapping("/homsom/settlement")
public class HomsomSettlementController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IHomsomApi homsomApiImpl;
	
	@RequestMapping(value = "/goto/settlement/import/{channel}", method = { RequestMethod.GET })
	public String gotoSettlementImport(Model model, @PathVariable Channel channel) {
		logger.info("进入还款核销导入页面");
		model.addAttribute("title", getTitle(channel));
		model.addAttribute("channel", channel);
		model.addAttribute("rediskey", homsomApiImpl.generateSettlementRedisKey());
		model.addAttribute("name", "还款");
		model.addAttribute("subTitle", "还款核销");
		model.addAttribute("urlParam", "settlement");
		model.addAttribute("redict", "reconciliation/" + channel);
		return "page/homsom/settlement/import";
	}
	
	@RequestMapping(value = "/settlement/upload/{channel}", method = RequestMethod.POST)
	@ResponseBody
	public void settlementUpload(@RequestParam MultipartFile excel, @RequestParam String rediskey, HttpServletResponse httpServletResponse, @PathVariable Channel channel) throws IOException {
		try {
			String fileName = excel.getOriginalFilename();
			byte[] bytes = excel.getBytes();
			List<List<String>> list = ExcelIEUtil.readFromInputStream(new ByteArrayInputStream(bytes), fileName, 15, 0, 1);
			if (CollectionUtils.isEmpty(list)) {
				logger.info("还款核销Excel数据为空！");
				httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"Excel数据为空！\"}");
			} else {
				homsomApiImpl.importSettlement(list, SecurityContextUtils.getOperatorId(), rediskey, MediaClientUtil.upload(bytes, MediaType.IMAGE, fileName), channel);
				httpServletResponse.getWriter().write("{\"status\":\"SUCCESS\",\"message\":\"上传还款核销Excel成功！\"}");
			}
		} catch (Exception e) {
			logger.error("还款核销excel导入错误", e);
			httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"还款核销Excel导入失败！\"}");
		}
	}

	@RequestMapping(value = "/settlement/query", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<?> settlementQuery(HomsomSettlementTicketExcelListRequest homsomSettlementTicketExcelListRequest) {
		try {
			homsomSettlementTicketExcelListRequest.setUserId(SecurityContextUtils.getOperatorId());
			return homsomApiImpl.getHomsomSettlementTicketExcelDTOFromRedis(homsomSettlementTicketExcelListRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/settlement/import", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> importSettlement2DB(String rediskey) {
		try {
			homsomApiImpl.importSettlement2DB(SecurityContextUtils.getOperatorId(), rediskey);
			return ObjectResult.success("还款核销导入成功");
		} catch (WebException e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/goto/buyBack/import/{channel}", method = { RequestMethod.GET })
	public String gotoBuyBackImport(Model model, @PathVariable Channel channel) {
		logger.info("进入账款回购导入页面");
		model.addAttribute("title", getTitle(channel));
		model.addAttribute("rediskey", homsomApiImpl.generateSettlementRedisKey());
		model.addAttribute("name", "回购");
		model.addAttribute("subTitle", "账款回购");
		model.addAttribute("urlParam", "buyBack");
		model.addAttribute("redict", "buyback/" + channel);
		return "page/homsom/settlement/import";
	}
	
	@RequestMapping(value = "/buyBack/upload/{channel}", method = RequestMethod.POST)
	@ResponseBody
	public void buyBackUpload(@RequestParam MultipartFile excel, @RequestParam String rediskey, HttpServletResponse httpServletResponse, @PathVariable Channel channel) throws IOException {
		try {
			String fileName = excel.getOriginalFilename();
			byte[] bytes = excel.getBytes();
			List<List<String>> list = ExcelIEUtil.readFromInputStream(new ByteArrayInputStream(bytes), fileName, 15, 0, 1);
			if (CollectionUtils.isEmpty(list)) {
				logger.info("账款回购销Excel数据为空！");
				httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"Excel数据为空！\"}");
			} else {
				homsomApiImpl.importBuyBack(list, SecurityContextUtils.getOperatorId(), rediskey, MediaClientUtil.upload(bytes, MediaType.IMAGE, fileName), channel);
				httpServletResponse.getWriter().write("{\"status\":\"SUCCESS\",\"message\":\"上传账款回购Excel成功！\"}");
			}
		} catch (Exception e) {
			logger.error("账款回购excel导入错误", e);
			httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"账款回购Excel导入失败！\"}");
		}
	}

	@RequestMapping(value = "/buyBack/query", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<?> buyBackQuery(HomsomBuyBackTicketExcelListRequest homsomBuyBackTicketExcelListRequest) {
		try {
			homsomBuyBackTicketExcelListRequest.setUserId(SecurityContextUtils.getOperatorId());
			return homsomApiImpl.getHomsomBuyBackTicketExcelDTOFromRedis(homsomBuyBackTicketExcelListRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/buyBack/import", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> importBuyBack2DB(String rediskey) {
		try {
			homsomApiImpl.importBuyBack2DB(SecurityContextUtils.getOperatorId(), rediskey);
			return ObjectResult.success("账款回购导入成功");
		} catch (WebException e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/goto/reconciliation/{channel}", method = { RequestMethod.GET })
	public String gotoSettlementReconciliation(Model model, @PathVariable Channel channel) {
		logger.info("进入还款核销对账页面");
		PaymentDataRequestDTO queryDTO = new PaymentDataRequestDTO();
		queryDTO.setChannel(channel);
		queryDTO.setPaymentType(PaymentType.SETTLEMENT);
		queryDTO.setState(SettlementState.UNSETTLE);
		model.addAttribute("channel", channel);
		model.addAttribute("summaryData", homsomApiImpl.querySummaryDataByPaymentType(queryDTO));
		return "page/homsom/settlement/reconciliation";
	}

	@RequestMapping(value = "/goto/buyback/{channel}", method = { RequestMethod.GET })
	public String gotoSettlementBuyback(Model model, @PathVariable Channel channel) {
		logger.info("进入还款核销对账页面");
		PaymentDataRequestDTO queryDTO = new PaymentDataRequestDTO();
		queryDTO.setChannel(channel);
		queryDTO.setPaymentType(PaymentType.BUYBACK);
		queryDTO.setState(SettlementState.UNSETTLE);
		model.addAttribute("channel", channel);
		model.addAttribute("summaryData", homsomApiImpl.querySummaryDataByPaymentType(queryDTO));
		return "page/homsom/settlement/buyback";
	}
	
	@RequestMapping(value = "/get/reconciliation/list/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getList(TicketLoanRequestDTO queryDTO, @PathVariable Channel channel) {
		try {
			queryDTO.setChannel(channel);
			return homsomApiImpl.querySettlementCounterpartyDetail(queryDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}

	@RequestMapping(value = "/get/reconciliation/ticket/list/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getSettlementTicketList(TicketLoanRequestDTO queryDTO, @PathVariable Channel channel) {
		try {
			queryDTO.setChannel(channel);
			return homsomApiImpl.queryTicketLoanDetail(queryDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}

	@RequestMapping(value = "/reconciliation/comfirm/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> getSettlementConfirm(@RequestBody SubmitSettlementRequestDTO requestDTO, @PathVariable Channel channel) {
		try {
			requestDTO.setChannel(channel);
			return homsomApiImpl.selttlementConfirm(SecurityContextUtils.getCustomerId(), requestDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}

	@RequestMapping(value = "/get/buyback/list/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getBuybackList(TicketLoanRequestDTO queryDTO, @PathVariable Channel channel) {
		try {
			queryDTO.setChannel(channel);
			return homsomApiImpl.querySettlementCounterpartyDetail(queryDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/get/buyback/ticket/list/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getBuybackTicketList(TicketLoanRequestDTO queryDTO, @PathVariable Channel channel) {
		try {
			queryDTO.setChannel(channel);
			return homsomApiImpl.queryTicketLoanDetail(queryDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}

	@RequestMapping(value = "/get/buyback/ticket/list/check/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> buybackTicketListCheck(TicketLoanRequestDTO queryDTO, @PathVariable Channel channel) {
		try {
			queryDTO.setChannel(channel);
			return homsomApiImpl.buybackTicketListCheck(queryDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "export/settlement/{channel}", method = RequestMethod.GET)
	public void exportSettlementExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable Channel channel) throws UnsupportedEncodingException {
		String fileName = "核销对账单.xls";
		setResponseHeader(fileName, request, response);
		try {
			OutputStream out = response.getOutputStream();
			HSSFWorkbook workbook = new HSSFWorkbook();
			ExcelUtils.generateWorkbook(workbook, "核销汇总", new String[]{"交易对手", "放款总金额", "核销总金额", "实还总金额", "归还总金额", "回购总金额"}, homsomApiImpl.getSettlementCounterpartyExcelList(channel), out, false);
			Map<String, StatementExcelDTO> settlementExcelDetailList = homsomApiImpl.getSettlementExceDetaillList(channel);
			ExcelUtils.generateWorkbook(workbook, "核销明细", new String[]{"开票日期", "单位编号", "单位客户名称", "销售单号", "票号", "结算金额", "回购日", "提现金额", "提现日期", "备注", "还款日期", "账单账期", "结算账期", "利息", "20%退款", "实际退款金额", "状态"}, settlementExcelDetailList.values(), out, false);
			List<StatementExcelDTO> repayemntNoticeList = homsomApiImpl.getRepaymentNoticeList(channel);
			Iterator<StatementExcelDTO> iterators = repayemntNoticeList.iterator();
			while (iterators.hasNext()) {
				StatementExcelDTO statementExcelDTO = iterators.next();
				if(settlementExcelDetailList.containsKey(statementExcelDTO.getTicketNo()))
					iterators.remove();
			}
			ExcelUtils.generateWorkbook(workbook, "还款提醒", new String[]{"开票日期", "单位编号", "单位客户名称", "销售单号", "票号", "结算金额", "回购日", "提现金额", "提现日期", "备注", "还款日期", "账单账期", "结算账期", "利息", "20%退款", "实际退款金额", "状态"}, repayemntNoticeList, out, false);
			workbook.write(out);
			IOUtils.closeQuietly(out);
		} catch (IOException e) {
			logger.error("生成" + fileName + "文件失败", e);
			throw WebException.instance("生成" + fileName + "文件失败");
		}
	}

	@RequestMapping(value = "export/buyback/{channel}", method = RequestMethod.GET)
	public void exportBuyback(HttpServletRequest request, HttpServletResponse response, @PathVariable Channel channel) throws UnsupportedEncodingException {
		String fileName = "回购对账单.xls";
		setResponseHeader(fileName, request, response);
		try {
			OutputStream out = response.getOutputStream();
			HSSFWorkbook workbook = new HSSFWorkbook();
			ExcelUtils.generateWorkbook(workbook, "回购汇总", new String[]{"交易对手", "应回购总金额", "回购总金额"}, homsomApiImpl.getBuybackCounterpartyExcelList(channel), out, false);
			ExcelUtils.generateWorkbook(workbook, "回购明细", new String[]{"开票日期", "单位编号", "单位客户名称", "销售单号", "票号", "结算金额", "回购日", "提现金额", "提现日期", "备注", "还款日期", "账单账期", "结算账期", "利息", "20%退款", "实际退款金额", "状态"}, homsomApiImpl.getBuyBackExcelDetailList(channel), out, false);
			workbook.write(out);
			IOUtils.closeQuietly(out);
		} catch (IOException e) {
			logger.error("生成" + fileName + "文件失败", e);
			throw WebException.instance("生成" + fileName + "文件失败");
		}
	}
	
	private void setResponseHeader(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if (request.getHeader("User-Agent").toUpperCase().matches(".*((MSIE)|(TRIDENT)|(EDGE)).*"))
			fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
		else
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		response.reset();
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
		response.setCharacterEncoding("utf-8");
	}

	private String getTitle(Channel channel) {
		String title = null;
		switch (channel) {
		case HOMSOM:
			title = "恒顺国旅项目";
			break;
		case SHFH:
			break;
		default:
			break;
		}
		return title;
	}
	
}
