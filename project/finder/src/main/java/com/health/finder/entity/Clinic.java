package com.health.finder.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "clinics")
public class Clinic {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_contact_id")
    private Contact primaryContact;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inventory> inventory;
}

