package com.health.finder.service;

import com.health.finder.dto.ClinicDTO;
import com.health.finder.entity.Clinic;
import com.health.finder.mapper.ClinicMapper;
import com.health.finder.repository.ClinicRepository;
import com.health.finder.repository.ContactRepository;
import com.health.finder.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final ContactRepository contactRepository;
    private final ClinicMapper clinicMapper;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository, ContactRepository contactRepository, ClinicMapper clinicMapper) {
        this.clinicRepository = clinicRepository;
        this.contactRepository = contactRepository;
        this.clinicMapper = clinicMapper;
    }

    public List<ClinicDTO> getClinicsByUserId(Long userId) {

        List<Clinic> clinics = clinicRepository.findByPrimaryContactId(userId);
        return clinicMapper.toClinicDTOs(clinics);
    }

    public ClinicDTO createClinic(ClinicDTO clinicDTO) {
        Clinic clinic = clinicMapper.toClinicEntity(clinicDTO);
        // You can add validation or business logic here if needed
        Clinic createdClinic = clinicRepository.save(clinic);
        return clinicMapper.toClinicDTO(createdClinic);
    }
}

