package com.telstra.codechallenge.git.model;

public class GitRepositoryRequest {

	private String query;
	private String sort;
	private String order;
	private String since;
	private Integer page;
	private Integer limit;
	
	private GitRepositoryRequest(GitRepositoryRequestBuilder builder) {
        this.query = builder.query;
        this.sort = builder.sort;
        this.order = builder.order;
        this.since = builder.since;
        this.page = builder.page;
        this.limit = builder.limit;
    }

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public static class GitRepositoryRequestBuilder {
		private String query;
		private String sort;
		private String order;
		private String since;
		private Integer page;
		private Integer limit;

		public GitRepositoryRequestBuilder(Integer page, Integer limit) {
			this.page = page;
			this.limit = limit;
		}

		public GitRepositoryRequestBuilder query(String query) {
			this.query = query;
			return this;
		}

		public GitRepositoryRequestBuilder sort(String sort) {
			this.sort = sort;
			return this;
		}

		public GitRepositoryRequestBuilder order(String order) {
			this.order = order;
			return this;
		}

		public GitRepositoryRequestBuilder since(String since) {
			this.since = since;
			return this;
		}

		public GitRepositoryRequest build() {
			GitRepositoryRequest user = new GitRepositoryRequest(this);
			return user;
		}
	}

}
