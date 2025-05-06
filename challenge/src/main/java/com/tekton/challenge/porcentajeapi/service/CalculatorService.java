    package com.tekton.challenge.porcentajeapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@Service
public class CalculatorService {

    private final ExternalPercentageService externalService;
    private final CacheManager cacheManager;

    public CalculatorService(ExternalPercentageService externalService, CacheManager cacheManager) {
        this.externalService = externalService;
        this.cacheManager = cacheManager;
    }

    @Cacheable(value = "percentage", unless = "#result == null")
    public Double getPercentage() {
        return externalService.fetchPercentage().orElse(null);
    }

    public double calculateSumWithPercentage(double num1, double num2) {
        Double percentage = getPercentage();
        if (percentage == null) {
            Cache cache = cacheManager.getCache("percentage");
            if (cache != null) {
                Double cached = cache.get("percentage", Double.class);
                if (cached != null) {
                    percentage = cached;
                } else {
                    throw new RuntimeException("No se pudo obtener el porcentaje y no hay valor en caché");
                }
            }
        }

        double sum = num1 + num2;
        return sum + (sum * (percentage != null ? percentage : 0.0));
    }
}

    