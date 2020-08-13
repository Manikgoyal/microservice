package com.telstra.codechallenge.git.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.telstra.codechallenge.base.commons.interceptor.CustomRestTemplateCustomizer;
import com.telstra.codechallenge.git.model.GitProperties;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties({ GitProperties.class })
public class GitRepositoryConfig {

	@Bean
	@Qualifier("customRestTemplateCustomizer")
	public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
		return new CustomRestTemplateCustomizer();
	}

	@Bean
	@DependsOn(value = { "customRestTemplateCustomizer" })
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder(customRestTemplateCustomizer());
	}

}
