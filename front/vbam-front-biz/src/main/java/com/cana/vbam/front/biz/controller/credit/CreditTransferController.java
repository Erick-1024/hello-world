package com.cana.vbam.front.biz.controller.credit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.flight.finance.common.dto.CreditTradeQueryCriteria;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.credit.dto.trade.CreditTradeFlowDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeOperateDTO;
import com.cana.vbam.common.credit.enums.CreditTradeType;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.utils.PasswordEncoderUtil;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping(value="/credit/transfer")
public class CreditTransferController {

	@Resource
	private IUserApi userApi;
	@Resource
	private ICreditApi creditApi;
	
	Logger logger=LoggerFactory.getLogger(CreditTransferController.class);
	
	@RequestMapping(value="/payment",method=RequestMethod.GET)
	public String gotoLoanFlow(){
		logger.info("进入放款流水列表页");
		return "page/credit/transfer/loanFlow";
	}
	
	@RequestMapping(value="/refund",method=RequestMethod.GET)
	public String gotoRefundFlow(){
		logger.info("进入退款流水列表页");
		return "page/credit/transfer/refundFlow";
	}
	
	/**
	 * 获取退款流水列表
	 */
	@RequestMapping(value="/refund",method=RequestMethod.POST)
	@ResponseBody
	public PageResult<CreditTradeFlowDTO> searchRefundFlowList(CreditTradeQueryCriteria criteria){
		PageResult<CreditTradeFlowDTO> pageResult=new PageResult<>();
		try {
			logger.info("查询退款流水列表");
			criteria.setTradeType(CreditTradeType.REFUND.name());
			pageResult=creditApi.queryCreditFlowList(criteria);
		} catch (Exception e) {
			logger.error("查询退款流水列表异常，{}",e);
		}
		return pageResult;
	}
	
	/**
	 * 获取放款款流水列表
	 */
	@RequestMapping(value="/payment",method=RequestMethod.POST)
	@ResponseBody
	public PageResult<CreditTradeFlowDTO> searchLoanFlowList(CreditTradeQueryCriteria criteria){
		PageResult<CreditTradeFlowDTO> pageResult=new PageResult<>();
		try {
			logger.info("查询放款流水列表");
			criteria.setTradeType(CreditTradeType.PAYMENT.name());
			pageResult=creditApi.queryCreditFlowList(criteria);
		} catch (Exception e) {
			logger.error("查询放款流水列表异常，{}",e);
		}
		return pageResult;
	}
	/**
	 * 点击人工操作获取相关信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/operate")
	@ResponseBody
	public ObjectResult<CreditTradeOperateDTO> getTransferInfo(String id){
		ObjectResult<CreditTradeOperateDTO> cResult=new ObjectResult<>();
		try {
			logger.info("点击人工操作获取转账信息");
			CreditTradeOperateDTO operateDTO=creditApi.getCreditTransferInfo(id);
			cResult.setData(operateDTO);
			cResult.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			logger.error("获取转账信息异常：{}",e);
		}
		return cResult;
	}
	
	/**
	 * 判断支付密码是否正确
	 */
	@RequestMapping(value="/payPassword")
	@ResponseBody
	public ObjectResult<Boolean> checkPayPassword(String payPassword){
		ObjectResult<Boolean> result=new ObjectResult<>();
		try {
			logger.info("校验支付密码");
			String customerId=SecurityContextUtils.getCustomerId();
			if(!userApi.validatePayPwd(customerId, payPassword)) {
				result.setMessage("支付密码错误");
				result.setStatus(AjaxResponseStatus.FAILED);
				return result;
			}
			result.setData(true);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			logger.error("校验支付密码是否正确异常,{}",e);
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("服务器异常");
		}
		return result;
	}
	/**
	 * 人工干预 失败的交易
	 * @param id 转账表id transfer_id
	 * @return
	 */
	@RequestMapping(value="/manual")
	@ResponseBody
	public ObjectResult<String> manualOperateCreditTransfer(String id,String password){
		ObjectResult<String> result=new ObjectResult<>();
		try {
			logger.info("人工干预放款失败的交易");
			if(!userApi.validatePayPwd(SecurityContextUtils.getCustomerId(), PasswordEncoderUtil.encrypt(password))){
				result.setMessage("支付密码错误");
				result.setStatus(AjaxResponseStatus.FAILED);
				return result;
			}
			String tradeStatus=creditApi.manualOperateCreditTransfer(id,SecurityContextUtils.getOperatorId()).desc();
			result.setData(tradeStatus);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch(WebException e) {
			result.setMessage(e.getMessage());
			result.setStatus(AjaxResponseStatus.FAILED);
		} catch (Exception e) {
			result.setMessage("服务器异常");
			result.setStatus(AjaxResponseStatus.FAILED);
		}
		return result;
	}
}
