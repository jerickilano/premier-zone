package com.pl.premier_zone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class DatabaseConfig {
    
    // Railway automatically provides DATABASE_URL with proper format
    // Spring Boot will handle the connection automatically
}
