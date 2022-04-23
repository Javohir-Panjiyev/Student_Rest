package uz.pdp.restcrud;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class RestCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestCrudApplication.class, args);
    }

}
