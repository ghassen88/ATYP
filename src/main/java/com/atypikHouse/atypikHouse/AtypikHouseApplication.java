package com.atypikHouse.atypikHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class AtypikHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtypikHouseApplication.class, args);


	}
@Bean

public BCryptPasswordEncoder getBCPE(){
	return new BCryptPasswordEncoder();
}


}
