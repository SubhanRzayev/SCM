package com.scm.controllers;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@AllArgsConstructor
@ControllerAdvice
public class RootController {

    private final UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Authentication authentication, Model model) {
        if (authentication == null) {
            return;
        }
        System.out.println("Adding logged in user information to the model");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);

    }
}
