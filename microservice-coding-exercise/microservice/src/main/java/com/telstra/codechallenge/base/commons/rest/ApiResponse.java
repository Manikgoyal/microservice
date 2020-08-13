package com.telstra.codechallenge.base.commons.rest;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Manik
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse {

	private String status;
	private String code;
	private List<String> messages;

	public ApiResponse() {
	}

	public ApiResponse(String status, String code) {
		this.status = status;
		this.code = code;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(final List<String> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
