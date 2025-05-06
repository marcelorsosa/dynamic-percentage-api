package com.tekton.challenge;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tekton.challenge.porcentajeapi.service.ExternalPercentageService;

@SpringBootTest
class CachedPercentageServiceTest {

    @MockBean
    private ExternalPercentageService externalPercentageService;

    @Autowired
    private CachedPercentageService cachedPercentageService;

    @BeforeEach
    void setUp() {
        cachedPercentageService.clear(); // método para limpiar el caché si lo implementaste
    }

    @Test
    void shouldFallbackToCachedPercentageWhenExternalFails() {
        // primer valor exitoso para guardar en caché
        Mockito.when(externalPercentageService.fetchPercentage()).thenReturn(0.1);
        cachedPercentageService.getCurrentPercentage(); // primera llamada guarda en caché

        // ahora simulamos fallo
        Mockito.when(externalPercentageService.fetchPercentage()).thenThrow(new RuntimeException("API down"));

        Optional<Double> fallbackPercentage = cachedPercentageService.getCurrentPercentage();

        Assertions.assertTrue(fallbackPercentage.isPresent());
        Assertions.assertEquals(0.1, fallbackPercentage.get());
    }

    @Test
    void shouldReturnEmptyIfNoCacheAndExternalFails() {
        // nunca se guardó nada en caché
        Mockito.when(externalPercentageService.fetchPercentage()).thenThrow(new RuntimeException("API down"));

        Optional<Double> fallbackPercentage = cachedPercentageService.getCurrentPercentage();

        Assertions.assertTrue(fallbackPercentage.isEmpty());
    }
}

