package com.ismardz.similarproducts.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Similar Products API",
        version = "1.0",
        description = "API for similar products"
    )
)
@Configuration
public class SwaggerConfig {
}
