package com.ProyectoSpring.Instituto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class InstitutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstitutoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		System.out.println("======================================");
		System.out.println("🌐 Swagger UI: http://localhost:8080/swagger-ui.html");
		System.out.println("📄 API Docs:  http://localhost:8080/v3/api-docs");
		System.out.println("======================================");
	}
}
