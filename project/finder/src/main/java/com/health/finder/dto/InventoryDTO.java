package com.health.finder.dto;

import lombok.Data;

@Data
public class InventoryDTO {
    private Long id;
    private Long clinicId; // Clinic ID
    private Long medicationId; // Medication ID
    private Integer quantity;
}

