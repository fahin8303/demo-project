package com.demo.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import lombok.Getter;

@Configuration
@Getter
public class AccessConfig {

	@Value("${oauth2.client-id}")
	private String clientId;

	@Value("${oauth2.client-secret-encrypted}")
	String clientSecretEnc;

	@Value("${oauth2.token-url}")
	private String tokenUrl;
	
	@Value("${mock.fareurl}")
	private String fareUrl;

	@Value("${mock.airportsurl}")
	private String airportsUrl;

	@Bean(name = "oAuth2Rest")
	public OAuth2RestTemplate oauth2RestTemplate() {
		return new OAuth2RestTemplate(resource());
	}

	public OAuth2ProtectedResourceDetails resource() {
		ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
		clientCredentialsResourceDetails.setAccessTokenUri(tokenUrl);
		clientCredentialsResourceDetails.setClientId(clientId);
		clientCredentialsResourceDetails.setClientSecret(new String(Base64.getDecoder().decode(clientSecretEnc)));
		return clientCredentialsResourceDetails;
	}
}
