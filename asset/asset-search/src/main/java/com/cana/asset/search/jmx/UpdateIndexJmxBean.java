package com.cana.asset.search.jmx;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.cana.asset.search.proxy.IndexBuildProxy;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.lucene.index.IndexBuilder;

@ManagedResource(description = "更新索引") 
public class UpdateIndexJmxBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource IndexBuildProxy indexBuildProxy;
	
	@ManagedOperation(description="触发全量更新索引")
	@ManagedOperationParameters({ 
	})
	public void doJob() throws Throwable{
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		indexBuildProxy.underlyingAssetIndexCreateOrUpdate(true, null);
		logger.info("全量更新索引成功");
	}
}
