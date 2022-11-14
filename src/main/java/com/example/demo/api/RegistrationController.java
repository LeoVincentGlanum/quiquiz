package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://www.rdlp.xyz:3001/")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {

        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";// template/registration.html
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO userDTO,
            BindingResult result)
    {

        System.out.println("registerUserAccount(): " + userDTO);

        if (result.hasErrors()) {
           return result.getAllErrors().toString();
        }
        else {
            User registered = userService.registerNewUserAccount(userDTO);
            return "registration-success";
        }
    }

    @GetMapping("/account")
    public String showMyInformation(Model model) {

        /* Authentication : Who is doing the request ? */
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Name: " + authentication.getName());
        System.out.println("authentication: " + authentication);
        System.out.println("principal: " + authentication.getPrincipal());

        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("user", userService.getUser(authentication.getName()));

        return "account";
    }
}