package com.cana.front.common.freemarker.whole;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.travelzen.framework.core.util.MoneyUtil;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class CentMoneyFormater implements TemplateMethodModelEx {

	@Override @SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		if (CollectionUtils.isEmpty(args)) {
			return "";
		}
		if (args.get(0) == null || StringUtils.isBlank(args.get(0).toString())) {
			return "";
		}
		return MoneyUtil.formatMoney(MoneyUtil.cent2Yuan(args.get(0).toString()));
	}
}
