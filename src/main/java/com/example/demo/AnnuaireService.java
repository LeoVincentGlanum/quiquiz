package com.example.demo;

import com.example.demo.model.Personne;
import com.example.demo.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnuaireService {
 
    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> getPersonnes() {
        return personneRepository.findAll();
    }
    public void addPersonne(Personne newPersonne){

        personneRepository.save(newPersonne);
    }

    public void deletePersonne(int id){
        personneRepository.deleteById(id);
    }

    public Optional<Personne> getPersonne(int id){
        return personneRepository.findById(id);
    }

    public void updatePersonne(int id, Personne personne) throws Exception{
        if(id != personne.getId())
            throw new Exception();

        Optional<Personne> personneExistante = personneRepository.findById(id);
        if(personneExistante.isPresent()) {
            personneRepository.save(personne);
        } else {
            throw new Exception();
        }
    }

    public List<Personne> findByNom(String nom){
        return personneRepository.findAllByNom(nom);
    }
}
