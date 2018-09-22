package com.baily.template.common.config.db.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: DruidConfig
 * @Description:
 * @author:YB
 * @date:2018年01月03日 13:59
 */
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
@ConditionalOnProperty(prefix = "druid.config",name = "enabled",value = "true")
public class DruidConfig {

    @Autowired
    private DruidProperties druidProperties;

	@Bean
    @ConditionalOnMissingBean(ServletRegistrationBean.class)
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),druidProperties.getUrlMapping());
		servletRegistrationBean.addInitParameter("resetEnable", druidProperties.getResetEnabl());
		servletRegistrationBean.addInitParameter("allow", druidProperties.getAllow());
		servletRegistrationBean.addInitParameter("deny", druidProperties.getDeny());
		servletRegistrationBean.addInitParameter("loginUsername", druidProperties.getLoginUsername());
		servletRegistrationBean.addInitParameter("loginPassword", druidProperties.getLoginPassword());

		return servletRegistrationBean;
	}

	@Bean
    @ConditionalOnMissingBean(FilterRegistrationBean.class)
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns(druidProperties.getUrlPatterns());
		filterRegistrationBean.addInitParameter("exclusions", druidProperties.getExclusions());
		filterRegistrationBean.addInitParameter("profileEnable", druidProperties.getProfileEnable());

		return filterRegistrationBean;
	}

	@Bean("wallFilter")
    @ConditionalOnMissingBean(WallFilter.class)
	public WallFilter wallFilter(){
		WallFilter wallFilter=new WallFilter();
		wallFilter.setConfig(wallConfig());
		return  wallFilter;
	}

	@Bean
    @ConditionalOnMissingBean(WallConfig.class)
	public WallConfig wallConfig(){
		WallConfig config =new WallConfig();
		//允许一次执行多条语句
		config.setMultiStatementAllow(true);
		//允许非基本语句的其他语句
		config.setNoneBaseStatementAllow(true);
		return config;
	}
}