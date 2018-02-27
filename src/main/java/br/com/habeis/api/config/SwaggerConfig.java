package br.com.habeis.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Diego Dulval
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.habeis.api.resources"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DataLogger - Habéis")
                .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum "
                        + "has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
                        + "took a "
                        + "galley of type and scrambled it to make a type specimen book. It has survived not only five "
                        + "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "
                        + "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum "
                        + "passages, and more recently with desktop publishing software like Aldus PageMaker including "
                        + "versions of Lorem Ipsum.")
                .license("D²")
                .version("1.0")
                .build();
    }

}
