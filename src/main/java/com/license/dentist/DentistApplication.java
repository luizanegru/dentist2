package com.license.dentist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class DentistApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentistApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner setupDefaultUser(CreateDefaultUserService service) {
//		return args -> {
//			service.createDefaultUser(new User(
//					null,
//					"Admin",
//					"Luiza",
//					"luiza.negru@yahoo.com",
//					"admin123",
//					true,
//					Arrays.asList(new Role(1,"ADMIN"))
//			));
//		};
//	}


}
