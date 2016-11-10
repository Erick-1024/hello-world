package com.cana.asset.service.transaction.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.custom.po.SpecialProgramGrossResult;
import com.cana.asset.dao.mapper.ABSUnderlyingAssetMapper;
import com.cana.asset.dao.mapper.gen.LoanInfoMapper;
import com.cana.asset.dao.mapper.gen.UnderlyingAssetMapper;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetCalcTransactionService;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetAmountSummary;
import com.cana.vbam.common.service.IVbamCommonService;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.NumberUtils;

@Service
public class UnderlyingAssetCalcTransactionServiceImpl implements IUnderlyingAssetCalcTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private LoanInfoMapper loanInfoMapper;
	@Resource
	private UnderlyingAssetMapper underlyingAssetMapper;
	@Resource
	private IABSDataPrivilegeTransactionService privilegeTransactionService;
	@Resource
	private ABSUnderlyingAssetMapper aBSUnderlyingAssetMapper;
	@Resource
	private IVbamCommonService vbamCommonService;

	@Override
	public Map<String, UnderlyingAssetAmountSummary> queryFinanceAmountBySpecialProgramIds(Set<String> specialProgramIds) {
		checkIdsIsNotBlank(specialProgramIds);

		String date10 = vbamCommonService.getCurrentDate();
		List<UnderlyingAssetAmountSummary> accounts = aBSUnderlyingAssetMapper.queryAccountAmountBySpecialProgramIds(specialProgramIds, date10);
		List<UnderlyingAssetAmountSummary> paids = aBSUnderlyingAssetMapper.queryPaidAmountBySpecialProgramIds(specialProgramIds, date10);

		return buildResults(specialProgramIds, accounts, paids);
	}

	private Map<String, UnderlyingAssetAmountSummary> buildResults(Set<String> ids, List<UnderlyingAssetAmountSummary> accounts, List<UnderlyingAssetAmountSummary> paids) {
		Map<String, UnderlyingAssetAmountSummary> results = Maps.newHashMap();
		for (String id : ids) {
			UnderlyingAssetAmountSummary summary = new UnderlyingAssetAmountSummary();
			summary.setId(id);
			results.put(id, summary);
		}
		for (UnderlyingAssetAmountSummary account : accounts) {
			UnderlyingAssetAmountSummary summary = results.get(account.getId());
			summary.setAccountPrincipal(summary.getAccountPrincipal() + account.getAccountPrincipal());
			summary.setAccountInterest(summary.getAccountInterest() + account.getAccountInterest());
			summary.setAccountOverdue(summary.getAccountOverdue() + account.getAccountOverdue());
		}
		for (UnderlyingAssetAmountSummary paid : paids) {
			UnderlyingAssetAmountSummary summary = results.get(paid.getId());
			summary.setPaidPrincipal(summary.getPaidPrincipal() + paid.getPaidPrincipal());
			summary.setPaidInterest(summary.getPaidInterest() + paid.getPaidInterest());
			summary.setPaidOverdue(summary.getPaidOverdue() + paid.getPaidOverdue());
		}
		for (UnderlyingAssetAmountSummary summary : results.values()) {
			summary.calcSummaryAmount();
		}
		return results;
	}

	@Override
	public Map<String, UnderlyingAssetAmountSummary> queryFinanceAmountByUnderlyingAssetIds(Set<String> underlyingAssetIds) {
		checkIdsIsNotBlank(underlyingAssetIds);

		String date10 = vbamCommonService.getCurrentDate();
		List<UnderlyingAssetAmountSummary> accounts = aBSUnderlyingAssetMapper.queryAccountAmountByUnderlyingAssetIds(underlyingAssetIds, date10);
		List<UnderlyingAssetAmountSummary> paids = aBSUnderlyingAssetMapper.queryPaidAmountByUnderlyingAssetIds(underlyingAssetIds, date10);

		return buildResults(underlyingAssetIds, accounts, paids);
	}

	private void checkIdsIsNotBlank(Set<String> ids) {
		if (CollectionUtils.isEmpty(ids))
			throw WebException.instance("参数不能为空");
		for (String id : ids)
			if (StringUtils.isBlank(id))
				throw WebException.instance("参数不能为空");
	}

	@Override
	public Map<String, Long> queryGrossBySpecialProgramIds(Set<String> specialProgramIds) {
		checkIdsIsNotBlank(specialProgramIds);

		List<SpecialProgramGrossResult> grosses = aBSUnderlyingAssetMapper.queryGrossBySpecialProgramIds(specialProgramIds);
		Map<String, Long> returnGrosses = Maps.newHashMap();
		for (SpecialProgramGrossResult gross : grosses) {
			returnGrosses.put(gross.getId(), gross.getGross());
		}
		for (String specialProgramId : specialProgramIds) {
			if (!returnGrosses.containsKey(specialProgramId))
				returnGrosses.put(specialProgramId, 0l);
		}
		return returnGrosses;
	}

	@Override
	public long queryGrossBySpecialProgramId(String specialProgramId) {
		return NumberUtils.getValue(
				queryGrossBySpecialProgramIds(Sets.newHashSet(specialProgramId)).get(specialProgramId));
	}

}
