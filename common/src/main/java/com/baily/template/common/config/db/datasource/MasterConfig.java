//package com.baily.template.common.config.db.datasource;
//
//import com.alibaba.druid.filter.Filter;
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.wall.WallFilter;
//import com.baily.template.common.config.db.multidatasource.DynamicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
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
//@Configuration
//@AutoConfigureBefore(DynamicDataSource.class)    //在动态数据源之前加载
//public class MasterConfig {
//
//    /**
//     * druid数据库名
//     */
//    @Value("${spring.datasource.master.name}")
//    private String name;
//    /**
//     * 数据库url
//     */
//    @Value("${spring.datasource.master.url}")
//    private String url;
//    /**
//     * 数据库用户名
//     */
//    @Value("${spring.datasource.master.username}")
//    private String username;
//    /**
//     * 数据库密码
//     */
//    @Value("${spring.datasource.master.password}")
//    private String password;
//    /**
//     * 数据库驱动器类
//     */
//    @Value("${spring.datasource.master.driverClassName}")
//    private String driverClassName;
//    /**
//     * 配置初始化大小、最小、最大
//     */
//    @Value("${spring.datasource.master.initialSize}")
//    private int initialSize;
//    /**
//     * 线程池最大活动数
//     */
//    @Value("${spring.datasource.master.maxActive}")
//    private int maxActive;
//
//    /**
//     * 线程池最小闲置数
//     */
//    @Value("${spring.datasource.master.minIdle}")
//    private int minIdle;
//    /**
//     * 配置获取连接等待超时的时间
//     */
//    @Value("${spring.datasource.master.maxWait}")
//    private int maxWait;
//    /**
//     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
//     */
//    @Value("${spring.datasource.master.timeBetweenEvictionRunsMillis}")
//    private long timeBetweenEvictionRunsMillis;
//    /**
//     * 配置一个连接在池中最小生存的时间，单位是毫秒
//     */
//    @Value("${spring.datasource.master.minEvictableIdleTimeMillis}")
//    private long minEvictableIdleTimeMillis;
//    /**
//     * 用来检测连接是否有效的sql，要求是一个查询语句
//     */
//    @Value("${spring.datasource.master.validationQuery}")
//    private String validationQuery;
//    /**
//     * 检测连接
//     */
//    @Value("${spring.datasource.master.testWhileIdle}")
//    private boolean testWhileIdle;
//
//    @Value("${spring.datasource.master.testOnBorrow}")
//    private boolean testOnBorrow;
//
//    @Value("${spring.datasource.master.testOnReturn}")
//    private boolean testOnReturn;
//    /**
//     * 是否开启PSCache
//     */
//    @Value("${spring.datasource.master.poolPreparedStatements}")
//    private boolean poolPreparedStatements;
//    /**
//     * 指定每个连接上PSCache的大小
//     */
//    @Value("${spring.datasource.master.maxPoolPreparedStatementPerConnectionSize}")
//    private int maxPoolPreparedStatementPerConnectionSize;
//    /**
//     * 配置监控统计拦截的filters
//     */
//    @Value("${spring.datasource.master.filters}")
//    private String filters;
//    /**
//     * 设置慢SQL记录
//     */
//    @Value("${spring.datasource.master.connectionProperties}")
//    private String connectionProperties;
//    /**
//     * 设置公用监控数据，合并多个DruidDataSource的监控数据
//     */
//    @Value("${spring.datasource.master.useGlobalDataSourceStat}")
//    private boolean useGlobalDataSourceStat;
//    @Autowired
//    private WallFilter wallFilter;
//
//    @Bean("master")
//    @Primary
//    public DataSource masterDataSource() throws Exception {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setName(name);
//        druidDataSource.setUrl(this.url);
//        druidDataSource.setUsername(this.username);
//        druidDataSource.setPassword(this.password);
//        druidDataSource.setDriverClassName(this.driverClassName);
//
//        druidDataSource.setInitialSize(this.initialSize);
//        druidDataSource.setMaxActive(this.maxActive);
//        druidDataSource.setMinIdle(this.minIdle);
//        druidDataSource.setMaxWait(this.maxWait);
//        druidDataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
//        druidDataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
//        druidDataSource.setValidationQuery(this.validationQuery);
//        druidDataSource.setTestWhileIdle(this.testWhileIdle);
//        druidDataSource.setTestOnBorrow(this.testOnBorrow);
//        druidDataSource.setTestOnReturn(this.testOnReturn);
//        druidDataSource.setPoolPreparedStatements(this.poolPreparedStatements);
//        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
//        //filters和proxyFilters不能同时设置，否则无法执行批量更新
////		druidDataSource.setFilters(this.filters);
//        druidDataSource.setConnectionProperties(connectionProperties);
//        druidDataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
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
