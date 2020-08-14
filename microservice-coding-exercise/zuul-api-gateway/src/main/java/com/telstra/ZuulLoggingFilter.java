package com.telstra;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private static final Logger LOG = LogManager.getLogger(ZuulLoggingFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
		LOG.info("request is -> {} request uri -> {}",httpServletRequest,httpServletRequest.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	//set priority to the filters
	@Override
	public int filterOrder() {
		return 1;
	}

}
