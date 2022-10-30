package mira.space.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    public static final String PRODUCT_CONTROLLER_TAG = "Product controller";
    public static final String LIST_CONTROLLER_TAG = "List controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(PRODUCT_CONTROLLER_TAG, "REST controller for interactions with Product.class"),
                        new Tag(LIST_CONTROLLER_TAG, "REST controller for interactions with List.class"));
    }
}
