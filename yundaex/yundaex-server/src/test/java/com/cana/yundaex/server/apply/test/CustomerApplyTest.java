package com.cana.yundaex.server.apply.test;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.member.api.IUserApi;
import com.cana.vbam.common.yundaex.dto.apply.YundaexCustomerAuditResultDTO;
import com.cana.yundaex.api.IYundaexAuditApi;
import com.cana.yundaex.api.IYundaexCreditApi;
import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyAddRequestDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyRequestDTO;
import com.cana.yundaex.common.dto.limit.YdLimitAuditResultRequest;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitResponse;
import com.cana.yundaex.common.dto.test.YundaexCustomerAapplyTestDto;
import com.cana.yundaex.common.enums.StationInfoType;
import com.cana.yundaex.common.enums.YundaexAccountOwner;
import com.cana.yundaex.common.enums.YundaexApplyType;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexJudge;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.common.util.YundaexFromJson;
import com.cana.yundaex.dao.mapper.gen.CommonAreaCodeMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCompositeCostMapper;
import com.cana.yundaex.dao.po.CommonAreaCode;
import com.cana.yundaex.dao.po.CommonAreaCodeExample;
import com.cana.yundaex.dao.po.YundaexCompositeCost;
import com.cana.yundaex.dao.po.YundaexCompositeCostExample;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.utils.IDGenerator;
import com.cana.yundaex.service.IYundaexAuditService;
import com.cana.yundaex.service.IYundaexAutomaticRulesService;
import com.cana.yundaex.service.IYundaexCreditService;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.net.http.HttpTookit;
import com.travelzen.framework.security.RSAUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class CustomerApplyTest {
	
	@Resource
	private IYundaexCreditApi ydCreditApi;
	
	@Resource
	private IYundaexAuditApi yundaexAuditApi;
	
	@Resource
	private IYundaexAutomaticRulesService automaticRulesService;
	
	@Resource
	private IYundaexCreditService ydCreditService;
	
	@Resource
	private IYundaexAuditService yundaexAuditService;
	
	@Resource
	private YundaexCompositeCostMapper compositeCostMapper;
	
	@Resource
	private CommonAreaCodeMapper commonAreaCodeMapper;
	
	@Resource
	private IUserApi userApi;
	
	private static Gson gson = new Gson();
	
	
	
	@Test
	public void test1(){
		List<YundaexCustomerApply> list = new ArrayList<>();
		YundaexCustomerApply yundaexCustomerApply = new YundaexCustomerApply();
		yundaexCustomerApply.setId(IDGenerator.generateCustomerApplyId());
		yundaexCustomerApply.setApplyDate(new Date());
		yundaexCustomerApply.setStationNo("11111111247200");
		yundaexCustomerApply.setStationName("安徽宇公司");
		yundaexCustomerApply.setStationMgr("宇韵达速递有限公司");
		yundaexCustomerApply.setProvince("安徽省");
		yundaexCustomerApply.setCity("池州市");
		yundaexCustomerApply.setAddress("池州市东至县尧渡镇环城西路108号(津利华大酒店)");
		yundaexCustomerApply.setBusiLimit(2l);
		yundaexCustomerApply.setBailBalance(1042l);
		yundaexCustomerApply.setYundaexJudge(YundaexJudge.getEnum("差").name());
		yundaexCustomerApply.setCustName("于晓宇");
		yundaexCustomerApply.setPayAccount("6215993790000318769");
		yundaexCustomerApply.setPayAccountAddress("中国邮政储蓄银行");
		yundaexCustomerApply.setCustIdno("342921198604130314");
		//数据库 非空
		yundaexCustomerApply.setCustPhone("13912992334");
		yundaexCustomerApply.setApplyCreditLimit(10000l);
		yundaexCustomerApply.setLoanLimit("6个月");
		
		//站点信息默认 Y
		yundaexCustomerApply.setWhetherStationInfo(StationInfoType.Y.name());
		yundaexCustomerApply.setReasonW("拉取数据成功");
		yundaexCustomerApply.setNotifyFlag(false);
		yundaexCustomerApply.setApplyType(YundaexApplyType.OFFLINE_APPLY.name());
		// wait 将开始执行系统审核
//		yundaexCustomerApply.setAccessAutomaticState(YundaexAuditState.WAIT.name());
		list.add(yundaexCustomerApply);
		yundaexAuditService.insertYundaexCustomerApply(yundaexCustomerApply);
	}
	
	
	@Test
	public void test(){
		YundaexCompositeCostExample costExample = new YundaexCompositeCostExample();
		CommonAreaCodeExample codeExample = new CommonAreaCodeExample();
	
        List<YundaexCompositeCost> costs = compositeCostMapper.selectByExample(costExample);
        StringBuffer stationCity = new StringBuffer();
        for(YundaexCompositeCost cost : costs){
        	 stationCity =stationCity.append(cost.getStationCity());
        }
        String[] split = stationCity.toString().split("、"); 
        for(int i =0;i<split.length;i++){
        	codeExample.createCriteria().andCityEqualTo(split[i]); 
        	List<CommonAreaCode> areaCodes = commonAreaCodeMapper.selectByExample(codeExample); 
        	if(areaCodes.size()==0||areaCodes == null){
        		System.out.println(split[i]);
        	}
        }
        System.out.println(split.length);
	}
	
	
	/**
	 * 测试韵达客户申请资料接口
	 */
	@Test
	public void testCustomerApply(){
		YdCustomerApplyRequestDTO jsonAudit = new YdCustomerApplyRequestDTO();
		jsonAudit.setStationNo(String.valueOf(Math.random()).substring(2, 17));
		jsonAudit.setStationName("测试0015");
		jsonAudit.setStationMgr("宇");
		jsonAudit.setCustName("宇１");
		jsonAudit.setCustIdno("340322199203217415");
		jsonAudit.setTelephone("13912992334");
		jsonAudit.setProvince("上海");
		jsonAudit.setCity("上海市");
		jsonAudit.setAddress("淮海中路222号");
		jsonAudit.setBusiLimit(3L);
		jsonAudit.setRegioncode("123456");
		jsonAudit.setApplyCreditLimit(10000000L);
		jsonAudit.setLoanLimit("10");
		jsonAudit.setAddCredit("增信１");
		jsonAudit.setRepaymentType("ORDER");
		jsonAudit.setExplain("正式测试08");
		System.out.println(jsonAudit.toString());
		try {
			ydCreditApi.saveYundaexAuditResult(jsonAudit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试补充资料提交数据
	 */
	@Test
	public void testSaveAdditionIfo(){
		YdCustomerApplyAddRequestDTO ydCustomerApplyAddRequestDTO = new YdCustomerApplyAddRequestDTO();
		ydCustomerApplyAddRequestDTO.setStationNo("368586968903504");
		ydCustomerApplyAddRequestDTO.setCustEmail("yuxiaoyu@Canacrop.net"); 
		ydCustomerApplyAddRequestDTO.setOrganizationNo("11111111");
		ydCustomerApplyAddRequestDTO.setOrganizationMediaId(upfile("11111111"));
		ydCustomerApplyAddRequestDTO.setBusinessLicenceNo("22222"); 
		ydCustomerApplyAddRequestDTO.setBusinessLicenceMediaId(upfile("22222")); 
		ydCustomerApplyAddRequestDTO.setTaxRegistrationCertificateNo("33333"); 
		ydCustomerApplyAddRequestDTO.setTaxRegistrationCertificateMediaId(upfile("22222")); 
		ydCustomerApplyAddRequestDTO.setPayAccount("1231123112312123");
		ydCustomerApplyAddRequestDTO.setPayAccountName("小宇"); 
		ydCustomerApplyAddRequestDTO.setLianHangNo("102100000021");
		ydCustomerApplyAddRequestDTO.setBailBalance(new BigDecimal(10000));
		ydCustomerApplyAddRequestDTO.setController("小顾"); 
		ydCustomerApplyAddRequestDTO.setControllerEmail("guguanggong@Canacrop.net"); 
		ydCustomerApplyAddRequestDTO.setControllerPhone("13955689874"); 
		ydCustomerApplyAddRequestDTO.setControllerIsLegal("0"); 
		ydCustomerApplyAddRequestDTO.setLegalPerson("舟");
		ydCustomerApplyAddRequestDTO.setLegalEmail("renjiangzhou@Canacrop.net"); 
		ydCustomerApplyAddRequestDTO.setLegalPhone("13945782456"); 
		ydCustomerApplyAddRequestDTO.setAccountOwner(YundaexAccountOwner.OTHER.desc()); 
		ydCustomerApplyAddRequestDTO.setAccountOwnerName("志文"); 
		ydCustomerApplyAddRequestDTO.setAccountOwnerEmail("huzhiwen@Canacrop.net"); 
		ydCustomerApplyAddRequestDTO.setAccountOwnerPhone("13946598741"); 
		ydCustomerApplyAddRequestDTO.setQualifiedInspectionRecord("合格"); 
		ydCustomerApplyAddRequestDTO.setRanchiseContractDeadline("2017-06-18 09:27:53");
		ydCustomerApplyAddRequestDTO.setShortLoan(new BigDecimal(20000)); 
		ydCustomerApplyAddRequestDTO.setLegalIdno("340322199203217415"); 
		ydCustomerApplyAddRequestDTO.setLegalIdnoFrontMediaId(upfile("身份证正面")); 
		ydCustomerApplyAddRequestDTO.setLegalIdnoBackMediaId(upfile("身份证背面")); 
		ydCustomerApplyAddRequestDTO.setAdditionInformationMediaId(upfile("申请补充资料")); 
		ydCustomerApplyAddRequestDTO.setWhetherTbOrder("1"); 
		ydCustomerApplyAddRequestDTO.setTbOrderRatio((70.00/100)+""); 
		ydCustomerApplyAddRequestDTO.setOtherExplain("韵达客户额度申请补充资料填写完毕");
		yundaexAuditApi.saveAdditionInfo(ydCustomerApplyAddRequestDTO);
		
	}
	
	/**
	 * 测试系统审核
	 */
	@Test
	public void testAutomaticRules(){
		automaticRulesService.checkApplysByAutomaticRules();
	}
	
	/**
	 * 测试系统评级
	 */
	@Test
	public void testAutomaticGrade(){
		automaticRulesService.calculateCustomerGrade();
	}
	
	/**
	 * 测试人工审核
	 */
	@Test
	public void testManualAudit(){
		YundaexCustomerAuditResultDTO resultDTO = new YundaexCustomerAuditResultDTO();
		resultDTO.setCustomerApplyId("160618092753701");
		resultDTO.setAuditorId("cana_user"); 
		resultDTO.setManualAuditRemarks("资料完整无误");
		resultDTO.setCheckBusinessLicenceCode(true); 
		resultDTO.setCheckOrganizationCode(true); 
		resultDTO.setCheckTaxRegistrationCertificateCode(true); 
		resultDTO.setCheckLegalIdno(true); 
		resultDTO.setStationAddress("TOWN"); 
		resultDTO.setCheckOther(true);
		
		yundaexAuditApi.auditYundaexCustomer(resultDTO);
	}
	
	/**
	 * 
	 */
	@Test
	public void testAuditResult(){
		YdLimitAuditResultRequest a = new YdLimitAuditResultRequest();
		a.setApplyResult("ACCESS");
		a.setStationNo("12312313");
		a.setTotalLimit(5000000l);
		YundaexBaseResponse b = postJsonAndLog("http://127.0.0.1:8080/yundaex-openapi/cana/test/auditResultPush?", a);
		String retCode = b.getRetCode();
		System.out.println(retCode);
	}
	
	
	/**
	 * 测试额度申请结果接口
	 * @param url
	 * @param body
	 * @return
	 */
	
	public static void main(String[] args) {
		YdLimitAuditResultRequest ydLimitRequest = new YdLimitAuditResultRequest();
		ydLimitRequest.setStationNo("510730");
		//ydLimitRequest.setApplyResult("NOTACCESS");
		//ydLimitRequest.setTotalLimit(100000l);
		//ydLimitRequest.setCanaUrl("");
		
		String stationNo = ydLimitRequest.getStationNo(); 
		try {
			String sign = new String(RSAUtil.sign(stationNo.getBytes(),"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ/bBMc8xt0rj5D4G3fjmfvS64x6abBV2svFVywNrUs84s4yEMYMLztlVbjlqfuRrk5kKJUxiQk/tcSCFexDDJg6eBKn/yhoaN5KPTQYWK4gvFC8GDvyiTYbIrUDBCUBgTlUw03KuOYfGig/FU64yo9fwncCptHpSGLGKG0PG6yRAgMBAAECgYBbWCLV2qPkNAUYmL3RdyxBWtggcL699YFOGVjjonkY2hTcMH2nPcUrko82VX4Zc7h/sPoDrseYkmBChvcSB4pE8nD7WYXyJAcn3awI7KgDLjGFXLECjAFxoWpUV/MeLl0eOHF0Aas/QKLCbNA+pSPlr9eTuL+cWv+bJKMGrsOHRQJBAPgygafNnorKMfHaQLm/gsqhPsn34fDre3c3KIcYaNXw8xnbENKiunjqBCN4tIFCeJ8koigvz8egBewLZ7LFjD8CQQCk4YmEzWabIZboqsPlV6lO3lXqEjFTTJMyoMIVPBPFDmaUtOWhyaR2QtLFB5iE2zci2rctHcHcx1zicDuiAdMvAkEA7HIhNp0aZpoNTpxfgRVDIorOaFYv9vY/Hul7QIguh3IOBhm+QwwnnT1DSA+b1nBevS2HFcXGqiR2v9J1rceovQJALWiT2Ag9huHUTtWgx8vBrB/dZ26FORywuVO+v9xg0Xdq1u+PAmSnL46tv2edIJHC+I1lvz4fwu8A7LfeJHr+LwJATG/AwsWYKu2yDx38+XNxcwGF32JpTnzzO2CMW2ofqvGu85PuT056mcniGb+2n9G1hN8Oh9YJzw9F2UzYI8JVUw=="));
//			String sign = new String(vbamCommonService.sign(stationNo.getBytes(), Institution.yd.name(), false));
			System.out.println("sign:"+sign);
			ydLimitRequest.setSign(sign);
		} catch (Exception e) {
			throw WebException.instance(ReturnCode.TP3022);
		}
		YundaexBaseResponse postJsonAndLog = postJsonAndLog(Constants.YUNDAEX_AUDIT_RESULT_URL_PREFIX, ydLimitRequest);
		System.out.println("code:"+postJsonAndLog.getRetCode());
		System.out.println("message:"+postJsonAndLog.getRetMsg());
	}
	
	private static YundaexBaseResponse postJsonAndLog(String url, Object body) {
		String returnStr = HttpTookit.doPostJson(url, body);
		YundaexBaseResponse fromJson = new YundaexBaseResponse();
		try {
			String data = YundaexFromJson.getData(returnStr);
			 fromJson = gson.fromJson(data, YundaexBaseResponse.class);
			 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return fromJson;
	}

	/**
	 * 测试额度生成的定时任务
	 */
	@Test
	public void testYundaexCalculateCreditLimitScheduler(){
		ydCreditService.calculateApplyCreditLimit();
	}
	
	
	/**
	 * 测试已审核额度结果信息查询
	 */
	@Test
	public void testQueryCreditLimit(){
		YdQueryCreditLimitDTO ydQueryCreditLimitDTO = new YdQueryCreditLimitDTO();
		ydQueryCreditLimitDTO.setStationNo("5268ddca45cebb77c5a70110");
		try {
			YdQueryCreditLimitResponse ydQueryCreditLimitResponse = ydCreditApi.queryCreditLimit(ydQueryCreditLimitDTO);
			System.out.println("状态"+ydQueryCreditLimitResponse.getStatus());
			System.out.println("总额度"+ydQueryCreditLimitResponse.getTotalLimit());
			System.out.println("未使用额度"+ydQueryCreditLimitResponse.getUnusedLimit());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String upfile(String code){
		byte[] bytes = code.getBytes(); 
		String media = "";
		try {
			 media = MediaClientUtil.upload(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;
	}
	
	/**
	 * 根据对象的字段，得到该对象对应的值，例：busiLimit --->ydApply.getBusiLimit();
	 * @param args
	 */
	/*public static void main(String[] args) {
		YundaexCustomerAapplyTestDto ydApply = new YundaexCustomerAapplyTestDto();
		ydApply.setBusiLimit(1000l);
		String secondTarget = "busiLimit";
		Map<String, Object> map = getFileds(ydApply,secondTarget);
		System.out.println(map.get(secondTarget));
	}
*/

	private static Map<String, Object> getFileds(YundaexCustomerAapplyTestDto ydApply,String secondTarget) {
		Class clazz = ydApply.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object> ();
		 for (int i = 0; i < fields.length; i++) {
			try {
				if(fields[i].getName().equals(secondTarget)){
					Object resultObject = invokeMethod(ydApply, fields[i].getName());
					System.out.println("name="+fields[i].getName());
					System.out.println("type="+fields[i].getType());
					System.out.println("resultObject="+resultObject);
					map.put(fields[i].getName(), resultObject);
//					map.put(fields[i].getName(), new YundaexCustomerAapplyTestDto(fields[i].getName(), resultObject, fields[i].getType()));
					break;
				}
				
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return map;
	}


	public static Object invokeMethod(Object owner, String fieldname) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class ownerClass = owner.getClass();

		Method method = null;
//		method = ownerClass.getMethod(GetterUtil.toGetter(fieldname));
		method = ownerClass.getMethod(new StringBuffer("get").append(fieldname.substring(0, 1).toUpperCase()).append(fieldname.substring(1)).toString());

		Object object = null;
		object = method.invoke(owner);

		return object;
	}
}
