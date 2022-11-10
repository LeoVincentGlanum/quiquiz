package com.example.demo;

import com.example.demo.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnnuaireMvcController {

    @Autowired
    private AnnuaireService annuaireService;

    @GetMapping("personnes")
    public String getPersonnesView(Model model){

        model.addAttribute("personnes", annuaireService.getPersonnes());
        return "personnes.html";
    }

    @PostMapping("newpersonne")
    public String addPersonne(Personne personne, Model model){

        annuaireService.addPersonne(personne);

        model.addAttribute("personnes", annuaireService.getPersonnes());
        return "personnes.html";
    }


}
