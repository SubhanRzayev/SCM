package com.scm.controllers;

import com.scm.form.ContactForm;
import com.scm.services.ContactService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/user/contacts")
public class ContactController {

    private ContactService contactService;

    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String saveContact(@ModelAttribute ContactForm contactForm){

        System.out.println(
                contactService.saveContact(null)
        );
        return "redirect:/user/contacts/add";
    }



}
