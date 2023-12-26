package com.health.medfinder.controller;

import com.health.medfinder.elastic.Clinic;
import com.health.medfinder.elastic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping("/search")
    public List<Clinic> getClinicsNearby(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {
        return clinicService.getClinicsWithinRadius(latitude, longitude, 50); // 50 miles radius
    }
}

