package com.tnluan.fishblogweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FishBlogWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishBlogWebApplication.class, args);
	}

}
