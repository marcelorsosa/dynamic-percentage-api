package com.tekton.challenge.porcentajeapi.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekton.challenge.porcentajeapi.model.ApiCallLog;
import com.tekton.challenge.porcentajeapi.repository.ApiCallLogRepository;
import com.tekton.challenge.porcentajeapi.service.CalculatorService;
import com.tekton.challenge.porcentajeapi.service.LogService;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final LogService logService;
    private final ApiCallLogRepository apiCallLogRepository;

    public CalculatorController(CalculatorService calculatorService, LogService logService, ApiCallLogRepository apiCallLogRepository) {
        this.calculatorService = calculatorService;
        this.logService = logService;
        this.apiCallLogRepository = apiCallLogRepository;
    }
    

    @GetMapping("/logs")
    public Page<ApiCallLog> getLogs(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        return apiCallLogRepository.findAll(PageRequest.of(page, size, Sort.by("timestamp").descending()));
    }

    /**
     * Endpoint to calculate the sum of two numbers and apply a percentage.
     * @param num1 First number
     * @param num2 Second number
     * @return The result of the calculation
     */
    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestParam double num1, @RequestParam double num2) {
        String params = "num1=" + num1 + ", num2=" + num2;
        try {
            double result = calculatorService.calculateSumWithPercentage(num1, num2);
            logService.log("/api/calculate", params, String.valueOf(result), null);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logService.log("/api/calculate", params, null, e.getMessage());
            throw e;
        }
    }
    
}
