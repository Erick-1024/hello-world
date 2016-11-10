

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.crawler.shixincourt.api.IShixinCourtApi;
import com.cana.marketing.api.IInterestRateApi;
import com.cana.vbam.common.crawler.dto.GetShixinCourtRequest;
import com.cana.vbam.common.crawler.enums.CourtExecutionSubject;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountRequest;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class TestDubbo extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private IInterestRateApi interestRateApi;
	
	@Resource
	private IShixinCourtApi shixinCourtApi;

	
	@Test
	public void getInterestRateDiscountInfo() throws Exception{
		InterestRateDiscountRequest request = new InterestRateDiscountRequest();
		request.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		System.out.println(new Gson().toJson(interestRateApi.getInterestRateDiscountInfo(request)));
	}
	
	@Test
	public void getInterestRateAfterDiscount() throws Exception{
		InterestRateDiscountRequest request = new InterestRateDiscountRequest();
		request.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		request.setOriginInterestRateUnit(InterestRateUnit.DAY);
		request.setOriginInterestRate(new BigDecimal("0.0005"));
		System.out.println(new Gson().toJson(interestRateApi.getInterestRateAfterDiscount(request)));
	}
	
	@Test
	public void getShixinCourtIndividual() throws Exception{
		GetShixinCourtRequest request = new GetShixinCourtRequest();
		request.setSubject(CourtExecutionSubject.individual);
		request.setName("陈宗荣");
		request.setCode("362125198312347012");
		request.setCacheDate(DateTimeUtil.addDay(new Date(), -1).toDate());
		System.out.println(new Gson().toJson(shixinCourtApi.getShixinCourt(request)));
	}
	
	@Test
	public void getShixinCourtCompany() throws Exception{
		GetShixinCourtRequest request = new GetShixinCourtRequest();
		request.setSubject(CourtExecutionSubject.company);
		request.setName("河南通顺运输有限公司");
		request.setCode("78621859-6");
		request.setCacheDate(DateTimeUtil.addDay(new Date(), -1).toDate());
		System.out.println(new Gson().toJson(shixinCourtApi.getShixinCourt(request)));
	}
}
