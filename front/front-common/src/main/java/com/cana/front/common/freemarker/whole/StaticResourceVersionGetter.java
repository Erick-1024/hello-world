package com.cana.front.common.freemarker.whole;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class StaticResourceVersionGetter implements TemplateMethodModelEx {

	private static final String VERSION = Long.toHexString(System.currentTimeMillis() / 1000 / 60 / 60 / 24);

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List args) throws TemplateModelException {
		return VERSION;
	}

}
