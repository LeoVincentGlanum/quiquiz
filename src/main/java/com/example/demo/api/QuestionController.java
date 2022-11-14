package com.example.demo.api;


import com.example.demo.model.Question;
import com.example.demo.model.Reponse;
import com.example.demo.service.QuestionService;
import com.example.demo.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://www.rdlp.xyz:3001/")
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @Autowired
    ReponseService reponseService;

    @GetMapping("questions")
    public List<Question> getQuestions(){
        return questionService.getQuestions();
    }


    @GetMapping("question/{id}")
    public Optional<Question> getQuestion(@PathVariable("id") int id){
        return questionService.getQuestion(id);
    }


    @GetMapping("reponses/{id}")
    public List<Reponse> getReponses(@PathVariable("id") Long id){
        return reponseService.getReponses(id);
    }


}
