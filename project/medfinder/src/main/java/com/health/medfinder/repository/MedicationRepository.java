package com.health.medfinder.repository;

import com.health.medfinder.elastic.Medication;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MedicationRepository extends ElasticsearchRepository<Medication, String> {
}

