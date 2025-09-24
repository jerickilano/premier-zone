package com.pl.premier_zone.config;

import com.pl.premier_zone.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupMessage implements CommandLineRunner {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        long playerCount = playerRepository.count();
        System.out.println("==========================================");
        System.out.println("🚀 Premier-Zone API is ready!");
        System.out.println("📊 Database connected with " + playerCount + " players");
        System.out.println("🌐 API available at: http://localhost:8080");
        System.out.println("📋 Health check: http://localhost:8080/api/v1/health/database");
        System.out.println("⚽ Players API: http://localhost:8080/api/v1/player");
        System.out.println("==========================================");
    }
}
