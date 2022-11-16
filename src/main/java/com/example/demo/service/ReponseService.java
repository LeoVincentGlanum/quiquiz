package com.example.demo.service;

import com.example.demo.model.Reponse;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.model.Question;
import com.example.demo.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReponseService {


    @Autowired
    private ReponseRepository reponseRepository;

    public List<Reponse> getReponses(){
        return reponseRepository.findAll();
    }

    public Optional<Reponse> getReponse(Integer id){
        return reponseRepository.findById(id);
    }

    public List<Reponse> getReponses(Long id){
        return reponseRepository.findByQuestion_Id(id);
    }



}
