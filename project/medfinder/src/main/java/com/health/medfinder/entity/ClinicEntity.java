package com.health.medfinder.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "clinics")
public class ClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;
    private String hours;
    private String telephone;
    private String mobile;
    private String webAddress;
    private Boolean hasIcu;
    private Boolean hasAmbulance;
    private Boolean hasWheelchairSupport;

    @Column(unique = true)
    private String governmentHealthCode;

}

