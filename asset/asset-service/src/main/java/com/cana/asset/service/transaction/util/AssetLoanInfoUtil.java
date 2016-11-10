package com.cana.asset.service.transaction.util;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;
import com.travelzen.framework.config.tops.TopsConfReader;

public class AssetLoanInfoUtil {

	/**
	 * 获取拒绝修改和还款冲销的合同号列表
	 * @return
	 */
	public static Set<String> getDenyModifyOrPaidContractNos() {
		String deny_modify_or_paid_contract_nos =
				TopsConfReader.getConfContent("properties/asset-common.properties",
						"deny_modify_or_paid_contract_nos");
		if (StringUtils.isNotBlank(deny_modify_or_paid_contract_nos))
			return Sets.newHashSet(deny_modify_or_paid_contract_nos.split(","));
		else
			return Sets.newHashSet();
	}
}
