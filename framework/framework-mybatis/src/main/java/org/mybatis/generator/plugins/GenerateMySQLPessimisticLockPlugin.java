/**
 * Copyright © 2016-2029 travelzen. All rights reserved.
 */
package org.mybatis.generator.plugins;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author ducer
 *
 */
public class GenerateMySQLPessimisticLockPlugin extends GeneratePessimisticLockPlugin {

	@Override
	protected void generateLimitElement(XmlElement parentElement) {
		XmlElement limit = new XmlElement("if");
		limit.addAttribute(new Attribute("test", "limitStart >= 0"));
		limit.addElement(new TextElement(" limit #{limitStart} , #{limitEnd} "));
		parentElement.addElement(limit);
	}

}
