package com.project.figma.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "---",
                version = "1.0",
                termsOfService = "Terms of service",
                contact = @Contact(
                        name = "---",
                        email = ""
                )
        )
)
public class SwaggerConfig {
}
