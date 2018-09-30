package com.baily.template.common.config.db.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;
import com.baily.template.common.config.db.multidatasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: Slave1Config
 * @Description:
 * @author:YB
 * @date:2018年01月03日 13:59
 */
//@Configuration
@EnableConfigurationProperties(SlaveProperties.class)
@AutoConfigureBefore(DynamicDataSource.class)	//在动态数据源之前加载
public class Slave1Config {

	@Autowired
	WallFilter wallFilter;

	@Autowired
	SlaveProperties slaveProperties;

	@Bean("slave1")
	public DataSource masterDataSource() throws Exception {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setName(slaveProperties.getName());
		druidDataSource.setUrl(slaveProperties.getUrl());
		druidDataSource.setUsername(slaveProperties.getUsername());
		druidDataSource.setPassword(slaveProperties.getPassword());
		druidDataSource.setDriverClassName(slaveProperties.getDriverClassName());

		druidDataSource.setInitialSize(slaveProperties.getInitialSize());
		druidDataSource.setMaxActive(slaveProperties.getMaxActive());
		druidDataSource.setMinIdle(slaveProperties.getMinIdle());
		druidDataSource.setMaxWait(slaveProperties.getMaxWait());
		druidDataSource.setTimeBetweenEvictionRunsMillis(slaveProperties.getTimeBetweenEvictionRunsMillis());
		druidDataSource.setMinEvictableIdleTimeMillis(slaveProperties.getMinEvictableIdleTimeMillis());
		druidDataSource.setValidationQuery(slaveProperties.getValidationQuery());
		druidDataSource.setTestWhileIdle(slaveProperties.isTestWhileIdle());
		druidDataSource.setTestOnBorrow(slaveProperties.isTestOnBorrow());
		druidDataSource.setTestOnReturn(slaveProperties.isTestOnReturn());
		druidDataSource.setPoolPreparedStatements(slaveProperties.isPoolPreparedStatements());
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(slaveProperties.getMaxPoolPreparedStatementPerConnectionSize());
		//filters和proxyFilters不能同时设置，否则无法执行批量更新
//		druidDataSource.setFilters(this.filters);
		druidDataSource.setConnectionProperties(slaveProperties.getConnectionProperties());
		druidDataSource.setUseGlobalDataSourceStat(slaveProperties.isUseGlobalDataSourceStat());
		//支持批量更新
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(wallFilter);
		druidDataSource.setProxyFilters(filters);

		druidDataSource.init();    //初始化
		return druidDataSource;
	}

}
