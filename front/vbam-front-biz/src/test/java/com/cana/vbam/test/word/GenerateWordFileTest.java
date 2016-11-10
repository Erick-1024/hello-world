package com.cana.vbam.test.word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.junit.Test;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.front.biz.utils.WordUtil;
import com.travelzen.framework.core.time.DateTimeUtil;

public class GenerateWordFileTest {

	@Test
	public void test() throws IOException {
		String templatePath = "/Users/meng/Downloads/contractTemplate.doc";
		Map<String, String> dataMap = packageWordData();
		byte[] wordContent = WordUtil.getFilledDocTemplateAsBytes(templatePath, dataMap);
		FileOutputStream output = new FileOutputStream(new File("/Users/meng/Downloads/hhh.doc"));
		output.write(wordContent);
	}
	
	private Map<String, String> packageWordData(){
		String supervisionAccountNo = "supervisionAccountNo";
	    String contractId = "contrffffactId";
	    // 用于组装word页面需要的数据
    	String factorId = "factorIDD";
	    Map<String, String> dataMap = new HashMap<String, String>();
	    dataMap.put("${contractId!}",contractId);
	    dataMap.put("${factorCompanyName!}","factorCompanyName");
	    dataMap.put("${factorBusinessLicenceCode!}","factorBusinessLicenceCode");
	    dataMap.put("${finaceCompanyName!}","finaceCompanyName");
	    dataMap.put("${finaceBusinessLicenceCode!}","finaceBusinessLicenceCode");
	    dataMap.put("${assetTransfereeRatio!}", "1:2");
	    dataMap.put("${transferPrice!}", "50");
	    dataMap.put("${expectedProfitRate!}", "123");
	    dataMap.put("${effectiveDate!}", DateTimeUtil.getTodayStr());
	    dataMap.put("${dueDate!}", DateTimeUtil.date10(DateTimeUtil.addYear(new DateTime(), 3)));
	    dataMap.put("${supervisionAccountNo!}",AccountNoUtil.formatBankAccountNo(supervisionAccountNo));
	    return dataMap;
	}
}
