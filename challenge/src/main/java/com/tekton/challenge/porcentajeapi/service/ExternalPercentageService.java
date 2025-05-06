package com.tekton.challenge.porcentajeapi.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import com.tekton.challenge.porcentajeapi.excepcion.PercentageUnavailableException;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ExternalPercentageService {

    private final CacheManager cacheManager;

    @Cacheable("percentage")
    public double getPercentage() {
        // Simular error si es necesario
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Fallo en servicio externo");
        }
        return 10.0; // porcentaje simulado
    }

    public double getPercentageWithFallback() {
        try {
            return getPercentage();
        } catch (Exception e) {
            // Intentar obtener desde la caché manualmente
            Cache cache = cacheManager.getCache("percentage");
            if (cache != null) {
                Double cachedValue = cache.get("percentage", Double.class);
                if (cachedValue != null) {
                    return cachedValue;
                }
            }
            throw new PercentageUnavailableException("No se pudo obtener el porcentaje");
        }
    }

    public Optional<Double> fetchPercentage() {
        // Simula una llamada externa
        return Optional.of(0.10); // 10%
    }
}

