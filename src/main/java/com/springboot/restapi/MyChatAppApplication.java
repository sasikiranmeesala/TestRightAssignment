package com.springboot.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springboot.restapi.Repository.ChatMessageRepository;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {"com.springboot.restapi"})
public class MyChatAppApplication {
	
	@Autowired
	private ChatMessageRepository chatMessageRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyChatAppApplication.class, args);
	}

	@PreDestroy 
	public void onShutdown() 
	{
		chatMessageRepository.deleteAll(); 
	}
}
