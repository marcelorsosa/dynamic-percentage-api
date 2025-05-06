package com.tekton.challenge;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tekton.challenge.porcentajeapi.service.CalculatorService;
import com.tekton.challenge.porcentajeapi.service.PercentageProviderService;


@SpringBootTest
class CalculationServiceTest {

    @MockBean
    private PercentageProviderService percentageService;

    @Autowired
    private CalculatorService calculationService;

    @Test
    void testCalculateWithPercentage() {
        // given
        double num1 = 100;
        double num2 = 50;
        double expectedPercentage = 0.10;

        Mockito.when(percentageService.getPercentage())
               .thenReturn(expectedPercentage);

        // when
        double result = calculationService.calculateSumWithPercentage(num1, num2);

        // then
        double expected = (num1 + num2) * (1 + expectedPercentage);
        Assertions.assertEquals(expected, result);
    }
}
