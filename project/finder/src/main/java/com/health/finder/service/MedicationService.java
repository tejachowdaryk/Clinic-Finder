package com.health.finder.service;

import com.health.finder.dto.MedicationDTO;
import com.health.finder.entity.Medication;
import com.health.finder.mapper.MedicationMapper;
import com.health.finder.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository, MedicationMapper medicationMapper) {
        this.medicationRepository = medicationRepository;
        this.medicationMapper = medicationMapper;
    }

    public List<MedicationDTO> getAllMedications() {
        List<Medication> medications = medicationRepository.findAll();
        return medicationMapper.toDTOList(medications);
    }

    public MedicationDTO getMedicationById(Long medicationId) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found"));
        return medicationMapper.toDTO(medication);
    }

    @Transactional
    public MedicationDTO createMedication(MedicationDTO medicationDTO) {
        Medication medication = medicationMapper.toEntity(medicationDTO);
        Medication savedMedication = medicationRepository.save(medication);
        return medicationMapper.toDTO(savedMedication);
    }

    @Transactional
    public MedicationDTO updateMedication(Long medicationId, MedicationDTO updatedMedicationDTO) {
        Medication existingMedication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found"));

        // Update existing medication with new data
        existingMedication.setMedicationName(updatedMedicationDTO.getMedicationName());
        existingMedication.setMedicationCode(updatedMedicationDTO.getMedicationCode());
        existingMedication.setAge(updatedMedicationDTO.getAge());

        Medication updatedMedication = medicationRepository.save(existingMedication);
        return medicationMapper.toDTO(updatedMedication);
    }

    @Transactional
    public void deleteMedication(Long medicationId) {
        Medication existingMedication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found"));
        medicationRepository.delete(existingMedication);
    }
}

