package com.cana.vbam.common.account.consts;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

public class AccountConsts {
	private static final Logger logger = LoggerFactory.getLogger(AccountConsts.class);

	private static final Gson gson = new Gson();
	// 代开一般户最大值
	public static final int agentAccountMaxNumber = 1;
	// 分号字符
	public static final String SEMICOLON = ";";
	// 主动开户每次最大可同时建立一般账户数
	public static final int selfAccountMaxNumber = 10;
	// 中信标志
	public static final String citic_bank_name = "中信银行";
	// 买方名称Excel模板
	public static final String buyer_name_excel_template;
	// 保理商代开户申请时所需的融资客户开户文件
	public static final String finace_apply_files;
	// 授权书模板
	public static final String authorization_letter_template;
	// 银行提现限制
	public static final List<String> withdraw_bank_limit;

	static {
		Properties templates = readTemplateConf(ConfScope.R);
		buyer_name_excel_template = templates.getProperty("buyer-name-template-id");
		finace_apply_files = templates.getProperty("finace-apply-files-id");
		authorization_letter_template = templates.getProperty("authorization-letter-template-id");
		withdraw_bank_limit = readeBankLimit(ConfScope.R);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static List<String> readeBankLimit(ConfScope scope) {
		logger.info("======================初始化提现银行限制========================");
		Properties p = TopsConfReader.getConfProperties("properties/withdraw-bank-limit.properties", scope);
		logger.info(gson.toJson(p));
		return new ArrayList(p.values());
	}

	private static Properties readTemplateConf(ConfScope scope) {
		logger.info("=====================初始化下载文件模板数据======================");
		Properties p = TopsConfReader.getConfProperties("properties/file-template.properties", scope);
		logger.info(gson.toJson(p));
		return p;
	}

	public static void main(String[] args) {
		System.out.println(JSON.toJSON(AccountConsts.buyer_name_excel_template));
	}
}
