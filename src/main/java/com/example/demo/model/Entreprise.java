package com.example.demo.model;

import com.example.demo.model.Personne;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="entreprises")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String adressePostale;
    private String numeroTelephone;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="employeur_id ")
    private List<Personne> employes = new ArrayList<>();

    public Entreprise() {
    }

    public Entreprise(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Personne> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Personne> employes) {
        this.employes = employes;
    }
}
