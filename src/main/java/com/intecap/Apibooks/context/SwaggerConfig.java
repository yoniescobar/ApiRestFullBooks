package com.intecap.Apibooks.context;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration // Indica que es una clase de configuración de Spring Boot (similar a un archivo de propiedades)
@EnableSwagger2 // Habilita la documentación de Swagger
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.intecap.Apibooks")) // Selecciona el paquete donde se encuentran los controladores
                .paths(PathSelectors.any()) // Selecciona todos los paths
                .build() // Construye la configuración
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Apiboos API",
                "La API REST de Apibooks permite realizar operaciones CRUD sobre la base de datos de libros.",
                "v1",
                "Terms of service",
                new Contact("Intecap", "www.intecap.com", "yoniedilzar@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}


