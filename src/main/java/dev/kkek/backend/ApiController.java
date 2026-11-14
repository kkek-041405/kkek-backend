package dev.kkek.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "kkek-backend",
            "timestamp", Instant.now().toString(),
            "javaVersion", System.getProperty("java.version"),
            "os", System.getProperty("os.name") + " " + System.getProperty("os.arch")
        ));
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        Runtime runtime = Runtime.getRuntime();
        return ResponseEntity.ok(Map.of(
            "totalMemoryMb", runtime.totalMemory() / (1024 * 1024),
            "freeMemoryMb", runtime.freeMemory() / (1024 * 1024),
            "maxMemoryMb", runtime.maxMemory() / (1024 * 1024),
            "availableProcessors", runtime.availableProcessors()
        ));
    }
}
