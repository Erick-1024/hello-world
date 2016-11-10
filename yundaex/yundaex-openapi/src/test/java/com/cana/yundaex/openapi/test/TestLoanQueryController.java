package com.cana.yundaex.openapi.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.yundaex.common.dto.YundaexLoanInfoListRequest;
import com.cana.yundaex.common.dto.YundaexLoanInfoListResponse;
import com.google.gson.Gson;
import com.travelzen.framework.net.http.HttpTookit;
import com.travelzen.framework.security.RSAUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class TestLoanQueryController {
	
	private static final Gson gson = new Gson();
	
	@Test
	public void testSaveAdditionIfo(){
		
		YundaexLoanInfoListRequest yundaexLoanInfoListRequest = new YundaexLoanInfoListRequest();
		yundaexLoanInfoListRequest.setStationNo("321300");
		yundaexLoanInfoListRequest.setStartBeginDate("2016-07-14");
		yundaexLoanInfoListRequest.setEndBeginDate("2016-07-14");
//		yundaexLoanInfoListRequest.setStartExpireDate("2017-01-01");
//		yundaexLoanInfoListRequest.setEndExpireDate("2017-01-15");

		
		StringBuffer singStr = new StringBuffer();
		singStr.append(yundaexLoanInfoListRequest.getStationNo())
				.append(StringUtils.isBlank(yundaexLoanInfoListRequest.getStartBeginDate())? "":yundaexLoanInfoListRequest.getStartBeginDate())
				.append(StringUtils.isBlank(yundaexLoanInfoListRequest.getEndBeginDate())? "":yundaexLoanInfoListRequest.getEndBeginDate())
				.append(StringUtils.isBlank(yundaexLoanInfoListRequest.getStartExpireDate())? "":yundaexLoanInfoListRequest.getStartExpireDate())
				.append(StringUtils.isBlank(yundaexLoanInfoListRequest.getEndExpireDate())? "":yundaexLoanInfoListRequest.getEndExpireDate());
		String sign = null;
		try {
			sign = new String(RSAUtil.sign(singStr.toString().getBytes(), "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIrOv2wQsA6AXCi/3xMS8UkPj81g+r+S4CaB/DsSwQ34VVoxNWgwr0F8HPY9kpfviMlQkdZbx+ClAOnF9yRxxW+Lw5VYRnGE67Vxh+VldS49CoFv652ovCuM1wTtHOz23kSF59rNJDjeyAMilJ3Pq8/SiphCIgk0DILSD+mzf0idAgMBAAECgYBMor8O09vP9dBr8xsfNcsfckcLtipakKXOvN9cYgtSQjQlepuo7ZAlEHgQ/4eq+OXFeAU8mymsfo0VhGXhqiq9i+0n3S8Y+h4rfmh6hvND/JTyh7+DEazJy5LnEcPmeBR9kjHKSfAjRXg8hkenkvE/P3jLNf5BStflf5y8cy4Q5QJBANkFV4xOUf9jcDI9XAKNXuE+y14MwbPwKeNuWGksSQVD8QuZ8BYNGAXxslfMWQKtE09yrchI5lMYeL44F1l6NYMCQQCjvSVvQ4zUwj1rBAiGEDlCrCCA3XlI8U927gr8LqxyvuoEkkYDyfq8aafR4J2Ic8sWwFz9HC2lem+4991VNk9fAkEAovwrEvljBZ1ljqWca2JGxn1FeH22H/AXVXHyvhH/SRAMgLz8nWL6DsTFPsD+fE8FeJ5Uu11cdT2kuJ8hkhaBBwJANtzemSxSGMvIln3weTMgbIWOEn+i7tzkGl5iUeM1pvDvKn70dLqNh+oC8CDJx/m8d7AWuDxj0wl2O8zZXX7oEwJBAMO37in/MaURTQew9Pyh/DdwGHuxyyCpbD5HLdlCYU/CgBDRujf+mUevWSPhXE1I7DhBY0/9BH5JBU2cHc6kIPU="));
		} catch (Exception e) {
			e.printStackTrace();
		}
		yundaexLoanInfoListRequest.setSign(sign);
		System.err.println(gson.toJson(yundaexLoanInfoListRequest));
		YundaexLoanInfoListResponse response = sendLoanInfoResult("http://127.0.0.1:8080/yundaex-openapi/cana/loanInfoQuery", yundaexLoanInfoListRequest);
		System.out.println(new Gson().toJson(response));
	}
	
	public YundaexLoanInfoListResponse sendLoanInfoResult(String notifyUrl, YundaexLoanInfoListRequest yundaexLoanInfoListRequests) {
		return postJsonAndLog(notifyUrl, yundaexLoanInfoListRequests, "异步通知放款状态，返回结果：{}", YundaexLoanInfoListResponse.class);
	}
	
	private <T> T postJsonAndLog(String url, Object body, String log, Class<T> classOfT) {
		String returnStr = HttpTookit.doPostJson(url, body);
		return gson.fromJson(returnStr, classOfT);
	}
}
