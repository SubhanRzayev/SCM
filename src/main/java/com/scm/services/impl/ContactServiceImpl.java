package com.scm.services.impl;

import com.scm.entities.Contact;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactServiceImpl implements ContactService {

    private ContactRepo contactRepo;

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        var contactOld = contactRepo.findById(contact.getContactId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        contactOld.setName(contact.getName());
        contactOld.setEmail(contact.getEmail());
        contactOld.setPhoneNumber(contact.getPhoneNumber());
        contactOld.setAddress(contact.getAddress());
        contactOld.setDescription(contact.getDescription());
        contactOld.setPicture(contact.getPicture());
        contactOld.setFavorite(contact.isFavorite());
        contactOld.setWebsiteLink(contact.getWebsiteLink());
        contactOld.setLinkedInLink(contact.getLinkedInLink());

        return contactRepo.save(contactOld);

    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getContactById(Long contactId) {
        return contactRepo.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id" + contactId));
    }

    @Override
    public void deleteContactById(Long contactId) {
        var contact = contactRepo.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id" + contactId));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return List.of();
    }

    @Override
    public List<Contact> getByUserId(Long userId) {
        return contactRepo.findByUserId(userId);
    }

}
