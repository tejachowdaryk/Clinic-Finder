package com.health.finder.controller;

import com.health.finder.dto.MedicationDTO;
import com.health.finder.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public ResponseEntity<List<MedicationDTO>> getAllMedications() {
        List<MedicationDTO> medications = medicationService.getAllMedications();
        return ResponseEntity.ok(medications);
    }

    @GetMapping("/{medicationId}")
    public ResponseEntity<MedicationDTO> getMedicationById(@PathVariable Long medicationId) {
        MedicationDTO medication = medicationService.getMedicationById(medicationId);
        return ResponseEntity.ok(medication);
    }

    @PostMapping
    public ResponseEntity<MedicationDTO> createMedication(@RequestBody MedicationDTO medicationDTO) {
        MedicationDTO createdMedication = medicationService.createMedication(medicationDTO);
        return new ResponseEntity<>(createdMedication, HttpStatus.CREATED);
    }

    @PutMapping("/{medicationId}")
    public ResponseEntity<MedicationDTO> updateMedication(
            @PathVariable Long medicationId,
            @RequestBody MedicationDTO updatedMedicationDTO) {
        MedicationDTO updatedMedication = medicationService.updateMedication(medicationId, updatedMedicationDTO);
        return ResponseEntity.ok(updatedMedication);
    }

    @DeleteMapping("/{medicationId}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long medicationId) {
        medicationService.deleteMedication(medicationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

