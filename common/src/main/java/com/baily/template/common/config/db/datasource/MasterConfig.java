//package com.baily.template.common.config.db.datasource;
//
//import com.alibaba.druid.filter.Filter;
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.wall.WallFilter;
//import com.baily.template.common.config.db.multidatasource.DynamicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName: MasterConfig
// * @Description:
// * @author:YB
// * @date:2018年01月03日 13:59
// */
////@Configuration
//@EnableConfigurationProperties(MasterProperties.class)
//@AutoConfigureBefore(DynamicDataSource.class)    //在动态数据源之前加载
//public class MasterConfig {
//
//    @Autowired
//    WallFilter wallFilter;
//
//    @Autowired
//    MasterProperties masterProperties;
//
//    @Bean("master")
//    @Primary
//    public DataSource masterDataSource() throws Exception {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setName(masterProperties.getName());
//        druidDataSource.setUrl(masterProperties.getUrl());
//        druidDataSource.setUsername(masterProperties.getUsername());
//        druidDataSource.setPassword(masterProperties.getPassword());
//        druidDataSource.setDriverClassName(masterProperties.getDriverClassName());
//
//        druidDataSource.setInitialSize(masterProperties.getInitialSize());
//        druidDataSource.setMaxActive(masterProperties.getMaxActive());
//        druidDataSource.setMinIdle(masterProperties.getMinIdle());
//        druidDataSource.setMaxWait(masterProperties.getMaxWait());
//        druidDataSource.setTimeBetweenEvictionRunsMillis(masterProperties.getTimeBetweenEvictionRunsMillis());
//        druidDataSource.setMinEvictableIdleTimeMillis(masterProperties.getMinEvictableIdleTimeMillis());
//        druidDataSource.setValidationQuery(masterProperties.getValidationQuery());
//        druidDataSource.setTestWhileIdle(masterProperties.isTestWhileIdle());
//        druidDataSource.setTestOnBorrow(masterProperties.isTestOnBorrow());
//        druidDataSource.setTestOnReturn(masterProperties.isTestOnReturn());
//        druidDataSource.setPoolPreparedStatements(masterProperties.isPoolPreparedStatements());
//        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(masterProperties.getMaxPoolPreparedStatementPerConnectionSize());
//        //filters和proxyFilters不能同时设置，否则无法执行批量更新
////		druidDataSource.setFilters(this.filters);
//        druidDataSource.setConnectionProperties(masterProperties.getConnectionProperties());
//        druidDataSource.setUseGlobalDataSourceStat(masterProperties.isUseGlobalDataSourceStat());
//        //支持批量更新
//        List<Filter> filters = new ArrayList<Filter>();
//        filters.add(wallFilter);
//        druidDataSource.setProxyFilters(filters);
//
//        druidDataSource.init();    //初始化
//        return druidDataSource;
//    }
//
//}
