    package com.tekton.challenge.porcentajeapi.service;

import org.springframework.stereotype.Service;

import com.tekton.challenge.porcentajeapi.dto.CalculationRequest;

@Service
    @RequiredArgsConstructor
    public class CalculationService {
    
        private final PercentageProviderService percentageProviderService;
    
        public CalculationResult calculateWithPercentage(CalculationRequest req) {
            double percentage = percentageProviderService.getPercentage(); // p.ej. 10% = 10.0
            double sum = req.num1() + req.num2();
            double result = sum + (sum * (percentage / 100));
            return new CalculationResult(result, percentage);
        }
    }
    