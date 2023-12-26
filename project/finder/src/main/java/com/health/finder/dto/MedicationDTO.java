package com.health.finder.dto;

import lombok.Data;

@Data
public class MedicationDTO {

    private Long id;
    private String medicationName;
    private String medicationCode;
    private Integer age;

    // Getters and setters for the fields
}

