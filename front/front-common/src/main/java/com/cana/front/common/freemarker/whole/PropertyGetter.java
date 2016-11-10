package com.cana.front.common.freemarker.whole;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.cana.vbam.common.utils.WebEnv;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class PropertyGetter implements TemplateMethodModelEx {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List args) throws TemplateModelException {
		if (CollectionUtils.isEmpty(args)) {
			return null;
		}
		Object key = args.get(0);
		if (key == null) {
			return null;
		}
		return WebEnv.get(key.toString(), "");
	}

}
