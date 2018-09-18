package com.baily.template.common.config.db.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
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
public class DruidConfig {

	@Value("${spring.druid.view}")
	private String druidView;

	@Value("${spring.druid.resetEnabl}")
	private String resetEnable;

	@Value("${spring.druid.allow}")
	private String allow;

	@Value("${spring.druid.deny}")
	private String deny;

	@Value("${spring.druid.loginUsername}")
	private String loginUsername;

	@Value("${spring.druid.loginPassword}")
	private String loginPassword;

	@Value("${spring.druid.urlPatterns}")
	private String urlPatterns;
	
	@Value("${spring.druid.exclusions}")
	private String exclusions;

	@Value("${spring.druid.profileEnable}")
	private String profileEnable;

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),druidView);
		servletRegistrationBean.addInitParameter("resetEnable", resetEnable);
		servletRegistrationBean.addInitParameter("allow", allow);
		servletRegistrationBean.addInitParameter("deny", deny);
		servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
		servletRegistrationBean.addInitParameter("loginPassword", loginPassword);

		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns(urlPatterns);
		filterRegistrationBean.addInitParameter("exclusions", exclusions);
		filterRegistrationBean.addInitParameter("profileEnable", profileEnable);

		return filterRegistrationBean;
	}

	@Bean("wallFilter")
	public WallFilter wallFilter(){
		WallFilter wallFilter=new WallFilter();
		wallFilter.setConfig(wallConfig());
		return  wallFilter;
	}

	@Bean
	public WallConfig wallConfig(){
		WallConfig config =new WallConfig();
		//允许一次执行多条语句
		config.setMultiStatementAllow(true);
		//允许非基本语句的其他语句
		config.setNoneBaseStatementAllow(true);
		return config;
	}
}