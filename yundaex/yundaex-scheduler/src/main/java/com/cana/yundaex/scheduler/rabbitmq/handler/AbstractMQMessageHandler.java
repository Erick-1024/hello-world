package com.cana.yundaex.scheduler.rabbitmq.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.service.IYundaexCustomerApplyService;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public abstract class AbstractMQMessageHandler<T> {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected IYundaexCustomerApplyService yundaexCustomerApplyService = SpringApplicationContext.getApplicationContext().getBean(IYundaexCustomerApplyService.class);

	protected YundaexOutCustomerMapper yundaexOutCustomerMapper = SpringApplicationContext.getApplicationContext().getBean(YundaexOutCustomerMapper.class);

	protected RepaymentLoanInfoMapper repaymentLoanInfoMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentLoanInfoMapper.class);

	protected IVbamCommonService vbamCommonServiceImpl = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);
	
	public abstract void handleMessage(T message) throws Exception;
	
}
