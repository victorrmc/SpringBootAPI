package com.bitboxer2.SpringBootAPI;

import com.bitboxer2.SpringBootAPI.Auth.AuthService;
import com.bitboxer2.SpringBootAPI.Auth.RegisterRequest;
import com.bitboxer2.SpringBootAPI.User.Role;
import com.bitboxer2.SpringBootAPI.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootApiApplication {
	@Autowired
	AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:5173")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthService service
	) {
		return args -> {

			System.out.println("Admin token: " + service.createAdmin().getToken());

			var manager = RegisterRequest.builder()
					.username("prueba@gmail.com")
					.firstname("prueba")
					.lastname("prueba")
					.password("1")
					.role(Role.USER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getToken());

		};
	}

}
