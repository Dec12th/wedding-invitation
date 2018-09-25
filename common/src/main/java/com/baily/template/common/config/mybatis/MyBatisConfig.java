package com.baily.template.common.config.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName: MyBatisConfig
 * @Description:
 * @author:YB
 * @date:2018年01月02日 17:59
 */
@Configuration
@EnableTransactionManagement // 加上这个注解，使得支持事务
public class MyBatisConfig implements TransactionManagementConfigurer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    /**
     * mybatis配置
     */
    @Value("${spring.mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;
    @Value("${spring.mybatis.mybatisConfigXml}")
    private String mybatisConfigXml;
    @Value("${spring.mybatis.mappingXml}")
    private String mappingXml;
    /**
     * pageHelper配置:数据库官方语言
     */
    @Value("${spring.pageHelper.dialect}")
    private String dialect;
    /**
     * RowBounds是否进行count查询 - 默认不查询
     */
    @Value("${spring.pageHelper.rowBoundsWithCount}")
    private String rowBoundsWithCount;
    /**
     * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
     */
    @Value("${spring.pageHelper.pageSizeZero}")
    private String pageSizeZero;
    @Value("${spring.pageHelper.reasonable}")
    private String reasonable;
    /**
     * 是否支持接口参数来传递分页参数，默认false
     */
    @Value("${spring.pageHelper.supportMethodsArguments}")
    private String supportMethodsArguments;
    /**
     * offset作为PageNum使用
     */
    @Value("${spring.pageHelper.offsetAsPageNum}")
    private String offsetAsPageNum;
    /**
     * 初始化SqlUtil的params
     */
    @Value("${spring.pageHelper.params}")
    private String params;
    /**
     * always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
     */
    @Value("${spring.pageHelper.returnPageInfo}")
    private String returnPageInfo;

    /**
     * 功能：mybatis文件配置，扫描所有mapper文件，采用的是xml方式
     *
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
//        properties.setProperty("closeConn", "true");	//查询后关闭连接
//        properties.setProperty("dialect", dialect);
        properties.setProperty("rowBoundsWithCount", rowBoundsWithCount);
        properties.setProperty("pageSizeZero", pageSizeZero);
        properties.setProperty("reasonable", reasonable);
        properties.setProperty("supportMethodsArguments", supportMethodsArguments);
        properties.setProperty("offsetAsPageNum", offsetAsPageNum);
        properties.setProperty("params", params);
        properties.setProperty("returnPageInfo", returnPageInfo);
        pageHelper.setProperties(properties);
        //添加分页插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录。使用xml配置方式，比较灵活且可实现复杂的sql语句，底层可使用generator生成
        //资源文件路径解析器，xml配置时所需
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            if (mybatisConfigXml != null && !"".equalsIgnoreCase(mybatisConfigXml)) {
                //mybatis配置文件
                sqlSessionFactoryBean.setConfigLocation(resolver.getResources(mybatisConfigXml)[0]);
            }
            if (mappingXml != null && !"".equalsIgnoreCase(mappingXml)) {
                //xml配置
                sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mappingXml));
            }
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            logger.error("create sqlSessionFactory error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能：创建sqlSessionTemplate
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 功能：启用对事务注解的支持
     */
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}