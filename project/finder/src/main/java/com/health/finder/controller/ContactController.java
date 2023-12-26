package com.health.finder.controller;

import com.health.finder.dto.ContactDTO;
import com.health.finder.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) {
        ContactDTO createdContact = contactService.createContact(contactDTO);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDTO> getContact(@PathVariable Long contactId) {
        ContactDTO contactDTO = contactService.getContact(contactId);
        return ResponseEntity.ok(contactDTO);
    }

    // Update an existing contact
    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDTO> updateContact(
            @PathVariable Long contactId,
            @RequestBody ContactDTO updatedContactDTO) {
        ContactDTO updatedContact = contactService.updateContact(contactId, updatedContactDTO);
        return ResponseEntity.ok(updatedContact);
    }

    // Delete a contact by ID
    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long contactId) {
        contactService.deleteContact(contactId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

