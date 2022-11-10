package com.example.demo;

import com.example.demo.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class AnnuaireApiController {

    @Autowired
    AnnuaireService annuaireService;


    @GetMapping("hello")
    public String sayHello(){

        return "<h1>Hello</h1>";
    }

    @GetMapping("personne")
    public Personne getPersonne(){

         Personne p = new Personne("Alain", "Delon", 55);

        return p;
    }

    @GetMapping("personnes")
    public List<Personne> getPersonnes(){

        return annuaireService.getPersonnes();
    }

    @PostMapping("personnes")
    public Personne addPersonne(@RequestBody Personne newPersonne){
        annuaireService.addPersonne(newPersonne);
        return newPersonne;
    }

    @DeleteMapping("personnes/{id}")
    public void deletePersonne(@PathVariable("id") int id){
        System.out.println("delete: "+id);
        annuaireService.deletePersonne(id);
    }

    @PutMapping("personnes/{id}")
    public ResponseEntity updatePersonne(@PathVariable("id") int id,
                                         @RequestBody Personne personne){
        try {
            annuaireService.updatePersonne(id, personne);
            return ResponseEntity.ok().body(personne);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("personnes/{id}")
    public ResponseEntity getPersonne(@PathVariable("id") int id){
        Optional<Personne> op = annuaireService.getPersonne(id);
        if(op.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(op.get());
        }
    }

    @GetMapping("findbynom")
    public List<Personne> findByNom(@RequestParam(value = "nom", required = false) String nom){
            return annuaireService.findByNom(nom);
    }
}
