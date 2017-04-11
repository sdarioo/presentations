package com.example;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@SpringBootApplication
public class HibernatePerformanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatePerformanceApplication.class, args);
	}

	@Bean
	@Primary
	@Profile("test")
	public DataSource proxyDataSource(DataSourceProperties props) {
		DataSource actualDataSource = props.initializeDataSourceBuilder().build();
		return ProxyDataSourceBuilder
	        .create(actualDataSource)
//	        .logQueryBySlf4j(SLF4JLogLevel.INFO)
	        .countQuery()
	        .build();
	}
}
