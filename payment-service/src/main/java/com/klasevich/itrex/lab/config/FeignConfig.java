package com.klasevich.itrex.lab.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

public class FeignConfig {

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.username}")
    private String username;
    @Value("${security.password}")
    private String password;
    @Value("${security.grant.type}")
    private String grantType;
    @Value("${security.token.name}")
    private String tokenName;

    @Bean
    RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    private OAuth2ProtectedResourceDetails resource() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setGrantType(grantType);
        resourceDetails.setTokenName(tokenName);
        return resourceDetails;
    }
}
