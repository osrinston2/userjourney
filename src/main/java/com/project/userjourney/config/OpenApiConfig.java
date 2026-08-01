package com.project.userjourney.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userJourneyOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Journey API")
                        .description("Travel Insurance User Journey API")
                        .version("v1.0.0"));
    }
}
