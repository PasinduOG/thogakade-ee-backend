package edu.icet.ecom.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(
                new Info().title("Thoga Kade Server")
                        .description("ඔබගේ තොග බඩු සපයන්නා...")
        );
    }
}
