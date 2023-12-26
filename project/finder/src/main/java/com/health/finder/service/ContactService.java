package com.health.finder.service;

import com.health.finder.dto.ContactDTO;
import com.health.finder.entity.Contact;
import com.health.finder.mapper.ContactMapper;
import com.health.finder.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        // Convert ContactDTO to Contact entity
        Contact contact = contactMapper.toEntity(contactDTO);

        // Save the Contact entity to the database
        Contact savedContact = contactRepository.save(contact);

        // Convert the saved Contact entity back to ContactDTO and return it
        return contactMapper.toDTO(savedContact);
    }

    public ContactDTO getContact(Long contactId) {
        // Retrieve the Contact entity from the database
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        // Convert the Contact entity to ContactDTO and return it
        return contactMapper.toDTO(contact);
    }

    @Transactional
    public ContactDTO updateContact(Long contactId, ContactDTO updatedContactDTO) {
        // Check if the contact with the given ID exists
        Contact existingContact = contactRepository.findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        // Update the existing contact with the new data
        existingContact.setEmailId(updatedContactDTO.getEmailId());
        existingContact.setFirstName(updatedContactDTO.getFirstName());
        existingContact.setLastName(updatedContactDTO.getLastName());

        // Save the updated contact
        Contact updatedContact = contactRepository.save(existingContact);

        // Convert the updated contact to DTO and return it
        return contactMapper.toDTO(updatedContact);
    }

    @Transactional
    public void deleteContact(Long contactId) {
        // Check if the contact with the given ID exists
        Contact existingContact = contactRepository.findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        // Delete the contact
        contactRepository.delete(existingContact);
    }
}


