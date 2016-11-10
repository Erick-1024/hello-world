package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cana.homsom.dao.po.HomsomDailyLoanReport;
import com.cana.vbam.common.homsom.dto.LoanAuditDTO;
import com.cana.vbam.common.homsom.dto.LoanAuditExcelDTO;
import com.cana.vbam.common.homsom.enums.LoanState;
import com.travelzen.framework.core.util.MoneyUtil;

public class HomsomLoanConvertor {

	public static LoanAuditDTO convertHomsomDailyLoanReport2LoanAuditDTO(HomsomDailyLoanReport homsomDailyLoanReport) {
		LoanAuditDTO loanAuditDTO = new LoanAuditDTO();
		BeanUtils.copyProperties(homsomDailyLoanReport, loanAuditDTO);
		loanAuditDTO.setLoanStateDesc(LoanState.valueOf(homsomDailyLoanReport.getLoanState()).desc());
		return loanAuditDTO;
	}
	
	public static List<LoanAuditDTO> convertHomsomDailyLoanReport2LoanAuditDTO(List<HomsomDailyLoanReport> homsomDailyLoanReports) {
		List<LoanAuditDTO> returnValue = new ArrayList<>();
		for (HomsomDailyLoanReport homsomDailyLoanReport : homsomDailyLoanReports)
			returnValue.add(convertHomsomDailyLoanReport2LoanAuditDTO(homsomDailyLoanReport));
		return returnValue;
	}
	
	public static List<LoanAuditExcelDTO> convertHomsomDailyLoanReport2LoanAuditExcelDTO(List<HomsomDailyLoanReport> homsomDailyLoanReports) {
		List<LoanAuditExcelDTO> returnValue = new ArrayList<>();
		for (HomsomDailyLoanReport homsomDailyLoanReport : homsomDailyLoanReports) {
			LoanAuditExcelDTO loanAuditExcelDTO = new LoanAuditExcelDTO();
			BeanUtils.copyProperties(homsomDailyLoanReport, loanAuditExcelDTO);
			loanAuditExcelDTO.setApplyAmount(MoneyUtil.cent2Yuan(homsomDailyLoanReport.getApplyAmount()));
			loanAuditExcelDTO.setLoanAmount(MoneyUtil.cent2Yuan(homsomDailyLoanReport.getLoanAmount()));
			loanAuditExcelDTO.setLoanStateDesc(LoanState.valueOf(homsomDailyLoanReport.getLoanState()).desc());
			returnValue.add(loanAuditExcelDTO);
		}
		return returnValue;
	}
	
}
