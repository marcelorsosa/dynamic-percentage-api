package com.tekton.challenge.porcentajeapi.model;

@Entity
public class CallLog {
    @Id @GeneratedValue
    private Long id;
    private LocalDateTime timestamp;
    private String endpoint;
    private String params;
    private String result;
    private boolean error;
}
