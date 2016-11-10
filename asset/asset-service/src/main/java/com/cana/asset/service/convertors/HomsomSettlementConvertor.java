package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;

import com.cana.homsom.dao.po.HomsomSettlementTrack;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackDTO;
import com.cana.vbam.common.homsom.enums.SettlementTrackState;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * 恒顺实体转换
 * @author jiangzhou.Ren
 * @time 2016年10月19日下午1:13:21
 */
@Component
public class HomsomSettlementConvertor {
	
	public static List<HomsomSettlementTrackDTO> convertorHomsomSettlement(List<HomsomSettlementTrack> settlementTracks,int count){
		List<HomsomSettlementTrackDTO> homsomSettlementTrackDTOs = new ArrayList<HomsomSettlementTrackDTO>();
		for (HomsomSettlementTrack settlementTrack : settlementTracks) {
			HomsomSettlementTrackDTO settlementTrackDTO = new HomsomSettlementTrackDTO();
			//主键id
			settlementTrackDTO.setId(settlementTrack.getId());
			//交易对手
			settlementTrackDTO.setCounterpartyName(settlementTrack.getCounterpartyName());
			//票号
			settlementTrackDTO.setTicketNo(settlementTrack.getTicketNo());
			//放款编号
			settlementTrackDTO.setLoanNo(settlementTrack.getLoanNo());
			//放款金额
			settlementTrackDTO.setLoanAmount(MoneyUtil.cent2Yuan(settlementTrack.getLoanAmount()));
			//放款天数
			settlementTrackDTO.setLoanDays(settlementTrack.getLoanDays());
			//利息
			settlementTrackDTO.setInterestAmount(MoneyUtil.cent2Yuan(settlementTrack.getInterestAmount()));
			//核销日期
			settlementTrackDTO.setSettleDate(settlementTrack.getSettleDate());
			//回购日期
			settlementTrackDTO.setBuybackDate(settlementTrack.getBuybackDate());

			//状态
			if (EnumUtils.isValidEnum(SettlementTrackState.class, settlementTrack.getState()))
				settlementTrackDTO.setState(SettlementTrackState.valueOf(settlementTrack.getState()).desc());
			else
				settlementTrackDTO.setState(settlementTrack.getState());
			
			homsomSettlementTrackDTOs.add(settlementTrackDTO);
		}
		return homsomSettlementTrackDTOs;
	}
	
}





