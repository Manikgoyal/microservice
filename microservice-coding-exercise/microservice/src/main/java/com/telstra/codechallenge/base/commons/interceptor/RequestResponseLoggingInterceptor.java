package com.telstra.codechallenge.base.commons.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {
	
	private static final Logger LOG = LogManager.getLogger(RequestResponseLoggingInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		 logRequest(request, body);
	        ClientHttpResponse response = execution.execute(request, body);
	        logResponse(response);
	        return response;
	}
	
	private void logRequest(HttpRequest request, byte[] body) throws IOException {
        if (LOG.isDebugEnabled()) {
        	LOG.debug("===========================request begin================================================");
        	LOG.debug("URI         : {}", request.getURI());
        	LOG.debug("Method      : {}", request.getMethod());
        	LOG.debug("Headers     : {}", request.getHeaders());
        	LOG.debug("Request body: {}", new String(body, "UTF-8"));
        	LOG.debug("==========================request end================================================");
        }
    }
	
	 private void logResponse(ClientHttpResponse response) throws IOException {
	        if (LOG.isDebugEnabled()) {
	        	LOG.debug("============================response begin==========================================");
	        	LOG.debug("Status code  : {}", response.getStatusCode());
	        	LOG.debug("Status text  : {}", response.getStatusText());
	        	LOG.debug("Headers      : {}", response.getHeaders());
	        	LOG.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
	        	LOG.debug("=======================response end=================================================");
	        }
	    }
	
	

}
