package com.Tourism_Agency;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TourismAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourismAgencyApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API Prueba Técnica Final Softtek")
				.version("0.0.1")
				.description("Documentación de la API de la Prueba Técnica Final Softtek."));
	}

}
