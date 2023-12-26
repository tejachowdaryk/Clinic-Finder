package com.health.finder.mapper;

import com.health.finder.dto.ClinicDTO;
import com.health.finder.entity.Clinic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClinicMapper {

    @Mapping(target = "primaryContactId", source = "primaryContact.id")
    ClinicDTO toClinicDTO(Clinic clinic);

    List<ClinicDTO> toClinicDTOs(List<Clinic> clinics);

    Clinic toClinicEntity(ClinicDTO clinicDTO);
}

