

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.vbam.common.enums.Education;
import com.cana.vbam.common.enums.IndustryType;
import com.cana.vbam.common.enums.Marriage;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.vj.dto.ApplyCreditRequest;
import com.cana.vbam.common.vj.dto.Bill;
import com.cana.vbam.common.vj.dto.ConfirmLoanRequest;
import com.cana.vbam.common.vj.dto.MatchLenderRequest;
import com.cana.vbam.common.vj.dto.QueryCreditRequest;
import com.cana.vbam.common.vj.enums.DepositState;
import com.cana.vj.api.IVJApi;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.RandomUtil;

@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class TestVJ extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private IVJApi vjApi;
	
	@Test
	public void applyCredit() {
		try {
			ApplyCreditRequest request = new ApplyCreditRequest();
			request.setCustomerName("任水");
			request.setIdentityCardNo("370983198311065830");
			request.setArea("上海");
			request.setMarriage(Marriage.unmarried);
			request.setHighestEducation(Education.E_070);
			request.setHousingFundAccountNo("housingFundAccountNo");
			request.setSocialSecurityAccountNo("socialSecurityAccountNo");
			List<Bill> housingFundBillHistory = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				Bill bill = new Bill();
				bill.setMonth(DateTimeUtil.month7(DateTimeUtil.addMonth(new Date(), -i)));
				bill.setAmount(200000);
				housingFundBillHistory.add(bill);
			}
			request.setHousingFundBillHistory(housingFundBillHistory);

			List<Bill> socialSecurityBillHistory = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				Bill bill = new Bill();
				bill.setMonth(DateTimeUtil.month7(DateTimeUtil.addMonth(new Date(), -i)));
				bill.setAmount(400000);
				socialSecurityBillHistory.add(bill);
			}
			request.setSocialSecurityBillHistory(socialSecurityBillHistory);
			request.setDepositBase(1600000);
			request.setDepositState(DepositState.normal);
			request.setCompanyName("CANA");
			request.setIndustryType(IndustryType.I_020);
			List<Bill> salaryBillHistory = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				Bill bill = new Bill();
				bill.setMonth(DateTimeUtil.month7(DateTimeUtil.addMonth(new Date(), -i)));
				bill.setAmount(2000100);
				salaryBillHistory.add(bill);
			}
			request.setSalaryBillHistory(salaryBillHistory);
			List<Bill> bonusBillHistory = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				Bill bill = new Bill();
				bill.setMonth(DateTimeUtil.month7(DateTimeUtil.addMonth(new Date(), -i)));
				bill.setAmount(0);
				bonusBillHistory.add(bill);
			}
			request.setBonusBillHistory(bonusBillHistory);
			request.setVjScore(100f);
			request.setMobileNo("18221302603");
			System.out.println(new Gson().toJson(vjApi.applyCredit(request)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryCredit(){
		QueryCreditRequest request = new QueryCreditRequest();
		request.setCustomerName("任水");
		request.setIdentityCardNo("370983198311065830");
		System.out.println(new Gson().toJson(vjApi.queryCredit(request)));
	}
	
	@Test
	public void matchLender(){
		MatchLenderRequest request = new MatchLenderRequest();
		request.setCanaCustomerId("201607110267");
		request.setFinanceAmount(1000);
		request.setInterestRateUnit(InterestRateUnit.DAY);
		request.setInterestRate("0.00050");
		request.setLoanPeriodUnit(DateUnit.DAY);
		request.setLoanPeriod(20);
		request.setRepaymentType(RepaymentType.ORDER);
		System.out.println(new Gson().toJson(vjApi.matchLender(request)));
	}
	
	@Test
	public void confirmLoan() throws Exception {
		ConfirmLoanRequest request = createConfirmLoanRequest();
		System.out.println(new Gson().toJson(vjApi.confirmLoan(request)));
	}
	
	public ConfirmLoanRequest createConfirmLoanRequest() throws IOException {
		ConfirmLoanRequest confirmLoanRequest = new ConfirmLoanRequest();
		confirmLoanRequest.setVjTranSeq(RandomUtil.getRandomStr(32));
		confirmLoanRequest.setCanaCustomerId("201607250428");
		confirmLoanRequest.setFinanceAmount(30000);
		confirmLoanRequest.setCanaLenderId("cana-baoli");
		confirmLoanRequest.setInterestRateUnit(InterestRateUnit.DAY);
		confirmLoanRequest.setInterestRate("0.00050");
		confirmLoanRequest.setLoanPeriodUnit(DateUnit.DAY);
		confirmLoanRequest.setLoanPeriod(20);
		confirmLoanRequest.setRepaymentType(RepaymentType.ORDER);
		confirmLoanRequest.setBankNo("上海银行张庙街道支行");
		confirmLoanRequest.setIssueBank("65595612415450");
		return confirmLoanRequest;
	}
	
	public byte[] getBytes(String filePath){
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }  
	
}
