package com.tekton.challenge.porcentajeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekton.challenge.porcentajeapi.model.ApiCallLog;

public interface ApiCallLogRepository extends JpaRepository<ApiCallLog, Long> {
}

