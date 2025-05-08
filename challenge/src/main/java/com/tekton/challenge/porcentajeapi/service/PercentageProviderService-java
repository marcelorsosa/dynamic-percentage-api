package com.tekton.challenge.porcentajeapi.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Service
public class PercentageProviderService {

    private final Cache<String, Double> percentageCache = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofMinutes(30))
        .build();

    public double getPercentage() {
        Double cached = percentageCache.getIfPresent("percentage");
        try {
            double value = callExternalService();
            percentageCache.put("percentage", value);
            return value;
        } catch (Exception e) {
            if (cached != null) return cached;
            throw new IllegalStateException("No percentage available", e);
        }
    }

    private double callExternalService() {
        // Aquí simularías una API externa. Puedes luego reemplazarlo con RestTemplate/WebClient.
        return 10.0; // Mock fijo por ahora
    }
}
