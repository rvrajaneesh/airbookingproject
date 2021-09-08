package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class UserSignupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSignupServiceApplication.class, args);
	}

	
//	@Bean
//	public Docket swaggerConfig() {
//	return new Docket(DocumentationType.SWAGGER_2)
//	.select()
//	.apis(RequestHandlerSelectors.basePackage("com.search"))
//	.build().apiInfo(apiDetails());
//	}
//	private ApiInfo apiDetails() {
//	return  new ApiInfo(
//	"User Service",
//	"User Registration Management",
//	"_",
//	"Free to use",
//	new springfox.documentation.service.Contact("Rajaneesh R V", "http://airflightbook.com","raj@yahoo.com"),
//	"Api License",
//	"http://airbooking.com",
//	Collections.emptyList());
//}
}
