package com.health.finder.mapper;

import com.health.finder.dto.MedicationDTO;
import com.health.finder.entity.Medication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    MedicationDTO toDTO(Medication entity);
    Medication toEntity(MedicationDTO dto);
    List<MedicationDTO> toDTOList(List<Medication> entities);
}

