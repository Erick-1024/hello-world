package com.cana.asset.service.transaction.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * 放款和基础资产ID检查与生成工具类
 * <p> 放款和基础资产ID均以合同号开头，中间加一个分隔符号，后跟1～99之间的一串数字字符串，
 * <p> 放款的分隔符号为'-'，基础资产的分隔符号为'-S'
 * @author XuMeng
 */
public class LoanAndUnderlyingAssetIdUtils {

	private static PropertiesMapper propertiesMapper = SpringApplicationContext.getApplicationContext().getBean(PropertiesMapper.class);
	
	private static SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);

	public static void checkLoanInfoIdIsValid(String contractNo, String loanInfoId) {
		checkIdIsValid(contractNo, loanInfoId, IdType.loanInfoId);
	}

	public static void checkUnderlyingAssetIdIsValid(String contractNo, String underlyingAssetId) {
		checkIdIsValid(contractNo, underlyingAssetId, IdType.underlyingAssetId);
	}

	public static String generateLoanInfoId(String contractNo) {
		return generateId(contractNo, IdType.loanInfoId);
	}

	public static String generateLoanInfoIdForHomsom(String contractNo) {
		return contractNo + IdType.loanInfoId.sep
				+ DateTimeFormat.forPattern("yyMMdd").print(new DateTime())
				+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_LOAN_INFO_ID, 5);
	}

	public static String generateUnderlyingAssetId(String contractNo) {
		return generateId(contractNo, IdType.underlyingAssetId);
	}

	public static String generateUnderlyingAssetLogId(){
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_UNDERLYING_ASSET_LOG_ID, 4);
	}
	
	public static void updateLoanInfoIdSequance(String loanInfoId) {
		updateIdSequance(loanInfoId, IdType.loanInfoId);
	}

	public static void updateUnderlyingAssetIdSequance(String underlyingAssetId) {
		updateIdSequance(underlyingAssetId, IdType.underlyingAssetId);
	}
	

	private static void checkIdIsValid(String contractNo, String idStr, IdType idType) {
		if (StringUtils.isBlank(contractNo))
			throw new IllegalArgumentException("参数不能为空");
		Id id = new Id(idStr, idType);
		if (!StringUtils.equals(id.contractNo, contractNo))
			throw WebException.instance(idType.desc + "必需以合同号开始");
	}

	private static String generateId(String contractNo, IdType idType) {
		if (StringUtils.isBlank(contractNo))
			throw new IllegalArgumentException("参数不能为空");
		if (contractNo.contains(idType.sep))
			throw WebException.instance("合同号不能包含分隔符［" + idType.sep + "］");
		Properties properties = propertiesMapper.selectByPrimaryKey(idType.key + contractNo);
		if (properties == null || StringUtils.isBlank(properties.getValue()))
			return contractNo + idType.sep + "1";
		if (!properties.getValue().matches(idType.seqRegex))
			throw WebException.instance("生成" + idType.desc + "失败");

		int seq = Integer.valueOf(properties.getValue());
		if (seq >= idType.seqMax)
			throw WebException.instance("生成" + idType.desc + "失败，该合同的" + idType.desc + "序列号已达最大值：" + idType.seqMax);
		return contractNo + idType.sep + String.valueOf(seq + 1);
	}


	private static void updateIdSequance(String idStr, IdType idType) {
		Id id = new Id(idStr, idType);
		Properties properties = propertiesMapper.selectByPrimaryKey(idType.key + id.contractNo);
		if (properties == null) {
			properties = new Properties();
			properties.setName(idType.key + id.contractNo);
			properties.setValue("1");
			propertiesMapper.insertSelective(properties);
			return;
		}

		if (StringUtils.isBlank(properties.getValue())) {
			properties.setValue("1");
		} else {
			if (!properties.getValue().matches(idType.seqRegex))
				throw WebException.instance("更新" + idType.desc + "序列号失败");
			int seqdb = Integer.valueOf(properties.getValue());
			if (id.seq <= seqdb)
				return;
			properties.setValue(id.seq.toString());
		}
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}

	private static class Id {
		public String contractNo;
		public Integer seq;
		public IdType idType;

		public Id(String id, IdType idType) {
			if (StringUtils.isBlank(id))
				throw WebException.instance(idType.desc + "不能为空");

			int index = id.indexOf(idType.sep);
			if (index < 0)
				throw WebException.instance(idType.desc + "必需包含［" + idType.sep + "］分隔符");
			String array[] = id.split(idType.sep);
			if (array.length != 2)
				throw WebException.instance(idType.desc + "只能包含一个［" + idType.sep + "］分隔符");
			if (StringUtils.isBlank(array[0]))
				throw WebException.instance(idType.desc + "分隔符［" + idType.sep + "］前必须是合同号");
			if (!array[1].matches(idType.seqRegex))
				throw WebException.instance(idType.desc + "分隔符［" + idType.sep + "］后必需是1～" + idType.seqMax + "之间的合法数字");

			this.idType = idType;
			this.contractNo = array[0];
			this.seq = Integer.valueOf(array[1]);
		}
	}

	private static enum IdType {
		loanInfoId("asset_server:generate_loan_info_id:", "-", "^[1-9][0-9]?$", 99, "放款编号"),
		underlyingAssetId("asset_server:generate_underlying_asset_id:", "-S", "^[1-9][0-9]?$", 99, "放款编号"); // 非保理业务产生的基础资产编号

		public String key; // 保存序列号的key
		public String sep; // 分隔符
		public String seqRegex; // 序列号的校验正则表达式
		public int seqMax; // 允许的最大序列号
		public String desc; // 名称

		private IdType(String idKey, String idSep, String idSeqRegex, int idSeqMax, String desc) {
			this.key = idKey;
			this.sep = idSep;
			this.seqRegex = idSeqRegex;
			this.seqMax = idSeqMax;
			this.desc = desc;
		}
	}

}
