package com.example.demo.service;


import com.example.demo.model.Question;
import com.example.demo.model.Reponse;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {


    @Autowired private QuestionRepository questionRepository;
    @Autowired private ReponseRepository reponseRepository;

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestion(Integer id){
        return questionRepository.findById(id);
    }

    public Question getRandomQuestion(){

        List<Question> list =  questionRepository.findAll();
        Random rand = new Random();
        Question randomQuestion = list.get(rand.nextInt(list.size()));
        return randomQuestion;

    }

}
