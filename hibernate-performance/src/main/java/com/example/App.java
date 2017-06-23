package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        return ProxyDataSourceBuilder
                .create(originalDataSource())
                .logSlowQueryBySlf4j(10, TimeUnit.SECONDS, SLF4JLogLevel.WARN)
                .countQuery()
                .build();
    }

    private DataSource originalDataSource() {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}
