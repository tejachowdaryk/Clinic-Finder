package com.health.medfinder.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "medications")
@Data
public class MedicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicationName;
    private String medicationCode;
    private Integer age;

    // Constructors, getters, and setters
}

