package com.health.medfinder.controller;

import com.health.medfinder.elastic.Medication;
import com.health.medfinder.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @GetMapping("/all")
    public Page<Medication> getAllMedications() {
        // Call the service to get all medications and return them.
        return medicationService.getAllMedications();
    }
}

