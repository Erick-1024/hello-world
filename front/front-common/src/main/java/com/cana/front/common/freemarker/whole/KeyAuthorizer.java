package com.cana.front.common.freemarker.whole;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.member.authorization.common.SecurityContextUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 *
 * @since Nov 11, 20151:44:34 PM
 * @author dev1
 *
 */
public class KeyAuthorizer implements TemplateMethodModelEx {

	@Override @SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		if (CollectionUtils.isEmpty(args)) {
			return true;
		}
		if (args.get(0) == null || StringUtils.isBlank(args.get(0).toString())) {
			return true;
		}
		String permKey = args.get(0).toString();
		return SecurityContextUtils.authorizePermKey(permKey);
	}

}
