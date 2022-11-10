package com.example.demo.api;


import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @GetMapping("questions")
    public List<Question> getQuestions(){
        return questionService.getQuestions();
    }


    @GetMapping("question/{id}")
    public Optional<Question> getQuestion(@PathVariable("id") int id){
        return questionService.getQuestion(id);
    }

}
