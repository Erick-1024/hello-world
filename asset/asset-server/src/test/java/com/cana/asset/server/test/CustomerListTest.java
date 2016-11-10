package com.cana.asset.server.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.dao.po.CustomerMas;
import com.cana.asset.dao.po.CustomerPurchase;
import com.cana.asset.dao.po.CustomerSale;
import com.cana.asset.dao.po.CustomerStk;
import com.cana.asset.service.transaction.IAssetCustomerTransactionService;
import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.CustomerListRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.dto.CustomerMagDTO;
import com.cana.vbam.common.asset.dto.CustomerMasDTO;
import com.cana.vbam.common.asset.dto.CustomerPurchaseDTO;
import com.cana.vbam.common.asset.dto.CustomerRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerSaleDTO;
import com.cana.vbam.common.asset.dto.CustomerStkDTO;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月25日上午10:32:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class CustomerListTest {
	
	
//	private final Logger logger = LoggerFactory.getLogger(getClass());
//	@Resource
//	private IAssetCustomerTransactionService assetCustomerTransactionService;
//	/**
//	 * 测试客户列表信息
//	 */
//	@Test
//	public void getCustomerList(){
//		
//		CustomerListRequestDTO request = new CustomerListRequestDTO();
//		request.setCustomerName("上海有限公司");
//		request.setCustomerType(CustomerTypeEnum.valueOf("FINANCEAPPLICANT"));
//		UserVo userDetail = new UserVo();
//		userDetail.setUserId("");
//		request.setPage(1);
//		request.setPageSize(2);
//		ListResult<CustomerListResponseDTO> lists = assetCustomerTransactionService.getCustomerList(userDetail,request);
//		System.out.println("客户信息列表" + new Gson().toJson(lists) );
//		
//	}
//	/**
//	 * 测试客户详情
//	 */
//	@Test
//	public void getCustomerDetail(){
////		//客户id
//		String customerId ="123456";
//		UserVo userDetail = new UserVo();
//		
//		CustomerDTO customerDetail = assetCustomerTransactionService.getCustomerDetail(userDetail,customerId);
//		System.out.println("客户详情"+ new Gson().toJson(customerDetail));
//		
//	}
//	/**
//	 * 测试新增客户
//	 */
//	@Test
//	public void addCustomer(){
//		UserVo userDetail = new UserVo();
//		CustomerRequestDTO customerRequest = new CustomerRequestDTO();
//		//客户基本信息
//		addCustomer(customerRequest);
//		//高管信息
//		addCustomerMag(customerRequest);
//		//股东信息
//		addCustomerSkt(customerRequest);
//		//采购信息
//		addCustomerPurchase(customerRequest);
//		//销售信息
//		addCustomerSale(customerRequest);
//		//融资信息
//		addCustomerMas(customerRequest);
//		assetCustomerTransactionService.addCustomer(userDetail, customerRequest);
//		
//	}
//	
//	
//	/**
//	 * 测试修改客户信息
//	 */
//	@Test
//	public void updateCustomer(){
//		UserVo userDetail = new UserVo();
//		CustomerRequestDTO customerRequest = new CustomerRequestDTO();
//		//客户基本信息
//		addCustomer(customerRequest);
//		//高管信息
//		addCustomerMag(customerRequest);
//		//股东信息
//		addCustomerSkt(customerRequest);
//		//采购信息
//		addCustomerPurchase(customerRequest);
//		//销售信息
//		addCustomerSale(customerRequest);
//		//融资信息
//		addCustomerMas(customerRequest);
//		assetCustomerTransactionService.updateCustomer(userDetail, customerRequest);
//		
//	}
//	
//	/**
//	 * 融资信息
//	 * @param customerRequest
//	 */
//	private void addCustomerMas(CustomerRequestDTO customerRequest) {
//		ArrayList<CustomerMasDTO> customerMasDTO = new ArrayList<CustomerMasDTO>();
//			CustomerMasDTO masDTO = new CustomerMasDTO();
//			masDTO.setId("");
//			masDTO.setCustomerId("");
//			masDTO.setFinancialInstitutionName("商业保理");
//			masDTO.setAmount(new BigDecimal("10000"));
//			masDTO.setStartDate("2016-12-19");
//			masDTO.setEndDate("2016-09-07");
//			masDTO.setGuaranteeType("无");
//			masDTO.setRemark("已到期还款");
//			customerMasDTO.add(masDTO);
//			customerRequest.setCustomerMass(customerMasDTO);
//	}
//	/**
//	 * 销售信息
//	 * @param customerRequest
//	 */
//	private void addCustomerSale(CustomerRequestDTO customerRequest) {
//		ArrayList<CustomerSaleDTO> customerSaleDTO = new ArrayList<CustomerSaleDTO>();
//			CustomerSaleDTO saleDTO = new CustomerSaleDTO();
//			saleDTO.setCustomerId("");
//			saleDTO.setCustomerId("");
//			saleDTO.setSaleCustomerName("王五");
//			saleDTO.setSaleLastYear(new BigDecimal("500"));
//			saleDTO.setPercent(new BigDecimal("0.05"));
//			saleDTO.setCooperationPeriod(new BigDecimal("600"));
//			saleDTO.setAccountReceivableBalance(new BigDecimal("700"));
//			customerSaleDTO.add(saleDTO);
//			customerRequest.setCustomerSales(customerSaleDTO);
//	}
//	/**
//	 * 采购信息
//	 * @param customerRequest
//	 */
//	private void addCustomerPurchase(CustomerRequestDTO customerRequest) {
//		ArrayList<CustomerPurchaseDTO> customerPurchaseDTO = new ArrayList<CustomerPurchaseDTO>();
//			CustomerPurchaseDTO purchaseDTO = new CustomerPurchaseDTO();
//			purchaseDTO.setId("");
//			purchaseDTO.setCustomerId("");
//			purchaseDTO.setSupplierName("格力公司");
//			purchaseDTO.setPurchaseLastYear(new BigDecimal("1000"));
//			purchaseDTO.setPercent(new BigDecimal("0.05"));
//			purchaseDTO.setSettleType("现款现货");
//			purchaseDTO.setAccountPayableBalance(new BigDecimal("8000"));
//			customerPurchaseDTO.add(purchaseDTO);
//			customerRequest.setCustomerPurchases(customerPurchaseDTO);
//	}
//	/**
//	 * 股东信息
//	 */
//	private void addCustomerSkt(CustomerRequestDTO customerRequest) {
//		ArrayList<CustomerStkDTO> customerStkDTO = new ArrayList<CustomerStkDTO>();
//			CustomerStkDTO stkDTO = new CustomerStkDTO();
//			stkDTO.setId("");
//			stkDTO.setCustomerId("");
//			//stkDTO.setStkType("自然人");
//			stkDTO.setStkName("小明");
//			stkDTO.setStkPayamt(new BigDecimal("0.05"));
//			stkDTO.setStkPcnt("50%");
//			stkDTO.setStkPayamtType("货币出资");
//			customerStkDTO.add(stkDTO);
//			customerRequest.setCustomerStks(customerStkDTO);
//			
//	}
//	/**
//	 * 高管信息
//	 * @param customerRequest
//	 */
//	private void addCustomerMag(CustomerRequestDTO customerRequest) {
//		ArrayList<CustomerMagDTO> customerMags = Lists.newArrayList();
//		CustomerMagDTO magDTO = new CustomerMagDTO();
//		magDTO.setId("");
//		magDTO.setCustomerId("");
//		magDTO.setMagName("小明");
//		magDTO.setMagDutyType("董事长");
//		magDTO.setMagEducation("硕士");
//		magDTO.setExperience("曾经担任过某公司董事长");
//		magDTO.setMagIdentityCardNo("3333333333333333333");
//		magDTO.setMagProfession("设计");
//		magDTO.setMagSex("男");
//		customerMags.add(magDTO);
//		customerRequest.setCustomerMags(customerMags);
//	}
//	/**
//	 * 基本信息
//	 * @param customerRequest
//	 */
//	private void addCustomer(CustomerRequestDTO customerRequest) {
//		customerRequest.setId("160728094346601");
//		customerRequest.setFactorId("");
//		customerRequest.setCustomerName("上海分公司");
//		customerRequest.setCustomerType("FINANCEAPPLICANT");
//		customerRequest.setContactName("王大拿");
//		customerRequest.setMobileNo("18817505167");
//		customerRequest.setMail("wangwu@.163.com");
//		customerRequest.setCompanyAddress("香港广场");
//		customerRequest.setEconomicCategory("STATE");
//		customerRequest.setIndustry("COMMERCE");
//		customerRequest.setCity("上海");
//		customerRequest.setBusinessLicenceCode("510100000213944545");
//		customerRequest.setBusinessLicenceCodeExpiryDate(new Date());
//		customerRequest.setTaxRegistrationCertificateCode("5101000002139445154");
//		customerRequest.setTaxRegistrationCertificateCodeExpiryDate(new Date());
//		customerRequest.setOrganizationCode("510100000213944543728");
//		customerRequest.setRegisteredCapital(new BigDecimal("50000"));
//		customerRequest.setLegalRepresentative("Hacker");
//		customerRequest.setOrganizationCodeExpiryDate(new Date());
//	}
	

}
