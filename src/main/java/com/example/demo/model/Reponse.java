package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "reponses")
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    private String reponse;

    private Boolean good;


    public Reponse(Question question, String reponse, Boolean good) {
        this.question = question;
        this.reponse = reponse;
        this.good = good;
    }

    public Reponse() {

    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Boolean getGood() {
        return good;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }
}
