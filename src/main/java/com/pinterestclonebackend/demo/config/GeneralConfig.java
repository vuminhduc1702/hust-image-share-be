package com.pinterestclonebackend.demo.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import com.pinterestclonebackend.demo.entity.AuditorAwareImpl;
import com.pinterestclonebackend.demo.repository.UserRepository;


@Configuration
@AllArgsConstructor
public class GeneralConfig {


    private final UserRepository userRepository;

    @Bean
    public OpenAPI openAPI() {

        Server local = new Server();
        local.setUrl("http://localhost:8080");

        return new OpenAPI().addServersItem(local)
                .info(new Info().title("Pinterest-clone API")
                .description("API to let you add and view our product")
                .version("0.0.1")
                .contact(new Contact().name("Vu Minh Duc")
                        .email("duc.vm204726@sis.hust.edu.vn")
                        .url("hust.edu.vn"))
                .license(new License().name("Copyright © 2023 by VMD")
                        .url("API license URL")));
    }

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new AuditorAwareImpl(userRepository);
    }
}
