package com.telstra.codechallenge.git.model;

import java.util.List;

import com.telstra.codechallenge.base.commons.rest.ApiResponse;

public class GitRepositoryResponse<T> extends ApiResponse {

	private long total_count;
	private boolean incomplete_results;	
	private List<T> items;
	
	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(long total_count) {
		this.total_count = total_count;
	}

	public boolean isIncomplete_results() {
		return incomplete_results;
	}

	public void setIncomplete_results(boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}

}
