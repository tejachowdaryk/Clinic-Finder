package com.health.medfinder.service;

import com.health.medfinder.elastic.Medication;
import com.health.medfinder.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    public Page<Medication> getAllMedications() {
        // Use the medicationRepository to fetch all medications from Elasticsearch.
        return (Page<Medication>) medicationRepository.findAll();
    }
}

