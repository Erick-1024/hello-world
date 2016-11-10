package com.cana.front.common.freemarker.whole;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.vbam.common.utils.AccountNoUtil;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 银行账号格式化
 * @author XuMeng
 *
 */
public class BankAccountNoFormater implements TemplateMethodModelEx {

	@Override @SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		if (CollectionUtils.isEmpty(args)) {
			return "";
		}
		if (args.get(0) == null || StringUtils.isBlank(args.get(0).toString())) {
			return "";
		}
		return AccountNoUtil.formatBankAccountNo(args.get(0).toString());
	}

}
