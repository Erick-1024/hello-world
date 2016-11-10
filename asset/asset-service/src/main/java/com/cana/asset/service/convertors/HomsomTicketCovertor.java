package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cana.homsom.dao.po.HomsomTicket;
import com.cana.vbam.common.homsom.dto.TicketDTO;
import com.cana.vbam.common.homsom.dto.TicketExcelDTO;
import com.cana.vbam.common.homsom.enums.TicketState;
import com.travelzen.framework.core.util.MoneyUtil;

public class HomsomTicketCovertor {

	public static TicketDTO convertHomsomTicket2TicketDTO(HomsomTicket homsomTicket) {
		TicketDTO ticketDTO = new TicketDTO();
		BeanUtils.copyProperties(homsomTicket, ticketDTO);
		ticketDTO.setStateDesc(TicketState.valueOf(homsomTicket.getState()).desc());
		return ticketDTO;
	}
	
	public static List<TicketDTO> convertHomsomTicket2TicketDTO(List<HomsomTicket> homsomTickets) {
		List<TicketDTO> ticketDTOs = new ArrayList<>();
		for (HomsomTicket homsomTicket : homsomTickets)
			ticketDTOs.add(convertHomsomTicket2TicketDTO(homsomTicket));
		return ticketDTOs;
	}
	
	public static List<TicketExcelDTO> convertHomsomTicket2TicketExcelDTO(List<HomsomTicket> homsomTickets) {		
		List<TicketExcelDTO> ticketExcelDTOs = new ArrayList<>();
		for (HomsomTicket homsomTicket : homsomTickets) {
			TicketExcelDTO ticketExcelDTO = new TicketExcelDTO();
			BeanUtils.copyProperties(homsomTicket, ticketExcelDTO);
			ticketExcelDTO.setAmount(MoneyUtil.cent2Yuan(homsomTicket.getAmount()));
			ticketExcelDTO.setStateDesc(TicketState.valueOf(homsomTicket.getState()).desc());
			ticketExcelDTOs.add(ticketExcelDTO);
		}
		return ticketExcelDTOs;
	}
	
}
