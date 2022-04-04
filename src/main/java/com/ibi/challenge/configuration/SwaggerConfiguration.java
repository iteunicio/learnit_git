package com.ibi.challenge.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	private ApiInfo apiInfo() {
		return new ApiInfo("Países - REST APIs", 
				"Back-End Developer Challenge", 
				"1.0", 
				"Termos de Serviço", 
				new Contact("Eunicio de Alberito", "www.comsol.co.mz", 
						"euniciodealberito.cun@gmail.com"), 
				"Licença da API", 
				"API Licence URL", 
				Collections.emptyList());
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.build();
	}
}