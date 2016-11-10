package com.cana.flight.finance.web.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.util.DateParseException;
import org.junit.Test;

import com.cana.credit.api.ICreditApi;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;

public class AutdioResultTest {
	@Resource
	private ICreditApi flightFinanceApi;
	@Test
	public void f() throws Exception{
//		CustomerApplyDTO customerApplyDTO=getJsonResult();
////		flightFinanceApi.saveTravelzenAuditResult(customerApplyDTO);
//		System.out.println(new Gson().toJson(getJsonResult()));
//		System.out.println(formateDate());
		System.out.println(getImageBase64());
	}
	
	public CustomerApplyRequestDTO getJsonResult() throws DateParseException, ParseException{
		CustomerApplyRequestDTO preAuditResultDTO=new CustomerApplyRequestDTO();
		preAuditResultDTO.setCustomerId("526f488745ce9ad7b9caaa35");
		preAuditResultDTO.setApplyTime("2015-01-12 10:10:10");
		preAuditResultDTO.setCompanyName("上海不夜城");
		preAuditResultDTO.setRealControlPerson("王强");
		preAuditResultDTO.setApplyCreditLimit(11200000L);
		preAuditResultDTO.setApplyType("REPAYMENT");
//		preAuditResultDTO.setCompanyNature("SOE");
//		preAuditResultDTO.setIsListing(true);
//		preAuditResultDTO.setAnnualSales(1223000L);
//		preAuditResultDTO.setMajorCooperativeProducts("机票");
//		preAuditResultDTO.setExistPeakSlackPeriod(false);
//		preAuditResultDTO.setPeakPeriod("3-6");
//		preAuditResultDTO.setSlackPeriod("7-9");
//		preAuditResultDTO.setBusinessModel("SELF");
//		preAuditResultDTO.setSubordinateCompanyName("真旅");
//		preAuditResultDTO.setQualifications("DOMESTIC");
		preAuditResultDTO.setOrganizationCode(getImageBase64());
		preAuditResultDTO.setBusinessLicenceCode(getImageBase64());
		preAuditResultDTO.setTaxRegistrationCertificateCode(getImageBase64());
//		preAuditResultDTO.setSameIndustryPlaceOnLocal("TOP3");
//		preAuditResultDTO.setSameTypePlace("MAJOR");
//		preAuditResultDTO.setCooperationDegree(1);
//		preAuditResultDTO.setMaybeInterruptPurchase("SURE");
//		preAuditResultDTO.setMaybeIncreasePurchase("SURE");
		preAuditResultDTO.setContactName("黎明");
		preAuditResultDTO.setPhoneNumber("13817470969");
		preAuditResultDTO.setEmail("1988339@qq.com");
//		preAuditResultDTO.setProposalCreditLimit(4343243400L);
//		preAuditResultDTO.setProposalAuditResult(true);
		preAuditResultDTO.setDownstreamCustomerType("INDIVIDUAL");
		return preAuditResultDTO;
	}
	
	private String getImageBase64(){
		String base64="";
		byte[] imageByte=getByteImage("/Users/meng/Downloads/1.png");
	    base64=Base64.encodeBase64String(imageByte);
	    return base64;
	  }
	
	private String  formateDate() throws DateParseException, ParseException{
		return String.valueOf(System.currentTimeMillis());
	}
	
	public byte[] getByteImage(String path){
		byte[] data = null;
	    FileImageInputStream input = null;
	    try {
	      input = new FileImageInputStream(new File(path));
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      byte[] buf = new byte[1024];
	      int numBytesRead = 0;
	      while ((numBytesRead = input.read(buf)) != -1) {
	      output.write(buf, 0, numBytesRead);
	      }
	      data = output.toByteArray();
	      output.close();
	      input.close();
	    }
	    catch (FileNotFoundException ex1) {
	      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
	    return data;
	}
		
		

}
