package br.com.rabbitmqlocal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RabbitMqLocalExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqLocalExampleApplication.class, args);
	}

	@Bean
	public Docket demoApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.any())
	            .paths(Predicates.not(PathSelectors.regex("/error.*")))
	            .build();
	}
}
