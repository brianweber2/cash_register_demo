package com.cashregister.demo.config;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration  // Tags the class as a source of bean definitions for the application context.
@PropertySource("application.properties")  // For accessing the resource application.properties.
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public Hashids hashids() {
        return new Hashids(env.getProperty("cashregister.hash.salt"), 8);
    }
}
