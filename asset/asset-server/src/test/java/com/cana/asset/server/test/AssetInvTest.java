package com.cana.asset.server.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.dao.mapper.CreditCustomMapper;
import com.cana.asset.service.IAssetInvoiceService;
import com.cana.asset.service.transaction.IAssetInvoiceTransactionService;
import com.cana.vbam.common.asset.dto.CustomerCreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditQueryCriteria;
import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.dto.InvoiceFilter;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.enums.CreditCurrencyType;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.google.gson.Gson;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.redis.client.SpringRedisClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class AssetInvTest {

	@Resource
	private IAssetInvoiceService assetInvoiceService;

	@Resource
	private CreditCustomMapper creditCustomerMapper;
	
	@Resource
	private IAssetInvoiceTransactionService assetInvoiceTransactionService;

	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	private Gson gson = new Gson();

	/**
	 * 应收账款汇总列表LIST
	 */
	@Test
	public void testList() {
		InvoiceQueryDTO queryDTO = new InvoiceQueryDTO();

		queryDTO.setCustomerName("滁州韵达速递（定远）责任有限公司"); // 客户名称
		queryDTO.setBusinessContractNo("TEST-001"); // 业务合同号
		queryDTO.setCurrencyType(CreditCurrencyType.RMB.name()); // 币别
		queryDTO.setBusinessProduct("DH_RECOURSE_FACTORING"); // 业务产品

		UserSessionDTO userSessionDTO = new UserSessionDTO();
		PageList<InvoiceListDTO> list = assetInvoiceService.getInvoiceList(queryDTO, userSessionDTO);
		System.err.println(gson.toJson(list));
	}

	@Test
	public void testMapper() {
		CustomerCreditQueryCriteria crieria = new CustomerCreditQueryCriteria();
		List<CustomerCreditDTO> returnva = creditCustomerMapper.searchCustomerByCondition(crieria);
		for (CustomerCreditDTO customerCreditDTO : returnva) {
			System.out.println(new Gson().toJson(customerCreditDTO));
		}
	}

	/**
	 * 应收账款详细页面
	 */
	@Test
	public void testManageList() {
		InvoiceQueryDTO queryDTO = new InvoiceQueryDTO();
		queryDTO.setId("0001"); // 应收账款主键ID
//		InvoiceListDTO list = assetInvoiceService.getInvoiceManage("0001");
//		System.err.println(gson.toJson(list));
	}

	/**
	 * 修改或是新增应收账款信息
	 */
	@Test
	public void updateInv() {
		UserSessionDTO userSessionDTO = new UserSessionDTO();
		InvoiceListDTO invoiceListDTO = new InvoiceListDTO();
		// 新增
//		userSessionDTO.setId("201607050033");
//		userSessionDTO.setUsername("凯拿商业保理（深圳）有限公司");
//		
//		invoiceListDTO.setCustomerId("160806163330001");
//		invoiceListDTO.setCustomerName("上海软件开发有限公司");
//		invoiceListDTO.setBusinessProduct("DH_RECOURSE_FACTORING");
//		invoiceListDTO.setBusinessContractNo("TEST-002");
//		invoiceListDTO.setCurrency(CreditCurrencyType.RMB.name());
//		
//		List<InvoiceInfoDTO> infoDTOs = new ArrayList<>();
//		for (int i = 0; i < 3; i++) {
//			InvoiceInfoDTO infoDTO = new InvoiceInfoDTO();
//			infoDTO.setCounterpartyId("buy00"+ (i+3));
//			infoDTO.setCounterparty("买方"+ (i+3));
//			infoDTO.setInvoiceNo("00"+ (i+3));
//			infoDTO.setNominvoiceAmt("10000");
//			infoDTO.setInvoiceAmt("10000");
//			infoDTO.setFinancingRatio(new BigDecimal(80));
//			infoDTO.setInvoiceDate("2016-08-09");
//			infoDTO.setDueDate("2017-08-09");
//			infoDTOs.add(infoDTO);
//		}
//		invoiceListDTO.setInvoiceInfoDTOs(infoDTOs);
//		
//		List<ExpenseDTO> expenseDTOs = new ArrayList<>();
//		for (int i = 0; i < 2; i++) {
//			ExpenseDTO expenseDTO = new ExpenseDTO();
//			expenseDTO.setExpenseSubject("管理费00"+ (i+1));
//			expenseDTO.setAmount(2000l);
//			expenseDTOs.add(expenseDTO);
//		}
//		invoiceListDTO.setExpenseDTOs(expenseDTOs);
		
		// 修改
		userSessionDTO.setId("201607050033");
		userSessionDTO.setUsername("凯拿商业保理（深圳）有限公司");
		
		invoiceListDTO.setId("160809142545101");
		invoiceListDTO.setCustomerId("160806163330001");
		invoiceListDTO.setCustomerName("上海软件开发有限公司");
		invoiceListDTO.setBusinessProduct("DH_RECOURSE_FACTORING");
		invoiceListDTO.setBusinessContractNo("TEST-002");
		invoiceListDTO.setCurrency(CreditCurrencyType.RMB.name());
		
		List<InvoiceInfoDTO> infoDTOs = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			InvoiceInfoDTO infoDTO = new InvoiceInfoDTO();
			infoDTO.setCounterpartyId("buy00"+ (i+3));
			infoDTO.setCounterparty("买方"+ (i+3));
			infoDTO.setInvoiceNo("00"+ (i+3));
			infoDTO.setNominvoiceAmt("10000");
			infoDTO.setInvoiceAmt("10000");
			infoDTO.setFinancingRatio(new BigDecimal(80));
			infoDTO.setInvoiceDate("2016-08-09");
			infoDTO.setDueDate("2017-08-09");
			infoDTOs.add(infoDTO);
		}
		invoiceListDTO.setInvoiceInfoDTOs(infoDTOs);
		
		List<ExpenseDTO> expenseDTOs = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			ExpenseDTO expenseDTO = new ExpenseDTO();
			expenseDTO.setExpenseSubject("管理费00"+ (i+1));
			expenseDTO.setAmount(2000l);
			expenseDTOs.add(expenseDTO);
		}
		invoiceListDTO.setExpenseDTOs(expenseDTOs);
		assetInvoiceTransactionService.updateInvManage(invoiceListDTO, userSessionDTO);
	}
	
	@Test
	public void getInvByExample() {
		InvoiceQueryDTO queryDTO = new InvoiceQueryDTO();
		queryDTO.setBusinessContractNo("TEST-002");
		queryDTO.setCounterpartyId("buy003");
		InvoiceListDTO invoiceListDTO = assetInvoiceTransactionService.getInvByExample(queryDTO);
		System.err.println(gson.toJson(invoiceListDTO));
	}
	
	@Test
	public void getRedisInfo(){
		InvoiceFilter invFilter = (InvoiceFilter)redisCache.read("");
		List<InvoiceListDTO> passInvoiceList = invFilter.getPassInvoiceList(); 
	}
}
