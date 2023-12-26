package com.health.finder.mapper;

import com.health.finder.dto.ContactDTO;
import com.health.finder.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDTO toDTO(Contact entity);

    Contact toEntity(ContactDTO dto);
}

