package com.cana.flight.finance.service.transaction.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.flight.finance.common.enums.NoValueCause;
import com.cana.flight.finance.common.enums.OrderType;
import com.cana.flight.finance.dao.mapper.gen.FlightTicketMapper;
import com.cana.flight.finance.dao.po.FlightTicket;
import com.cana.flight.finance.dao.po.FlightTicketExample;
import com.cana.flight.finance.service.constants.FlightFinanceConstant;
import com.cana.flight.finance.service.transaction.IFlightTicketTransactionService;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class FlightTicketTransactionServiceImpl implements IFlightTicketTransactionService {

	@Resource
	private FlightTicketMapper flightTicketMapper;
	
	@Override
	public String updateFlag(String startRecordId) {
		
		// 获取标记开始位置到昨天最后一张客票的所有客票记录，按出票顺序倒序
		FlightTicketExample flightTicketExample = new FlightTicketExample();
		flightTicketExample.createCriteria().andRecordIdGreaterThanOrEqualTo(startRecordId).andCompleteIssueTimeLessThan(DateTimeUtil.date10()).andNoValueCauseIsNull();
		flightTicketExample.or(flightTicketExample.createCriteria().andRecordIdGreaterThanOrEqualTo(startRecordId).andCompleteIssueTimeLessThan(DateTimeUtil.date10()).andNoTakeOffNumberNotEqualTo(0));
		flightTicketExample.setOrderByClause("record_id desc");
		// 本次共需更新客票记录数
		int count = flightTicketMapper.countByExample(flightTicketExample);
		int start = 0;
		// 记录本批退票、改签票的originRecordId，用于更新原客票的无价值原因 
		Map<String, NoValueCause> nugatoryRecordIds = new HashMap<>();

		while (start < count) {
			flightTicketExample.setLimitStart(start);
			flightTicketExample.setLimitEnd(FlightFinanceConstant.update_flag_size);
			
			List<FlightTicket> flightTickets = flightTicketMapper.selectByExample(flightTicketExample);
			for (FlightTicket flightTicket : flightTickets) {
				// 取值
				String recordId= flightTicket.getRecordId();
				String originRecordId = flightTicket.getOrigRecordId();
				OrderType orderType = OrderType.valueOf(flightTicket.getOrderType());
				String[] departureDateTimes = flightTicket.getDepartureDateTime().split(",");
				Boolean oldValuable = flightTicket.getIsValuable();
				Integer noTakeOffNumber = flightTicket.getNoTakeOffNumber();
				String noValueCause = flightTicket.getNoValueCause();
				
				// 判断航班是否全部起飞
				if(noTakeOffNumber == null || 0 != noTakeOffNumber) {
					flightTicket.setNoTakeOffNumber(getNotTakeOffNumber(departureDateTimes));
					if(flightTicket.getNoTakeOffNumber() == 0) {
						if(alterableNoValueCause(noValueCause)) {
							flightTicket.setIsValuable(Boolean.FALSE);
							flightTicket.setNoValueCause(NoValueCause.TAKE_OFF.name());
						}
					} else {
						if(alterableNoValueCause(noValueCause))
							flightTicket.setIsValuable(Boolean.TRUE);
						startRecordId = recordId;	// 只要还有未起飞的航班，就将该客票的recordId作为下次更新客票标记的起始位置
					}
				}
				
				// 更新客票是否有价值、无价值原因字段
				if(alterableNoValueCause(noValueCause)) {
					switch (orderType) {
					case REFUND:
						setFlightTicketNoValue(flightTicket, NoValueCause.REFUND);
						nugatoryRecordIds.put(originRecordId, NoValueCause.REFUND);
						break;
					case ENDORSE:
						if(nugatoryRecordIds.containsKey(recordId)) {
							setFlightTicketNoValue(flightTicket, nugatoryRecordIds.get(recordId));
							nugatoryRecordIds.remove(recordId);
							nugatoryRecordIds.put(originRecordId, NoValueCause.ENDORSE);
						} else
							nugatoryRecordIds.put(originRecordId, NoValueCause.REFUND);
						break;
					default:
						if(nugatoryRecordIds.containsKey(recordId)) {
							setFlightTicketNoValue(flightTicket, nugatoryRecordIds.get(recordId));
							nugatoryRecordIds.remove(recordId);
						}
						break;
					}
				}
				
				// 当客票的的价值发生变化（有价值->无价值；未起飞航班数量发生变化），则更新该客票
				if(flightTicket.getIsValuable() != oldValuable || flightTicket.getNoTakeOffNumber() != noTakeOffNumber) {
					flightTicket.setUpdateTime(new Date());
					flightTicketMapper.updateByPrimaryKeySelective(flightTicket);
				}
			}
			start += FlightFinanceConstant.update_flag_size;
		}
		return startRecordId;
	}
	
	/**
	 * 判断当前的导致没有价值原因的状态是否最终状态（当状态为被退票、被改签时为不可修改的最终状态）<br>
	 * 解释为何全部起飞的航班不是最终状态：<br>
	 * 因为更新客票的标记无法做到实时更新，会存在一段时间间隔，假设一个订单是6点购买的，它是10点起飞的机票，然后在7点是被改签了一张第二天10点的机票。然后定时更新是在晚上24点进行的。这样就有可能先将最开始10点的票无价值原因标记为全部起飞，然后程序执行到那张改签票时就会将之前无价值原因标记为全部起飞的客票重新改为被改签（因为被改签的优先级大于航班全部起飞）
	 * @param noValueCause 导致没有价值的原因
	 * @return
	 */
	private boolean alterableNoValueCause(String noValueCause) {
		return !NoValueCause.REFUND.name().equals(noValueCause) && !NoValueCause.ENDORSE.name().equals(noValueCause);
	}
	
	/**
	 * 获取未起飞的航班数量
	 * @param departureDateTimes 所有航班的起飞时间
	 * @return
	 */
	private int getNotTakeOffNumber(String[] departureDateTimes) {
		int notTakeOffNumber = 0;
		for (String departureDateTime : departureDateTimes)
			if(departureDateTime.compareTo(DateTimeUtil.date10()) >= 0)
				notTakeOffNumber ++;
		return notTakeOffNumber;
	}

	private void setFlightTicketNoValue(FlightTicket flightTicket, NoValueCause noValueCause) {
		flightTicket.setIsValuable(Boolean.FALSE);
		flightTicket.setNoValueCause(noValueCause.name());
	}
	
}
