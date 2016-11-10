package com.travelzen.framework.dao.rdbms.dataSource;

/**
 * @author yujunfeng
 * @function 动态产生需要的数据源
 * @className DynamicDataSource
 * 
 */
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	// 数据源集群
	@Override
	protected Object determineCurrentLookupKey() {
		DataSourceType dataSourceType = DataSourceContextHolder.getDataSourceType();

		if (dataSourceType != null) {
			return dataSourceType.name();
		}
		return null;
	}

}
