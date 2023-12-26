package com.health.finder.repository;

import com.health.finder.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    // You can define custom query methods here if needed
}

