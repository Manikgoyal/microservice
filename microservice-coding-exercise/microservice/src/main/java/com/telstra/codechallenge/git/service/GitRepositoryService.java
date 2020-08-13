package com.telstra.codechallenge.git.service;

import com.telstra.codechallenge.git.model.GitRepositoryModel;
import com.telstra.codechallenge.git.model.GitRepositoryRequest;
import com.telstra.codechallenge.git.model.GitRepositoryResponse;


public interface GitRepositoryService {
	
	public GitRepositoryResponse<GitRepositoryModel> searchRepositories(GitRepositoryRequest gitRepositoryRequest);
}
