package com.juny.core;

import com.juny.core.ch01.ApplicationStartingEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Date;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(CoreApplication.class);
		springApplication.addListeners(new ApplicationStartingEventListener());
		SpringApplication.run(CoreApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
		System.out.println("Application Ready Event generated at " + new Date(applicationReadyEvent.getTimestamp()));
	}

}
