package com.scm.services;

import com.scm.entities.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {
    //save contacts
    Contact saveContact(Contact contact);

    //update contact
    Contact updateContact(Contact contact);

    //get contacts
    List<Contact> getAllContacts();

    //get contact by id
    Contact getContactById(Long contactId);

    //delete contact
    void deleteContactById(Long contactId);

    //search contact
    List<Contact> search(String name,String email,String phoneNumber);

    //get contacts by userId
    List<Contact> getByUserId(Long userId);

}
