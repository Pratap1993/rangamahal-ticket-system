package com.chagu.rangmahal.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Pratap Bhanu
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.chagu.rangmahal")).paths(PathSelectors.any()).build()
				.apiInfo(apiInformation());
	}

	private ApiInfo apiInformation() {
		return new ApiInfo("Rangmahal Booking System", "Web application for booking tickets online.", "1.0.0",
				"Free To User",
				new Contact("Pratap Bhanu's Github", "https://github.com/Pratap1993/", "dhal.pratapbhanu@gmail.com"),
				"API Lincense", "www.chagu.com", Collections.emptyList());
	}

}
