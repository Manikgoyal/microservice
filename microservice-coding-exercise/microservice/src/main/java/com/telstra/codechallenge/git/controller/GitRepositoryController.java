package com.telstra.codechallenge.git.controller;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.git.model.GitRepositoryModel;
import com.telstra.codechallenge.git.model.GitRepositoryRequest;
import com.telstra.codechallenge.git.model.GitRepositoryResponse;
import com.telstra.codechallenge.git.service.GitRepositoryService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/repositories")
@Api(value = "Git Service", tags = "Git Service")
public class GitRepositoryController {
	
	private static final Logger LOG = LogManager.getLogger(GitRepositoryController.class);

	@Autowired
	private GitRepositoryService gitRepositoryService;

	@GetMapping
	public ResponseEntity<GitRepositoryResponse<GitRepositoryModel>> getGitRepository(
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit) {
		LOG.debug("GitRepositoryController --> getGitRepository ");
		return new ResponseEntity<>(gitRepositoryService.searchRepositories(buildRequestObject(page, limit)),
				HttpStatus.OK);
	}

	private GitRepositoryRequest buildRequestObject(Integer page, Integer limit) {
		if (!Objects.nonNull(page) || page < 0)page = 1;
		if (!Objects.nonNull(limit) || limit < 0)limit = 5;
		return new GitRepositoryRequest.GitRepositoryRequestBuilder(page, limit).query("stars").sort("stars")
				.order("desc").since("weekly").build();
	}

}
