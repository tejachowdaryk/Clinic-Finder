package com.health.finder.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClinicDTO {
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
    private String governmentHealthCode;
    private Long primaryContactId; // This is the ID of the primary contact
//    private List<InventoryDTO> inventory;
}
