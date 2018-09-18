package com.baily.template.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * 拦截防止xss注入
 * 通过Jsoup过滤请求参数内的特定字符
 * @author yangwk 
 */  
public class XssFilter extends ZuulFilter{
	private static Logger logger = LoggerFactory.getLogger(XssFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if (!handleExcludeURL(request)) {
			XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request, true);
			ctx.setRequest(xssRequest);
		}
		return null;
	}

	private boolean handleExcludeURL(HttpServletRequest request) {
		List<String> excludes = new ArrayList<String>();
		excludes.add("/favicon.ico");
		excludes.add("/img/*");
		excludes.add("/js/*");
		excludes.add("/css/*");

		String url = request.getServletPath();
		for (String pattern : excludes) {
			Pattern p = Pattern.compile("^" + pattern);
			Matcher m = p.matcher(url);
			if (m.find()) {
				return true;
			}
		}

		return false;
	}

}
