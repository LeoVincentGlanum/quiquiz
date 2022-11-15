package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class AuthController {

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
            @ModelAttribute("user") @Valid  UserDTO userDTO,
            BindingResult result)
    {

        System.out.println("registerUserAccount(): " + userDTO);

        if (userService.getUserByEmail(userDTO.getEmail()) == null) {
            if (userService.getUser(userDTO.getUserName()) == null) {
                if (result.hasErrors()) {
                    return "registration";
                } else {
                    User registered = userService.registerNewUserAccount(userDTO);
                    return "registration-success";
                }
            }
            else {
                ObjectError error = new ObjectError("userName","Ce nom d'utilisateur est deja pris .");
                result.rejectValue("userName", "error.user", "Ce nom d'utilisateur est deja pris .");
                result.addError(error);
                return "registration";
            }
        }
        else{
            ObjectError error = new ObjectError("email","An account already exists for this email.");
            result.rejectValue("email", "error.user", "An account already exists for this email.");
            result.addError(error);
            return "registration";
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
        if (authentication.getName() == "anonymousUser"){
            return "redirect:/login";
        }
        return "account";
    }


    @GetMapping("/dashbord")
    public String showDashbord(Model model) {

        /* Authentication : Who is doing the request ? */
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Name: " + authentication.getName());
        System.out.println("authentication: " + authentication);
        System.out.println("principal: " + authentication.getPrincipal());

        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("user", userService.getUser(authentication.getName()));
        if (authentication.getName() == "anonymousUser"){
            return "redirect:/login";
        }
        return "dashbord";
    }
}