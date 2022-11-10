package com.example.demo.model;

import com.example.demo.model.Personne;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metiers")
public class Metier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String categorie;

    public Metier() {
    }
    public Metier(String nom) {
        this.nom = nom;
    }

    @ManyToMany
    @JoinTable(name="personnes_metiers",
            joinColumns = @JoinColumn(name="metier_id"),
            inverseJoinColumns = @JoinColumn(name="personne_id")
    )
    private List<Personne> personnes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }
}
