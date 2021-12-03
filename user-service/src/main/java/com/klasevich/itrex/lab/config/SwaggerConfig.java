package com.klasevich.itrex.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Profile("!test")
public class SwaggerConfig {
    private static final String CLIENT_ID = "mobile";
    private static final String CLIENT_SECRET = "pin";
    private static final String AUTH_SERVER_URL = "http://localhost:8282/oauth/token";
    private static final String SWAGGER_API_NAME = "Payment system API";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.klasevich.itrex.lab"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    @Bean
    public SecurityConfiguration securityConfiguration() {
        return SecurityConfigurationBuilder.builder()
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .build();
    }

    private SecurityScheme securityScheme() {
        return new OAuthBuilder()
                .grantTypes(Collections.singletonList(
                        new ResourceOwnerPasswordCredentialsGrant(AUTH_SERVER_URL)))
                .name(SWAGGER_API_NAME)
                .build();
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .operationsSorter(OperationsSorter.ALPHA)
                .docExpansion(DocExpansion.LIST)
                .displayRequestDuration(true)
                .showExtensions(true)
                .deepLinking(true)
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .forPaths(PathSelectors.any())
                .securityReferences(Collections.singletonList(SecurityReference.builder()
                        .scopes(new AuthorizationScope[0])
                        .reference(SWAGGER_API_NAME)
                        .build()))
                .build();
    }
}








