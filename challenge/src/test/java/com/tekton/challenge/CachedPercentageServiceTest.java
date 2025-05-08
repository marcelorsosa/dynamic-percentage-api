package com.tekton.challenge;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tekton.challenge.porcentajeapi.service.CalculatorService;
import com.tekton.challenge.porcentajeapi.service.ExternalPercentageService;

@SpringBootTest
class CachedPercentageServiceTest {

    @MockBean
    private ExternalPercentageService externalPercentageService;

    @Autowired
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService.clearAllCache("percentage"); // método para limpiar el caché si lo implementaste
    }

    @Test
    void shouldFallbackToCachedPercentageWhenExternalFails() {
        // primer valor exitoso para guardar en caché
        Mockito.when(externalPercentageService.fetchPercentage()).thenReturn( Optional.of( 0.1));
        externalPercentageService.getPercentageWithFallback(); // primera llamada guarda en caché

        // ahora simulamos fallo
        Mockito.when(externalPercentageService.fetchPercentage()).thenThrow(new RuntimeException("API down"));

        Optional<Double> fallbackPercentage = Optional.of( externalPercentageService.getPercentageWithFallback());

        Assertions.assertTrue(fallbackPercentage.isPresent());
        Assertions.assertEquals(0.1, fallbackPercentage.get());
    }

    @Test
    void shouldReturnEmptyIfNoCacheAndExternalFails() {
        // nunca se guardó nada en caché
        Mockito.when(externalPercentageService.fetchPercentage()).thenThrow(new RuntimeException("API down"));

        Optional<Double> fallbackPercentage = Optional.of(externalPercentageService.getPercentageWithFallback());

        Assertions.assertTrue(fallbackPercentage.isEmpty());
    }
}