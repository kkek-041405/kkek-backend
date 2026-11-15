package dev.kkek.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ApiController {

    private final Instant startTime = Instant.now();

    @GetMapping({"/", "/api"})
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "kkek-backend");
        response.put("status", "ONLINE");
        response.put("message", "Welcome to the KKEK Backend API");
        response.put("timestamp", Instant.now().toString());
        response.put("uptimeSeconds", ManagementFactory.getRuntimeMXBean().getUptime() / 1000);
        response.put("version", "1.0.0");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("health", "/api/health");
        endpoints.put("info", "/api/info");
        endpoints.put("ping", "/api/ping");
        endpoints.put("actuator", "/actuator/health");
        response.put("endpoints", endpoints);

        return ResponseEntity.ok(response);
    }

    @GetMapping({"/api/health", "/health"})
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("service", "kkek-backend");
        response.put("timestamp", Instant.now().toString());
        response.put("javaVersion", System.getProperty("java.version"));
        response.put("os", System.getProperty("os.name") + " " + System.getProperty("os.arch"));
        response.put("uptimeSeconds", ManagementFactory.getRuntimeMXBean().getUptime() / 1000);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/ping")
    public ResponseEntity<Map<String, String>> ping() {
        return ResponseEntity.ok(Map.of(
            "status", "PONG",
            "timestamp", Instant.now().toString()
        ));
    }

    @GetMapping({"/api/info", "/info"})
    public ResponseEntity<Map<String, Object>> info() {
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "kkek-backend");
        response.put("totalMemoryMb", runtime.totalMemory() / (1024 * 1024));
        response.put("freeMemoryMb", runtime.freeMemory() / (1024 * 1024));
        response.put("maxMemoryMb", runtime.maxMemory() / (1024 * 1024));
        response.put("availableProcessors", runtime.availableProcessors());
        response.put("startedAt", startTime.toString());
        return ResponseEntity.ok(response);
    }
}
