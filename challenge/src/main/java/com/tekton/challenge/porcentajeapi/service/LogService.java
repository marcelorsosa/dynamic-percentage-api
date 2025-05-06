package com.tekton.challenge.porcentajeapi.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tekton.challenge.porcentajeapi.model.ApiCallLog;
import com.tekton.challenge.porcentajeapi.repository.ApiCallLogRepository;

@Service
public class LogService {

    private final ApiCallLogRepository repository;

    public LogService(ApiCallLogRepository repository) {
        this.repository = repository;
    }

    @Async
    public void log(String endpoint, String parameters, String response, String error) {
        ApiCallLog log = new ApiCallLog();
        log.setEndpoint(endpoint);
        log.setParameters(parameters);
        log.setResponse(response);
        log.setError(error);
        log.setTimestamp(LocalDateTime.now());
        repository.save(log);
    }
}
