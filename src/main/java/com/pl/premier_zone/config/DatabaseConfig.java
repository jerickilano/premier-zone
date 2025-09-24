package com.pl.premier_zone.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class DatabaseConfig {
    
    // This configuration will help with database setup
    // The application will use H2 in-memory database if DATABASE_URL is not set
    // or PostgreSQL if DATABASE_URL is properly configured
}
