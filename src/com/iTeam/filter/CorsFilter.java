package com.iTeam.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.iTeam.controller.UserMessageController;
import com.iTeam.util.CollectionUtil;
import com.iTeam.util.StringUtil;

/**
 * 于过滤所有的HTTP请求，并将CORS响应头写入response对象中
 * 
 * @author guyue_NO1
 *
 */
public class CorsFilter implements Filter {

	private static final Logger logger = Logger.getLogger(UserMessageController.class);
	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String currentOrigin = request.getHeader("Origin");
		logger.debug("currentOrigin : " + currentOrigin);
		if (StringUtil.isNotEmpty(allowOrigin)) {
			List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
			logger.debug("allowOriginList : " + allowOrigin);
			if (CollectionUtil.isNotEmpty(allowOriginList)) {
				if (allowOriginList.contains(currentOrigin)) {
					response.setHeader("Access-Control-Allow-Origin", currentOrigin);
				}
			}
		}
		/**
		 * Access-Control-Allow-Origin：允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制；
		 * 
		 * Access-Control-Allow-Methods：允许访问的方法名，多个方法名用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS；
		 * 
		 * Access-Control-Allow-Credentials：是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true；
		 * 
		 * Access-Control-Allow-Headers：允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type；
		 * 
		 * Access-Control-Expose-Headers：允许客户端访问的服务端响应头，多个响应头用逗号分割。
		 */
		if (StringUtil.isNotEmpty(allowMethods)) {
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		if (StringUtil.isNotEmpty(allowCredentials)) {
			response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
		}
		if (StringUtil.isNotEmpty(allowHeaders)) {
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		if (StringUtil.isNotEmpty(exposeHeaders)) {
			response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
		}
		chain.doFilter(req, res);

	}

	/**
	 * 从web.xml中读取相关Filter初始化参数
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");

	}

}
