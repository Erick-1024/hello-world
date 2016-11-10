package com.cana.asset.search.handler;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.search.proxy.IndexBuildProxy;
import com.cana.asset.service.IUnderlyingAssetIndexService;
import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.dianping.cat.Cat;
import com.google.common.collect.Lists;

@Service
public class BatchIndexUpdateHandler implements IBatchIndexUpdateHandler{
	
	private Set<String> affectedAssetIdSet = new HashSet<>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IUnderlyingAssetIndexService underlyingAssetIndexService;
	
	@Resource
	private IndexBuildProxy indexBuildProxy;
	
	@Override
	public void prepare() {
		try{
			logger.info("开始新的批次");
			affectedAssetIdSet.clear();
		}catch(Exception e){
			logger.info("", e);
			Cat.logMetricForCount("update_index_prepare_error");
		}
	}

	@Override
	public void underlyingAssetChanged(String assetId) {
		try{
			logger.info("基础资产发生变动，id={}", assetId);
			affectedAssetIdSet.add(assetId);
		}catch(Exception e){
			logger.info("", e);
			Cat.logMetricForCount("update_index_underlyingAssetChanged_error");
		}
	}

	@Override
	public void customerChanged(String customerId) {
		try{
			logger.info("客户信息发生变动，id={}", customerId);
			Set<String> assetIds = underlyingAssetIndexService.queryAssetIdByCustomerId(customerId, UnderlyingAssetPoolStatus.ENTERING);
			if(CollectionUtils.isNotEmpty(assetIds))
				affectedAssetIdSet.addAll(assetIds);
		}catch(Exception e){
			logger.info("", e);
			Cat.logMetricForCount("update_index_customerChanged_error");
		}
	}

	@Override
	public void loanPlanChanged(String planId, String loanInfoId) {
		try{
			logger.info("还款计划发生变动， planId={}, loanInfoId={}", planId, loanInfoId);
			affectedAssetIdSet.add(loanInfoId);
		}catch(Exception e){
			logger.info("", e);
			Cat.logMetricForCount("update_index_loanPlanChanged_error");
		}
	}

	@Override
	public void loanInfoChanged(String loanInfoId) {
		try{
			logger.info("放款信息发生变动, id={}", loanInfoId);
			affectedAssetIdSet.add(loanInfoId);
		}catch(Exception e){
			logger.info("", e);
			Cat.logMetricForCount("update_index_loanInfoChanged_error");
		}
	}

	@Override
	public void creditChanged(String businessContractNo) {
		try{
			logger.info("授信额度发生变更, businessContractNo={}", businessContractNo);
			String assetId = underlyingAssetIndexService.getAssetIdByContractNo(businessContractNo, UnderlyingAssetPoolStatus.ENTERING);
			if(StringUtils.isNotBlank(assetId));
				affectedAssetIdSet.add(assetId);
		}catch(Exception e){
			logger.info("", e);
			Cat.logMetricForCount("update_index_creditChanged_error");
		}
	}

	@Override
	public void commit() {
		try{
			logger.info("本批次的修改开始提交");
			if(CollectionUtils.isNotEmpty(affectedAssetIdSet))
				indexBuildProxy.underlyingAssetIndexCreateOrUpdate(false, Lists.newArrayList(affectedAssetIdSet));
			else
				logger.info("本批次未存在改动");
		}catch(Exception e){
			logger.info("", e);
			Cat.logMetricForCount("update_index_commit_error");
		}
	}

}
