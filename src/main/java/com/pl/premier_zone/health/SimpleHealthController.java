package com.pl.premier_zone.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
public class SimpleHealthController {

    @GetMapping("/simple")
    public Map<String, Object> simpleHealth() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "UP");
            response.put("message", "Application is running");
            response.put("timestamp", System.currentTimeMillis());
            response.put("uptime", System.currentTimeMillis() - getStartTime());
            return response;
        } catch (Exception e) {
            response.put("status", "DOWN");
            response.put("message", "Application error: " + e.getMessage());
            response.put("timestamp", System.currentTimeMillis());
            return response;
        }
    }
    
    private static final long startTime = System.currentTimeMillis();
    
    private long getStartTime() {
        return startTime;
    }
}



