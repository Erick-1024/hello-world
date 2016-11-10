package com.cana.front.common.freemarker.whole;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cana.front.common.util.EnumMapUtil;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.LoanState;
import com.cana.vbam.common.credit.enums.ApplyApplicantType;
import com.cana.vbam.common.credit.enums.CreditLimitStatus;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.credit.enums.CreditTradeStatus;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventSubCategory;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.vj.enums.TranState;
import com.cana.vbam.common.vj.enums.TranType;
import com.travelzen.framework.core.util.TZUtil;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class EnumInfoGetter extends EnumMapUtil implements TemplateMethodModelEx{

	 static{
	        new EnumInfoGetter().init();
	    }
	    @SuppressWarnings("rawtypes")
	    @Override
	    public Object exec(List params) throws TemplateModelException {
	        if (TZUtil.isEmpty(params)) {
	            return null;
	         }
	         if (params.get(0) == null || StringUtils.isBlank(params.get(0).toString())) {
	            return null;
	         }
	         String key = params.get(0).toString();
	         return new EnumInfoGetter().getEnum(key);
	    }

	    @Override
	    public void init() {
	    	addEnum(TranState.class, TranState.class.getSimpleName(), "desc");
	    	addEnum(TranType.class, TranType.class.getSimpleName(), "desc");
	    	addEnum(AccountType.class);
	    	addEnum(BusinessProduct.class, BusinessProduct.class.getSimpleName(), "desc");
	    	addEnum(LoanState.class, LoanState.class.getSimpleName(), "desc");
	    	addEnum(Currency.class, Currency.class.getSimpleName(), "desc");
	    	addEnum(RepaymentType.class, RepaymentType.class.getSimpleName(), "desc");
	    	addEnum(SettleStatus.class, SettleStatus.class.getSimpleName(), "desc");
	    	addEnum(CreditTradeStatus.class, CreditTradeStatus.class.getSimpleName(), "desc");
	    	addEnum(CreditLimitStatus.class, CreditLimitStatus.class.getSimpleName(), "desc");
	    	addEnum(ApplyApplicantType.class, ApplyApplicantType.class.getSimpleName(), "desc");
	    	addEnum(CreditMode.class, CreditMode.class.getSimpleName(), "desc");
	    	addEnum(EarlywarningEventCategory.class, EarlywarningEventCategory.class.getSimpleName(), "desc");
	    	addEnum(YundaexEarlywarningEventCategory.class, YundaexEarlywarningEventCategory.class.getSimpleName(), "desc");
	    	addEnum(YundaexEarlywarningEventSubCategory.class, YundaexEarlywarningEventSubCategory.class.getSimpleName(), "desc");
	    }

}
