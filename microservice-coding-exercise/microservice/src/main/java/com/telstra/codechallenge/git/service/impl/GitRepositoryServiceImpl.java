package com.telstra.codechallenge.git.service.impl;

import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.telstra.codechallenge.base.commons.exception.DataNotFoundException;
import com.telstra.codechallenge.base.commons.rest.ApiResponseCodes;
import com.telstra.codechallenge.git.model.GitProperties;
import com.telstra.codechallenge.git.model.GitRepositoryModel;
import com.telstra.codechallenge.git.model.GitRepositoryRequest;
import com.telstra.codechallenge.git.model.GitRepositoryResponse;
import com.telstra.codechallenge.git.service.GitRepositoryService;

import static com.telstra.codechallenge.base.commons.rest.ApiResponseCodes.DATA_NOT_FOUND_ERROR_MESSAGE;
import static com.telstra.codechallenge.base.commons.rest.ApiResponseCodes.DATA_FOUND_SUCCESS_MESSAGE;;

@Service
public class GitRepositoryServiceImpl implements GitRepositoryService {

	@Autowired
	private GitProperties gitProperties;

	@Autowired
	RestTemplate restTemplate;


	public GitRepositoryResponse<GitRepositoryModel> searchRepositories(
			GitRepositoryRequest gitRepositoryRequest) {

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(gitProperties.getUrl() + "/search/repositories")
				.queryParam("q", gitRepositoryRequest.getQuery()).queryParam("sort", gitRepositoryRequest.getSort())
				.queryParam("order", gitRepositoryRequest.getOrder())
				.queryParam("since", gitRepositoryRequest.getSince()).queryParam("page", gitRepositoryRequest.getPage())
				.queryParam("per_page", gitRepositoryRequest.getLimit());

		RequestEntity<Void> requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(builder.toUriString()));
		ParameterizedTypeReference<GitRepositoryResponse<GitRepositoryModel>> type = new ParameterizedTypeReference<GitRepositoryResponse<GitRepositoryModel>>() {
		};
		ResponseEntity<GitRepositoryResponse<GitRepositoryModel>> response = restTemplate.exchange(requestEntity, type);
		if (response.getStatusCode().is2xxSuccessful()) {				
			return buildResponseObject(response);
		}
		throw new DataNotFoundException(DATA_NOT_FOUND_ERROR_MESSAGE);
	}
	
	private GitRepositoryResponse<GitRepositoryModel> buildResponseObject(ResponseEntity<GitRepositoryResponse<GitRepositoryModel>> response) {
		GitRepositoryResponse<GitRepositoryModel> gitRepositoryResponse = response.getBody();
		gitRepositoryResponse.setCode(ApiResponseCodes.SUCCESS_CODE);
		gitRepositoryResponse.setStatus(ApiResponseCodes.SUCCESS_STATUS);
		gitRepositoryResponse.setMessages(Arrays.asList(DATA_FOUND_SUCCESS_MESSAGE));
		return gitRepositoryResponse;
	}


}
