package com.cana.yundaex.scheduler.rabbitmq.handler;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.annotation.MQConsumer;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.repayment.message.dto.RepaymentSuccessMessage;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexRepaymentResultDTO;
import com.cana.yundaex.common.dto.YundaexCustomerApplyDTO;
import com.cana.yundaex.common.dto.YundaexCustomerApplyQueryDTO;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.net.http.HttpTookit;

@MQConsumer(RepaymentSuccessMessage.class)
public class RepaymentSuccessHandler extends AbstractMQMessageHandler<RepaymentSuccessMessage>{

	@Override
	public void handleMessage(RepaymentSuccessMessage message) throws Exception {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(message.getLoanInfoId());
		if(!StringUtils.equals(loanInfoBO.getBusinessProductId(), "yundaex_project_id")){
			return;
		}
		logger.info("处理韵达项目还款成功消息开始");
		// 获取用户的stationNo
		YundaexOutCustomerExample yundaexExample = new YundaexOutCustomerExample();
		yundaexExample.createCriteria().andMemberIdEqualTo(message.getFinanceId());
		List<YundaexOutCustomer> customerList = yundaexOutCustomerMapper.selectByExample(yundaexExample);
		if(customerList == null || customerList.size() < 1){
			throw WebException.instance("用户不存在");
		}
		// 获取用户详细信息
		YundaexCustomerApplyQueryDTO yundaexCustomerApplyQueryDTO = new YundaexCustomerApplyQueryDTO();
		yundaexCustomerApplyQueryDTO.setStationNo(customerList.get(0).getStationNo());
		List<YundaexCustomerApplyDTO> customerApplyDTOList = yundaexCustomerApplyService.getYdCustApplyByParam(yundaexCustomerApplyQueryDTO);
		if(customerApplyDTOList == null || customerApplyDTOList.size() < 1){
			throw WebException.instance("用户申请信息不存在");
		}
		YundaexRepaymentResultDTO yundaexRepaymentResultDTO = new YundaexRepaymentResultDTO();
		yundaexRepaymentResultDTO.setStationNo(customerApplyDTOList.get(0).getStationNo());
		yundaexRepaymentResultDTO.setStationName(customerApplyDTOList.get(0).getStationName());
		yundaexRepaymentResultDTO.setCustName(customerApplyDTOList.get(0).getCustName());
		yundaexRepaymentResultDTO.setCustIdno(customerApplyDTOList.get(0).getCustIdno());
		// 获取放款信息
		RepaymentLoanInfo loanInfo = repaymentLoanInfoMapper.selectByPrimaryKey(message.getLoanInfoId());
		if(loanInfo == null){
			throw WebException.instance("放款信息不存在");
		}
		yundaexRepaymentResultDTO.setPutoutno(loanInfo.getLoanNo());
		yundaexRepaymentResultDTO.setPutoutamt(loanInfo.getFinanceAmount());
		yundaexRepaymentResultDTO.setRepaymode(loanInfo.getRepaymentMethod());
		yundaexRepaymentResultDTO.setRepayamt(message.getTotal());
		yundaexRepaymentResultDTO.setRepaydate(message.getRepaymentDate());
		StringBuffer sb = new StringBuffer();
		sb.append(yundaexRepaymentResultDTO.getStationNo());
		sb.append(yundaexRepaymentResultDTO.getStationName());
		sb.append(yundaexRepaymentResultDTO.getCustName());
		sb.append(yundaexRepaymentResultDTO.getCustIdno());
		sb.append(yundaexRepaymentResultDTO.getPutoutno());
		yundaexRepaymentResultDTO.setSign(new String(vbamCommonServiceImpl.sign(sb.toString().getBytes(), Institution.yd.name(), false)));
		logger.info("处理韵达项目还款成功内容  {}", new Gson().toJson(yundaexRepaymentResultDTO));
		String returnStr = HttpTookit.doPostJson(Constants.YUNDAEX_REPAYMENT_RESULT_URL_PREFIX, yundaexRepaymentResultDTO);
		logger.info("韵达返回数据 {}", returnStr);
		logger.info("处理韵达项目还款成功结束");
	}

}
