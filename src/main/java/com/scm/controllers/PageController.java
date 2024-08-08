package com.scm.controllers;

import com.scm.entities.User;
import com.scm.form.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class PageController {

    private final UserService userService;


    @GetMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "Substring Technologies");
        return "home";
    }


    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("IsLogin", false);

        System.out.println("About is loading");
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        System.out.println("Service is loading");
        return "services";
    }

    @GetMapping("/contact")
    public String contact(){
        System.out.println("Contact is loading");
        return "contact";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("Login is loading");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        System.out.println("Register is loading");
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult  ,HttpSession session){
//        User user = User.builder()
//                .name(userForm.getName())
//                .email(userForm.getEmail())
//                .password(userForm.getPassword())
//                .about(userForm.getAbout())
//                .phoneNumber(userForm.getPhone())
//                .profilePicture(
//                        "")
//                .build();

        //validate form data

        if(bindingResult.hasErrors()){
            return "register";
        }

        //add save user

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhone());
        user.setProfilePicture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKKOdmJz8Z2pDtYgFgR2u9spABvNNPKYYtGw&s");


        User saveUser = userService.saveUser(user);
        System.out.println(saveUser);

        // meesage = "Registration Successfull"

        // add the message

        Message message = Message.builder().content("Registration Successfull").type(MessageType.green).build();
        session.setAttribute("message",message);


        return "redirect:/register";
    }

//    @PostMapping("/do-authenticate")
//    public String authenticate(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session){
//        if(bindingResult.hasErrors()){
//            return "login";
//        }
//
//        System.out.println("Authenticate is loading");
//        return "redirect:home";
//    }

}
