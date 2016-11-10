package com.cana.vbam.front.biz.controller.vj;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.vj.dto.BankBillListResponse;
import com.cana.vbam.common.vj.dto.TranDetailResponse;
import com.cana.vbam.common.vj.dto.VJTranQueryDTO;
import com.cana.vbam.common.vj.dto.VJTranRepaymentDetailDTO;
import com.cana.vbam.common.vj.dto.VJTranRepaymentResponseDTO;
import com.cana.vbam.common.vj.enums.TranState;
import com.cana.vbam.common.vj.enums.TranType;
import com.cana.vj.api.IVJApi;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.util.ExcelUtils;

@Controller
@RequestMapping(value = "/vj/tran")
public class TranController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IVJApi vjApi;
	
	/**---------放款明细模块------------**/
	@RequestMapping(value="/loan/detailList", method = RequestMethod.GET)
	public String gotoLoanDetail(Model model){
		logger.info("进入放款明细列表页！");
		return "page/vj/tran/loanDetailList";
	}
	
	@RequestMapping(value="/loan/detail/searchList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ListResult<TranDetailResponse> searchloanDetailList(VJTranQueryDTO queryDTO){
		try {
			queryDTO.setTranType(TranType.CONFIRM_LOAN);
			return vjApi.searchloanDetailList(queryDTO);
		} catch (Exception e) {
			logger.info("获取放款明细列表异常:", e);
			return ListResult.fail("获取放款明细列表异常");
		}
	}

	/**---------主动还款明细模块------------**/
	@RequestMapping(value="/repayment/detailList",method = RequestMethod.GET)
	public String gotoRepaymentDetailList(Model model){
		model.addAttribute("tranStateList", Arrays.asList(TranState.values()));
		logger.info("进入还款明细列表页！");
		return "page/vj/tran/repaymentDetailList";
	}
	
	@RequestMapping(value="/repayment/details/searchList",method = RequestMethod.POST)
	@ResponseBody
	public ListResult<VJTranRepaymentResponseDTO> searchRepaymentDetailsList(VJTranQueryDTO queryDTO){
		queryDTO.setTranType(TranType.ACTIVE_REPAYMENT);
		try {
			ListResult<VJTranRepaymentResponseDTO> response = vjApi.getRepaymentDetailsList(queryDTO);
			return response;
		} catch (Exception e) {
			logger.info("获取主动还款明细列表异常:", e);
			return ListResult.fail("获取主动还款明细列表异常");
		}
	}
	
	/**
	 * 根据还款汇总记录Id获取还款详情
	 * @param loanId
	 * @return
	 */
	@RequestMapping(value="repayment/detail",method=RequestMethod.POST)
	@ResponseBody
	public ListResult<VJTranRepaymentDetailDTO> getRepaymentDetail(@RequestParam String repaymentSingleCollectId)
	{
		ListResult<VJTranRepaymentDetailDTO> result = new ListResult<VJTranRepaymentDetailDTO>();
		try {
			result = vjApi.queryRepaymentDetail(repaymentSingleCollectId);
			result.setStatus(AjaxResponseStatus.SUCCESS);
			logger.info("查询还款详情成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询还款详情失败,原因："+e.getMessage());
			logger.error("查询还款详情失败", e);
		}
		return result;
	}
	
	/**---------对账模块------------**/
	@RequestMapping(value="/account/balance", method = RequestMethod.GET)
	public String gotoBalanceOfAccount(Model model){
		logger.info("进入对账列表页！");
		return "page/vj/tran/accountBalance";
	}
	
	@RequestMapping(value="/account/balance/list", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ListResult<BankBillListResponse> searchBalanceOfAccountList(VJTranQueryDTO queryDTO){
		try {
			return vjApi.searchBalanceOfAccountList(queryDTO);
		} catch (Exception e) {
			logger.error("获取对账明细列表异常:", e);
			return ListResult.fail("获取对账明细列表异常");
		}
	}

	@RequestMapping(value="/account/balance/export")
	public void exportAccountBalance(VJTranQueryDTO queryDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListResult<BankBillListResponse> result=new ListResult<>();
		try {
			logger.info("导出VJ对账明细(没有分页)");
			result = vjApi.searchBalanceOfAccountList(queryDTO);
		} catch (Exception e) {
			logger.error("查询VJ对账单异常，{}",e);
		}
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String excelTitle = "VJ对账单";
		if(queryDTO.getTranStartDate()!=null && queryDTO.getTranEndDate()!=null)
			excelTitle+="("+DateTimeUtil.date10(queryDTO.getTranStartDate())+"_"+DateTimeUtil.date10(queryDTO.getTranEndDate())+")";
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
			String[] excelHeaders = {"序号", "CANA交易流水号", "客户名称", "身份证号", "交易类型", "交易状态", "金额", "交易时间"};
			ExcelUtils.exportExcel(excelTitle, excelHeaders, result.getData(), out, true);
		} catch (IOException e) {
			logger.error("生成VJ对账单文件失败", e);
			throw WebException.instance("您请求的文件不存在");
		}
	}
	
}
