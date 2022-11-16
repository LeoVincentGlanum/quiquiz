package com.example.demo.controller;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Question;
import com.example.demo.model.Reponse;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.ReponseRepository;
import com.example.demo.service.QuestionService;
import com.example.demo.service.ReponseService;
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

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ReponseRepository reponseRepository;

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {

        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";// template/registration.html
    }

    @GetMapping("/connexion")
    public String showConnexion(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "connexion";
    };

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


    @GetMapping("/index")
    public String redirectIndex(Model model){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName() == "anonymousUser"){
            return "index_without_auth";
        }
        return "redirect:/dashbord";

    }

    @GetMapping("/")
    public String redirectLogin(Model model){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName() == "anonymousUser"){
            return "redirect:/index";
        }
        return "redirect:/dashbord";
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
            return "redirect:/connexion";
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
            return "redirect:/connexion";
        }
        return "dashbord";
    }


    @GetMapping("/admin")
    public String showAdmin(Model model) {

        /* Authentication : Who is doing the request ? */
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Name: " + authentication.getName());
        System.out.println("authentication: " + authentication);
        System.out.println("principal: " + authentication.getPrincipal());
        User user = userService.getUser(authentication.getName());
        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("user", userService.getUser(authentication.getName()));

        model.addAttribute("question", new QuestionDTO());
        if (authentication.getName() == "anonymousUser"){
            return "redirect:/connexion";
        }
        if (user.getRole().equals("ROLE_ADMIN")){



            return "admin";
        } else {
            System.out.println(user.getRole());
            return "redirect:/dashbord";
        }

    }

    @PostMapping("/admin/createQuestion")
    public String createQuestion(String question,String reponse1,String reponse2,String reponse3, String reponse4,String flexRadioDefault){
        System.out.println("❤❤❤");
        System.out.println(question);

        System.out.println(flexRadioDefault);

        Boolean good1 = false;
        Boolean good2 = false;
        Boolean good3 = false;
        Boolean good4 = false;

        if (flexRadioDefault.equals("reponse1")){
            good1 = true;
        }
        if (flexRadioDefault.equals("reponse2")){
            good2 = true;
        }
        if (flexRadioDefault.equals("reponse3")){
            good3 = true;
        }
        if (flexRadioDefault.equals("reponse4")){
            good4 = true;
        }

        Question q1 = new Question(question, 1);

        questionRepository.save(q1);

        Reponse r1 = new Reponse(q1,reponse1,good1);
        Reponse r2 = new Reponse(q1,reponse2,good2);
        Reponse r3 = new Reponse(q1,reponse3,good3);
        Reponse r4 = new Reponse(q1,reponse4,good4);

        reponseRepository.save(r1);
        reponseRepository.save(r2);
        reponseRepository.save(r3);
        reponseRepository.save(r4);


        return "admin_success";
    }

}