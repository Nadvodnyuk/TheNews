package com.example.TheNews;

import com.example.TheNews.entity.User;
import com.example.TheNews.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheNewsApplication implements CommandLineRunner{


	public static void main(String[] args) {
		SpringApplication.run(TheNewsApplication.class, args);
	}

	@Autowired
	private UserRepo userRepo;

	@Override
	public void run(String... args) throws Exception {
		User user1 = User.builder()
			.first_name("Markus")
			.last_name("Revazov")
			.role("Admin")
			.username("Batman")
			.password("password1")
			.build();
		userRepo.save(user1);
	}

}
