package com.health.finder.repository;

import com.health.finder.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<Clinic> findByPrimaryContactId(Long userId);
    // Define custom query methods here if needed
}

