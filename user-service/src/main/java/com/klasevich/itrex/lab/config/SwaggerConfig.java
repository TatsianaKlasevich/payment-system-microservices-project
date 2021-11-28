package com.klasevich.itrex.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Profile("!test")
public class SwaggerConfig {
    private static final String CLIENT_ID = "mobile";
    private static final String CLIENT_SECRET = "pin";
    private static final String AUTH_SERVER = "http://localhost:8081/users/v1/oauth/token";
    private static final String SWAGGER_API_NAME = "Payment system API";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.klasevich.itrex.lab.controller"))
                .paths(PathSelectors.any())
                .build();
//                .securitySchemes(List.of(securityScheme()))
//                .securityContexts(List.of(securityContext()));
//    }
//
//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId(CLIENT_ID)
//                .clientSecret(CLIENT_SECRET)
//                .scopeSeparator(" ")
//                .useBasicAuthenticationWithAccessCodeGrant(true)
//                .build();
//    }
//
//    private SecurityScheme securityScheme() {
//        return new OAuthBuilder()
//                .grantTypes(Collections.singletonList(
//                        new ResourceOwnerPasswordCredentialsGrant(AUTH_SERVER)))
//                .name(SWAGGER_API_NAME)
//                .build();
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .forPaths(PathSelectors.any())
//                .securityReferences(Collections.singletonList(SecurityReference.builder()
//                        .scopes(new AuthorizationScope[0])
//                        .reference(SWAGGER_API_NAME)
//                        .build()))
//                .build();
//    }

    }
}




