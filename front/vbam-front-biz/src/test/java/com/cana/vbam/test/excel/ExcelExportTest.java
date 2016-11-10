//package com.cana.vbam.test.excel;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//
//import com.cana.vbam.common.report.consts.ReportConsts;
//import com.cana.vbam.common.report.dto.ReportFactorFinanceYearDTO;
//import com.cana.vbam.common.utils.ExcelUtils;
//
//public class ExcelExportTest {
//	
//	@Test
//	public void testExport() {
//		try {
//			FileOutputStream out = new FileOutputStream("/home/dev4/融资管理.xls");
//			List<ReportFactorFinanceYearDTO> dataset = new ArrayList<>();
//			byte[] bytes = ExcelUtils.exportExcel(ReportConsts.repaymentAnnualReportSheetTitle, ReportConsts.repaymentDailyReportHeaders, dataset);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
//}
