package com.health.medfinder.elastic.service;

import com.health.medfinder.elastic.Clinic;
import com.health.medfinder.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public List<Clinic> getClinicsWithinRadius(double latitude, double longitude, int radiusMiles) {
        // Calculate the boundaries for latitude and longitude to form a bounding box
        double lat1 = latitude - (radiusMiles / 69.0); // 1 degree latitude ≈ 69 miles
        double lat2 = latitude + (radiusMiles / 69.0);
        double lon1 = longitude - (radiusMiles / (69.0 * Math.cos(Math.toRadians(latitude))));
        double lon2 = longitude + (radiusMiles / (69.0 * Math.cos(Math.toRadians(latitude))));

        // Fetch clinics within the bounding box
        return clinicRepository.findByLatitudeBetweenAndLongitudeBetween(lat1, lat2, lon1, lon2);
    }
}

