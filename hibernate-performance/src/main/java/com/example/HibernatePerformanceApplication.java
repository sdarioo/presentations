package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

@SpringBootApplication
public class HibernatePerformanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatePerformanceApplication.class, args);
	}
	
	
	@Bean
	public InstrumentationLoadTimeWeaver loadTimeWeaver()  throws Throwable {
	    InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
	    return loadTimeWeaver;
	}
}
