package com.yance520.itpower.db;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义数据源路由
 *
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final Logger logger = Logger.getLogger(DynamicDataSource.class);

	@Override
	protected Object determineCurrentLookupKey() {
		// 从自定义的位置获取数据源标识
		String dataSource = DataSourceContextHolder.get();
		// logger.info("======== [使用数据源] ====== use the data source: " + dataSource);
		return dataSource;
	}

}
