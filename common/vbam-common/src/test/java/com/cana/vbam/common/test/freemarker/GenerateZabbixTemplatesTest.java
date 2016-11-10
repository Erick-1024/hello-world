package com.cana.vbam.common.test.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenerateZabbixTemplatesTest {

	private static String warAppNames = "tops_mediaserver,vbam_front_biz,credit_openapi";
	private static String zipAppNames = "member_server,message_server,repayment_server,repayment_scheduler,report_server,report_scheduler,account_server,account_scheduler,bankgate_server,credit_server,credit_scheduler,flight_finance_scheduler,flight_finance_server,marketing_server,asset_server";
	private static String appnames = warAppNames + "," + zipAppNames;

	public static void main(String[] args) throws IOException, TemplateException {
		Map<Object, Object> dataMap = new HashMap<>();
		dataMap.put("templateName", "Cana templates");
		dataMap.put("applicationNames", appnames.replace("_", "-").split(","));
		generateZabbixTemplates(dataMap);
	}

	public static void generateZabbixTemplates(Map<Object, Object> dataMap) throws IOException, TemplateException {
		String zabbixTemplatePath = GenerateZabbixTemplatesTest.class.getResource("").getPath();
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setDirectoryForTemplateLoading(new File(zabbixTemplatePath));
        Template template = configuration.getTemplate("zabbixTemplate.ftl");
        FileWriter out =  new FileWriter("/tmp/zabbixTemplate.xml");
        template.process(dataMap, out);
        out.flush();
        out.close();
	}
}
