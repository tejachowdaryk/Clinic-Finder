package com.health.medfinder.writer;

import com.health.medfinder.elastic.Medication;
import com.health.medfinder.elastic.service.MedicationIndexingService;
import com.health.medfinder.repository.MedicationRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicationItemWriter implements ItemWriter<Medication> {
    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationIndexingService medicationIndexingService;

    @Override
    public void write(List<? extends Medication> medications) throws Exception {
        // Call the indexing service to handle upsert logic and document existence checks.
        for (Medication medication : medications) {
            medicationIndexingService.indexMedication(medication);
        }

        // Save the medications using the repository.
        medicationRepository.saveAll(medications);
    }
}

