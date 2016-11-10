package com.cana.asset.server.test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.dao.custom.po.SpecialProgramGrossResult;
import com.cana.asset.dao.mapper.ABSUnderlyingAssetMapper;
import com.cana.asset.service.IUnderlyingAssetImportService;
import com.cana.asset.service.transaction.IUnderlyingAssetCalcTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetTransactionService;
import com.cana.asset.service.transaction.util.LoanAndUnderlyingAssetIdUtils;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.underlyingasset.dto.EditUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EnterAssetPoolRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetResponse;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetAmountSummary;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetExcelDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml", "classpath*:spring/asset-service-*.xml"})
public class AssetUnderlyingAssetTest {

	private Gson gson = new Gson();

	private UserVo userVo;
	private String factorId = "201607050033";

	@Resource
	private IMemberQueryApi memberQueryApi;

	@Resource
	private IUnderlyingAssetTransactionService underlyingAssetTransactionService;
	@Resource
	private IUnderlyingAssetCalcTransactionService underlyingAssetCalcTransactionService;
	@Resource
	private ABSUnderlyingAssetMapper aBSUnderlyingAssetMapper;
	@Resource
	private IUnderlyingAssetImportService underlyingAssetImportService;

	@Before
	public void before() {
		userVo = memberQueryApi.findUserById(factorId);
	}

	@Test
	public void testBind() {
		EnterAssetPoolRequest request = new EnterAssetPoolRequest();
		request.setSpecialProgramId("test002");
		request.setUnderlyingAssetIds(Lists.newArrayList("52b731c18ec2-1", "535cf6cfce35-1"));
		underlyingAssetTransactionService.bindSpecialProgram(userVo, request);
	}

	@Test
	public void testEnter() {
		underlyingAssetTransactionService.enterAssetPool(memberQueryApi.findUserById("201609010583"), Lists.newArrayList("52b731c18ec2-1", "535cf6cfce35-1"));
	}

	@Test
	public void testCreate() throws Exception {
		QueryFactorLoanForUnderlyingAssetRequest request = new QueryFactorLoanForUnderlyingAssetRequest();
		request.setPage(1);
		request.setPageSize(5);
		ListResult<QueryFactorLoanForUnderlyingAssetResponse> response = underlyingAssetTransactionService.queryFactorLoanForUnderlyingAsset(userVo, request);
		System.out.println(response.getTotalNum());
		System.out.println(new Gson().toJson(response.getData()));

		if (response.getTotalNum() > 1)
			underlyingAssetTransactionService.createUnderlyingAssetByFactorLoan(
					userVo, Lists.newArrayList(response.getData().get(0).getLoanInfoId(), response.getData().get(1).getLoanInfoId()));
	}

	@Test
	public void testQueryLoan() throws Exception {
		QueryFactorLoanForUnderlyingAssetRequest request = new QueryFactorLoanForUnderlyingAssetRequest();
		request.setPage(1);
		request.setPageSize(111);
		ListResult<QueryFactorLoanForUnderlyingAssetResponse> response = underlyingAssetTransactionService.queryFactorLoanForUnderlyingAsset(userVo, request);
		System.out.println(response.getTotalNum());
		System.out.println(new Gson().toJson(response.getData()));

	}

	@Test
	public void testDelte() throws Exception {
		underlyingAssetTransactionService.deleteUnderlyingAsset(userVo, "4e7a2968c176-1");
	}

	@Test
	public void testGross() {
		List<SpecialProgramGrossResult> result = aBSUnderlyingAssetMapper.queryGrossBySpecialProgramIds(Sets.newHashSet("123", "456"));
		System.out.println(new Gson().toJson(result));
		Map<String, Long> resultMap = underlyingAssetCalcTransactionService.queryGrossBySpecialProgramIds(Sets.newHashSet("123", "32323"));
		System.out.println(resultMap);
		System.out.println(underlyingAssetCalcTransactionService.queryGrossBySpecialProgramId("123"));
		System.out.println(underlyingAssetCalcTransactionService.queryGrossBySpecialProgramId("7456"));
	}


	@Test
	public void testFinanceAmount() {
		Map<String, UnderlyingAssetAmountSummary> summarys = underlyingAssetCalcTransactionService.queryFinanceAmountByUnderlyingAssetIds(Sets.newHashSet("3836eeeb6c55-1", "4e7a2968c176-1"));
		for (UnderlyingAssetAmountSummary summary : summarys.values())
			System.out.println(new Gson().toJson(summary));
	}

	@Test
	public void testImport() {
		EditUnderlyingAssetRequest request = prepareRequest();
		underlyingAssetTransactionService.checkImportUnderlyingAssetRequest(userVo, request);
		underlyingAssetTransactionService.importUnderlyingAsset(userVo, Lists.newArrayList(request));
		request.setFinanceAmount("600");
		underlyingAssetTransactionService.updateUnderlyingAsset(userVo, request);
	}

	public EditUnderlyingAssetRequest prepareRequest() {
		EditUnderlyingAssetRequest request = new EditUnderlyingAssetRequest();
		request.setContractNo(DateTimeUtil.datetime14());
		request.setUnderlyingAssetId(LoanAndUnderlyingAssetIdUtils.generateUnderlyingAssetId(request.getContractNo()));
		request.setCustomerName("借款人名称1");
		request.setCustomerCity("上海");
		request.setCustomerIndustry("旅游");
		request.setCustomerEconomicCategory("私有");
		request.setCounterparty("交易对手1");
		request.setCounterpartyEconomicCategory("国有");
		request.setCounterpartyCity("beijing");
		request.setCounterpartyIndustry("人力资源");
		request.setCreditLimit("1000");
		request.setCreditBalance("500");
		request.setCounterpartyLimit("0");
		request.setCounterpartyBalance("0");
		request.setInvoiceAmount("200");
		request.setInvoiceBalance("100");
		request.setFinanceAmount("500");
		request.setFinanceBalance("300");
		request.setLoanDate("2016-08-09");
		request.setDueDate("2016-10-09");
		request.setRepaymentMethod("等额本息");
		request.setInterestRateUnit(InterestRateUnit.YEAR.name());
		request.setInterestRate("12%");
		request.setLoanPeriod("90天");
		return request;
	}

	@Test
	public void testImport2() throws IllegalAccessException, InvocationTargetException {
		EditUnderlyingAssetRequest request = prepareRequest();
		UnderlyingAssetExcelDTO dto = new UnderlyingAssetExcelDTO();
		BeanUtils.copyProperties(dto, request);
		String rediskey = underlyingAssetImportService.generateUnderlyingAssetRedisKey();
		underlyingAssetImportService.importUnderlyingAssetExcel2Redis(Lists.newArrayList(dto), factorId, rediskey);
		ListResult<UnderlyingAssetExcelDTO> passed = underlyingAssetImportService.getUnderlyingAssetFromRedis(rediskey, factorId, true, 1, 10);
		ListResult<UnderlyingAssetExcelDTO> notPassed = underlyingAssetImportService.getUnderlyingAssetFromRedis(rediskey, factorId, false, 1, 10);

		System.out.println("通过的\n" + gson.toJson(passed.getData()));
		System.out.println("未通过的\n" + gson.toJson(notPassed.getData()));

		if (CollectionUtils.isNotEmpty(passed.getData()))
			underlyingAssetImportService.importUnderlyingAssetExcel2DB(factorId, rediskey);
	}
	
	@Test
	public void testBatchImport() {
		for(int i=1000000 ; i<2000000; i++){
			EditUnderlyingAssetRequest request = new EditUnderlyingAssetRequest();
			request.setContractNo("huzhiwe"+i);
			request.setUnderlyingAssetId(LoanAndUnderlyingAssetIdUtils.generateUnderlyingAssetId(request.getContractNo()));
			request.setCustomerName("胡志文"+i);
			request.setCustomerCity("上海");
			request.setCustomerIndustry("旅游");
			request.setCustomerEconomicCategory("私有");
			request.setCounterparty("交易对手1");
			request.setCounterpartyEconomicCategory("国有");
			request.setCounterpartyCity("beijing");
			request.setCounterpartyIndustry("人力资源");
			request.setCreditLimit("1000");
			request.setCreditBalance("500");
			request.setCounterpartyLimit("0");
			request.setCounterpartyBalance("0");
			request.setInvoiceAmount("200");
			request.setInvoiceBalance("100");
			request.setFinanceAmount("500");
			request.setFinanceBalance("300");
			request.setLoanDate("2016-08-09");
			request.setDueDate("2016-10-09");
			request.setRepaymentMethod("等额本息");
			request.setInterestRateUnit(InterestRateUnit.YEAR.name());
			request.setInterestRate("12%");
			request.setLoanPeriod("90天");
			underlyingAssetTransactionService.checkImportUnderlyingAssetRequest(userVo, request);
			underlyingAssetTransactionService.importUnderlyingAsset(userVo, Lists.newArrayList(request));
//			request.setFinanceAmount("600");
			EnterAssetPoolRequest poolRequest = new EnterAssetPoolRequest();
			poolRequest.setSpecialProgramId("160906150518914");
			poolRequest.setUnderlyingAssetIds(Lists.newArrayList(request.getUnderlyingAssetId()));
			underlyingAssetTransactionService.bindSpecialProgram(userVo, poolRequest);
		}
	}

}
