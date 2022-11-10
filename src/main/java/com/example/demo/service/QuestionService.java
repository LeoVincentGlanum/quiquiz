package com.example.demo.service;


import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {


    @Autowired private QuestionRepository questionRepository;

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestion(Integer id){
        return questionRepository.findById(id);
    }

}
