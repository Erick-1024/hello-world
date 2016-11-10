package com.cana.credit.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.credit.dao.mapper.OutCustomerDataCustomMapper;
import com.cana.credit.dao.mapper.gen.OutCustomerMapper;
import com.cana.credit.dao.po.OutCustomer;
import com.cana.credit.dao.po.OutCustomerExample;
import com.cana.credit.dao.po.OutCustomerExample.Criteria;
import com.cana.credit.service.IOutCustomerService;
import com.cana.credit.service.convertors.OutCustomerConvertor;
import com.cana.credit.service.utils.CheckParamUtil;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerDTO;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerQuery;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class OutCustomerServiceImpl implements IOutCustomerService {

	@Resource
	private OutCustomerMapper outCustomerMapper;
	
	@Resource
	private OutCustomerDataCustomMapper outCustomerDataCustomMapper;
	
	@Resource
	private DailyBillMapper dailyBillMapper;

	@Override
	public Map<String, Map<String, String>> getAllOutCustomer() {
		return OutCustomerConvertor.convertOutCustomer2Map(outCustomerMapper.selectByExample(null));
	}

	@Override
	public List<OutCustomerDTO> getOutCustomerDTO(OutCustomerQuery outCustomerQuery) {
		OutCustomerExample example = null;
		if(outCustomerQuery != null) {
			example = new OutCustomerExample();
			Criteria criteria = example.createCriteria();
			String memberId = outCustomerQuery.getMemberId();
			if(memberId != null)
				criteria.andMemberIdEqualTo(memberId);
			String institutionId = outCustomerQuery.getInstitutionId();
			if(institutionId != null)
				criteria.andInstitutionIdEqualTo(institutionId);
			String customerId = outCustomerQuery.getCustomerId();
			if(customerId != null)
				criteria.andOutCustomerIdEqualTo(customerId);
			String companyName = outCustomerQuery.getCompanyName();
			if(companyName != null)
				criteria.andCompanyNameEqualTo(companyName);
		}
		return OutCustomerConvertor.convertOutCustomer2OutCustomerDTO(outCustomerMapper.selectByExample(example));
	}
	
	@Override
	public String getCanaFinanceIdByOutCustomerId(String institution, String outCustomerId) {
		CheckParamUtil.checkInstitution(institution);
		OutCustomerExample example = new OutCustomerExample();
		example.createCriteria().andInstitutionIdEqualTo(institution).andOutCustomerIdEqualTo(outCustomerId);
		List<OutCustomer> outCustomers = outCustomerMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(outCustomers) || outCustomers.size() > 1)
			throw WebException.instance(ReturnCode.TP3002);
		return outCustomers.get(0).getMemberId();
	}

	@Override
	public int getOutCustomerNumber(String institution) {
		OutCustomerExample outCustomerExample = new OutCustomerExample();
		outCustomerExample.createCriteria().andInstitutionIdEqualTo(institution);
		return outCustomerMapper.countByExample(outCustomerExample);
	}

	@Override
	public List<MonitorMoneyDTO> getFlightTicketSales(String date10) {
		return outCustomerDataCustomMapper.getFlightTicketSales(date10);
	}

	@Override
	public List<MonitorMoneyDTO> getQualifiedAR(String startRecordId) {
		return outCustomerDataCustomMapper.getQualifiedAR(startRecordId);
	}

	@Override
	public List<MonitorMoneyDTO> getDailySales(String startMonth, String endMonth, int dayNumber) {
		return outCustomerDataCustomMapper.getDailySales(startMonth, endMonth, dayNumber);
	}
	
}
