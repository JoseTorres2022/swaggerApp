package com.jpa.Swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//esta clase es de configuracion
@EnableSwagger2//activando el swagger
public class SwaggerConfig {

    @Bean
        //docket para personalizar el swagger
    Docket api(){
        //swagger 2 para documentar la api
        return new Docket(DocumentationType.SWAGGER_2).select()
                //q cosas vamos a documentar
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //seleccioname las clases q tengan esta anotacion RestController q van a ser documentadas
                .paths(PathSelectors.any()).build();//incluyeme todos los metodos q tenga la clase
    }


}
