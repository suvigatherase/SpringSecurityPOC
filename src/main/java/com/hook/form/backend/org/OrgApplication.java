package com.hook.form.backend.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")

public class OrgApplication {

	public static void main(String[] args) {
		//DEV-STABLE
		SpringApplication.run(OrgApplication.class, args);
	}

	}


