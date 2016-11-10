package com.cana.early.warning.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.credit.api.ICreditApi;
import com.cana.early.warning.dao.mapper.EarlyWarningAndCreditCustomMapper;
import com.cana.early.warning.dao.mapper.EarlyWarningEventCustomMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningCustomerMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventMapper;
import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.early.warning.dao.po.EarlywarningEventExample;
import com.cana.early.warning.dao.po.EarlywarningEventExample.Criteria;
import com.cana.early.warning.service.IYundaexEarlyWarningService;
import com.cana.early.warning.service.convertors.EarlyWarningEventConvertor;
import com.cana.early.warning.service.transaction.IEarlyWarningEventTransactionService;
import com.cana.early.warning.service.utils.IEarlyWarningServiceHelper;
import com.cana.repayment.api.IRepaymentProductApi;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;

@Service
public class YundaexEarlyWarningServiceImpl implements IYundaexEarlyWarningService {

	@Resource
	private IEarlyWarningServiceHelper earlywarningServiceHelper;
	
	@Resource
	private IRepaymentProductApi repaymentProductApiImpl;
	
	@Resource
	private ICreditApi creditApiImpl;
	
	@Resource
	private IEarlyWarningEventTransactionService earlywarningEventTransactionServiceImpl;
	
	@Resource
	private EarlywarningEventMapper earlywarningEventMapper;
	
	@Resource
	private EarlywarningCustomerMapper earlywarningCustomerMapper;
	
	@Resource
	private EarlyWarningEventCustomMapper earlywarningEventCustomerMapper;
	
	@Resource
	private EarlyWarningAndCreditCustomMapper earlyWarningAndCreditCustomMapper;
	
	@Override
	public List<EarlyWarningEventDetailDTO> getSingleEarlyWarningEventCollect(List<String> state, String productId, String memberId, String outCustomerId) {
		List<EarlywarningEvent> allEvents = getEventsWithState(state, productId, memberId, outCustomerId);
		Iterator<EarlywarningEvent> iterator = allEvents.iterator();
		List<EarlywarningEvent> nonSystemEvents = new ArrayList<>();//预警种类为除了‘系统’的事件
		while (iterator.hasNext()) {
			EarlywarningEvent earlywarningEvent = iterator.next();
			if(!YundaexEarlywarningEventCategory.SYSTEM.name().equals(earlywarningEvent.getType())) {
				nonSystemEvents.add(earlywarningEvent);
				iterator.remove();
			}
		}//到目前为止，allEvents只剩下‘系统’的事件
		Map<YundaexEarlywarningEventCategory,EarlywarningLevel> eventCategoryMap = new HashMap<>();

		for (EarlywarningEvent event : nonSystemEvents) {
			EarlywarningLevel oldLevel = eventCategoryMap.get(event.getType());
			EarlywarningLevel eventLevel = EarlywarningLevel.valueOf(event.getLevel());
			if(oldLevel == null || eventLevel.compareTo(oldLevel) > 0)
				eventCategoryMap.put(YundaexEarlywarningEventCategory.valueOf(event.getType()), eventLevel);
		}
		Iterator<Map.Entry<YundaexEarlywarningEventCategory,EarlywarningLevel>> it = eventCategoryMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<YundaexEarlywarningEventCategory,EarlywarningLevel> entry = it.next();
			EarlywarningEvent event = new EarlywarningEvent();
			event.setType(entry.getKey().name());
			event.setLevel(entry.getValue().name());
			event.setRepresent("有");
			event.setExtraData("有");
			allEvents.add(event);
		}
		return EarlyWarningEventConvertor.convertYundaexEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(allEvents);
	}

	private List<EarlywarningEvent> getEventsWithState(List<String> state, String productId, String memberId, String outCustomerId) {
		EarlywarningEventExample earlywarningEventExample = new EarlywarningEventExample();
		Criteria criteria = earlywarningEventExample.createCriteria();
		criteria.andProductIdEqualTo(productId).andFinanceIdEqualTo(memberId).andOutCustomerIdEqualTo(outCustomerId);
		if(state !=null)
			criteria.andStateIn(state);
		return earlywarningEventMapper.selectByExample(earlywarningEventExample);
	}
	
}
