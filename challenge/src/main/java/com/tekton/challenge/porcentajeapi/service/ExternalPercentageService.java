package com.tekton.challenge.porcentajeapi.service;

import java.math.BigDecimal;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalPercentageService {

    private final CacheManager cacheManager;

    public BigDecimal getPercentage() {
        Cache cache = cacheManager.getCache("percentage");
        BigDecimal cached = cache != null ? cache.get("value", BigDecimal.class) : null;

        try {
            // Simulamos el servicio externo
            BigDecimal fetched = fetchFromExternal();
            if (cache != null) cache.put("value", fetched);
            return fetched;
        } catch (Exception e) {
            if (cached != null) return cached;
            throw new RuntimeException("No percentage available");
        }
    }

    private BigDecimal fetchFromExternal() {
        return new BigDecimal("20.0"); // Simulado
    }
}
