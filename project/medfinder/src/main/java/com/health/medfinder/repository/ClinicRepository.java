package com.health.medfinder.repository;

import com.health.medfinder.elastic.Clinic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ClinicRepository extends ElasticsearchRepository<Clinic, String> {
    // You can define custom query methods here if needed.

    List<Clinic> findByLatitudeBetweenAndLongitudeBetween(
            double minLatitude, double maxLatitude, double minLongitude, double maxLongitude);
}

