package com.health.finder.controller;

import com.health.finder.dto.ClinicDTO;
import com.health.finder.entity.Clinic;
import com.health.finder.service.ClinicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinics")
@Slf4j
public class ClinicController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClinicDTO>> getClinicsForUser(@PathVariable Long userId) {
        List<ClinicDTO> userClinics = clinicService.getClinicsByUserId(userId);
        return ResponseEntity.ok(userClinics);
    }

    @PostMapping
    public ResponseEntity<ClinicDTO> createClinic(@RequestBody ClinicDTO newClinic) {
        log.info("Create clinic request received: {}", newClinic);
        ClinicDTO createdClinic = clinicService.createClinic(newClinic);
        return new ResponseEntity<>(createdClinic, HttpStatus.CREATED);
    }
}

