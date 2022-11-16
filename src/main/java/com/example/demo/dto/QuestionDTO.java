package com.example.demo.dto;

import com.example.demo.model.Reponse;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {

    private Long id;

    private List<Reponse> reponses = new ArrayList<>();

    private String question;

    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
